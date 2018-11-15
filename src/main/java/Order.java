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
    private DAO dao;

    public Order() {
        date = new Date();
        this.basket = new Basket();
        dao = new DAO();
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

    public void addToBasket(String[] productParam) {
        if(!productParam[1].matches("[0-9]*")) {
            System.out.println("Invalid amount");
            return;
        }

        if(dao.getProduct(productParam[0]) != null) {
            basket.addProduct(dao.getProduct(productParam[0]), Integer.parseInt(productParam[1]));
        } else {
            System.out.println("There is no such product.");
        }
    }


    public Iterator getBasketIterator() {
        return basket.getIterator();
    }

    public void deleteProduct(String choice) {
        Iterator iterator = getBasketIterator();
        while(iterator.hasNext()) {
            Product product = (Product)iterator.next();
            if (product.getName().equalsIgnoreCase(choice)) {
                basket.deleteProduct(product);
                break;
            } else {
                iterator.next();
            }
        }

    }

    public void changeProductQuantinty(String[] productParam) {
        if(!productParam[1].matches("[0-9]*")) {
            System.out.println("Invalid amount");
            return;
        }

        Iterator iterator = getBasketIterator();
        while(iterator.hasNext()){
            Product product= (Product) iterator.next();
            if(product.getName().equalsIgnoreCase(productParam[0])){
                basket.editProductQuantity(product, Integer.parseInt(productParam[1]));
                return;
            }
        }
        System.out.println("No such product: " + productParam[0]);

    }

}
