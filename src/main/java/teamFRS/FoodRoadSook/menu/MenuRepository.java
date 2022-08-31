package teamFRS.FoodRoadSook.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {
    MenuEntity save(MenuEntity menuEntity);
    Optional<MenuEntity> findById(int id);
    Optional<MenuEntity> findByMenuname(String menu_name);//메뉴명으로 찾기
    List<MenuEntity> findAll();
    void deleteByMenuname(String menu_name);
}
