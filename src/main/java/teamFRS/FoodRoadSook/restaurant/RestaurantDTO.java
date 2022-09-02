package teamFRS.FoodRoadSook.restaurant;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RestaurantDTO {
    private int id;
    @NonNull
    @NotBlank
    private String res_name;
    @NonNull
    @NotBlank
    private LocalDateTime res_time;
    @NonNull
    @NotBlank
    private boolean res_delivery;

    private float res_score;

    private boolean res_takeout;

    private int res_recnum;
    @NonNull
    @NotBlank
    private int res_mood;

    private int res_price;
    @NonNull
    @NotBlank
    private int res_category;

    private String res_info;


    //  DTO -> Entity
    public RestaurantEntity toEntity() {
        return RestaurantEntity.builder()
                .id(id)
                .resname(res_name)
                .restime(res_time)
                .resdelivery(res_delivery)
                .resscore(res_score)
                .restakeout(res_takeout)
                .resrecnum(res_recnum)
                .resmood(res_mood)
                .resprice(res_price)
                .rescategory(res_category)
                .resinfo(res_info)
                .build();

    }
}
