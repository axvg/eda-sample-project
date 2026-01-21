package com.group.lms.shared.domain.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.group.lms.shared.infrastructure.config.RabbitMQConfig.EXCHANGE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQEventPublisher {
    private final RabbitTemplate template;

    public void publish(String routingKey, DomainEvent event) {
        log.info("Publishing event to RabbitMQ: {} - {} - {}",
            event.getEventId(),
            event.getEventType(),
            event.getOcurredOn()
        );
        template.convertAndSend(
            EXCHANGE_NAME,
            routingKey,
            event
        );
    }
}
