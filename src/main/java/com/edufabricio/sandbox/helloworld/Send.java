package com.edufabricio.sandbox.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

public class Send {

    public static Logger log = Logger.getLogger(Send.class);

    public static void main(String[] args) throws IOException{

        // Create Connection and Queue
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Configurator.RABBITMQ_SERVER_IP);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Configurator.QUEUE_HELLO,false, false, false, null);

        // Send
        String message = "Hello RabbitMQ, sending a new message at " + new Date();
        channel.basicPublish("", Configurator.QUEUE_HELLO,null, message.getBytes());
        log.info(" [X] Sent '" + message + "'");

    }


}
