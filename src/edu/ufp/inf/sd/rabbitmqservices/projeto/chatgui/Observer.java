package edu.ufp.inf.sd.rabbitmqservices.projeto.chatgui;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.*;
import edu.ufp.inf.sd.rabbitmqservices._04_topics.chatgui.ObserverGuiClient;
import edu.ufp.inf.sd.rabbitmqservices.util.RabbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Create a chat room (like zoom chat private msg), supporting:
 * - open *general* messages to all users
 * - private messages to a specific *user*
 *
 * <p>
 * Each _05_observer will receive messages from its queue with 2 Binding keys:
 * - room1.general (public msg for general/all users) and room1.pedro (private msg for given user)
 *
 * <p>
 * Send message with specific Routing keys:
 * - routingKey="room1.general" (public to general/all users)
 * - routingKey="room1.pedro"   (private to specific user)
 *
 * <p>
 * Run _05_observer with 3 parameters <room> <user> <general>:
 * $ runobserver room1 pedro general
 *
 *
 * @author rui
 */
public class Observer {

    //Reference for edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.buildings.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.gui
    private final ObserverGuiClient gui;

    //Preferences for exchange...
    private final Channel channelToRabbitMq;
    private final String exchangeName;
    private final BuiltinExchangeType exchangeType;
    private final String[] exchangeBindingKeys;
    private final String messageFormat;

    //Settings for specifying topics
    private final String room;
    private final String user;
    private final String general;

    //Store received message to be get by edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.buildings.edu.ufp.inf.sd.rabbitmqservices.projeto.jogo.gui
    private String receivedMessage;

    /**
     * @param gui
     */
    public Observer(ObserverGuiClient gui, String host, int port, String brokerUser, String brokerPass, String room, String user, String general, String exchangeName, BuiltinExchangeType exchangeType, String messageFormat) throws IOException, TimeoutException {
        this.gui=gui;
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, " going to attach _05_observer to host: " + host + "...");

        Connection connection=RabbitUtils.newConnection2Server(host, port, brokerUser, brokerPass);
        this.channelToRabbitMq=RabbitUtils.createChannel2Server(connection);
        this.exchangeName=exchangeName;
        this.exchangeType=exchangeType;

        this.room=room;
        this.user=user;
        this.general=general;

        // TODO: Set 2 binding keys (to receive msg from public room.general and private room.user
        String bingingKeys[]={room+"."+general, room+"."+user};


        this.exchangeBindingKeys=bingingKeys;
        this.messageFormat=messageFormat;

        bindExchangeToChannelRabbitMQ();
        attachConsumerToChannelExchangeWithKey();
    }

    /**
     * Declare exchange of specified type.
     */
    private void bindExchangeToChannelRabbitMQ() throws IOException {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Declaring Exchange '" + this.exchangeName + "' with policy = " + this.exchangeType);

        // TODO: Declare exchange type
        this.channelToRabbitMq.exchangeDeclare(this.exchangeName,BuiltinExchangeType.TOPIC);
    }

    /**
     * Creates a Consumer associated with an unnamed queue.
     */
    private void attachConsumerToChannelExchangeWithKey() {
        try {
            // TODO: Create a non-durable, exclusive, autodelete queue with a generated name.
            String queueName=this.channelToRabbitMq.queueDeclare().getQueue();

            // TODO: Bind to each routing key (received from args[3] upward)
            this.channelToRabbitMq.queueBind(queueName,this.exchangeName,this.exchangeBindingKeys[0]);
            this.channelToRabbitMq.queueBind(queueName,this.exchangeName,this.exchangeBindingKeys[1]);

            /* Use a DeliverCallback lambda function instead of DefaultConsumer to receive messages from queue;
               DeliverCallback is an interface which provides a single method:
                void handle(String tag, Delivery delivery) throws IOException; */
            DeliverCallback deliverCallback=(consumerTag, delivery) -> {
                String message=new String(delivery.getBody(), "UTF-8");
                setReceivedMessage(message);
                System.out.println(" [x] Received '" + message + "'");
                gui.updateTextArea();
            };
            CancelCallback cancelCalback=consumerTag -> {
                System.out.println(" [x] CancelCallback invoked");
            };

            // TODO: Consume with deliver and cancel callbacks
            this.channelToRabbitMq.basicConsume(queueName, true, deliverCallback,cancelCalback);

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.toString());
        }
    }

    /**
     * Publish messages to existing exchange instead of the nameless one.
     * - Messages will be lost if no queue is bound to the exchange yet.
     * - User may be some 'username' or 'general' (for all)
     */
    public void sendMessage(String msgToSend, String user) throws IOException {
        BasicProperties prop=MessageProperties.PERSISTENT_TEXT_PLAIN;
        //User maybe some <username> (private msg) or 'general' (public msg for all)

        // TODO: Publish message with routing key
        this.channelToRabbitMq.basicPublish(exchangeName,this.room+"."+user,null,msgToSend.getBytes("UTF-8"));
    }

    /**
     * @return the receivedMessage
     */
    public String getReceivedMessage() {
        return receivedMessage;
    }

    /**
     * @param receivedMessage the receivedMessage to set
     */
    public void setReceivedMessage(String receivedMessage) {
        this.receivedMessage=receivedMessage;
    }
}
