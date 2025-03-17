package kr.co.staystrongtoday.staystrongtoday.presentation;

import jakarta.validation.Valid;
import kr.co.staystrongtoday.staystrongtoday.application.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/session") //을 동작으로 볼 수도 있기에 "/"는 restful하지 않을수도? "/session"으로 변경을 고려해보자.->변경완료
public class LoginController {
    //member의 인증을 처리하는 컨트롤러

    private final LoginService loginService;

    @PostMapping("")
    public ResponseEntity<Map<String, String>> loginMember(@RequestBody @Valid LoginDTO loginDTO){
        String token = loginService.loginMember(loginDTO.getMemberName(), loginDTO.getMemberPassword());

        Map<String, String> response = new HashMap<>();
        response.put("token", token); //json으로 파싱

        return ResponseEntity.ok(response);
    }
}
