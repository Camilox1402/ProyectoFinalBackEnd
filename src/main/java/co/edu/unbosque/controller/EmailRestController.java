package co.edu.unbosque.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.unbosque.entity.Email;
import co.edu.unbosque.utils.EmailService;

@RestController
@RequestMapping("/email")
public class EmailRestController {

 @Autowired private EmailService emailService;

 @PostMapping("/sendMail")
 public String
 sendMail(@RequestBody Email email)
 {
     String status
         = emailService.sendSimpleMail(email);

     return status;
 }
}
