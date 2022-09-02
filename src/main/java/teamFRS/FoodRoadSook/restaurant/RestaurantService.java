package teamFRS.FoodRoadSook.restaurant;



public interface RestaurantService {
    //가게 정보 저장
    String Restaurant_insert(RestaurantDTO RestaurantDTO);

    //여기서 정보 받아올때 식별자로 쓸 변수 res_name(가게명)
    RestaurantDTO Restaurant_select(String res_name);

    //가게 정보 변경
    boolean Restaurant_update(int id, RestaurantDTO RestaurantDTO);

    //가게 정보 삭제
    String Restaurant_delete(String res_name);

}
