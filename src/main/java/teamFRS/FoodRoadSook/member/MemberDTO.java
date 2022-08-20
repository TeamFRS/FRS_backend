package teamFRS.FoodRoadSook.member;

import lombok.*;

import java.awt.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
/* 회원 서비스 요청 RequestDTO 클래스 */
public class MemberDTO {
    //아이디(이메일), 비밀번호, 주소, 닉네임, 찜한 가게, 학생증, 신뢰도, 숙명여대/주민[0,1]
    @NonNull
    private String user_id;
    @NonNull
    private String user_pw;
    @NonNull
    private String user_address;
    @NonNull
    private String user_name;
    private String user_store;
    private String user_image;//원래는 Image 타입인데 사진은 경로저장(https://sql-factory.tistory.com/622)을 주로 한다고 하여 String 으로 해둠.
    @NonNull
    private int user_grade;
    @NonNull
    private Boolean user_sm;//무조건 숙대생으로 하기로 했으므로 필요없는 필드일 수 있음.
    private Timestamp regdate;
    private Timestamp updatedate;
    //이메일 인증 여부를 위한 토큰으로 아직 사용 X
    boolean emailToken_authentication;

    //생성자 2개 : 기본생성자, 멤버변수를 인자로 전부를 가진 생성자
//
//    public MemberDTO(){}
//    public MemberDTO(String user_id, String user_pw, String user_address, String user_name, String user_store, String user_image, int user_grade, Boolean user_sm, Timestamp regdate, Timestamp updatedate) {
//        super();
//        this.user_id = user_id;
//        this.user_pw = user_pw;
//        this.user_address = user_address;
//        this.user_name = user_name;
//        this.user_store = user_store;
//        this.user_image = user_image;
//        this.user_grade = user_grade;
//        this.user_sm = user_sm;
//        this.regdate = regdate;
//        this.updatedate = updatedate;
//    }

//  DTO -> Entity
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .userid(user_id)
                .userpw(user_pw)
                .useraddress(user_address)
                .username(user_name)
                .userstore(user_store)
                .userimage(user_image)
                .usergrade(user_grade)
                .usersm(user_sm)
                .regdate(regdate)
                .updatedate(updatedate)
                .emailToken_authentication(emailToken_authentication)
                .build();

    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_store() {
        return user_store;
    }
    public void setUser_store(String user_store) {
        this.user_store = user_store;
    }
    //계정 인증 완료
//    public void setVerified(){
//        this.emailToken_authentication = true;
//    }

}
