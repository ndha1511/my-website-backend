package com.example.myshop.controllers;

import com.example.myshop.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send")
    public String sendMail(String to, String[] cc, String subject, String body) {
        return emailService.sendMail(to, cc, subject, body);
    }
}
