package blockchain.essentials.blockchain;


import blockchain.essentials.exceptions.ValidationException;
import blockchain.essentials.constants.Block;

public class Main {
    public static void main(String[] args) {
        try{
            BlockChain blockChain = new BlockChain(Block.CAPACITY.value);
            for(int i = 0; i < Block.CAPACITY.value; i++){
                blockChain.addBlock();
            }
            System.out.println(blockChain);
        } catch (ValidationException e){
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
