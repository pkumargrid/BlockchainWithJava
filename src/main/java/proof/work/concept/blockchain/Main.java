package proof.work.concept.blockchain;



import proof.work.concept.exceptions.ValidationException;
import proof.work.concept.constants.Block;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
            int numberOfZeros = scanner.nextInt();
            BlockChain blockChain = new BlockChain(Block.CAPACITY.value);
            for(int i = 0; i < Block.CAPACITY.value; i++){
                blockChain.addBlock(numberOfZeros);
            }
            System.out.println(blockChain);
        } catch (ValidationException e){
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}
