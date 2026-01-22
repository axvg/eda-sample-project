package com.group.lms.shared.infrastructure.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.TopicExchange;

import org.springframework.context.annotation.Bean;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "lms.event";
    public static final String COURSE_QUEUE = "lms.queue";
    // routing keys
    public static final String COURSE_CREATED_RK = "course.created";
    public static final String COURSE_PUBLISHED_RK = "course.published";

    @Bean
    public TopicExchange eventExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue courseQueue() {
        return new Queue(COURSE_QUEUE, true);
    }

    @Bean
    public Binding courseBinding() {
        return BindingBuilder
            .bind(courseQueue())
            .to(eventExchange())
            .with(COURSE_CREATED_RK);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}