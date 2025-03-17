package kr.co.staystrongtoday.staystrongtoday.presentation;

import jakarta.validation.Valid;
import kr.co.staystrongtoday.staystrongtoday.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("") //회원 생성
    //HTTP 응답의 상태코드를 설정하기 위해 ResponseEntity를 타입으로
    public ResponseEntity<String> registerMember(@RequestBody @Valid RegisterDTO registerDTO){
        //유효성 검사 통과 후, 회원가입
        //2025.3.5 - 전역예외핸들러를 만들어 try-catch를 해제하고 간결한 코드로 정리
        memberService.registerMember(registerDTO);
        return ResponseEntity.ok("회원가입 성공!");
    }

}
