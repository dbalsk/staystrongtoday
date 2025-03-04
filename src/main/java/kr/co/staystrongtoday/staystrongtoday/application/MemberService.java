package kr.co.staystrongtoday.staystrongtoday.application;

import jakarta.validation.Valid;
import kr.co.staystrongtoday.staystrongtoday.domain.MemberEntity;
import kr.co.staystrongtoday.staystrongtoday.infrastructure.MemberRepository;
import kr.co.staystrongtoday.staystrongtoday.presentation.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public MemberDTO registerMember(MemberDTO memberDTO) {
        //1. 도메인 객체에 대한 유효성 검증
        validationService.checkValid(MemberDTO.toEntity(memberDTO));

        //2.이메일 중복 체크 (해당 이메일로 조회하여 존재하는지 체크)
        if (memberRepository.findByMemberEmail(memberDTO.getMemberEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다");
        }

        //3. 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(memberDTO.getMemberPassword());

        //4. 엔티티 객체 생성
        MemberEntity memberEntity = new MemberEntity(memberDTO.getMemberName(), memberDTO.getMemberEmail(), encryptedPassword);

        //5. DB에 저장
        MemberEntity saveEntity = memberRepository.save(memberEntity);
        return MemberDTO.toDTO(saveEntity);
    }
}
