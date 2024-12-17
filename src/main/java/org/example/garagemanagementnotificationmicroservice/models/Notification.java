package org.example.garagemanagementnotificationmicroservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String subject;
    private String message;
    private String timestamp;
    private String clientEmail;

    public Notification(String subject, String message, String timestamp, String clientEmail) {
        this.subject = subject;
        this.message = message;
        this.timestamp = timestamp;
        this.clientEmail = clientEmail;
    }
}
