package add.message.blockchain.message.handler;

import java.util.ArrayList;
import java.util.List;

public class Buffer extends MessageDecorator{
    private List<Message> buffer;

    public Buffer() {
        super(null);
        buffer = new ArrayList<>();
    }

    public void add(Message message) {
        buffer.add(message);
    }

    public List<Message> getBuffer() {
        return buffer;
    }

    public void empty() {
        buffer = new ArrayList<>();
    }
}
