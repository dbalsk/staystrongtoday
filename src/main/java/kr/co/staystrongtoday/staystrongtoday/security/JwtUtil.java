package kr.co.staystrongtoday.staystrongtoday.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts; //3.12 추가
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
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

    private final SecretKey secretKey;  // 비밀 키 (하드코딩을 안하고 application.yml에 저장)
    private final long validityInMilliseconds = 3600000;  // 1시간

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        // HMAC-SHA256 알고리즘을 사용하기에 32바이트 이상의 충분한 길이 필요
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    // JWT 토큰 생성
    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username) //토큰에 이름 저장
                .setIssuedAt(new Date()) //토큰 발급 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds)) //토큰 만료 시간 설정
                .signWith(secretKey) //HMAC-SHA256 알고리즘을 사용해서 서명
                .compact();
    }

    // HTTP 요청에서 JWT 추출 (Authorization 헤더에서 Bearer 토큰)
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization"); //헤더에서 토큰 추출
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // 앞의 Bearer prefix를 제거하고 토큰만 추출
        }
        return null;
    }

    // JWT 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token); //토큰이 올바른지 확인
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // JWT 토큰에서 사용자 정보 가져오기
    public Authentication getAuthentication(String token) {
        String username = Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token) //토큰을 파싱하여 Claims(페이로드 정보-사용자이름,만료시간 등)를 가져옴
                .getBody()
                .getSubject(); //이름 추출
        //추후에 username만 반환하지말고 detail로 유저정보를 가져오도록 구현
        return new UsernamePasswordAuthenticationToken(username, "", new ArrayList<>()); //Spring Security에서 사용하는 인증 객체 생성
    }
}
