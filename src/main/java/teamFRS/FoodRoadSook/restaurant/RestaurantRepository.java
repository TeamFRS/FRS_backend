package teamFRS.FoodRoadSook.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository  extends JpaRepository<RestaurantEntity, Integer> {
    RestaurantEntity save(RestaurantEntity restaurant);
    Optional<RestaurantEntity> findById(int id);
    Optional<RestaurantEntity> findByResname(String res_name);//가게명으로 찾기
    List<RestaurantEntity> findAll();
    void deleteByResname(String res_name);

}
