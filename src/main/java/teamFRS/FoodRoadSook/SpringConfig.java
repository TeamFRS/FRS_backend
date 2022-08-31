package teamFRS.FoodRoadSook;

import teamFRS.FoodRoadSook.emailauth.EmailService;
import teamFRS.FoodRoadSook.emailauth.RedisUtil;
import teamFRS.FoodRoadSook.member.MemberRepository;
import teamFRS.FoodRoadSook.member.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import teamFRS.FoodRoadSook.member.MemberServiceImpl;
import teamFRS.FoodRoadSook.menu.MenuRepository;
import teamFRS.FoodRoadSook.menu.MenuService;
import teamFRS.FoodRoadSook.menu.MenuServiceImpl;
import teamFRS.FoodRoadSook.restaurant.RestaurantRepository;
import teamFRS.FoodRoadSook.restaurant.RestaurantService;
import teamFRS.FoodRoadSook.restaurant.RestaurantServiceImpl;
import teamFRS.FoodRoadSook.review.ReviewRepository;
import teamFRS.FoodRoadSook.review.ReviewService;
import teamFRS.FoodRoadSook.review.ReviewServiceImpl;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final MenuRepository menuRepository;
    private final EmailService emailService;
    private final RedisUtil redisUtil;

    public SpringConfig(MemberRepository memberRepository, RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, MenuRepository menuRepository, EmailService emailService, RedisUtil redisUtil) {
        this.memberRepository = memberRepository;
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
        this.menuRepository = menuRepository;
        this.emailService = emailService;
        this.redisUtil = redisUtil;
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(emailService, redisUtil, memberRepository);
    }

    @Bean
    public RestaurantService restaurantService(){return new RestaurantServiceImpl(restaurantRepository); }

    @Bean
    public ReviewService reviewService(){return new ReviewServiceImpl(reviewRepository,memberRepository,restaurantRepository); }

    @Bean
    public MenuService menuService(){return new MenuServiceImpl(menuRepository, restaurantRepository); }
}