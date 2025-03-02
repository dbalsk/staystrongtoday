package kr.co.staystrongtoday.staystrongtoday.presentation;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.co.staystrongtoday.staystrongtoday.domain.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;

    @NotBlank(message = "이름을 입력하세요")
    @Getter
    private String memberName; // 사용자 이름 (로그인 ID로 사용)

    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "이메일 형식이 올바르지 않습니다")
    @Getter
    private String memberEmail; // 이메일

    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(min = 5, message = "비밀번호는 최소 5자리 이상이어야 합니다")
    @Getter //getter 금지
    private String memberPassword; // 비밀번호

    public MemberDTO(String memberName, String memberEmail) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
    }

    public static MemberDTO toDTO(MemberEntity memberEntity) {
        //엔티티 -> dto
        //비밀번호는 받지않음.
        MemberDTO memberDTO = new MemberDTO(
                memberEntity.getMemberName(),
                memberEntity.getMemberEmail()
        );

        return memberDTO;
    }
}
