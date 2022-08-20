package teamFRS.FoodRoadSook;

import teamFRS.FoodRoadSook.member.MemberController;
import teamFRS.FoodRoadSook.member.MemberRepository;
import teamFRS.FoodRoadSook.member.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import teamFRS.FoodRoadSook.member.MemberServiceImpl;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository);
    }

}