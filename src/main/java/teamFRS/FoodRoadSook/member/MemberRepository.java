package teamFRS.FoodRoadSook.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    MemberEntity save(MemberEntity member);
    Optional<MemberEntity> findById(Long id);
    Optional<MemberEntity> findByUserid(String user_id); //이메일로 찾기
    Optional<MemberEntity> findByUsername(String user_name); //닉네임으로 찾기
    List<MemberEntity> findAll();
    void deleteByUserid(String user_id);

}
