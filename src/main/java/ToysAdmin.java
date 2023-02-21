import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ToysAdmin {
    private final String pathToysCSV = "Toys.csv";
    private ArrayList<Toy> toysList;

    protected void randomChanceVal (ArrayList<Toy> toysList){
        Random r = new Random();
        for (Toy toy:toysList) {
            toy.setChanceVal(r.nextInt(1, 10));
        }
    }

    protected void importCSVtoArray() throws IOException {
        toysList = new ArrayList<>();
        File csvFile = new File(pathToysCSV);
        if (csvFile.isFile()) {
            BufferedReader csvReader = new BufferedReader(new FileReader(pathToysCSV));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(";");
                toysList.add(new Toy(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
            }
            csvReader.close();
            for (Toy toy:toysList) {
                System.out.println(toy);
            }
            //System.out.println(toysList);
        }
    }

    private Toy addToyInstance() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя:");
        String name = sc.nextLine();
        System.out.println("Введите id:");
        int id = sc.nextInt();
        System.out.println("Введите количество:");
        int quantity = sc.nextInt();
        System.out.println("Введите вес:");
        int chanceVal = sc.nextInt();
        sc.close();
        return new Toy(id, name, quantity, chanceVal);
    }

    private void addToyToList(Toy toy) {
        toysList.add(toy);
    }

    private void addToyToCSV(Toy toy) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(toy.getId_num()));
        sb.append(';');
        sb.append(toy.getName());
        sb.append(';');
        sb.append(Integer.toString(toy.getQuantity()));
        sb.append(';');
        sb.append(Integer.toString(toy.getChanceVal()));
        sb.append('\n');
        try (Writer writer = Files.newBufferedWriter(Paths.get(pathToysCSV), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.append(sb.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected void addToyFinal() {
        Toy newToy = this.addToyInstance();
        this.addToyToCSV(newToy);
        this.addToyToList(newToy);
    }


}
