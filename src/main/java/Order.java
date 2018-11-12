import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class Order {
    private static int id;
    private Basket basket;
    private User user;
    private Date date;
    private Date orderPayAt;


    public Order() {
        date = new Date();
        this.basket = new Basket();
    }

    public Basket getBasket() {
        return basket;
    }

    public Boolean pay() {
        BigDecimal cost = new BigDecimal("0.00");
        Iterator iterator = basket.getIterator();

        while(iterator.hasNext()) {
            Product currProduct = (Product)iterator.next();
            cost = BigDecimal.valueOf(currProduct.getAmount() * currProduct.getPrice().doubleValue());
        }


        System.out.println("Your order cost: " + cost);
        System.out.println("13 1140 2004 0000 3702 7657 6565, send it and then we will send you your order.");
        return true;
    }

    public void addToBasket(Product product, int amount) {
        basket.addProduct(product, amount);
    }

    public void editProductQuantity(String name, int amount) {
        Iterator iterator = getBasketIterator();
        while(iterator.hasNext()){
            Product product= (Product) iterator.next();
            if(product.getName().equalsIgnoreCase(name)){
                basket.editProductQuantity(product, amount);
                return;
            }
        }
        System.out.println("No such product: " + name);
    }

    public Iterator getBasketIterator() {
        return basket.getIterator();
    }

    public void removeProduct(Product product) {
        basket.deleteProduct(product);
    }



}
