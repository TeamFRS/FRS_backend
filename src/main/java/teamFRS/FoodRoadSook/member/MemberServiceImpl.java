package teamFRS.FoodRoadSook.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired //이걸통해 의존관계주입을 component scan시 자동으로 해준다
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 정보 DB에 추가
     * @return
     */
    @Override
    public String member_insert(MemberDTO memberDTO) {
        //1.해당 user_id가 존재하는지 체크
        Optional<MemberEntity> member =memberRepository.findByUserid(memberDTO.getUser_id());
        if(member.isPresent()) {
            return "이미존재하는 회원입니다.";
        }
        else{
            MemberEntity memberEntity = memberDTO.toEntity();
            memberRepository.save(memberEntity);

            return "회원가입 성공";
        }
    }

    @Override
    public MemberDTO member_select(String user_id) {
        //1.해당 user_id가 존재하는지 체크
        Optional<MemberEntity> member =memberRepository.findByUserid(user_id);
        return member.get().toDTO();
    }
    /**
     * 로그인 -미구현
     * @return
     */
//    @Override
//    public MemberDTO member_login() {
//
//    }

    @Override
    public boolean member_id_check(String user_id) {
        if(memberRepository.findByUserid(user_id) != null)//이미 존재하는 아이디일때 (DB에서 동일 아이디를 가져오기에 성공했을때)
            return false;
        else
            return true;
    }

    /**
     * 회원 정보 업데이트 -미구현
     * @return
     */
    @Override
    public boolean member_update(Long id, MemberDTO memberDTO) {
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
        return false;
    }
}