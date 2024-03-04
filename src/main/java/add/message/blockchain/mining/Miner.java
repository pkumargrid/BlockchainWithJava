package add.message.blockchain.mining;

import add.message.blockchain.blockchain.BlockChain;
import add.message.blockchain.constants.Block;
import add.message.blockchain.exceptions.ValidationException;

public class Miner implements Runnable {

    private final int minerId;

    public Miner(int minerId) {
        this.minerId = minerId;
    }

    public void mining(){
        try{
            BlockChain blockChain = BlockChain.getInstance(Block.CAPACITY.value);
            blockChain.addBlock(minerId);
        } catch (ValidationException e){
//            System.out.println("Miner: " + minerId + " failed validation");
//            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
       mining();
    }
}
