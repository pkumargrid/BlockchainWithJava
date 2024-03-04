package add.message.blockchain.message.handler;

public class PlainMessage implements Message{

    private final String content;

    public PlainMessage(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}
