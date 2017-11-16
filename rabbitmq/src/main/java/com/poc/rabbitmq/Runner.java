package com.poc.rabbitmq;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate                 rabbitTemplate;
    private final Receiver                       receiver;
    private final ConfigurableApplicationContext context;

    public Runner(final Receiver receiver, final RabbitTemplate rabbitTemplate, final ConfigurableApplicationContext context) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println("Envoi du message en cours...");
        this.rabbitTemplate.convertAndSend(Application.queueName, "Ceci est le message envoyé");
        this.receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        this.context.close();
    }

}