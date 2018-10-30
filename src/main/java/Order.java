import java.util.Date;
import java.util.Iterator;

public class Order {
    private static int id;
    private Basket basket;
    private User user;
    private Date date;
    private OrderStatus orderStatus;
    private Date orderPayAt;


    public Order() {
        orderStatus = ;
        date = new Date().
        basket = new Basket();
    }

    public Boolean pay() {
        Double cost = 0d;
        Iterator iterator = basket.getIterator();

        while(iterator.hasNext()) {
            Product currProduct = iterator.next();
            cost += currProduct.getPrice() * basket.getAmount(currProduct);
        }

        ...
    }




}
