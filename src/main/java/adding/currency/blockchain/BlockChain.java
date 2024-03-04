package adding.currency.blockchain;

import adding.currency.exceptions.ValidationException;
import adding.currency.person.Person;
import adding.currency.transaction.TransactionFacade;
import adding.currency.verification.BlockVerification;
import adding.currency.message.handler.MessageFacade;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockChain {

    private final List<Block> blocks;
    private static int Id;

    private volatile static BlockChain instance;

    private volatile int numberOfZeros;
    public synchronized int getNumberOfZeros() {
        return numberOfZeros;
    }

    public synchronized void increaseNumberOfZeros() {
        this.numberOfZeros += 1;
    }

    public synchronized void decreaseNumberOfZeros() {
        this.numberOfZeros -= 1;
    }

    private BlockChain(int numberOfBlocks){
        this.blocks = new ArrayList<>(numberOfBlocks);
    }


    public void makeTransaction(Person person) throws Exception {
        TransactionFacade transactionFacade = TransactionFacade.getInstance();
        Random random = new Random();
        int cnt = random.nextInt(5);
        while(cnt-- > 0){
            transactionFacade.makeTransaction(person);
        }
    }
    public void addBlock(Person person) throws Exception {
        LocalTime start = LocalTime.now();
        while(getBlockChainSize() < adding.currency.constants.Block.CAPACITY.value){
            Block block = null;
            if (Id == 0) {
                block = new Block("0", Id);
            } else {
                block = new Block(this.blocks.get(Id - 1).getHash(), Id);
            }
            try {
                BlockVerification.valid(Id, block, this.blocks);
                synchronized (this) {
                    if(Id == block.getId()) {
                        makeTransaction(person);
                        if(Id != 0)
                            filling(block);
                        LocalTime end = LocalTime.now();
                        long duration = Duration.between(start, end).getSeconds();
                        start = LocalTime.now();
                        block.setPerson(person);
                        block.setStatus((int)duration);
                        block.setGeneratingTime((int)duration);
                        this.blocks.add(block);
                        increaseId();
                        this.wait(10);
                    }
                }
            } catch (ValidationException ignored){

            }
        }
    }

    public void filling(Block block) throws Exception {
        MessageFacade messageFacade = MessageFacade.getInstance();
        block.setMessages(messageFacade.receiveMessages());
    }

    public void increaseId() {
        Id++;
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int id = 0; id < Id; id++){
            stringBuilder.append(this.blocks.get(id));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static synchronized BlockChain getInstance(int numberOfBlocks) {
        if (instance == null){
            instance = new BlockChain(numberOfBlocks);
        }
        return instance;
    }

    public synchronized int getBlockChainSize(){
        return this.blocks.size();
    }
}
