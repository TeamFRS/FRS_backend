package teamFRS.FoodRoadSook.member;


import teamFRS.FoodRoadSook.exception.NotFoundException;


public interface MemberService {

    //회원가입시 회원 정보 저장
    String member_insert(MemberDTO memberDTO);

    //여기서 정보 받아올때 식별자로 쓸 변수를 id(int) 로 할지 user_id(string) 로 할지 고민중.
    //마이페이지에서 회원 정보 확인
    MemberDTO member_select(String user_id);

    //로그인  <<미구현
    //Entity를 넘겨줄까 생각중
    String member_login(Logindata logindata);


    //마이페이지에서 회원 정보 변경 저장
    boolean member_update(int id, MemberDTO memberDTO);

    //회원 정보 탈퇴
    boolean member_delete(String user_id);

    void sendVerificationMail(String user_id) throws NotFoundException;

    void verifyEmail(String key) throws NotFoundException;

}
