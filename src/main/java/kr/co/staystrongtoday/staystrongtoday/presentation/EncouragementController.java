package kr.co.staystrongtoday.staystrongtoday.presentation;

import kr.co.staystrongtoday.staystrongtoday.application.EncouragementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController //API용 컨트롤러 (json)
@RequiredArgsConstructor //final 필드의 생성자 자동생성 (EncouragementService 의존성 자동 주입됨.)
@RequestMapping("/encouragement")
public class EncouragementController {
    private final EncouragementService encouragementService;

    @GetMapping("/random")
    public EncouragementDTO getEncouragementMessage() {
        //응원 메시지 반환 (모든 응원메시지 중 랜덤으로)
        EncouragementDTO encouragementDTO = encouragementService.getEncouragementMessage();
        System.out.println(encouragementDTO.getEncourageMessage());
        return encouragementDTO;
    }

    @PostMapping("/like/{id}")
    public EncouragementDTO increaseLikeCount(@PathVariable Long id){
        //좋아요 증가
        EncouragementDTO encouragementDTO = encouragementService.increaseLikeCount(id);
        System.out.println(encouragementDTO.getLikeCount());
        return encouragementDTO;
    }

    @PostMapping("/save")
    public EncouragementDTO saveEncouragementMessage(@RequestParam String encourageMessage){
        //추후 작성자(회원정보)도 받아오는게 좋을듯.
        //1. 응원 메시지 추가
        //2. 메인페이지로 이동
        //3. 본인의 응원메세지가 출력되어있도록.
        EncouragementDTO encouragementDTO = new EncouragementDTO(encourageMessage);
        return encouragementService.saveEncouragementMessage(encouragementDTO);
    }
}
