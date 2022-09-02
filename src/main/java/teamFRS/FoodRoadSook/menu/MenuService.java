package teamFRS.FoodRoadSook.menu;



import teamFRS.FoodRoadSook.restaurant.RestaurantDTO;


import java.util.List;

public interface MenuService {
    //메뉴 정보 저장
    String Menu_insert(MenuDTO menuDTO);

    //메뉴 가져오기
    MenuDTO Menu_select(String res_name);

    //메뉴 정보 변경
    boolean Menu_update(int id, MenuDTO menuDTO);

    //메뉴 정보 삭제
    String Menu_delete(String res_name);

    List<MenuDTO> res_menulist(RestaurantDTO restaurantDTO);
}
