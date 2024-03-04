package adding.security.message.handler;


import adding.currency.hash.generator.RSAKeyGenerator;
import adding.security.verification.SignMessage;

import java.security.PublicKey;

public class MessageDecorator implements Message{
    private final Message message;
    private final byte[] signature;
    private final PublicKey publicKey;
    protected final int UNIQUE_IDENTIFIER;

    public MessageDecorator(Message message, int uniqueIdentifier) throws Exception {
        this.message = message;
        if(message != null){
            RSAKeyGenerator.KeyPair keyPair = RSAKeyGenerator.getInstance().generate();
            this.signature = SignMessage.sign(message.getContent(), keyPair.privateKey);
            this.publicKey = keyPair.publicKey;
        }
        else{
            this.signature = null;
            this.publicKey = null;
        }
        this.UNIQUE_IDENTIFIER = uniqueIdentifier;
    }

    @Override
    public String getContent() {
        return message.getContent();
    }

    public byte[] getSignature() {
        return signature;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public int getUniqueIdentifier() {
        return UNIQUE_IDENTIFIER;
    }
}
