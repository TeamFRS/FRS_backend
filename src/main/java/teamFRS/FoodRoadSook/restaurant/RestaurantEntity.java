package teamFRS.FoodRoadSook.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Table(name = "restaurant") //DB 테이블명 작성
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//MySQL의 AUTO_INCREMENT를 사용함을 명
    Long id;
    @Column(nullable = false, name = "res_name")
    String resname;
    @Column(nullable = false ,name = "res_time")
    LocalDateTime restime;
    @Column(nullable = false,name = "res_delivery")
    boolean resdelivery;
    @Column(nullable = false,name = "res_score")
    float resscore;
    @Column(name = "res_takeout")
    boolean restakeout;
    @Column(name = "res_recnum")
    int resrecnum;
    @Column(nullable = false,name = "res_mood")
    int resmood;
    @Column(nullable = false,name = "res_price")
    int resprice;
    @Column(nullable = false, name = "res_category")
    int rescategory; //이거 enum 처리할지 고민중
    @Column(nullable = false, name = "res_info")
    String resinfo; //이거 enum 처리할지 고민중

    //  Entity -> DTO
    public RestaurantDTO toDTO(){
        return RestaurantDTO.builder()
                .res_name(resname)
                .res_time(restime)
                .res_delivery(resdelivery)
                .res_score(resscore)
                .res_takeout(restakeout)
                .res_recnum(resrecnum)
                .res_mood(resmood)
                .res_price(resprice)
                .res_category(rescategory)
                .res_info(resinfo)
                .build();

    }



}
