package add.message.blockchain.message.handler;


public class MessageDecorator implements Message{
    private final Message message;

    public MessageDecorator(Message message){
        this.message = message;
    }

    @Override
    public String getContent() {
        return message.getContent();
    }
}
