package kr.co.staystrongtoday.staystrongtoday.presentation;

import jakarta.validation.Valid;
import kr.co.staystrongtoday.staystrongtoday.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    //HTTP 응답의 상태코드를 설정하기 위해 ResponseEntity를 타입으로
    public ResponseEntity<String> registerMember(@RequestBody @Valid MemberDTO memberDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            //BindingResult로 유효성 검사에서 발생한 에러를 반환
            return ResponseEntity.badRequest().body("잘못된 입력이 있습니다");
        }

        //유효성 검사 통과 후, 회원가입 처리
        try{
            //회원가입 성공 시
            memberService.registerMember(memberDTO);
            return ResponseEntity.ok("회원가입 성공!");
        }catch (IllegalArgumentException e){
            //회원가입 실패 시
            return ResponseEntity.badRequest().body("회원가입 실패: " + e.getMessage());
        }
    }
}
