package adding.currency.message.handler;

import java.util.ArrayList;
import java.util.List;

public class Buffer extends MessageDecorator{
    private List<Message> buffer;

    public Buffer() throws Exception {
        super(null, 0);
        buffer = new ArrayList<>();
    }

    public synchronized void add(Message message){
        buffer.add(message);
    }

    public synchronized List<Message> getBuffer() {
        return buffer;
    }

    public synchronized void empty(){
        buffer = new ArrayList<>();
    }

    public synchronized int getIdentifier() {
        return buffer.size() + 1;
    }
}
