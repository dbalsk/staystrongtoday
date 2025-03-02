package kr.co.staystrongtoday.staystrongtoday.infrastructure;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import kr.co.staystrongtoday.staystrongtoday.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //단순 조회 메소드이기에 @Query 필요없이도 JPA가 알아서 처리
    Optional<MemberEntity> findByMemberEmail(String memberEmail); //이메일 중복체크
}
