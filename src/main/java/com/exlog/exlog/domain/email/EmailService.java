package com.exlog.exlog.domain.email;


import com.exlog.exlog.domain.auth.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Async // 비동기 처리
    public void sendStreakWarningEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());

        message.setSubject("[Ex-Log] " + user.getUsername() + "님, 운동 스택이 끊길 위기예요! 🔥");

        String text = String.format(
                "안녕하세요, %s님!\n\n" +
                        "벌써 3일 동안 운동 기록이 없네요. 😢\n" +
                        "열심히 쌓아온 스트릭이 초기화되기 직전입니다!\n\n" +
                        "가벼운 스트레칭이라도 좋으니 지금 바로 기록을 남겨보세요.\n\n" +
                        "오늘도 득근하세요! 💪\n\n" +
                        "Ex-Log 팀 드림.",
                user.getUsername()
        );
        message.setText(text);

        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("스트릭 경고 메일 발송 실패: User='{}', Error='{}'", user.getEmail(), e.getMessage());
        }
    }
}

