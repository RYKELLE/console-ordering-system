package ordering.app.service;


import java.util.InputMismatchException;
import java.util.Scanner;

public class InputService {

    public static boolean checkNumberRange(int choice, int min, int max){
        return (choice >= min && choice <= max);
    }

    public static int getChoice(Scanner scanner, int min, int max){
        int choice;
        while(true){

            try{
                System.out.print("Choose (" + min + "-" + max + "): ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch(InputMismatchException e){
                System.out.println("\nInvalid input pls enter a Number!");
                scanner.nextLine();
                continue;
            }

            if(!InputService.checkNumberRange(choice, 1, 4)){
                System.out.println("\nPlease Enter a valid Number!");
                continue;
            }

            return choice;
        }
    }

}
