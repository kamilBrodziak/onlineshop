import java.util.ArrayList;
import java.util.List;

public class Category extends  FeaturedCategory{

    private int id;
    private String name;
    private boolean isAvailable;
    private ArrayList<Product> productList;

    public Category(int id, String name, boolean isAvailable){
        super (id, name, isAvailable);
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
        this.productList = new ArrayList<Product>();
    }
}
