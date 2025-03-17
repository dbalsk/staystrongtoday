package kr.co.staystrongtoday.staystrongtoday.presentation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO { //MemberDTO를 LoginDTO와 RegisterDTO로 분리하여 유지보수에 용이하도록 수정
    private Long id;

    @NotBlank(message = "아이디를 입력하세요.")
    @Size(min = 2, max = 10, message = "아이디는 2~10자리이어야 합니다.")
    @Getter
    private String memberName; // 사용자 이름 (로그인 ID로 사용)

    @NotBlank(message = "비밀번호를 입력하세요.")
    @Size(min = 4, message = "비밀번호는 최소 4자리 이상이어야 합니다.")
    @Getter //getter 금지
    private String memberPassword; // 비밀번호
}
