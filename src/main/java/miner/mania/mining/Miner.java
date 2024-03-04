package miner.mania.mining;


import miner.mania.blockchain.BlockChain;
import miner.mania.constants.Block;
import miner.mania.exceptions.ValidationException;

public class Miner implements Runnable{

    private final int minerId;

    public Miner(int minerId) {
        this.minerId = minerId;
    }

    public void mining(){
        try{
            BlockChain blockChain = BlockChain.getInstance(Block.CAPACITY.value);
            blockChain.addBlock(minerId);
        } catch (ValidationException ignored){
        }
    }

    @Override
    public void run() {
         mining();
    }
}
