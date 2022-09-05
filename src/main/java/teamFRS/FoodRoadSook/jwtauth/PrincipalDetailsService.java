package teamFRS.FoodRoadSook.jwtauth;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import teamFRS.FoodRoadSook.member.MemberEntity;
import teamFRS.FoodRoadSook.member.MemberRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        Optional<MemberEntity> memberEntity = memberRepository.findByUserid(userid);
        return new PrincipalDetails(memberEntity.get());
    }
}

