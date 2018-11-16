import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;

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

    public BigDecimal pay() {
        BigDecimal cost = new BigDecimal("0.00");
        Iterator iterator = basket.getIterator();

        while(iterator.hasNext()) {
            Product currProduct = (Product)iterator.next();
            cost = cost.add(BigDecimal.valueOf(Math.round(((double)basket.getAmount(currProduct)) * currProduct.getPrice().doubleValue() * 100d) / 100d));
        }

        return cost.setScale(1);
    }

    public boolean addToBasket(String[] productParam) {
        if(!productParam[1].matches("[0-9]*")) {
            System.out.println("Invalid amount");
            return false;
        }

        if(dao.getProduct(productParam[0]) != null) {
            basket.addProduct(dao.getProduct(productParam[0]), Integer.parseInt(productParam[1]));
            return true;
        } else {
            System.out.println("There is no such product.");
        }
        return false;
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
            }
        }
    }

    public boolean changeProductQuantinty(String[] productParam) {
        if(!productParam[1].matches("[0-9]*")) {
            System.out.println("Invalid amount");
            return false;
        }

        Iterator iterator = getBasketIterator();
        while(iterator.hasNext()){
            Product product= (Product) iterator.next();
            if(product.getName().equalsIgnoreCase(productParam[0])){
                basket.editProductQuantity(product, Integer.parseInt(productParam[1]));
                return true;
            }
        }
        System.out.println("No such product: " + productParam[0]);
        return false;
    }

}
