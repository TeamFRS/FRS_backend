package teamFRS.FoodRoadSook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import teamFRS.FoodRoadSook.jwtauth.JwtAuthenticationFilter;
import teamFRS.FoodRoadSook.member.MemberRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsConfig corsConfig;
    @Autowired
    private MemberRepository memberRepository;



    @Bean
    public BCryptPasswordEncoder encoder() {
        // DB 패스워드 암호화
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.   httpBasic().disable() // rest api 만을 고려하여 기본 설정은 해제하겠습니다
                .csrf().disable() // rest api이므로 csrf 보안이 필요없으므로 disable처리
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 스프링 시큐리티가 세션 쿠키 방식으로 동작하지 않도록 설정
                                                                        //->대신 JWT토큰을 사용하여 매번 토큰을 넘겨 인증(세션로그인 방식 X)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
