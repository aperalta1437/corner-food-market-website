package com.cornerfoodmarketwebsite.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Value(value = "${spring.mail.username}")
    private static String senderEmail;

    public boolean sendTfaCodeEmail(String receiverEmail, int tfaCode) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(senderEmail);
        message.setTo(receiverEmail);
        message.setSubject("Two Factor Authentication code from our Service");
        message.setText("Your Two Factor Authentication code is: " + tfaCode);
        emailSender.send(message);

        return true;
    }
}
