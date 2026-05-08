package wandideliverymanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class AddOrder {

    public static void addOrder(String[] status, ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName, int count) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        System.out.println("===========================================\n"
                + "                 ADD ORDER\n"
                + "===========================================");
        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();
        orderId.add(id);
        for (int x = 0; x < count; x++) {//for-loop for break and continue statement
            if (count > 1) {//Checks if first time using addOrder() function
                if (orderId.get(count - 1).equals(orderId.get(x))) {// Comparing if the current order Id is equal to the previous ones
                    if (x == count - 1) {//To skip the current index of the Id
                        continue;// Skip the entire loop
                    }
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Error: Order ID" + orderId.get(count - 1) + " already exists.");
                    System.out.println("Use option [5] to update its status instead.");
                    System.out.println("----------------------------------------------------------");
                    i++;// for while loop to not function
                    break;
                }
            }
        }

        while (i == 0) {
            System.out.print("Sender Name   : ");
            String sender = scanner.nextLine();
            senderName.add(sender);

            System.out.print("Recipient Name: ");
            String reciever = scanner.nextLine();
            recipientName.add(reciever);

            System.out.println("-------------------------------------------");
            System.out.println("Order " + orderId.get(count - 1) + " added successfully!");
            System.out.println("Status set to: [" + status[0] + "]");
            System.out.println("-------------------------------------------");
            i++;//Iteration to stop the loop entirely
        }

        System.out.print("Press ENTER to return to menu...");
        scanner.nextLine();
    }

   /* public static void main(String[] args) {
        String[] status = {"Preparing", "Packed ", "Shipped", "Delivered",};
        ArrayList<String> orderId = new ArrayList<>();
        ArrayList<String> senderName = new ArrayList<>();
        ArrayList<String> recipientName = new ArrayList<>();

        int count = 0;// count is for how many times the user added an order
        int m = 0;// eme-eme var for loop

//Switch case inside Do-while loop repeating until choice in menu is 7
        //Menu selection [1] (Inside switch 1)
        do {// eme eme do-while lang para mag loop
            count++;
            addOrder(status, orderId, senderName, recipientName, count);
            m++;
        } while (m < 5);
        System.out.println(orderId);
        System.out.println(senderName);
        System.out.println(recipientName);
    } */
}
