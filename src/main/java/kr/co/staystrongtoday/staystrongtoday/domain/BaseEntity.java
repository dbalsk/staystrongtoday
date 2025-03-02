package kr.co.staystrongtoday.staystrongtoday.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//시간과 관련된 엔티티를 따로 만들어 상속해줌
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp //생성 시 시간
    @Column(updatable = false) //수정에서는 관여x
    private LocalDateTime createdTime;

    @UpdateTimestamp //수정 시 시간
    @Column(insertable = false) //입력에서는 관여x
    private LocalDateTime updatedTime;
}
