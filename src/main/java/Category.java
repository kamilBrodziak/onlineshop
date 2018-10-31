import java.util.ArrayList;
import java.util.List;

public class Category extends  FeaturedCategory{

    private int id;
    private String name;
    private ArrayList<Product> productList;

    public Category(int id, String name){
        super (id, name, isAvailable);
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
        this.productList = new ArrayList<Product>();
    }

    public void addToCategory(Product product) {
        productList.add(product);
    }
}
