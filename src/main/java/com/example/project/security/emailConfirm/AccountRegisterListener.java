package com.example.project.security.emailConfirm;

import com.example.project.domain.User;
import com.example.project.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@EnableAsync
public class AccountRegisterListener implements ApplicationListener<OnAccountRegisterCompleteEvent> {
    private final AuthService authService;

    private final JavaMailSender javaMailSender;

    @Async
    @Override
    public void onApplicationEvent(OnAccountRegisterCompleteEvent event) {
        this.confirmAccountRegister(event);
    }

    private void confirmAccountRegister(OnAccountRegisterCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        authService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Account Register Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/confirmRegister?token=" + token;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper email = new MimeMessageHelper(mimeMessage, "utf-8");
        try {
            email.setTo(recipientAddress);
            email.setSubject(subject);
            email.setText("<!doctype html>\n" +
                    "<html>\n" +
                    "  <head>\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                    "    <title>Account Register Confirm</title></head>\n" +
                    "  <body><div style='justify-content:center;'><div><h2 style='justify-content:center;background-color: #a61e4d; color: #eee;padding: 15px 25px;'>Welcome to Team Finder</h2>" +
                    "<h3>You successfully registered your new account. To enable it click button below</h3>" +
                    "<a class='btn btn-primary w-100 w-lg-50 align-center' href='http://localhost:4200/#" + confirmationUrl + "'><button style='background-color: #a61e4d;\n" +
                    "  color: #eee;\n" +
                    "  padding: 15px 25px;\n" +
                    "  border: none;'>Enable account</button></a></div></div></body>" +
                    "</html>", true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
