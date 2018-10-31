import java.util.*;

public class ProductController {
    private Map<Integer, Category> categoryMap;

    public ProductController() {
        categoryMap = new HashMap<Integer, Category>();
        DAO dao = new DAO();
        categoryMap = dao.getCategories();
    }

    public List<Category> getCategoryList() {
        return categoryMap;
    }

    public Map<Product, Integer> getProduct() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Categories:\n\tid\t\tname");
        for(int id: categoryMap.keySet()) {
            System.out.println("\t" + id + "\t\t" + categoryMap.get(id).getName());
        }

        System.out.println("Which category you choose(choose id)?");

        int idC = 5;
        Category chosenCategory;
        while(scanner.hasNextLine()) {
            String chosenCategoryId = scanner.nextLine();
            if(chosenCategoryId.matches("[1-9]") && categoryMap.containsKey(chosenCategoryId)) {
                idC = Integer.parseInt(scanner.nextLine());
                break;
            } else {
                System.out.println("Invalid category id.");
            }
        }

        chosenCategory = categoryMap.get(idC);
        System.out.println("Products in category "  + chosenCategory.getName() + ":\n\tid\t\tname");
        for(Product product: chosenCategoryId.getProductList()) {
            System.out.println("\t" + product.getId() + "\t\t" + product.getName());
        }
        System.out.println("Invalid category id.");

        int idP = -1;
        while(idP == -1) {
            String chosenCategoryId = "";
            if (scanner.hasNextLine()) {
                chosenCategoryId = scanner.nextLine();
            }
            if(chosenCategoryId.matches("[0-9]") && categoryMap.containsKey(chosenCategoryId)) {
                idP = Integer.parseInt(scanner.nextLine());
                break;
            } else {
                System.out.println("Invalid product id.");
            }
        }

        int amount = -1;
        Product product = chosenCategory.getProductList.get(idP);

        while(true) {
            String chosenAmount = "";
            System.out.println("Provide amount of " + product.getAmount());
            if (scanner.hasNextLine()) {
                chosenAmount = scanner.nextLine();
            }
            if(chosenAmount.matches("[0-9]") ) {
                amount = Integer.parseInt(chosenAmount);
                if(amount < product.getAmount()) {
                    break;
                } else {
                    System.out.println("That amount of products is not available.");
                }
            } else {
                System.out.println("Wrong amount!");
            }
        }

        Map<Product, Integer> productToBasket = new HashMap<Product, Integer>();
        productToBasket.put(product, amount);


    }

}
