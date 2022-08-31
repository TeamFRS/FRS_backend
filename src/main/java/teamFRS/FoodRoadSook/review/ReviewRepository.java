package teamFRS.FoodRoadSook.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    ReviewEntity save(ReviewEntity restaurant);
    Optional<ReviewEntity> findById(int id);
    List<ReviewEntity> findAll();
    void deleteById(int id);

}
