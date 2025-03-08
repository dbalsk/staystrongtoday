package kr.co.staystrongtoday.staystrongtoday.presentation;

import jakarta.validation.Valid;
import kr.co.staystrongtoday.staystrongtoday.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    //HTTP 응답의 상태코드를 설정하기 위해 ResponseEntity를 타입으로
    public ResponseEntity<String> registerMember(@RequestBody @Valid MemberDTO memberDTO){
        //유효성 검사 통과 후, 회원가입
        //2025.3.5 - 전역예외핸들러를 만들어 try-catch를 해제하고 간결한 코드로 정리
        memberService.registerMember(memberDTO);
        return ResponseEntity.ok("회원가입 성공!");
    }

/*    @PostMapping("")
    public MemberDTO loginMember(@RequestBody MemberDTO memberDTO){
        //로그인
        MemberDTO loginResult = memberService.loginMember(memberDTO);

        //세션 설정해야될듯.

        return loginResult;
    }*/
}
