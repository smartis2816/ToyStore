import java.util.StringJoiner;

public class Toy {
    private int id_num;
    private String name;
    private int quantity;
    private int chanceVal;

    public Toy(int id_num, String name, int quantity, int chanceVal) {
        this.id_num = id_num;
        this.name = name;
        this.quantity = quantity;
        this.chanceVal = chanceVal;
    }

    public String getInfo() {
        return new StringJoiner(", ", this.getClass().getSimpleName() + " [", "]")
                .add("id = " + id_num)
                .add("name = " + name)
                .add("quantity = " + quantity)
                .add("value of chance = " + chanceVal)
                .toString();
    }

    @Override
    public String toString() {
        return this.getInfo();
    }

    public int getChanceVal() {
        return chanceVal;
    }

    public void setChanceVal(int chanceVal) {
        this.chanceVal = chanceVal;
    }

    public int getId_num() {
        return id_num;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
