import java.math.BigDecimal;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;
    private int amount;
    private boolean isAvailable;
    private String category;

    public Product(int id, String name, BigDecimal price, int amount, String category) {
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

    public String getCategory() {
        return category;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String toString(){
        return "Name: " + name + " |  Price: " + price.toString()+ " |  Amount: " + amount + " |  Available: " + isAvailable + " |  Category: " + category;
    }


}