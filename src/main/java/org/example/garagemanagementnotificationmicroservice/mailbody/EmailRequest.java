package org.example.garagemanagementnotificationmicroservice.mailbody;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailRequest {
    private String to;
    private String subject;
    private String message;
}

