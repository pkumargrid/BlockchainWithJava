package proof.work.concept.blockchain;


import proof.work.concept.exceptions.ValidationException;

public class BlockChain {

    Block[] blocks;
    private static int Id;

    BlockChain(int numberOfBlocks){
        this.blocks = new Block[numberOfBlocks];
    }
    public void addBlock(int numberOfZeros) throws ValidationException {
        if(Id == 0){
            this.blocks[Id] = new Block("0", Id, numberOfZeros);
        }
        else{
            this.blocks[Id] = new Block(this.blocks[Id-1].getHash(), Id, numberOfZeros);
        }
        valid();
        Id++;
    }

    public void valid() throws ValidationException {
        if(Id == 0){
            return;
        }
        if(!(this.blocks[Id].getPrevHash().equals(this.blocks[Id-1].getHash()))){
            throw new ValidationException("ERROR");
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int id = 0; id < Id; id++){
            stringBuilder.append(this.blocks[id]);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
