import java.util.*;

public abstract class FeaturedCategory {

    private String name;
    private int id;
    private Map<Integer, Product> categoryMap;

    public FeaturedCategory(int id, String name){
        this.id = id;
        this.name = name;
        this.categoryMap = new HashMap<>();
    }
    public String toString(){
        return id + " " + name;
    }
}
