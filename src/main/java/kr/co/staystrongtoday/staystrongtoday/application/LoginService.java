package kr.co.staystrongtoday.staystrongtoday.application;

import kr.co.staystrongtoday.staystrongtoday.domain.MemberEntity;
import kr.co.staystrongtoday.staystrongtoday.infrastructure.MemberRepository;
import kr.co.staystrongtoday.staystrongtoday.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String loginMember(String memberName, String memberPassword) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberName(memberName);

        //아이디 또는 비밀번호 조회의 실패 메시지 (공격자가 아이디를 유추하지 못하도록 아이디와 비밀번호를 통합)
        //비밀번호의 getter 사용을 막기위해 passwordEncoder.matches를 사용하지않고 엔티티 내부에서 비밀번호 비교
        if (optionalMemberEntity.isEmpty() || !optionalMemberEntity.get().isPasswordMatch(memberPassword, passwordEncoder)) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        //추가 필요 -> 로그인 실패 횟수 제한 / 토큰에 최소한의 정보만 포함

        return jwtUtil.createToken(memberName);
    }
}
