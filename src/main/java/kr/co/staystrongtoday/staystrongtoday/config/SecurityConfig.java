package kr.co.staystrongtoday.staystrongtoday.config;

import kr.co.staystrongtoday.staystrongtoday.security.JwtAuthFilter;
import kr.co.staystrongtoday.staystrongtoday.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //스프링이 위 설정을 읽고 관리
@RequiredArgsConstructor
public class SecurityConfig { //스프링 설정 클래스
    private final JwtUtil jwtUtil; //JwtUtil 의존성 주입

    @Bean //PasswordEncoder 빈으로 등록
    public PasswordEncoder passwordEncoder(){ //비밀번호 안전하게 해싱(암호화) 및 비교하는 인터페이스
        return new BCryptPasswordEncoder(); //BCrypt 알고리즘을 사용하는 PasswordEncoder
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(){
        //spring이 JwtAuthFilter을 생성할 때 자동으로 jwtUtil 주입
        return new JwtAuthFilter(jwtUtil);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http //Spring Security 6 이상에서는 람다식으로 사용
            .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (원래 요청 시 CSRF 토큰 필요)
            .authorizeHttpRequests(auth -> auth
                    //.anyRequest().permitAll() // 모든 요청 허용
                    .requestMatchers(HttpMethod.GET, "/encouragement").permitAll()
                    .requestMatchers(HttpMethod.POST, "/encouragement").authenticated() //인증된 사용자만 허용
                    .requestMatchers("/","/member/register", "/member/login", "/encouragement/save", "/member", "/session").permitAll()//로그인 전 특정 api의 요청 허용
                    //.requestMatchers("/admin/**").hasRole("ADMIN") // 관리자 페이지는 관리자만 허용
                    .anyRequest().authenticated() //나머지 요청은 인증 필요
            )
            .formLogin(formLogin -> formLogin.disable()) //기본 로그인 창 비활성화 (추후 jwt 로그인 구현 예정)
            .httpBasic(httpBasic -> httpBasic.disable()) //HTTP Basic 인증 비활성화
            .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);  // JWT 필터 추가
        return http.build();
    }
}
