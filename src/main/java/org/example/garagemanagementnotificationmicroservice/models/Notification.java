package org.example.garagemanagementnotificationmicroservice.models;


import jakarta.persistence.Entity;
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
    private long id;
    private String subject;
    private String message;
    private String timestamp;
    private long clientId;
}
