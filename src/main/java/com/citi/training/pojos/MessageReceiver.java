package com.citi.training.pojos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @Autowired
    JmsTemplate jmsTemplate;


    @JmsListener(destination = "queue/BuyQueue")
    public void receiveMessage(String message) {
        System.out.println("Received message on buy queue: <" + message + ">");
       jmsTemplate.convertAndSend("queue/ReturnQueue", "Yes");
    }

    @JmsListener(destination = "queue/SellQueue")
    public void receiveMessageTopic(String message) {
        System.out.println("Received message on sell queue: <" + message + ">");
        jmsTemplate.convertAndSend("queue/ReturnQueue", "Yes");

    }

    @JmsListener(destination = "queue/ReturnQueue")
    public void receiveYesMessage(String message) {
        System.out.println("Broker's reply is : " + message);
   }

}