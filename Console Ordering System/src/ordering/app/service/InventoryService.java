package ordering.app.service;

import ordering.app.model.Cookie;
import ordering.app.model.Drink;
import ordering.app.model.Food;
import ordering.app.model.Product;

import java.util.ArrayList;
import java.util.Comparator;


public class InventoryService {

    public static void initializeList(ArrayList<Product> list){
        list.add(new Cookie("P010", 9.49, 15, 80, "Red Velvet"));
        list.add(new Cookie("P011", 8.99, 9, 80, "Dark Chocolate"));
        list.add(new Cookie("P012", 9.29, 6, 80, "Matcha Macadamia"));

        // Drinks: (id, price, stockQuantity, flavor, ml)
        list.add(new Drink("P020", 7.99, 14, "Classic Black", 250));
        list.add(new Drink("P021", 8.49, 11, "Vanilla", 300));
        list.add(new Drink("P022", 8.99, 18, "Wintermelon", 350));

        // Food: (id, price, stockQuantity, foodName, hotness)
        list.add(new Food("P030", 24.9, 10, "Chicken Adobo", "Savory"));
        list.add(new Food("P031", 27.9, 7, "Pork Sisig", "Spicy"));
        list.add(new Food("P032", 19.9, 20, "Tuna Sandwich", "Mild"));

    }

    public static void displayList(ArrayList<Product> list){
        System.out.println("\nPRODUCTS: ");
        for(Product product : list){
            System.out.println(product);
        }
    }

    public static Product findProductFromList(ArrayList<Product> list, String id){
        for(Product product: list){
            if(id.equals(product.getId())){
                return product;
            }
        }

        return null;
    }

    public static void addProduct(ArrayList<Product> list, Product product){
        list.add(product);
        sortById(list);
        displayList(list);

        System.out.println("Product Added!");
    }

    public static void removeProduct(ArrayList<Product> list, Product removeId){
        boolean remove = list.remove(removeId);

        if(!remove){
            System.out.println("Product not found!");
        }
    }

    public static void sortById(ArrayList<Product> list){
        list.sort(Comparator.comparing(Product::getId));
    }

    public static void removeOutOfStock(ArrayList<Product> list){
            list.removeIf(p -> p.getStockQuantity() == 0);

    }



}
