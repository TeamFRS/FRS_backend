package teamFRS.FoodRoadSook.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping(value = "api/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;
    @Autowired
    public RestaurantController(RestaurantService restaurantService){

        this.restaurantService = restaurantService;

    }

    /**
     * 가게정보 가져오기
     * @return RestaurantDTO
     */
    //http://localhost:8080/api/restaurant/info/{가게 명}
    @GetMapping("/info/res_name")
    public RestaurantDTO RestaurantProfile(@PathVariable("res_name") String res_name) {
        return restaurantService.Restaurant_select(res_name);
    }
}
