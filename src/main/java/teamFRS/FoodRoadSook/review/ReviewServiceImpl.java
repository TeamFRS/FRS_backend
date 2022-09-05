package teamFRS.FoodRoadSook.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamFRS.FoodRoadSook.member.MemberDTO;
import teamFRS.FoodRoadSook.member.MemberEntity;
import teamFRS.FoodRoadSook.member.MemberRepository;
import teamFRS.FoodRoadSook.restaurant.RestaurantDTO;
import teamFRS.FoodRoadSook.restaurant.RestaurantEntity;
import teamFRS.FoodRoadSook.restaurant.RestaurantRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, MemberRepository memberRepository, RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
        this.restaurantRepository = restaurantRepository;
    }
    /**
     * 리뷰 정보 DB에 추가
     * @return
     */
    @Override
    public String Review_insert(ReviewDTO reviewDTO, MemberEntity memberEntity) {
        ReviewEntity reviewEntity = reviewDTO.toEntity();
        //유저 설정
        Optional <MemberEntity> member= memberRepository.findById(reviewDTO.getMemeber_id());
        reviewEntity.setMember(member.get());
        //가게 설정
       Optional <RestaurantEntity> restaurant =restaurantRepository.findByResname(reviewDTO.getReview_name());
        reviewEntity.setRestaurant(restaurant.get());
        reviewRepository.save(reviewEntity);
        return "리뷰 업로드 성공";

    }
    /**
     * 리뷰 정보 DB에서 가져오기(단일)
     * @return reviewDTO
     */
    @Override
    public ReviewDTO Review_select(int id) {
        Optional<ReviewEntity> review =reviewRepository.findById(id);
        return review.get().toDTO();
    }
    /**
     * 리뷰 내용만(Review_content만 변경
     * @return
     */
    @Override
    public boolean Review_update(ReviewDTO reviewDTO) {
        Optional<ReviewEntity> review =reviewRepository.findById(reviewDTO.getId());
        if (review.isPresent()) {
            ReviewEntity reviewedit = review.get();
            reviewedit.changeContent(reviewDTO.getReview_content());//리뷰 내용 변경
            reviewRepository.save(reviewedit);
        }
        return true;
    }
    /**
     * 리뷰 정보 삭제
     * @return
     */
    //주의 본인이 쓴글이 맞을 경우에만 삭제 승인.
    //주의 reviewRepository에서 뿐만 review와 연관된 Restaurant과 Member의 reviews 에서도 삭제해 줘야할것같은데
    //이게 객체가 각 필드에서 자동으로 사라질지는 잘 모르겠음.
    //각 클래스에 deletereview() 구현하면 될 것 같음.
    @Override
    public String Review_delete(ReviewDTO reviewDTO) {
        Optional<ReviewEntity> review =reviewRepository.findById(reviewDTO.getId());
        if(review.isPresent()) {
            reviewRepository.deleteById(reviewDTO.getId());
            return "성공적으로 리뷰를 삭제했습니다.";
        }
        else{
            return "존재하지않는 리뷰를 삭제할 수 없습니다.";
        }
    }
    /**
     * 리뷰리스트 DB에서 가져오기 for review 게시판
     * @return List<ReviewDTO>
     */
    //후기 게시판을 위한 리뷰 가져오기 (최근순으로 DB에서 전부 가져옴)
    @Override
    public List<ReviewDTO> reviewlist() {
        List<ReviewEntity> reviewEntities = reviewRepository.findAll();
        List<ReviewDTO> collect = reviewEntities.stream()
                .map(ReviewEntity::toDTO)
                .collect(Collectors.toList());

        return collect;
    }
    /**
     * 해당 가게 리뷰리스트 DB에서 가져오기 for 가게 페이지 후기 탭
     * @return List<ReviewDTO>
     */
    //가게 페이지 후기 탭을 위한 리뷰 가져오기(해당 가게에 연관되어있는 리뷰만 가져옴)
    @Override
    public List<ReviewDTO> res_reviewlist(RestaurantDTO restaurantDTO) {
        List<ReviewEntity> reviewEntities = restaurantRepository.findByResname(restaurantDTO.getRes_name()).get().getReviews();
        List<ReviewDTO> collect = reviewEntities.stream()
                .map(ReviewEntity::toDTO)
                .collect(Collectors.toList());

        return collect;
    }
    /**
     * 내가 쓴 리뷰리스트 DB에서 가져오기 for 마이페이지
     * @return List<ReviewDTO>
     */
    //마이페이지 나의 후기페이지를 위한 리뷰 가져오기(내가 쓴 리뷰만 가져옴)
    @Override
    public List<ReviewDTO> member_reviewlist(MemberDTO memberDTO) {
        List<ReviewEntity> reviewEntities = memberRepository.findByUserid(memberDTO.getUser_id()).get().getReviews();
        List<ReviewDTO> collect = reviewEntities.stream()
                .map(ReviewEntity::toDTO)
                .collect(Collectors.toList());

        return collect;

    }
}
