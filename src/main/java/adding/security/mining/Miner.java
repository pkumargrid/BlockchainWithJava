package adding.security.mining;



import adding.security.blockchain.BlockChain;
import adding.security.constants.Block;
import adding.security.exceptions.ValidationException;

import java.util.Arrays;

public class Miner implements Runnable {

    private final int minerId;

    public Miner(int minerId) {
        this.minerId = minerId;
    }

    public void mining(){
        try{
            BlockChain blockChain = BlockChain.getInstance(Block.CAPACITY.value);
            blockChain.addBlock(minerId);
        } catch (ValidationException ignored){
        } catch (Exception e){
            System.out.println("Exception occurred: " + e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void run() {
       mining();
    }
}
