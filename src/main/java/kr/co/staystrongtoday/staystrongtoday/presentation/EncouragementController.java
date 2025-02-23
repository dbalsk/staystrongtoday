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

    @GetMapping("") //restful한 api를 위해 url 수정 (동작를 의미하는 단어 제거)
    public EncouragementDTO getEncouragementMessage() {
        //응원 메시지 반환 (모든 응원메시지 중 랜덤으로)
        EncouragementDTO encouragementDTO = encouragementService.getEncouragementMessage();
        System.out.println(encouragementDTO.getEncourageMessage());
        return encouragementDTO;
    }

    //POST에서 PATCH로 수정 (리소스의 일부를 UPDATE하는 개념이기에)
    @PatchMapping("/{id}/like")
    public EncouragementDTO increaseLikeCount(@PathVariable Long id){
        //좋아요 증가
        EncouragementDTO encouragementDTO = encouragementService.increaseLikeCount(id);
        System.out.println(encouragementDTO.getLikeCount());
        return encouragementDTO;
    }

    @PostMapping("/save")
    public EncouragementDTO saveEncouragementMessage(@RequestParam String encourageMessage){
        //응원 메시지 저장
        //추후 작성자(회원정보)도 받아오는게 좋을듯.
        //1. 응원 메시지 추가
        //2. 메인페이지로 이동
        //3. 본인의 응원메세지가 출력되어있도록.
        EncouragementDTO encouragementDTO = new EncouragementDTO(encourageMessage); //책임의 분리를 위해 dto로 객체 생성
        return encouragementService.saveEncouragementMessage(encouragementDTO);

    }
}
