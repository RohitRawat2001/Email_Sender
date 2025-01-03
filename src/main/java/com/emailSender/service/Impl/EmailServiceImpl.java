package com.emailSender.service.Impl;

import com.emailSender.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("goshopping854@gmail.com");
        javaMailSender.send(simpleMailMessage);
        logger.info("Email has been sent ..");
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("goshopping854@gmail.com");
        javaMailSender.send(simpleMailMessage);
    }


    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom("goshopping854@gmail.com");
            helper.setText(htmlContent, true);  // This sets the content as HTML
            javaMailSender.send(mimeMessage);
            logger.info("Email has been sent ..");
        } catch (MessagingException e) {
            logger.error("Failed to send email", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void sendEmailWithFile(String to, String subject, String message, File file) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

            helper.setFrom("goshopping854@gmail.com");
            helper.setTo(to);
            helper.setText(message);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(),file);
            javaMailSender.send(mimeMessage);
            logger.info("Mail sent successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, InputStream is) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

            helper.setFrom("goshopping854@gmail.com");
            helper.setTo(to);
            helper.setText(message,true);
            helper.setSubject(subject);
            File file = new File("src/main/resources/static/images/test.png");
            Files.copy(is,file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(),file);
            javaMailSender.send(mimeMessage);
            logger.info("Mail sent successfully");

        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
