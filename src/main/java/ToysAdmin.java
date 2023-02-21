import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ToysAdmin {
    Queue<Toy> toysQueue = new ArrayDeque<>();
    private final int numberOfPrizes = 5;
    private final String pathPrizes = "Prizes.csv";
    private final String pathToysCSV = "Toys.csv";
    private ArrayList<Toy> toysList;

    protected void run() {
        randomChanceVal();
        for (int i = 1; i <= numberOfPrizes; i++) {
            Toy chosenToy = chooseToy();
            manageChosenToy(chosenToy);
        }
        System.out.println(toysQueue);
        writePrizes();
    }

    // Устанавливает рандомные знаечения от 1 до 9 в поле ChanceVal
    private void randomChanceVal() {
        Random r = new Random();
        for (Toy toy : toysList) {
            toy.setChanceVal(r.nextInt(1, 10));
        }
        importListToCSV();
    }

    // Выбирает игрушку из массива toysList
    protected Toy chooseToy() {
        Lottery lot = new Lottery();
        while (true) {
            for (Toy toy : toysList) {
                if (lot.lottery(toy.getChanceVal())) {
                    return toy;
                }
            }
        }
    }

    // Уменьшает количество у выбранной игрушки. Перезаписывает CSV. Добавляет в очередь. Выводит информацию на экран.
    protected void manageChosenToy(Toy toy) {
        toy.setQuantity(toy.getQuantity() - 1);
        String info = '\n' +
                "Выбрана игрушка: " +
                toy.getName() +
                ". " +
                "Таких игрушек осталось: " +
                toy.getQuantity() +
                '\n';
        System.out.println(info);
        if (toy.getQuantity() <= 0) {
            toysList.remove(toy);
        }
        toysQueue.add(toy);
        importListToCSV();
    }

    private String makeStringForCSV(Toy toy) {
        StringBuilder sb = new StringBuilder();
        sb.append(toy.getId_num());
        sb.append(';');
        sb.append(toy.getName());
        sb.append(';');
        sb.append(toy.getQuantity());
        sb.append(';');
        sb.append(toy.getChanceVal());
        sb.append('\n');
        return sb.toString();
    }

    // Записывает призы из очереди в файл
    protected void writePrizes() {
        final Path path = Paths.get(pathPrizes);
        while (!toysQueue.isEmpty()) {
            Toy toy = toysQueue.poll();
            String str = makeStringForCSV(toy);
            try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
                writer.append(str.toString());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    // Запись из массива toysList в CSV
    protected void importListToCSV() {
        String str1 = makeStringForCSV(toysList.get(0));
        Path path = Paths.get(pathToysCSV);
        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.append(str1.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
        for (int i = 1; i < toysList.size(); i++) {
            String str2 = makeStringForCSV(toysList.get(i));
            try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
                writer.append(str2.toString());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    // Импорт из CSV в toysList
    protected void importCSVtoArray() {
        toysList = new ArrayList<>();
        File csvFile = new File(pathToysCSV);
        if (csvFile.isFile()) {
            BufferedReader csvReader = null;
            try {
                csvReader = new BufferedReader(new FileReader(pathToysCSV));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String row;
            while (true) {
                try {
                    if ((row = csvReader.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String[] data = row.split(";");
                toysList.add(new Toy(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
            }
            try {
                csvReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (Toy toy : toysList) {
                System.out.println(toy);
            }
        }
    }

    // Добавляет экземпляр Toy с вводом полей с клавиатуры
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

    // Добавляет созданный экземпляр в массив
    private void addToyToList(Toy toy) {
        toysList.add(toy);
    }

    // Добавляет созданный экземпляр в CSV
    private void addToyToCSV(Toy toy) {
        String str = makeStringForCSV(toy);
        try (Writer writer = Files.newBufferedWriter(Paths.get(pathToysCSV), StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            writer.append(str.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Выполняет предыдущие 3 метода
    protected void addToyFinal() {
        Toy newToy = this.addToyInstance();
        this.addToyToCSV(newToy);
        this.addToyToList(newToy);
    }
}
