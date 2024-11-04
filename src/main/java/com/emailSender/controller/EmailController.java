package com.emailSender.controller;

import com.emailSender.helper.CustomResponse;
import com.emailSender.request.EmailRequest;
import com.emailSender.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.WriteAbortedException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
       emailService.sendEmailWithHtml(request.getTo(),request.getSubject(),request.getMessage());
       return  ResponseEntity.ok(
               CustomResponse.builder().message("Email send Successfully !!").httpStatus(HttpStatus.OK).success(true).build()
       );
    }

    @PostMapping("/send-with-file")
    public ResponseEntity<CustomResponse> sendWithFile(@RequestPart EmailRequest request, @RequestPart MultipartFile file) throws IOException {
        emailService.sendEmailWithFile(request.getTo(), request.getSubject(), request .getMessage(), file.getInputStream());
        return ResponseEntity.ok(
                CustomResponse.builder().message("Email send Successfully !!").httpStatus(HttpStatus.OK).success(true).build()
        );
    }
    
}
