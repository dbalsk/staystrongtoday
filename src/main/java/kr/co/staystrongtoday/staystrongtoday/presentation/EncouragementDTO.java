package kr.co.staystrongtoday.staystrongtoday.presentation;

import kr.co.staystrongtoday.staystrongtoday.domain.EncouragementEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class EncouragementDTO {
    private Long id;

    @NotNull
    private String encourageMessage;

    @NotNull
    private int likeCount;

    public static EncouragementDTO toDTO(EncouragementEntity encouragementEntity){
        //엔티티 -> dto
        EncouragementDTO encouragementDTO = new EncouragementDTO();
        encouragementDTO.setId(encouragementEntity.getId());
        encouragementDTO.setEncourageMessage(encouragementEntity.getEncourageMessage());
        encouragementDTO.setLikeCount(encouragementEntity.getLikeCount());
        return encouragementDTO;
    }
}
