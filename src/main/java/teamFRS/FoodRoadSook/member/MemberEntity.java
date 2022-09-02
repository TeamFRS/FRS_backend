package teamFRS.FoodRoadSook.member;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import teamFRS.FoodRoadSook.review.ReviewEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Table(name = "member") //DB 테이블명 작성
public class MemberEntity {
//정수형을 Integer로 해줘야하는지 int로 해줘야하는지 잘 모르겠음.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//MySQL의 AUTO_INCREMENT를 사용함을 명시
    int id;
    @Column(nullable = false, name = "user_id")
    String userid;
    @Column(nullable = false ,name = "user_pw")
    String userpw;
    @Column(nullable = false,name = "user_address")
    String useraddress;
    @Column(nullable = false,name = "user_name")
    String username;
    @Column(name = "user_store")
    String userstore;
    @Column(name = "user_image")
    String userimage;
    @Column(nullable = false,name = "user_grade")
    int usergrade;
    //무조건 숙대생으로 하기로 했으므로 필요없는 필드. user_sm 필드는 지움

    //이메일 인증 여부를 위한 토큰으로 아직 사용 X
    @Column(nullable = false, name = "email_auth")
    Boolean emailAuth;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews = new ArrayList<ReviewEntity>();


    public void addReview(ReviewEntity review){
        this.reviews.add(review);
        // 이부분이 없으면 무한 루프에 걸린다.
        if (review.getMember() != this) {
            review.setMember(this);
        }
    }
    //  Entity -> DTO
    public MemberDTO toDTO(){
        return MemberDTO.builder()
                .user_id(userid)
                .user_pw(userpw)
                .user_address(useraddress)
                .user_name(username)
                .user_store(userstore)
                .user_image(userimage)
                .user_grade(usergrade)
                .emailAuth(emailAuth)
                .build();

    }
    //계정 인증 완료
    public void emailVerified(){
        this.emailAuth = true;
    }


}
