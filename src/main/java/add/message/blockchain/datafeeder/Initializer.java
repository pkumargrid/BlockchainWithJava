package add.message.blockchain.datafeeder;


import add.message.blockchain.model.RandomMessage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Initializer {

    public static final String PATH = System.getProperty("user.dir") + "/src/datafeeder/dummy.txt";
    public static final String TEST_PATH = System.getProperty("user.dir") + "/Blockchain with Java/task/src/datafeeder/dummy.txt";
    public static RandomMessage initialize() throws FileNotFoundException {
        List<String> stringList = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(PATH))){
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
