package ordering.app.controller;

import ordering.app.model.*;
import ordering.app.service.InputService;
import ordering.app.service.InventoryService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MenuController {

    public static void showProductMenu(Scanner scanner, ArrayList<Product> list){
        while(true){
            System.out.println("\nPRODUCTS");
            System.out.println("[1] Add Product");
            System.out.println("[2] Remove Product");
            System.out.println("[3] Show Products");
            System.out.println("[4] Back");

            int choice = InputService.getChoice(scanner, 1, 4);

            if(choice == 4){
                break;
            }else if(choice == 1){
                showAddProduct(scanner, list);
            }else if(choice == 2){
                showRemoveProduct(scanner, list);
            }else if(choice == 3){
                InventoryService.displayList(list);
            }
        }
    }

    public static void showAddProduct(Scanner scanner, ArrayList<Product> list){
        while(true){
            System.out.println("\n[1] COOKIE");
            System.out.println("[2] DRINK");
            System.out.println("[3] FOOD");
            System.out.print("ENTER PRODUCT TYPE or [4] BACK: ");

            int choice = InputService.getChoice(scanner, 1, 4);

            if(choice == 4){
                break;
            }

            System.out.print("Enter Product ID [P0**] : ");
            String id = scanner.next().toUpperCase();

            System.out.print("Enter Product Price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter Product Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1){
                System.out.print("Enter Cookie Flavor: ");
                String cookieFlavor = scanner.nextLine();

                System.out.print("Enter Cookie Weight in Grams: ");
                int cookieGrams = scanner.nextInt();

                Product product = new Cookie(id, price, quantity, cookieGrams, cookieFlavor);
                InventoryService.addProduct(list, product);
            }else if(choice == 2){
                System.out.print("Enter Drink Flavor: ");
                String drinkFlavor = scanner.nextLine();

                System.out.print("Enter Drink serving in ML: ");
                int drinkMl = scanner.nextInt();

                Product product = new Drink(id, price, quantity, drinkFlavor, drinkMl);
                InventoryService.addProduct(list, product);
            }else if(choice == 3){
                System.out.print("Enter Food Name: ");
                String foodName = scanner.nextLine();

                System.out.print("Enter Food hotness: ");
                String foodHotness = scanner.next();

                Product product = new Food(id, price, quantity, foodName, foodHotness);
                InventoryService.addProduct(list, product);
            }
        }
    }

    public static void showRemoveProduct(Scanner scanner, ArrayList<Product> list){
        while(true){
            InventoryService.displayList(list);
            System.out.print("\nEnter product ID to remove or [0] BACK: ");
            String input = scanner.next().toUpperCase();
            if(input.equals("0")){
                break;
            }else{
                  Product remove = InventoryService.findProductFromList(list, input);
                  if(remove != null){
                      InventoryService.removeProduct(list, remove);
                  }
            }
        }

    }

    public static void showCreateOrderMenu(Scanner scanner, ArrayList<Product> list){
        System.out.println("\nCREATE ORDER");
        InventoryService.displayList(list);
        System.out.print("\nCustomer First Name: ");
        String name = scanner.next();
        Cart cart = new Cart(name);
        System.out.println(cart);

        makeOrder(scanner, list, cart);

    }

    public static void makeOrder(Scanner scanner, ArrayList<Product> list, Cart cart){
        while(true){
            String productId = "";
            Product product = null;
            while(true){
                System.out.print("\nEnter product ID or (DONE): ");
                productId = scanner.next();
                scanner.nextLine();

                if(productId.equalsIgnoreCase("DONE")){
                    break;
                }

                product = InventoryService.findProductFromList(list, productId.toUpperCase());
                if(product != null){
                    break;
                }

                System.out.println("Product ID not found!");
            }

            if(productId.equalsIgnoreCase("DONE")){
                if(cart.isEmpty()){
                    System.out.println("Cart is empty, Add products first!");
                    continue;
                }else{
                    checkOut(cart, scanner, list);
                    return;
                }
            }

            CartLine line = cart.checkInCart(product);

            if(line != null){
                addMoreQuantity(line, scanner);
                System.out.println(cart);
                continue;
            }

            int quantity = 0;
            while(true){
                System.out.println("\nProduct available stock: " + product.getStockQuantity());
                System.out.print("Enter Quantity: ");
                quantity = scanner.nextInt();
                if(quantity <= product.getStockQuantity()){
                    System.out.println("Product added to cart!");
                    break;
                }else if(quantity <= 0){
                    System.out.println("Quantity cannot be less than or 0!");
                }else{
                    System.out.println("Cannot enter more than available stock!");
                }

            }

            cart.addLine(new CartLine(product, quantity));
            System.out.println(cart);



        }
    }


    public static void checkOut(Cart cart, Scanner scanner, ArrayList<Product> list){
        System.out.println("\nSUMMARY: ");
        System.out.println(cart);

        System.out.println("[1] CONFIRM ORDER");
        System.out.println("[0] BACK");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        if(choice == 0 ){
            return;
        }else{
            cart.checkOutCart();
            InventoryService.removeOutOfStock(list);
            System.out.println("Order Successfully Placed!");
            cart.printReceipt();
        }
    }

    public static void addMoreQuantity(CartLine line, Scanner scanner){
        System.out.println("\nThis product already exists would you like to add more?");
        System.out.println("[1] Add more");
        System.out.println("[2] Enter New Product");

        int choice = InputService.getChoice(scanner, 1, 2);

        if(choice == 2){
            return;
        }else if(choice == 1){
            int quantityLeft = line.getProduct().getStockQuantity() - line.getQuantityBought();
            System.out.println("\nProduct quantity left: " + quantityLeft);
            while(true){
                System.out.print("Enter New Quantity: ");
                int quantity = scanner.nextInt();
                scanner.nextLine();

                if(quantity > quantityLeft){
                    System.out.println("\nNot enough products, only " + quantityLeft + " left");
                }else{
                    line.updateQuantityBought(quantity);
                    break;
                }
            }
        }
    }


}
