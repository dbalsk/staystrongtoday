package kr.co.staystrongtoday.staystrongtoday.application;

import kr.co.staystrongtoday.staystrongtoday.domain.MemberEntity;
import kr.co.staystrongtoday.staystrongtoday.infrastructure.MemberRepository;
import kr.co.staystrongtoday.staystrongtoday.presentation.RegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationService validationService;

    @Transactional
    public RegisterDTO registerMember(RegisterDTO registerDTO) {
        //1. 아이디 중복체크 (해당 아이디로 조회하여 존재하는지 체크)
        if (memberRepository.findByMemberName(registerDTO.getMemberName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        //2. 이메일 중복 체크 (해당 이메일로 조회하여 존재하는지 체크)
        if (memberRepository.findByMemberEmail(registerDTO.getMemberEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        //3. 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(registerDTO.getMemberPassword());

        //4. 엔티티 객체 생성
        MemberEntity memberEntity = new MemberEntity(registerDTO.getMemberName(), registerDTO.getMemberEmail(), encryptedPassword);

        //5. 도메인 객체에 대한 유효성 검증
        validationService.checkValid(memberEntity);

        //6. DB에 저장
        MemberEntity saveEntity = memberRepository.save(memberEntity);
        return RegisterDTO.toDTO(saveEntity);
    }

}
