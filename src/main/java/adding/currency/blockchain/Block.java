package adding.currency.blockchain;

import adding.currency.constants.Miner;
import adding.currency.magic.generator.MagicGenerator;
import adding.currency.message.handler.Message;
import adding.currency.person.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Block {
    private volatile String hash;
    private final String prevHash;
    private final long timeStamp;
    private final int id;
    private volatile int magic;
    private volatile String status;
    private List<Message> messages;

    private Person person;

    public void setPerson(Person person) {
        person.setVC(person.getVC() + Miner.Amount.value);
        this.person = person;
    }

    public synchronized void setStatus(int currentTime) {
        status = "N stays the same";
    }

    private volatile int generatingTime;
    public Block(String prevHash, int id){
        this.prevHash = prevHash;
        this.id = id;
        this.timeStamp = new Date().getTime();
        messages = new ArrayList<>();
        createHash();
    }

    public String getHash(){
        return this.hash;
    }

    public synchronized void createHash(){
        BlockChain blockChain = BlockChain.getInstance(adding.currency.constants.Block.CAPACITY.value);
        MagicGenerator.HashMagic hashMagic =
                MagicGenerator.generate(  id + prevHash + timeStamp + getMessages(), blockChain.getNumberOfZeros(), 0);
        setMagic(hashMagic.magic());
        setHash(hashMagic.hash());
    }

    public void setMessages(List<Message> messages){
        this.messages = messages;
    }
    public synchronized void setGeneratingTime(int generatingTime) {
        this.generatingTime = generatingTime;
    }

    public synchronized void setHash(String hash) {
        this.hash = hash;
    }

    public synchronized void setMagic(int magic) {
        this.magic = magic;
    }

    public String getPrevHash() {
        return prevHash;
    }
    @Override
    public String toString(){
        return """
               Block:
               Created by: %s
               %s gets %d VC
               Id: %d
               Timestamp: %d
               Magic number: %d
               Hash of the previous block:
               %s
               Hash of the block:
               %s
               Block data: %s
               Block ws generating for %d seconds
               %s
               """.formatted(person.getName(), person.getName(),
                Miner.Amount.value, id + 1, timeStamp, magic, prevHash, hash,
                getMessages(), generatingTime, status);
    }


    public String getMessages(){
        StringBuilder stringBuilder = new StringBuilder();
        for(Message message : messages){
            stringBuilder.append("\n").append(message.getContent());
        }
        if(stringBuilder.isEmpty()) stringBuilder.append("no messages");
        return stringBuilder.toString();
    }
    public List<Message> getListOfMessage(){
        return messages;
    }

    public int getId() {
        return id;
    }
}
