package kr.co.staystrongtoday.staystrongtoday.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
@Table(name = "Encourage_table")
public class EncouragementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    //@Setter
    private Long id;

    @Getter
    @Setter
    @Column(length = 50, nullable = false) //20자까지, null 불가
    private String encourageMessage;

    @Getter
    @Setter
    @Column
    private int likeCount;

    public EncouragementEntity(String encourageMessage) {
        this.encourageMessage = encourageMessage;
    }

    public static EncouragementEntity getRamdomEncouragement(List<EncouragementEntity> encouragementEntityList) {
        //응원메시지 리스트에서 랜덤으로 하나의 객체를 반환
/*        if (encouragementEntityList.isEmpty()) {
            throw new RuntimeException("응원 메시지가 없습니다.");
        }*/

        //랜덤으로 하나의 객체 반환 (ThreadLocalRandom -> 멀티스레드 환경)
        return encouragementEntityList.get(ThreadLocalRandom.current().nextInt(encouragementEntityList.size()));
    }
}
