package kr.co.staystrongtoday.staystrongtoday.application;

import kr.co.staystrongtoday.staystrongtoday.domain.EncouragementEntity;
import kr.co.staystrongtoday.staystrongtoday.infrastructure.EncouragementRepository;
import kr.co.staystrongtoday.staystrongtoday.presentation.EncouragementDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EncouragementService {
    private final EncouragementRepository encouragementRepository;


    public EncouragementDTO getEncouragementMessage() {
        //1. 레포지토리에서 모든 응원메시지 받아오기
        //2. 엔티티에서 랜덤으로 하나 반환 (도메인지식 외부로 유출되지않기위해 getter 사용x)
        //3. dto로 변환하여 반환
        List<EncouragementEntity> encouragementEntityList = encouragementRepository.findAll();
        EncouragementEntity encouragement = EncouragementEntity.getRamdomEncouragement(encouragementEntityList);
        return EncouragementDTO.toDTO(encouragement);

/*        Optional<EncouragementEntity> optionalEncouragementEntity = encouragementRepository.findById();
        if(optionalEncouragementEntity.isPresent()) { //객체가 있다면
            EncouragementEntity encouragementEntity = optionalEncouragementEntity.get(); //엔티티로 주고
            EncouragementDTO encouragementDTO = EncouragementDTO.toDTO(encouragementEntity); //dto로 변환
            return encouragementDTO;
        }else{
            return null;
        }*/
    }

    @Transactional
    public EncouragementDTO increaseLikeCount(Long id) {
        encouragementRepository.increaseLikeCount(id);

        Optional<EncouragementEntity> optionalEncouragementEntity = encouragementRepository.findById(id);
        if(optionalEncouragementEntity.isPresent()) {
            EncouragementEntity encouragementEntity = optionalEncouragementEntity.get(); //엔티티로 변환
            return EncouragementDTO.toDTO(encouragementEntity); //dto로 변환
        }else{
            return null;
        }
    }
}
