import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Category extends  FeaturedCategory{

    private int id;
    private String name;

    private Map<Product, Integer> productMap;

    public Category(int id, String name){
        super (id, name);
        this.id = id;
        this.name = name;
        this.productMap = new HashMap<>();
    }

    public void addToCategory(Product product) {
        productMap.put(product, product.getId());
    }

    public String getName() {
        return name;
    }

    public Map<Product, Integer> getProductMap() {
        return productMap;
    }

    public int getId() {
        return id;
    }

    public Product getProductById(int id) {
        for(Product product: productMap.keySet()) {
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
