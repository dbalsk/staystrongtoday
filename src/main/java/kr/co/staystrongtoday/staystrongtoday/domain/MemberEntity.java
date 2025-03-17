package kr.co.staystrongtoday.staystrongtoday.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Getter
    //@Setter
    private Long id;

    @Column(nullable = false, unique = true) //null불가, 중복방지
    @Getter
    private String memberName; // 사용자 이름 (로그인 ID로 사용)

    @Column(nullable = false, unique = true)
    @Getter
    private String memberEmail; // 이메일

    @Column(nullable = false)
    //getter 금지
    private String memberPassword; // 비밀번호

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public MemberEntity(String memberName, String memberEmail, String memberPassword) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
    }

    public boolean isPasswordMatch(String memberPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(memberPassword, this.memberPassword);
        //비밀번호(도메인지식)의 유출을 막기위해 엔티티 안에서 비교
    }
}
