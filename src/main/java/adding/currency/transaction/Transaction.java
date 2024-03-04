package adding.currency.transaction;


import adding.currency.client.Client;
import adding.currency.message.handler.Message;
import adding.currency.mining.Miner;
import adding.currency.person.Person;

public class Transaction implements Message {

    private final Person receiver;

    private final Person sender;

    private final int vc;

    public Transaction(Person sender, Person receiver, int vc){
        this.receiver = receiver;
        this.sender = sender;
        this.vc = vc;
    }

    @Override
    public String getContent() {
        return  getName(sender) + " sent " + vc + " VC to " + getName(receiver);
    }

    public String getName(Person person) {
        if(person instanceof Client client) {
            return client.getName();
        }
        else if(person instanceof Miner miner){
            return miner.getName();
        }
        else {
            throw new IllegalArgumentException("Unknown type of Person + " + person);
        }
    }

    public Person getReceiver() {
        return receiver;
    }

    public Person getSender() {
        return sender;
    }

    public int getVc() {
        return vc;
    }
}
