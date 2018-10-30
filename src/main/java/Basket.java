import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Basket {
    private static int id = 0;
    private Iterator iterator = new ProductIterator();
    Map<Product, Integer> products;

    public Basket() {
        id++;
        products = new HashMap<Product, Integer>();
    }

    public Iterator getIterator() {
        return new ProductIterator();
    }

    public void addProduct(Product product, int amount) {
        if (products.containsKey(product)) {
            products.put(products.get(product) + amount);
            return;
        }
        products.put(amount);
    }

    public void editProductQuantity(Product product, int amount) {
        if (products.containsKey(product)) {
            products.put(amount);
        }
    }

    public void deleteProduct(Product product) {
        if (products.containsKey(product)) {
            products.remove(product);
        }
    }

    public int getAmount(Product product) {
        return products.get(product);
    }

    private class ProductIterator implements Iterator {
        private int i;

        ProductIterator() {
            i = 0;
        }

        public boolean hasNext() {
            return i < products.size();
        }

        public Object next() {
            return products.get(i++);
        }

    }
}
