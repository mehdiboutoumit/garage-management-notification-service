package org.example.garagemanagementnotificationmicroservice.services;

import org.example.garagemanagementnotificationmicroservice.config.RabbitMQConfig;
import org.example.garagemanagementnotificationmicroservice.mailbody.EmailRequest;
import org.example.garagemanagementnotificationmicroservice.models.Notification;
import org.example.garagemanagementnotificationmicroservice.repositories.NotificationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InvoiceConsumerService {

    @Autowired
    private JavaMailSender mailSender;

    private final NotificationRepository notificationRepository;

    public InvoiceConsumerService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.INVOICE_QUEUE)
    public void receiveInvoice(String invoice) {
        System.out.println("Processing invoice from queue: " + invoice);
        String emailTo = "mehdijk31m@gmail.com";
        String emailSubject = "Invoice Details";
        EmailRequest emailRequest = new EmailRequest(emailTo, emailSubject, invoice);
        sendMail(emailRequest);
    }

    public String sendMail(EmailRequest emailRequest) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(emailRequest.getTo());
            mailMessage.setSubject(emailRequest.getSubject());
            mailMessage.setText(emailRequest.getMessage());

            Notification notification = new Notification(emailRequest.getSubject(), emailRequest.getMessage(), new Date().toString(), emailRequest.getTo());
            notificationRepository.save(notification);

            mailSender.send(mailMessage);
            return "Mail Sent Successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while sending mail.";
        }
    }


}

