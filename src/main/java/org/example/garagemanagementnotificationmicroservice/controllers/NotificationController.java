package org.example.garagemanagementnotificationmicroservice.controllers;


import org.example.garagemanagementnotificationmicroservice.mailbody.EmailRequest;
import org.example.garagemanagementnotificationmicroservice.models.Notification;
import org.example.garagemanagementnotificationmicroservice.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private JavaMailSender mailSender;

    private final NotificationService notificationService;
    public NotificationController(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/")
    public List<Notification> getNotifications() {
        return notificationService.getAllNotifications();
    }

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailRequest emailRequest) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(emailRequest.getTo());
            mailMessage.setSubject(emailRequest.getSubject());
            mailMessage.setText(emailRequest.getMessage());

            System.out.println(mailMessage);

            Notification notification = new Notification(emailRequest.getSubject(), emailRequest.getMessage(), new Date().toString(), emailRequest.getTo());
            //notificationService.saveNotification(notification);

            mailSender.send(mailMessage);
            return "Mail Sent Successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while sending mail.";
        }
    }


    @GetMapping("/client/{id}")
    public List<Notification> getNotifications(@PathVariable String mail) {
        return notificationService.getNotificationsByClientEmail(mail);
    }





}
