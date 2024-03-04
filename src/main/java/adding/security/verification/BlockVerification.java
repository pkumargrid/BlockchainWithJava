package adding.security.verification;


import adding.security.blockchain.Block;
import adding.security.exceptions.ValidationException;
import adding.security.message.handler.Message;
import adding.security.message.handler.MessageDecorator;

import java.util.List;

public class BlockVerification {
    public synchronized static void valid(int Id, Block block, List<Block> blocks) throws Exception {
        if(Id > 0) {
            if(!(block.getPrevHash().equals(blocks.get(Id-1).getHash()))){
                throw new ValidationException("Validation Error");
            }
        }

        List<Message> blockMessages = block.getListOfMessage();
        for(int index = 0; index < blockMessages.size(); index++){
            Message currentMessage = getMessage(blockMessages, index);
            if(currentMessage instanceof MessageDecorator curr){
                if(!VerifyMessage.verify(currentMessage.getContent().getBytes(),
                        curr.getSignature(), curr.getPublicKey())){
                    throw new ValidationException("Validation Error");
                }
            }
        }
    }

    private synchronized static Message getMessage(List<Message> blockMessages, int index) throws ValidationException {
        Message currentMessage = blockMessages.get(index);
        if(!(currentMessage instanceof MessageDecorator)){
            throw new ValidationException("Validation Error");
        }
        if(index > 0) {
            Message prevMessage = blockMessages.get(index -1);
            if(!(prevMessage instanceof MessageDecorator)){
                throw new ValidationException("Verification Error");
            }
            if(((MessageDecorator) currentMessage).getUniqueIdentifier()
                    <= ((MessageDecorator) prevMessage).getUniqueIdentifier()){
                throw new ValidationException("Validation Error");
            }
        }
        return currentMessage;
    }
}
