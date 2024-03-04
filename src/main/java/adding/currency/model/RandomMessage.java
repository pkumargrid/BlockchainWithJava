package adding.currency.model;

import java.util.Collections;
import java.util.List;

public class RandomMessage{

    private int currentIndex;
    private List<String> listOfMessage;

    public List<String> getListOfMessage() {
        return listOfMessage;
    }

    public void setListOfMessage(List<String> listOfMessage) {
        this.currentIndex = 0;
        this.listOfMessage = listOfMessage;
    }

    public String getRandomName() {
        Collections.shuffle(listOfMessage);
        if(currentIndex < listOfMessage.size()){
            return listOfMessage.get(currentIndex++);
        }
        throw new ArrayIndexOutOfBoundsException("No random name available");
    }
}
