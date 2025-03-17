package kr.co.staystrongtoday.staystrongtoday.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    //Spring Security의 필터 -> http요청에서 JWT 추출 및 검증 + 유효한 경우 인증정보를 SecurityContext에 설정
    //요청이 들어오면 JwtUtil 클래스를 사용하여 JWT 토큰 검증
    private final JwtUtil jwtUtil; //jwtUtil 의존성 주입

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // JwtUtil의 메소드를 호출하여 **요청에서 JWT 토큰 추출**
        String token = jwtUtil.resolveToken(request);

        // 토큰이 존재하고 유효한 경우 인증 처리
        if (token != null && jwtUtil.validateToken(token)) {
            // 인증 정보 생성 (토큰에 포함된 사용자 정보를 바탕으로 객체 생성)
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) jwtUtil.getAuthentication(token);

            // SecurityContext에 인증 정보 저장 (후속 요청에서 인증된 사용자로 간주되도록)
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 필터 체인 다음으로 진행
        filterChain.doFilter(request, response);
    }
}
