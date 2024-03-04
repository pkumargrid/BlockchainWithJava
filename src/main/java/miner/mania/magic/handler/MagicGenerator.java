package miner.mania.magic.handler;


import miner.mania.generator.HashGenerator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MagicGenerator {

    public record HashMagic(String hash, int magic) {
    }

    public static HashMagic generate(String input, int numberOfZeros, int magic){
        String toCheck = Stream.generate(() -> "0").limit(numberOfZeros).collect(Collectors.joining());
        String hexString = HashGenerator.applySha256(input + magic);
        while(!(hexString.toString().startsWith(toCheck))){
            magic++;
            hexString = HashGenerator.applySha256(input + magic);
        }
        return new HashMagic(hexString.toString(), magic);
    }

}
