import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ToysAdmin {
    private final String pathToysCSV = "Toys.csv";
    private ArrayList<Toy> toysList;

    protected void readToys() throws IOException {
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
        }
    }
    protected Toy addToy(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите id:");
        int id = sc.nextInt();
        System.out.println("Введите id:");
        String name = sc.nextLine();
        System.out.println("Введите количество:");
        int quantity = sc.nextInt();
        System.out.println("Введите вес:");
        int chanceVal = sc.nextInt();
        sc.close();
        return new Toy(id, name, quantity, chanceVal);
    }
    protected void addToyToList(Toy toy){
        toysList.add(toy);
    }
    protected void addToyToCSV(Toy toy) {
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(pathToysCSV);
            csvWriter.append(Integer.toString(toy.getId_num()));
            csvWriter.append(';');
            csvWriter.append(toy.getName());
            csvWriter.append(';');
            csvWriter.append(Integer.toString(toy.getQuantity()));
            csvWriter.append(';');
            csvWriter.append(Integer.toString(toy.getChanceVal()));
            csvWriter.append('\n');

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
