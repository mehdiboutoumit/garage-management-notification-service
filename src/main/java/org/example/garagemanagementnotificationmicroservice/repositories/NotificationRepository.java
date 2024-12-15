package org.example.garagemanagementnotificationmicroservice.repositories;


import org.example.garagemanagementnotificationmicroservice.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByClientId(long id);
}
