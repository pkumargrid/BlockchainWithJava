package adding.currency.message.handler;


import adding.currency.transaction.Transaction;
import adding.currency.verification.MessageVerification;

import java.util.ArrayList;
import java.util.List;

public class MessageFacade {
    private final Buffer buffer;

    private volatile static MessageFacade instance;

    private MessageFacade(Buffer buffer){
        this.buffer = buffer;
    }

    public synchronized void add(Transaction transaction) throws Exception {
        boolean valid = MessageVerification.isValid(transaction);
        if(valid)
            buffer.add(new MessageDecorator(new PlainMessage(transaction.getContent()), buffer.getIdentifier()));
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
