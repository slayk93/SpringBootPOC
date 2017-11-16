package com.poc.rabbitmq;

import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

@Component
public class Receiver {

    // Nombre de threads qui doivent être exécutés afin que la tâche
    // comprise dans un autre thread puisse être invoquée
    private final CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return this.latch;
    }

    public void receiveMessage(final String message) {
        System.out.println("Message reçu -> " + message);
        this.getLatch().countDown();
    }

}