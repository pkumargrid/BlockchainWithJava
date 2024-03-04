package adding.currency.hash.generator;

import java.security.*;

public class RSAKeyGenerator {

    private static RSAKeyGenerator instance;

    private RSAKeyGenerator() {

    }

    public static class KeyPair{
        public PrivateKey privateKey;
        public PublicKey publicKey;

        KeyPair(PrivateKey privateKey, PublicKey publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }
    }

    public synchronized KeyPair generate(){
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            java.security.KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            return new KeyPair(privateKey, publicKey);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Algorithm not supported: " + e.getMessage());
        }
        return null;
    }

    public synchronized static RSAKeyGenerator getInstance() {
        if(instance == null){
            instance = new RSAKeyGenerator();
        }
        return instance;
    }
}
