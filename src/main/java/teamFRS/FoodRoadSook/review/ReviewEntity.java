package teamFRS.FoodRoadSook.review;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import teamFRS.FoodRoadSook.member.MemberEntity;
import teamFRS.FoodRoadSook.restaurant.RestaurantEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Table(name = "review") //DB 테이블명 작성
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//MySQL의 AUTO_INCREMENT를 사용함을 명
    int id;
    //가게이름 FK
    @ManyToOne(fetch = FetchType.LAZY)//연관관계의 주인
    @JoinColumn(nullable = false, name = "review_name")
    private RestaurantEntity restaurant;
    //유저 번호 FK
    @ManyToOne(fetch = FetchType.LAZY)//연관관계의 주인
    @JoinColumn(nullable = false,name = "member_id")
    private MemberEntity member;

    @Column(nullable = false, name = "review_clean")
    private int reviewclean;
    @Column(nullable = false, name = "review_nice")
    private int reviewnice;
    @Column(nullable = false, name = "review_taste")
    private int reviewtaste;
    @Column(nullable = false, name = "review_score")
    private int reviewscore;
    @Column(nullable = false, name = "review_content")
    private String reviewcontent;
    @Column(nullable = false, name = "review_image")
    private String reviewimage;
    @Column(nullable = false, name = "review_time")
    private LocalDateTime reviewtime;
    @Column(nullable = false, name = "review_menu")
    private String reviewmenu;

    public void setMember(MemberEntity member) {
        // 이 부분에서 기존에 member와 연관관계가 있다면
        // member에서 해당 review를 먼저 제거한다.
        if(this.member != null) {
            this.member.getReviews().remove(this);
        }
        this.member = member;
        // 이부분이 없으면 무한 루프에 걸린다.
        if(!member.getReviews().contains(this)) {
            member.addReview(this);
        }
    }
    public void setRestaurant(RestaurantEntity restaurant) {
        // 이 부분에서 기존에 member와 연관관계가 있다면
        // member에서 해당 review를 먼저 제거한다.
        if(this.restaurant != null) {
            this.restaurant.getReviews().remove(this);
        }
        this.restaurant = restaurant;
        // 이부분이 없으면 무한 루프에 걸린다.
        if(!restaurant.getReviews().contains(this)) {
            restaurant.addReview(this);
        }
    }

    //  DTO -> Entity
    public ReviewDTO toDTO() {
        return ReviewDTO.builder()
                .review_clean(reviewclean)
                .review_nice(reviewnice)
                .review_taste(reviewtaste)
                .review_score(reviewscore)
                .review_content(reviewcontent)
                .review_image(reviewimage)
                .review_time(reviewtime)
                .review_menu(reviewmenu)
                .build();

    }
    public void changeContent(String content){this.reviewcontent = content;}
}
