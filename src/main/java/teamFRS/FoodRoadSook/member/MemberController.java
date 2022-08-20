package teamFRS.FoodRoadSook.member;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
//import teamFRS.FoodRoadSook.EmailAuth.MailSendService;

@Controller
@RestController
@RequestMapping(value = "api/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    /**
     * 회원정보 가져오기 #DB 체크 용 메서드
     * @return MemberDTO
     */
    //http://localhost:8080/api/member/UserProfile/{실제 유저아이디}
    @GetMapping("/UserProfile/{user_id}")
    public MemberDTO UserProfile(@PathVariable("user_id") String user_id) {
        return memberService.member_select(user_id);
    }

    /**
     * 회원가입
     * @return String (회원가입 성공여부)
     */
    //http://localhost:8080/api/member/register
    //@RequestBody 어노테이션을 적어줘야 MemberDTO객체를 정상적으로 클라이언트에서 가져옴. @RequestBody 누락시 모든 값이 null이 들어가는 에러 발생.
    @PostMapping("/register")
    public String register(@RequestBody MemberDTO memberDTO) {

        return memberService.member_insert(memberDTO);


    }



    /**
     * 로그인
     * @return
     */
//    @ResponseBody @RequestMapping("/login")
//    public String login(String user_id, String user_pw, HttpSession session) {
//        //화면에서 입력한 아이디와 비밀번호가 일치하는 회원 정보가 DB에 있는지 확인하여
//
//
//        //일치하는 회원 정보가 있다면 회원 정보를 세션에 담는다
//
//    }

    /**
     * 로그아웃
     * @return
     */
//    @ResponseBody @RequestMapping("/logout")
//    public void logout() {
//
//    }


}