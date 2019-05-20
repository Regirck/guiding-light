package com.kriger.guidinglight.service;

import com.kriger.guidinglight.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String MESSAGE_FROM;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessage(User user) {
        SimpleMailMessage message;

        try {
            message = new SimpleMailMessage();
            message.setFrom(MESSAGE_FROM);

            message.setTo(user.getEmail());

            message.setSubject("Registration on the guiding light");

            message.setText("Dear " + user.getEmail() + "! \n \n " +
                    "Thank you for the registration! \n \n " +
                    "Please activation your profile: \n " +
                    "\t https://guiding-light.herokuapp.com/activation/" + user.getActivation());

            javaMailSender.send(message);

        } catch (Exception e) {
            log.error("Error e-mail send: " + user.getEmail() + "  " + e);
        }


    }

}
