package teamFRS.FoodRoadSook.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import teamFRS.FoodRoadSook.jwtauth.PrincipalDetails;
import teamFRS.FoodRoadSook.emailauth.Response;
import teamFRS.FoodRoadSook.member.MemberEntity;

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
    public String createReview(@RequestBody ReviewDTO reviewDTO, Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        MemberEntity member = principalDetails.getMember();
        return reviewService.Review_insert(reviewDTO, member);
    }

    /**
     * 리뷰 삭제
     * @return ReviewDTO
     */
    //삭제 후 리다이렉트 처리 아직 안해줌
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/delete/{id}")
    public String deleteReview(@PathVariable("id") Integer id, Authentication authentication) {
        return reviewService.Review_delete(reviewDTO);
    }

    /**
     * 리뷰 수정하기
     * @return ReviewDTO
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public Response edit(@RequestBody ReviewDTO reviewDTO,@PathVariable("id") Integer id, Authentication authentication) {
        // 원래 로그인을 하면, User 정보는 세션을 통해서 구하고 주면 되지만,
        // 지금은 핵심 개념을 알기 위해서, JWT 로그인은 생략하고, 임의로 findById 로 유저 정보를 넣어줬습니다.

        // 추후에 JWT 로그인을 배우고나서 적용해야할 것

        // 1. 현재 요청을 보낸 유저의 JWT 토큰 정보(프론트엔드가 헤더를 통해 보내줌)를 바탕으로
        // 현재 로그인한 유저의 정보가 PathVariable로 들어오는 BoardID 의 작성자인 user정보와 일치하는지 확인하고
        // 맞으면 아래 로직 수행, 틀리면 다른 로직(ResponseFail 등 커스텀으로 만들어서) 수행
        // 이건 if문으로 처리할 수 있습니다. * 이 방법 말고 service 내부에서 확인해도 상관 없음
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        MemberEntity member = principalDetails.getMember();
        if (member.getUserid().equals(reviewService.Review_select(id).getMemeber_id())) {
            // 로그인된 유저의 글이 맞다면
            return new Response("성공", "글 수정 성공", reviewService.Review_update(id, reviewDTO));
        } else {
            return new Response("실패", "본인 게시물만 수정할 수 있습니다.", null);

    }

    /**
     * 해당 개별 리뷰 보기
     * @return ReviewDTO
     */
    //http://localhost:8080/api/review/view/{고유 번호}
    @GetMapping("/view/{id}")
    public ReviewDTO viewReview(@PathVariable("id") int id) {
        return reviewService.Review_select(id);
    }

    /**
     *
     * 리뷰리스트 보기 (리뷰 게시판용)
     * @return List<ReviewDTO>
     */
    // 전체 게시글 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/list")
    public List<ReviewDTO> viewReviewList() {
        return reviewService.reviewlist();
    }

    /**
     * 리뷰리스트 보기 (가게 페이지 리뷰 탭)
     * @return List<ReviewDTO>
     */
    @GetMapping("/reslist")
    public List<ReviewDTO> viewResReviewList(RestaurantDTO restaurantDTO) {
        return reviewService.res_reviewlist(restaurantDTO);
    }

    /**
     * 리뷰리스트 보기 (가게 페이지 리뷰 탭)
     * @return List<ReviewDTO>
     */
    @GetMapping("/mylist")
    public List<ReviewDTO> viewMyReviewList(MemberDTO memberDTO) {
        return reviewService.member_reviewlist(memberDTO);
    }
}
