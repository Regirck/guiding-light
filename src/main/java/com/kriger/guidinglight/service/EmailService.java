package com.kriger.guidinglight.service;

import com.kriger.guidinglight.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    private String MESSAGE_FROM = "guidinglight.webapp@gmail.com";

    @Qualifier("javaMailService")
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendRegistrationMessage(User user) {
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

    public void sendForgotPasswordMessage(User user) {
        SimpleMailMessage message;

        try {
            message = new SimpleMailMessage();
            message.setFrom(MESSAGE_FROM);

            message.setTo(user.getEmail());

            message.setSubject("Password recovery on the guiding light");

            message.setText("Dear " + user.getEmail() + "! \n \n " +
                    "If you have requested password recovery click on the link below. " +
                            "If you didn't ask for it, please ignore this email. \n " +
                    "\t https://guiding-light.herokuapp.com/password_recovery/" + user.getEmail() + "&" + user.getActivation());

            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("Error e-mail send: " + user.getEmail() + "  " + e);
        }
    }

}
