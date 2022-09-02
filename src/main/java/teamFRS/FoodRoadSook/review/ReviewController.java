package teamFRS.FoodRoadSook.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import teamFRS.FoodRoadSook.member.MemberDTO;
import teamFRS.FoodRoadSook.restaurant.RestaurantDTO;

import java.util.List;

@Controller
@RestController
@RequestMapping(value = "api/review")
public class ReviewController {
    private final ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService){

        this.reviewService = reviewService;

    }
    /**
     * 리뷰 쓰기
     * @return ReviewDTO
     */
    //리뷰 게시후 리다이렉트 처리 아직 안해줌
    @PostMapping("/write")
    public String createReview(ReviewDTO reviewDTO) {
        return reviewService.Review_insert(reviewDTO);
    }
    /**
     * 리뷰 삭제
     * @return ReviewDTO
     */
    //삭제 후 리다이렉트 처리 아직 안해줌
    @PostMapping("/delete")
    public String deleteReview(ReviewDTO reviewDTO) {
        return reviewService.Review_delete(reviewDTO);
    }
    /**
     * 해당 리뷰 보기
     * @return ReviewDTO
     */
    //http://localhost:8080/api/review/view/{고유 번호}
    @GetMapping("/view/id")
    public ReviewDTO viewReview(@PathVariable("id") int id) {
        return reviewService.Review_select(id);
    }


    /**
     * 리뷰리스트 보기 (리뷰 게시판용)
     * @return ReviewDTO
     */
    @GetMapping("/list")
    public List<ReviewDTO> viewReviewList() {
        return reviewService.reviewlist();
    }

    /**
     * 리뷰리스트 보기 (가게 페이지 리뷰 탭)
     * @return ReviewDTO
     */
    @GetMapping("/reslist")
    public List<ReviewDTO> viewResReviewList(RestaurantDTO restaurantDTO) {
        return reviewService.res_reviewlist(restaurantDTO);
    }

    /**
     * 리뷰리스트 보기 (가게 페이지 리뷰 탭)
     * @return ReviewDTO
     */
    @GetMapping("/mylist")
    public List<ReviewDTO> viewMyReviewList(MemberDTO memberDTO) {
        return reviewService.member_reviewlist(memberDTO);
    }
}
