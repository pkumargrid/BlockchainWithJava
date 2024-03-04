package adding.currency.constants;

public enum ClientName {
    PATH(System.getProperty("user.dir") + "/src/datafeeder/clients.txt"),
    TEST_PATH(System.getProperty("user.dir") + "/Blockchain with Java/task/src/datafeeder/clients.txt");

    public final String value;

    ClientName(String value){
        this.value = value;
    }
}
