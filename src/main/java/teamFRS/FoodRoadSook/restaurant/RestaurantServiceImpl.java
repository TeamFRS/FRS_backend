package teamFRS.FoodRoadSook.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class RestaurantServiceImpl implements RestaurantService{
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * 가게 정보 DB에 추가
     * @return
     */
    @Override
    public String Restaurant_insert(RestaurantDTO restaurantDTO) {

        //1.해당 restaurant가 이미 존재하는지 체크
        Optional<RestaurantEntity> restaurant =restaurantRepository.findByResname(restaurantDTO.getRes_name());
        if(restaurant.isPresent()) {
            return "이미존재하는 가게입니다.";
        }
        else{
            RestaurantEntity restaurantEntity = restaurantDTO.toEntity();
            restaurantRepository.save(restaurantEntity);
            return "새로운 가게 등록 완료";
        }
    }
    /**
     * 가게 정보 DB에서 가져오기
     * @return restaurantDTO
     */
    @Override
    public RestaurantDTO Restaurant_select(String res_name) {
        //1.해당 res_name이 존재하는지 체크
        Optional<RestaurantEntity> restaurant =restaurantRepository.findByResname(res_name);
        return restaurant.get().toDTO();

    }
    /**
     * 미구현@@
     * 가게 정보 업데이트
     * @return
     */
    @Override
    public boolean Restaurant_update(int id, RestaurantDTO RestaurantDTO) {
        return false;
    }
    /**
     * 가게 정보 DB에서 삭제
     * @return
     */
    @Override
    public String Restaurant_delete(String res_name) {
        // res_name으로 조회해서 해당 객체 삭제
        Optional<RestaurantEntity> restaurant =restaurantRepository.findByResname(res_name);
        if(restaurant.isPresent()) {
            restaurantRepository.deleteByResname(res_name);
            return "성공적으로 가게를 삭제했습니다.";
        }
        else{
            return "존재하지않는 가게를 삭제할 수 없습니다.";
        }
    }
}
