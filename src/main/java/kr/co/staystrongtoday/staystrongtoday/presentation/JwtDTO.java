package kr.co.staystrongtoday.staystrongtoday.presentation;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtDTO {
    private String accessToken;
    private String refreshToken;
    //추후 토큰 만료시간, 권한정보 추가 필요

}
