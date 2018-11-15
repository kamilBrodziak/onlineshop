import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int amount;
    private boolean isAvailable;
    private int category;

    public Product(int id, String name, BigDecimal price, int amount, int category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }


    @Override
    public boolean equals(Object obj) {
        return this.id == ((Product)obj).getId();
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}