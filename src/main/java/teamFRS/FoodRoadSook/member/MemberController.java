package teamFRS.FoodRoadSook.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import teamFRS.FoodRoadSook.emailauth.KeyVO;
import teamFRS.FoodRoadSook.emailauth.Response;

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
        // DB에 기본정보 insert
        return memberService.member_insert(memberDTO);
    }

    /**
     * 이메일(user_id) 인증
     * @return String 이메일 인증 여부
     */
    //http://localhost:8080/api/member/send-verify/
    @GetMapping("/send-verify/{user_id}")
    public Response sendmail(@PathVariable("user_id") String user_id) {
        Response response;
        try {
            memberService.sendVerificationMail(user_id);
            response = new Response("success", "성공적으로 인증메일을 보냈습니다.", null);
        } catch (Exception exception) {
            response = new Response("error", "인증메일을 보내는데 문제가 발생했습니다.",exception);
        }
        return response;
    }
    //http://localhost:8080/api/member/verify
    @PostMapping("/verify")//프론트에서 Json형태로 받으므로 KeyVO 객체로 맵핑시켜받은 후 필드로 처리
    public Response verify(@RequestBody KeyVO keyVO) {
        Response response;
        try {
            memberService.verifyEmail(keyVO.getKey());
            response = new Response("success", "성공적으로 인증메일을 확인했습니다.", null);

        } catch (Exception e) {
            response = new Response("error", "인증메일을 확인하는데 실패했습니다.", null);
        }
        return response;
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