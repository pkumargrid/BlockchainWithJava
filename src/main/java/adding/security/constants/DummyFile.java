package constants;

public enum DummyFile {
    PATH(System.getProperty("user.dir") + "/src/datafeeder/dummy.txt"),
    TEST_PATH(System.getProperty("user.dir") + "/Blockchain with Java/task/src/datafeeder/dummy.txt");

    public final String value;

    DummyFile(String value){
        this.value = value;
    }
}
