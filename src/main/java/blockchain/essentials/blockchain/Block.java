package blockchain.essentials.blockchain;


import blockchain.essentials.generator.HashGenerator;

import java.util.Date;

public class Block {
    private String hash;
    private final String prevHash;

    private final long timeStamp;

    private final int id;

    public Block(String prevHash, int id){
        this.prevHash = prevHash;
        this.id = id;
        this.timeStamp = new Date().getTime();
        makeHash();
    }

    public String getHash(){
        return this.hash;
    }

    public void makeHash(){
        this.hash = HashGenerator.applySha256(id + prevHash + timeStamp);
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
               Hash of the previous block:
               %s
               Hash of the block:
               %s
               """.formatted(id + 1, timeStamp, getPrevHash(), getHash());
    }
}
