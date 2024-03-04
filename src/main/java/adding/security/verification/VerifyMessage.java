package adding.security.verification;

import java.security.PublicKey;
import java.security.Signature;

public class VerifyMessage {

    private VerifyMessage() {

    }

    @SuppressWarnings("unchecked")
    public synchronized static boolean verify(byte[] data, byte[] signature, PublicKey publicKey) throws Exception{
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(publicKey);
        sig.update(data);
        return sig.verify(signature);
    }
}
