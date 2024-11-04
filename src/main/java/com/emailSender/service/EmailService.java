package com.emailSender.service;

import java.io.File;
import java.io.InputStream;

public interface EmailService {

    //send email to single person
    void sendEmail(String to,String subject,String message);

    //send email to multiple people
    void sendEmail(String [] to,String subject,String message);

    //void sendEmailWithHtml
    void sendEmailWithHtml(String to,String subject,String htmlContent);

    //void sendEmailWithFile
    void sendEmailWithFile(String to, String subject, String message, File file);

    //sendEmailWithInputStream
    void sendEmailWithFile(String to, String subject, String message, InputStream is);


}
