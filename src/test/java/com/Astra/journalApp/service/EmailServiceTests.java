package com.Astra.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;
    @Disabled
    @Test
    public void sendMail(){
        emailService.sendEmail("aryan.raj.codi@gmail.com","Testing java mail sender!!","Hi, I am Aryan.");
    }
}
