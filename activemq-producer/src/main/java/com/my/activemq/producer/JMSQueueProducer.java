package com.my.activemq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by kyle on 2018/12/4.
 */
public class JMSQueueProducer {

    public static void main(String[] args) {

        // 1. ConnectionFactory
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.5.129:61616");

        // 2. Create and start Connection
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            // 3. Create Session
            Session session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);

            // 4. Create Destination
            Destination destination = session.createQueue("myQueue");

            // 5. Create MessageProducer
            MessageProducer messageProducer = session.createProducer(destination);

            // 6. Create Message
            TextMessage textMessage = session.createTextMessage("Hello World .");

            // 7. send Message
            messageProducer.send(textMessage);

            // 8. commit
            //session.commit();

            //
            session.close();

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
