package kr.co.staystrongtoday.staystrongtoday.infrastructure;


import kr.co.staystrongtoday.staystrongtoday.domain.EncouragementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EncouragementRepository extends JpaRepository<EncouragementEntity, Long>{
    //@Transactional
    @Modifying
    @Query("UPDATE EncouragementEntity e SET e.likeCount = e.likeCount + 1 WHERE e.id = :id")
    void increaseLikeCount(@Param("id") Long id);
}
