package teamFRS.FoodRoadSook.member;


import java.util.Optional;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamFRS.FoodRoadSook.emailauth.EmailService;
import teamFRS.FoodRoadSook.emailauth.RedisUtil;
import teamFRS.FoodRoadSook.exception.NotFoundException;


@Service
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService {

    private final EmailService emailService;
    private final RedisUtil redisUtil;
    private final MemberRepository memberRepository;


    @Autowired //이걸통해 의존관계주입을 component scan시 자동으로 해준다
    public MemberServiceImpl(EmailService emailService, RedisUtil redisUtil, MemberRepository memberRepository) {
        this.emailService = emailService;
        this.redisUtil = redisUtil;
        this.memberRepository = memberRepository;

    }

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
            Boolean emailauth = member.get().toDTO().getEmailAuth();
            if (logindata.getPw().equals(password)) {
                if(emailauth == true)//맞는다면 세션유지
                    return " 인증된 아이디. 로그인에 성공하셨습니다.";
                else
                    return "이메일 인증이 필요합니다.";

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
    public boolean member_update(int id, MemberDTO memberDTO) {
        //member_insert 구현한것 처럼 DTO 를 Entity로 바꾸어 구현.
        return false;
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

