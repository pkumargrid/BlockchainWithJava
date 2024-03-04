package adding.security.magic.generator;



import adding.security.hash.generator.HashGenerator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MagicGenerator {

    public record HashMagic(String hash, int magic) {
    }

    public synchronized static HashMagic generate(String input, int numberOfZeros, int magic){
        String toCheck = Stream.generate(() -> "0").limit(numberOfZeros).collect(Collectors.joining());
        String hexString = HashGenerator.applySha256(input + magic);
        while(!(hexString.startsWith(toCheck))){
            magic++;
            hexString = HashGenerator.applySha256(input + magic);
        }
        return new HashMagic(hexString, magic);
    }

}
