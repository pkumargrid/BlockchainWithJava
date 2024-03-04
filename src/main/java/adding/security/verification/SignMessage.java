package adding.security.verification;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Signature;


public class SignMessage {

    private SignMessage() {
    }

    public synchronized static byte[] sign(String data, PrivateKey privateKey) throws InvalidKeyException, IOException, Exception {
        Signature rsa = Signature.getInstance("SHA1withRSA");
        rsa.initSign(privateKey);
        rsa.update(data.getBytes());
        byte[] sign = rsa.sign();
        return sign;
    }

}
