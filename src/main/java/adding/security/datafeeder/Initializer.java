package adding.security.datafeeder;


import adding.security.model.RandomMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Initializer {

    public static RandomMessage initialize() throws FileNotFoundException {
        List<String> stringList = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(constants.DummyFile.PATH.value))){
            String input;
            while(scanner.hasNext()){
                input = scanner.nextLine();
                stringList.add(input);
            }
            RandomMessage randomMessage = new RandomMessage();
            randomMessage.setListOfMessage(stringList);
            return randomMessage;
        }
    }

}
