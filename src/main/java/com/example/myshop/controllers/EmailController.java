package com.example.myshop.controllers;

import com.example.myshop.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = {"http://localhost:3000"})
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send")
    public String sendMail(String to, String[] cc, String subject, String body) {
        return emailService.sendMail(to, cc, subject, body);
    }
}
