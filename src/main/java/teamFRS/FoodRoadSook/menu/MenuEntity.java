package teamFRS.FoodRoadSook.menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import teamFRS.FoodRoadSook.restaurant.RestaurantEntity;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Builder
@Table(name = "menu") //DB 테이블명 작성
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//MySQL의 AUTO_INCREMENT를 사용함을 명시
    int id;
    @Column(nullable = false, name = "menu_name")
    String menuname;
    @Column(nullable = false ,name = "menu_price")
    int menuprice;
    @Column(nullable = false,name = "menu_info")
    String menuinfo;
    @Column(nullable = false,name = "menu_image")
    String menuimage;
    @Column(name = "menu_flavor")
    int menuflavor;

    @ManyToOne(fetch = FetchType.LAZY)//연관관계의 주인
    @JoinColumn(nullable = false, name = "restaurant_id")
    RestaurantEntity restaurant;

    //  Entity -> DTO
    public MenuDTO toDTO(){
        return MenuDTO.builder()
                .id(id)
                .menu_name(menuname)
                .menu_price(menuprice)
                .menu_info(menuinfo)
                .menu_image(menuimage)
                .menu_flavor(menuflavor)
                .build();

    }
    public void setRestaurant(RestaurantEntity restaurant) {
        // 이 부분에서 기존에 member와 연관관계가 있다면
        // member에서 해당 review를 먼저 제거한다.
        if(this.restaurant != null) {
            this.restaurant.getMenus().remove(this);
        }
        this.restaurant = restaurant;
        // 이부분이 없으면 무한 루프에 걸린다.
        if(!restaurant.getMenus().contains(this)) {
            restaurant.addMenu(this);
        }
    }
}
