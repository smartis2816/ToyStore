import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ToysAdmin test = new ToysAdmin();
        Toy toy1 = new Toy(11, "test", 50, 10);
        test.addToyToCSV(toy1);
    }
}
