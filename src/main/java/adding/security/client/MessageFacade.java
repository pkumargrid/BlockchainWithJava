package adding.security.client;


import adding.security.message.handler.Buffer;
import adding.security.message.handler.Message;
import adding.security.message.handler.MessageDecorator;
import adding.security.message.handler.PlainMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageFacade {
    private final Buffer buffer;

    private volatile static MessageFacade instance;

    private MessageFacade(Buffer buffer){
        this.buffer = buffer;
    }

    public synchronized void add(String message) throws Exception {
        this.buffer.add(new MessageDecorator(new PlainMessage(message), buffer.getIdentifier()));
    }

    public synchronized void generateRandomMessages(List<String> messages) throws Exception {
        Random random = new Random();
        for(String message : messages){
            boolean rand = random.nextBoolean();
            if(rand){
                add(message);
                break;
            }
        }
    }

    public synchronized static MessageFacade getInstance() throws Exception {
        if(instance == null){
            instance = new MessageFacade(new Buffer());
        }
        return instance;
    }

    public synchronized List<Message> receiveMessages() {
        List<Message> messages = buffer.getBuffer();
        if(buffer.getBuffer() == null) messages = new ArrayList<>();
        buffer.empty();
        return messages;
    }

}
