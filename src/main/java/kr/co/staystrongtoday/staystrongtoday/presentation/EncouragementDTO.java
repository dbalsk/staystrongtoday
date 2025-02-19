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

    public EncouragementDTO(String encourageMessage) {
        this.encourageMessage = encourageMessage;
    }

    //엔티티(도메인계층)가 dto를 의존하면 안되기에 두 변환 작업 모두 dto 안에서 수행.
    public static EncouragementDTO toDTO(EncouragementEntity encouragementEntity){
        //엔티티 -> dto
        //생성자로 변환 작업 수행. (setter 사용 제거)
        EncouragementDTO encouragementDTO = new EncouragementDTO(
                encouragementEntity.getId(),
                encouragementEntity.getEncourageMessage(),
                encouragementEntity.getLikeCount()
        );
        return encouragementDTO;
    }

    public static EncouragementEntity toEntity(EncouragementDTO encouragementDTO){
        //dto -> 엔티티
        //생성자로 변환 작업 수행. (setter 사용 제거)
        EncouragementEntity encouragementEntity = new EncouragementEntity(
                encouragementDTO.getId(),
                encouragementDTO.getEncourageMessage(),
                encouragementDTO.getLikeCount()
        );
        return encouragementEntity;
    }
}
