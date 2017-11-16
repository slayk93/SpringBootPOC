package com.poc.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    final static String queueName = "TheQueue";

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Binding binding(final Queue queue, final TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Application.queueName);
    }

    @Bean
    SimpleMessageListenerContainer container(final ConnectionFactory connectionFactory, final MessageListenerAdapter listenerAdapter) {
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(Application.queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    TopicExchange exchange() {
        // http://igm.univ-mlv.fr/~dr/XPOSE2011/rabbitmq/modele.html
        return new TopicExchange("l'exchange c'est quoi");
    }

    @Bean
    MessageListenerAdapter listenerAdapter(final Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    // Cette annotation indique que la methode produit un bean qui sera géré automatiquement par le container de Spring
    @Bean
    Queue queue() {
        // le boolean sert à indiquer si la queue est durable, c'est-à-dire que la queue reste même après redemarrage du serveur
        return new Queue(Application.queueName, false);
    }

}
