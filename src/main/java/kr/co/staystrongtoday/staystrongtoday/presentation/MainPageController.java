package kr.co.staystrongtoday.staystrongtoday.presentation;

import kr.co.staystrongtoday.staystrongtoday.application.EncouragementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //뷰 렌더링 용 컨트롤러 (웹페이지 요청)
@RequiredArgsConstructor
public class MainPageController {
    private final EncouragementService encouragementService;

    @GetMapping("/encouragement/save")
    public String saveEncouragementMessage(){
        return "save"; //save.html 파일로
    }

    @GetMapping("/member/register")
    public String registerMember(){
        return "register"; //register.html 파일로
    }

    @GetMapping("/member/login")
    public String loginMember(){
        return "login"; //login.html 파일로
    }
}
