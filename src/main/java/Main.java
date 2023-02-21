public class Main {
    public static void main(String[] args) {
        ToysAdmin admin = new ToysAdmin();
        admin.importCSVtoArray();
        admin.addToyFinal();
        admin.run();
    }
}
