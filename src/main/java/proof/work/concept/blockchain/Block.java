package proof.work.concept.blockchain;



import proof.work.concept.magicHandler.MagicGenerator;

import java.time.LocalTime;
import java.util.Date;

public class Block {
    private String hash;
    private final String prevHash;

    private final long timeStamp;

    private final int id;

    private int magic;

    private int generatingTime;
    public Block(String prevHash, int id, int numberOfZeros){
        this.prevHash = prevHash;
        this.id = id;
        this.timeStamp = new Date().getTime();
        makeHash(numberOfZeros);
    }

    public String getHash(){
        return this.hash;
    }

    public void makeHash(int numberOfZeros){
        LocalTime start = LocalTime.now();
        MagicGenerator.HashMagic hashMagic =  MagicGenerator.generate(  id + prevHash + timeStamp, numberOfZeros, 0);
        LocalTime end = LocalTime.now();
        generatingTime = end.toSecondOfDay() - start.toSecondOfDay();
        hash = hashMagic.hash();
        magic = hashMagic.magic();
    }

    public String getPrevHash() {
        return prevHash;
    }
    @Override
    public String toString(){
        return """
               Block:
               Id: %d
               Timestamp: %d
               Magic number: %d
               Hash of the previous block:
               %s
               Hash of the block:
               %s
               Block ws generating for %d seconds
               """.formatted(id + 1, timeStamp, magic, getPrevHash(), getHash(), generatingTime);
    }
}
