import java.util.Random;

public class Lottery {
    private final int[] items = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    private final Random random = new Random();

    private int chooseRandom() {
        int randomIndex = random.nextInt(items.length);
        return items[randomIndex];
    }

    protected boolean lottery(int num) {
        int choice = this.chooseRandom();
        return choice <= num;
    }
}
