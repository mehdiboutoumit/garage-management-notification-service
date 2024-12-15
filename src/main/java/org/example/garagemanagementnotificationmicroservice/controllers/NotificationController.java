package org.example.garagemanagementnotificationmicroservice.controllers;


import org.example.garagemanagementnotificationmicroservice.models.Notification;
import org.example.garagemanagementnotificationmicroservice.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Autowired
    private JavaMailSender mailSender;

    private final NotificationService notificationService;
    public NotificationController(final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/sendMail")
    public String sendMail(@RequestParam String to, @RequestParam String subject, @RequestParam String message) {
        try {
            notificationService.sendNotification((new Notification()));
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);

            mailSender.send(mailMessage);
            return "Mail Sent Successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while sending mail.";
        }
    }

    @GetMapping("/client/{id}")
    public List<Notification> getNotifications(@PathVariable long id) {
        return notificationService.getNotificationsByClientId(id);
    }





}
