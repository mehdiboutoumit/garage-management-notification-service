package org.example.garagemanagementnotificationmicroservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String INVOICE_QUEUE = "invoiceQueue";
    public static final String NOTIFICATION_QUEUE = "notificationQueue";

    public static final String TOPIC_EXCHANGE = "garageExchange";

    public static final String INVOICE_ROUTING_KEY = "invoice.routingKey";
    public static final String NOTIFICATION_ROUTING_KEY = "notification.routingKey";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    // Define the invoice queue
    @Bean
    public Queue invoiceQueue() {
        return new Queue(INVOICE_QUEUE, true); // durable queue
    }

    // Define the notification queue
    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE, true); // durable queue
    }

    // Bind the invoice queue to the exchange with the routing key
    @Bean
    public Binding invoiceBinding(Queue invoiceQueue, TopicExchange exchange) {
        return BindingBuilder.bind(invoiceQueue).to(exchange).with(INVOICE_ROUTING_KEY);
    }

    // Bind the notification queue to the exchange with the routing key
    @Bean
    public Binding notificationBinding(Queue notificationQueue, TopicExchange exchange) {
        return BindingBuilder.bind(notificationQueue).to(exchange).with(NOTIFICATION_ROUTING_KEY);
    }
}
