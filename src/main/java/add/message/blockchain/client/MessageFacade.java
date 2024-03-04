package add.message.blockchain.client;

import add.message.blockchain.message.handler.Buffer;
import add.message.blockchain.message.handler.Message;
import add.message.blockchain.message.handler.MessageDecorator;
import add.message.blockchain.message.handler.PlainMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageFacade {
    private final Buffer buffer;

    private volatile static MessageFacade instance;

    private MessageFacade(Buffer buffer){
        this.buffer = buffer;
    }

    public synchronized void add(String message){
        this.buffer.add(new MessageDecorator(new PlainMessage(message)));
    }

    public synchronized void generateRandomMessages(List<String> messages){
        Random random = new Random();
        for(String message : messages){
            boolean rand = random.nextBoolean();
            if(rand){
                add(message);
                break;
            }
        }
    }

    public synchronized static MessageFacade getInstance() {
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
