package teamFRS.FoodRoadSook.review;


import teamFRS.FoodRoadSook.member.MemberDTO;
import teamFRS.FoodRoadSook.member.MemberEntity;
import teamFRS.FoodRoadSook.restaurant.RestaurantDTO;

import java.util.List;

public interface ReviewService {
    //가게 정보 저장
    String Review_insert(ReviewDTO reviewDTO, MemberEntity memberEntity);

    //여기서 정보 받아올때 식별자로 쓸 변수 id(자동 고유 번호)
    ReviewDTO Review_select(int id);

    //리뷰 정보 변경
    boolean Review_update(ReviewDTO reviewDTO);

    //리뷰 삭제
    String Review_delete(ReviewDTO reviewDTO);

    //리뷰 게시판을 위한 리뷰 가져오기 (DB에서 전부 가져옴)
    List<ReviewDTO> reviewlist();

    //가게페이지속 리뷰페이지를 위한 리뷰 가져오기(해당 가게에 연관되어있는 리뷰만 가져옴)
    List<ReviewDTO> res_reviewlist(RestaurantDTO restaurantDTO);

    //마이페이지 나의 후기페이지를 위한 리뷰 가져오기(내가 쓴 리뷰만 가져옴)
    List<ReviewDTO> member_reviewlist(MemberDTO memberDTO);


}