package teamFRS.FoodRoadSook.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamFRS.FoodRoadSook.restaurant.RestaurantDTO;


import java.util.List;

@Controller
@RestController
@RequestMapping(value = "api/menu")
public class MenuController {
    private final MenuService menuService;
    @Autowired
    public MenuController(MenuService menuService){

        this.menuService = menuService;

    }

    /**
     * 메뉴 리스트 가져오기
     * @return list<MenuDTO></MenuDTO>
     */
    @GetMapping("/menulist")
    public List<MenuDTO> viewMenuList(RestaurantDTO restaurantDTO) {
        return menuService.res_menulist(restaurantDTO);
    }
}
