package com.my.activemq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by kyle on 2018/12/4.
 */
public class JMSQueueConsumer {
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

            // 4. Create Destination -- Queue
            Destination destination = session.createQueue("myQueue");

            // 5. Create MessageConsumer
            MessageConsumer messageConsumer = session.createConsumer(destination);

            // 6. Receive Message
            System.out.println("-------");
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            System.out.println(textMessage.getText());

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
