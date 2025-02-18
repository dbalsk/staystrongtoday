package kr.co.staystrongtoday.staystrongtoday.init;

import kr.co.staystrongtoday.staystrongtoday.domain.EncouragementEntity;
import kr.co.staystrongtoday.staystrongtoday.infrastructure.EncouragementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EncouragementInitData implements CommandLineRunner{ //애플리케이션 부트스트랩(초기화) 코드
    private final EncouragementRepository encouragementRepository;
    //초기화 로직에서 레포지토리를 의존하는 것은 괜찮음. (도메인주도설계 원칙에 벗어나지않음)

    @Override
    public void run(String... args) {
        if (encouragementRepository.count() == 0) { // 기존 데이터가 없을 경우만 추가
            List<EncouragementEntity> defaultMessages = List.of(
                    new EncouragementEntity("넌 할 수 있어!"),
                    new EncouragementEntity("오늘도 최고야!"),
                    new EncouragementEntity("네 노력은 배신하지 않아!"),
                    new EncouragementEntity("포기하지 마!"),
                    new EncouragementEntity("너라면 해낼 수 있어!"),
                    new EncouragementEntity("어제보다 더 나아질 거야!"),
                    new EncouragementEntity("작은 노력들이 모여 큰 변화를 만든다!"),
                    new EncouragementEntity("너 자신을 믿어!"),
                    new EncouragementEntity("힘들어도 한 걸음씩 나아가자!"),
                    new EncouragementEntity("언제나 응원하고 있어!")
            );
            encouragementRepository.saveAll(defaultMessages);
        }
    }
}

