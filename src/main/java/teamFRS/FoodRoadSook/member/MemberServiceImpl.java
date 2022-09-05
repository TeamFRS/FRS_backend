package teamFRS.FoodRoadSook.member;


import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamFRS.FoodRoadSook.emailauth.EmailService;
import teamFRS.FoodRoadSook.emailauth.RedisUtil;
import teamFRS.FoodRoadSook.exception.NotFoundException;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final EmailService emailService;
    private final RedisUtil redisUtil;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 회원 정보 DB에 추가
     *
     * @return
     */
    @Override
    public String member_insert(MemberDTO memberDTO) {
        //1.해당 user_id가 존재하는지 체크
        Optional<MemberEntity> member = memberRepository.findByUserid(memberDTO.getUser_id());
        if (member.isPresent()) {
            return "이미존재하는 회원입니다.";
        } else {
            MemberEntity memberEntity = memberDTO.toEntity();
            memberEntity.encryptPw(bCryptPasswordEncoder.encode(memberDTO.getUser_pw()));
            memberRepository.save(memberEntity);
            return "회원가입 성공";
        }
    }

    @Override
    public MemberDTO member_select(String user_id) {
        //1.해당 user_id가 존재하는지 체크
        Optional<MemberEntity> member = memberRepository.findByUserid(user_id);
        return member.get().toDTO();

    }


    /**
     * 로그인 -미구현
     * @return
     */
    @Override
    public String  member_login(Logindata logindata) {
        Optional<MemberEntity> member = memberRepository.findByUserid(logindata.getId()); //1.해당 user_id가 존재하는지 체크
        if (member.isEmpty()) {
            return "존재하지 않는 회원입니다";
        }
        else {//비밀번호 일치하는지 확인
            String password = member.get().toDTO().getUser_pw();
            if (logindata.getPw().equals(password)) {
                return " 로그인에 성공하셨습니다.";
                //맞는다면 세션유지
            }
            else{
                return "비밀번호가 틀렸습니다";
            }
        }
    }


//
//
//
//

    /**
     * 회원 정보 업데이트 -미구현
     * @return
     */
    @Override
    public String member_update(MemberDTO memberDTO) {
        //member_insert 구현한것 처럼 DTO 를 Entity로 바꾸어 구현.
        Optional<MemberEntity> member =memberRepository.findByUserid(memberDTO.getUser_id());
        if (member.isPresent()) {
            MemberEntity memberedit = member.get();
            memberedit.changepw(memberDTO.getUser_pw());//비번 변경
            memberedit.changeaddress(memberDTO.getUser_address());//주소 변경
            //중복된 닉네임이 아닐경우만 바꿀 수 있음.
            if (memberRepository.findByUsername(memberDTO.getUser_name()).isEmpty())
                memberedit.changeusername(memberDTO.getUser_name());//닉네임 변경
            else{
                return "중복된 닉네임으로 닉네임을 바꾸지 못했습니다.";

            }
            memberRepository.save(memberedit);
            return " 회원정보를 업데이트 했습니다.";
        }
        return "회원 정보를 불러오는데 실패했습니다.";
    }
    /**
     * 회원 정보 삭제 - 미구현
     * @return
     */
    @Override
    public boolean member_delete(String user_id) {
        // user_id로 조회해서 해당 객체 삭제
        memberRepository.deleteByUserid(user_id);
        return true;
    }

    /**
     * 인증 이메일 보내기
     * @return
     */
    @Override
    public void sendVerificationMail(String user_id) throws NotFoundException {
        String VERIFICATION_LINK = "http://localhost:8080/member/verify/";
        Optional<MemberEntity> member =memberRepository.findByUserid(user_id);
        if (member.isEmpty()) throw new NotFoundException("멤버엔티티가 조회되지 않음");
        UUID uuid = UUID.randomUUID();
        redisUtil.setDataExpire(uuid.toString(), user_id, 60 * 30L);//만료기한 30분
        emailService.send(user_id, VERIFICATION_LINK + uuid.toString());
    }
    /**
     * 유저아이디 인증
     * @return
     */
    @Override
    public void verifyEmail(String key) throws NotFoundException {
        String user_id = redisUtil.getData(key);
        Optional<MemberEntity> member = memberRepository.findByUserid(user_id);
        if (member.isEmpty()) throw new NotFoundException("멤버엔티티가 조회되지않음");
        member.get().emailVerified();
        redisUtil.deleteData(key);
    }

}

