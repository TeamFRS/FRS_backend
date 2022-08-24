package teamFRS.FoodRoadSook;

import teamFRS.FoodRoadSook.emailauth.EmailService;
import teamFRS.FoodRoadSook.emailauth.RedisUtil;
import teamFRS.FoodRoadSook.member.MemberRepository;
import teamFRS.FoodRoadSook.member.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import teamFRS.FoodRoadSook.member.MemberServiceImpl;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final RedisUtil redisUtil;

    public SpringConfig(MemberRepository memberRepository, EmailService emailService, RedisUtil redisUtil) {
        this.memberRepository = memberRepository;
        this.emailService = emailService;
        this.redisUtil = redisUtil;
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(emailService, redisUtil, memberRepository);
    }

}