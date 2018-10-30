import java.util.*;

public class Basket {
    private Iterator iterator;
    private Map<Product, Integer> products;

    public Basket() {
        products = new HashMap<Product, Integer>();
        iterator = new ProductIterator(products);
    }

    public Iterator getIterator() {
        return new ProductIterator(products);
    }

    public void addProduct(Product product, int amount) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + amount);
            return;
        }
        products.put(product, amount);
    }

    public void editProductQuantity(Product product, int amount) {
        if (products.containsKey(product)) {
            products.put(product, amount);
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
        Set<Product> products;

        ProductIterator(Map<Product, Integer> products) {
            i = 0;
            this.products = products.keySet();
        }

        public boolean hasNext() {
            return i < products.size();
        }

        public Product next() {
            int j = 0;
            for(Product product: products) {
                if(i == j) {
                    return product;
                }

                j++;
            }
            return null;
        }

    }
}
