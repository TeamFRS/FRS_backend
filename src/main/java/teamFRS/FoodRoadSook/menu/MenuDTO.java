package teamFRS.FoodRoadSook.menu;

import lombok.*;


import javax.validation.constraints.NotBlank;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MenuDTO {
    //아이디(이메일), 비밀번호, 주소, 닉네임, 찜한 가게, 학생증, 신뢰도, 숙명여대/주민[0,1]
    int id;
    @NonNull
    @NotBlank
    private String menu_name;
    @NonNull
    @NotBlank
    private int menu_price;
    @NonNull
    @NotBlank
    private String menu_info;
    @NonNull
    @NotBlank
    private String menu_image;
    @NonNull
    @NotBlank
    private int menu_flavor;
    @NonNull
    @NotBlank
    private String restaurant_id;


    //  DTO -> Entity
    public MenuEntity toEntity(){
        return MenuEntity.builder()
                .id(id)
                .menuname(menu_name)
                .menuprice(menu_price)
                .menuinfo(menu_info)
                .menuimage(menu_image)
                .menuflavor(menu_flavor)
                .build();

    }

}
