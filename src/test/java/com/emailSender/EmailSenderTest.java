package com.emailSender;

import com.emailSender.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailService emailService;

    @Test
    void emailSendTest(){
      emailService.sendEmail("bossrawat640@gmail.com","Email send to springboot","hello what about you");
    }

    @Test
    void sendEmailWithHtml() {
        String html = "<html><body><h1 style='color:red;'>Email Boss</h1><p>This is a test email</p></body></html>";
        emailService.sendEmail("20cs1003@mvn.edu.in", "Email send to boss", html);
    }

    @Test
    void sendEmailWithFile(){
        emailService.sendEmailWithFile("bossrawat640@gmail.com",
                "Email with file",
                "This email contains a file"
                ,new File("C:\\Users\\ROHIT RAWAT\\OneDrive\\Desktop\\EmailSender\\EmailSender\\src\\main\\resources\\static\\images\\banner.jpg"));
    }
    @Test
    void sendEmailWithFileWithStream(){
       File file = new File("C:\\Users\\ROHIT RAWAT\\OneDrive\\Desktop\\EmailSender\\EmailSender\\src\\main\\resources\\static\\images\\banner.jpg");
        try {
            InputStream is = new FileInputStream(file);
            emailService.sendEmailWithFile("bossrawat640@gmail.com",
                    "Email with file",
                    "This email contains a file",is);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
