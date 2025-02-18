package kr.co.staystrongtoday.staystrongtoday.presentation;

import kr.co.staystrongtoday.staystrongtoday.application.EncouragementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor //final 필드의 생성자 자동생성 (EncouragementService 의존성 자동 주입됨.)
@RequestMapping("/encouragement")
public class EncouragementController {
    private final EncouragementService encouragementService;

/*    @PostMapping("/create")
    public EncouragementDTO createEncouragementMessage(String ){
        //응원 메시지 추가
        return encouragementService.createEncouragementMessage();
    }*/

    @GetMapping("/random")
    public EncouragementDTO getEncouragementMessage() {
        //응원 메시지 반환 (모든 응원메시지 중 랜덤으로)
        EncouragementDTO encouragementDTO = encouragementService.getEncouragementMessage();
        System.out.println(encouragementDTO.getEncourageMessage());
        return encouragementDTO;
    }

    @PostMapping("/like/{id}")
    public EncouragementDTO increaseLikeCount(@PathVariable Long id){
        EncouragementDTO encouragementDTO = encouragementService.increaseLikeCount(id);
        System.out.println(encouragementDTO.getLikeCount());
        return encouragementDTO;
    }
}
