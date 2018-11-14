import java.util.*;

public class Basket {
    private Iterator iterator;
    private Map<Product, Integer> products;

    public Basket() {
        products = new HashMap<>();
        iterator = new ProductIterator(products);
    }

    public Iterator getIterator() {
        return new ProductIterator(products);
    }

    public void addProduct(Product product, int amount) {
        if(amount >= 0 && product.getAmount() >= amount) {
            if (products.containsKey(product)) {
                products.put(product, products.get(product) + amount);
            } else {
                products.put(product, amount);
            }
            product.setAmount(product.getAmount() - amount);
        } else {
            System.out.println("Too low amount, you want " + amount + ", there is only " + product.getAmount() + " in stock");
        }

    }

    public void editProductQuantity(Product product, int amount) {
        if (products.containsKey(product)) {
            product.setAmount(product.getAmount() + products.get(product));
            if(amount > 0 && amount <= product.getAmount()) {
                products.put(product, amount);
                product.setAmount(product.getAmount() - amount);
            } else if (amount < 1) {
                deleteProduct(product);
            }
        }
    }

    public void deleteProduct(Product product) {
        if (products.containsKey(product)) {
            product.setAmount(product.getAmount() + products.get(product));
            products.remove(product);
        }
    }

    public int getAmount(Product product) {
        if(products.containsKey(product)) {
            return products.get(product);
        } else {
            return 0;
        }
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
                    i++;
                    return product;
                }

                j++;
            }
            return null;
        }

    }

}
