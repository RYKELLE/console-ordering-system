package ordering.app;

import ordering.app.model.Product;
import ordering.app.service.InputService;
import ordering.app.service.InventoryService;
import ordering.app.controller.MenuController;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {

        ArrayList<Product> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        InventoryService.initializeList(list);


        while(true){
            System.out.println("\nMAIN MENU: ");
            System.out.println("[1] Products");
            System.out.println("[2] Create Order");
            System.out.println("[3] Exit");

            int choice = InputService.getChoice(scanner, 1, 3);

            if(choice == 3){
                System.out.println("Exiting...");
                break;
            }else if(choice == 1){
                MenuController.showProductMenu(scanner, list);
            }else if(choice == 2){
                MenuController.showCreateOrderMenu(scanner, list);
            }
        }


    }








}