package teamFRS.FoodRoadSook.review;

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
public class ReviewDTO {
    private int id;
    @NonNull
    @NotBlank
    private String review_name;
    @NonNull
    @NotBlank
    private int memeber_id;
    @NonNull
    @NotBlank
    private int review_clean;
    @NonNull//기본값 0
    @NotBlank
    private int review_nice;
    @NonNull//기본값 0
    @NotBlank
    private int review_taste;
    @NonNull//기본값 0
    @NotBlank
    private int review_score;
    @NonNull
    @NotBlank
    private String review_content;

    private String review_image;
    @NonNull
    @NotBlank
    private LocalDateTime review_time;
    @NonNull
    @NotBlank
    private String review_menu;


    //  DTO -> Entity
    public ReviewEntity toEntity() {
        ReviewEntity reviewEntity =
                ReviewEntity.builder()
                        .id(id)
                .reviewclean(review_clean)
                .reviewnice(review_nice)
                .reviewtaste(review_taste)
                .reviewscore(review_score)
                .reviewcontent(review_content)
                .reviewimage(review_image)
                .reviewtime(review_time)
                .reviewmenu(review_menu)
                .build();
        return reviewEntity;

    }
}
