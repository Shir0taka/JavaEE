package com.books.bookscrud.controllers;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@RequestScoped
public class MessageController {
    public MessageController() {
        System.out.println("MessageController created!");
    }
    @Resource(lookup = "jms/jakartaEERecipeConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/jakartaEERecipeQueue")
    private Queue queue;

    @Resource
    private JMSContext jmsContext;

    private String message;

    public void sendMessage() {
        try {
            if (connectionFactory != null && queue != null) {
                TextMessage textMessage = jmsContext.createTextMessage(message);
                jmsContext.createProducer().send(queue, textMessage);
            } else {
                Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, "ConnectionFactory or Queue is null.");
            }
        } catch (Exception e) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, "Error sending message", e);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
