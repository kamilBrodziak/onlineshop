import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class FeaturedCategory {

    private String name;
    private boolean isAvailable;
    private int id;
    private ArrayList<Product> productList;

    public FeaturedCategory(int id, String name, boolean isAvailable){
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
        this.productList = new ArrayList<Product>();
    }

    public List<Product> GetProductList(){
        return productList;
    }

    public String toString(){
        return id + " " + name;
    }
}
