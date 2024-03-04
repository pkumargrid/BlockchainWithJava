package miner.mania.blockchain;


import miner.mania.magic.handler.MagicGenerator;
import java.util.Date;

public class Block {
    private volatile String hash;
    private final String prevHash;

    private final long timeStamp;

    private final int id;

    private volatile int magic;

    private volatile int minerId;

    private volatile String status;

    private volatile boolean making;
    
    public synchronized void setStatus(int currentTime) {
        BlockChain blockChain = BlockChain.getInstance(miner.mania.constants.Block.CAPACITY.value);
        if(currentTime > 6){
            status = "N was decreased by 1";
            blockChain.decreaseNumberOfZeros();
        }
        else if(currentTime < 2){
            blockChain.increaseNumberOfZeros();
            status = "N was increased to " + blockChain.getNumberOfZeros();
        }
        else{
            status = "N stays the same";
        }
    }

    private volatile int generatingTime;
    public Block(String prevHash, int id){
        this.prevHash = prevHash;
        this.id = id;
        this.timeStamp = new Date().getTime();
        createHash();
    }

    public String getHash(){
        return this.hash;
    }

    public synchronized void createHash(){
        BlockChain blockChain = BlockChain.getInstance(miner.mania.constants.Block.CAPACITY.value);
        MagicGenerator.HashMagic hashMagic =  MagicGenerator.generate(  id + prevHash + timeStamp, blockChain.getNumberOfZeros(), 0);
        setMagic(hashMagic.magic());
        setHash(hashMagic.hash());
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
               Created by miner # %d
               Id: %d
               Timestamp: %d
               Magic number: %d
               Hash of the previous block:
               %s
               Hash of the block:
               %s
               Block was generating for %d seconds
               %s
               """.formatted(minerId, id + 1, timeStamp, magic, prevHash, hash, generatingTime, status);
    }

    public synchronized void setMinerId(int minerId) {
        this.minerId = minerId;
    }

    public int getId() {
        return id;
    }

    public synchronized void setMaking(boolean making) {
        this.making = making;
    }

    public synchronized boolean getMaking(){
        return making;
    }
}
