package com.pillgood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        String subject="필굿 임시 비밀번호 전송";
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("glaskdkbk@naver.com");
            helper.setTo(to); //받는사람
            helper.setSubject(subject); //제목
            helper.setText(body); //본문
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
