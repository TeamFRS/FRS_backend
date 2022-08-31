package teamFRS.FoodRoadSook.menu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamFRS.FoodRoadSook.restaurant.RestaurantDTO;
import teamFRS.FoodRoadSook.restaurant.RestaurantEntity;
import teamFRS.FoodRoadSook.restaurant.RestaurantRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class MenuServiceImpl implements MenuService{
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    public String Menu_insert(MenuDTO menuDTO) {
        MenuEntity menuEntity = menuDTO.toEntity();
        //가게 설정
        Optional <RestaurantEntity> restaurant =restaurantRepository.findByResname(menuDTO.getRestaurant_id());
        menuEntity.setRestaurant(restaurant.get());
        menuRepository.save(menuEntity);
        return "메뉴 추가 성공";
    }

    @Override
    public MenuDTO Menu_select(String res_name) {
        return null;
    }

    @Override
    public boolean Menu_update(int id, MenuDTO menuDTO) {
        return false;
    }

    @Override
    public String Menu_delete(String res_name) {
        return null;
    }
    /**
     * 해당 가게의 메뉴 리스트 가져오기
     * @return List<MenuDTO>
     */
    @Override
    public List<MenuDTO> res_menulist(RestaurantDTO restaurantDTO) {
        List<MenuEntity> menuEntities = restaurantRepository.findByResname(restaurantDTO.getRes_name()).get().getMenus();
        List<MenuDTO> collect = menuEntities.stream()
                .map(MenuEntity::toDTO)
                .collect(Collectors.toList());
        return collect;
    }
}
