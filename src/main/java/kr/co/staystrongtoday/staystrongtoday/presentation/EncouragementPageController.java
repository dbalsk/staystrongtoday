package kr.co.staystrongtoday.staystrongtoday.presentation;

import kr.co.staystrongtoday.staystrongtoday.application.EncouragementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //뷰 렌더링 용 컨트롤러 (웹페이지 요청)
@RequiredArgsConstructor
@RequestMapping("/encouragement")
public class EncouragementPageController {
    private final EncouragementService encouragementService;

    @GetMapping("/save")
    public String saveEncouragementMessage(){
        return "save"; //save.html 파일로
    }

}
