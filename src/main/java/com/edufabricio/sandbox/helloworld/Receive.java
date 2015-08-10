package com.edufabricio.sandbox.helloworld;

import com.rabbitmq.client.*;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Receive {

    public static Logger log = Logger.getLogger(Receive.class);

    public static void main(String[] args) throws IOException, InterruptedException {

        // Create Connection and Queue
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Configurator.RABBITMQ_SERVER_IP);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Configurator.QUEUE_HELLO,false, false, false, null);

        // Consuming Queue
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                log.info(" [X] Received '"+message+"'");
            }
        };
        channel.basicConsume(Configurator.QUEUE_HELLO, true, consumer);


    }
}
