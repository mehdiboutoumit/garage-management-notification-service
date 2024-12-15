package org.example.garagemanagementnotificationmicroservice.services;


import org.example.garagemanagementnotificationmicroservice.models.Notification;
import org.example.garagemanagementnotificationmicroservice.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public boolean sendNotification(Notification notification) {
        notificationRepository.save(notification);

        return true;
    }

    public List<Notification> getNotificationsByClientId(long clientId) {
        return notificationRepository.findByClientId(clientId);
    }
}
