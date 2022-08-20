package teamFRS.FoodRoadSook.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    MemberEntity save(MemberEntity member);
    Optional<MemberEntity> findById(Long id);
    Optional<MemberEntity> findByUserid(String user_id);
    Optional<MemberEntity> findByUsername(String user_name);
    List<MemberEntity> findAll();

}
