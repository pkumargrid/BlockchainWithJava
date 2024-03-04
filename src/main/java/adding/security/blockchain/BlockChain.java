package adding.security.blockchain;

import adding.security.exceptions.ValidationException;
import adding.security.client.MessageFacade;
import adding.security.verification.BlockVerification;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public void addBlock(int minerId) throws Exception {
        LocalTime start = LocalTime.now();
        while(getBlockChainSize() < adding.security.constants.Block.CAPACITY.value){
            Block block = null;
            if (Id == 0) {
                block = new Block("0", Id);
            } else {
                block = new Block(this.blocks.get(Id - 1).getHash(), Id);
            }
            try {
                BlockVerification.valid(Id, block, this.blocks);
            } catch (ValidationException ignored){

            }
            synchronized (this) {
                if(Id == block.getId()) {
                    if(Id != 0)
                        filling(block);
                    LocalTime end = LocalTime.now();
                    long duration = Duration.between(start, end).getSeconds();
                    start = LocalTime.now();
                    block.setStatus((int)duration);
                    block.setMinerId(minerId);
                    block.setGeneratingTime((int)duration);
                    this.blocks.add(block);
                    increaseId();
                    this.wait(10);
                }
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
