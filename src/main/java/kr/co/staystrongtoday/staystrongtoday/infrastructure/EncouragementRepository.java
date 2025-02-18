package kr.co.staystrongtoday.staystrongtoday.infrastructure;


import kr.co.staystrongtoday.staystrongtoday.domain.EncouragementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncouragementRepository extends JpaRepository<EncouragementEntity, Long>{
}
