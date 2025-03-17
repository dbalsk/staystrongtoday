package kr.co.staystrongtoday.staystrongtoday.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts; //3.12 추가
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date; //3.12 추가 (JwtUtil에서는 util 사용 권장)

@Component //SecurityConfig에서만 사용된다면 굳이 안붙여도될듯?
public class JwtUtil {
    //유틸리티클래스 -> 토큰 생성 및 검증 관련된 모든 기능 + 사용자 정보 추출 (즉, 토큰을 실제로 **제공**)

    private final SecretKey secretKey;  // 비밀 키
    private final long validityInMilliseconds = 3600000;  // 1시간

    public JwtUtil() {
        // 32바이트 이상의 충분한 길이의 키를 Base64로 인코딩하여 사용
        String keyString = "your-very-secure-and-long-secret-key-which-is-32bytes";
        String base64Key = Base64.getEncoder().encodeToString(keyString.getBytes());

        // Base64 디코딩 후 SecretKey 생성
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Key));
    }

    // JWT 토큰 생성
    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims parseJwt(String jwt) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwt)  // JWT를 파싱하고 클레임 추출
                    .getBody();  // Claims 객체 반환
        } catch (JwtException | IllegalArgumentException e) {
            return null;  // 예외가 발생하면 null 반환
        }
    }

    // HTTP 요청에서 JWT 추출 (Authorization 헤더에서 Bearer 토큰)
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // Bearer prefix 제거하고 토큰만 추출
        }
        return null;
    }

    // JWT 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // JWT 토큰에서 인증 정보 생성
    public Authentication getAuthentication(String token) {
        String username = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return new UsernamePasswordAuthenticationToken(username, "", new ArrayList<>());
    }
}
