import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ToysAdmin test = new ToysAdmin();
        test.importCSVtoArray();
        test.addToyFinal();

    }
}
