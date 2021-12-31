package com.cornerfoodmarketwebsite.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final String senderEmail;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, @Value(value = "${spring.mail.username}") String senderEmail) {
        this.javaMailSender = javaMailSender;
        this.senderEmail = senderEmail;
    }

    public void sendEmail(String receiverEmail, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setFrom(senderEmail);
        mimeMessageHelper.setTo(receiverEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);

        this.javaMailSender.send(mimeMessage);
    }

    public void sendNewAdminSignupUrl(String receiverEmail, String uuid) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(receiverEmail);
        message.setSubject("New Administrator Signup");
        message.setText("Sign up as a new administrator using the following link: https://localhost:3000/admin/new-admin-signup/" + uuid);
        javaMailSender.send(message);
    }
}
