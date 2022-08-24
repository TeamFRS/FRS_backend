package teamFRS.FoodRoadSook.emailauth;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync//전송하는 동안 블럭상태에 놓임을 방지
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private final JavaMailSender javaMailSender;

    @Async//동기방식 메서드를 비동기 방식으로 구동
    public void send(String user_id, String authToken) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(user_id);//누구에게 보낼지
        smm.setSubject("회원가입 이메일 인증");//제목
        smm.setText("http://localhost:8080/sign/confirm-email?email="+user_id+"&authToken="+authToken);//내용

        javaMailSender.send(smm);
    }

}
