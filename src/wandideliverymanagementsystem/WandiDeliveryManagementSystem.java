package wandideliverymanagementsystem;

import java.util.ArrayList;
import java.util.*;

public class WandiDeliveryManagementSystem {

    public static void ShowMenu() {
        System.out.println("====================================================\n"
                + "            Wandi Delivery Tracking System\n"
                + "====================================================");

        System.out.println("  [1]  Add Order             [4]  View Orders \n"
                + "  [2]  Delete Order          [5]  Update Status\n"
                + "  [3]  Search Order          [6]  Filter by Status\n"
                + "        \t   [7]  Exit\n"
                + "----------------------------------------------------");
    }

    public static int addOrder(Scanner scanner, String[] status, ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName, int count) {
        int i = 0;
        System.out.println("====================================================\n"
                + "                      ADD ORDER\n"
                + "====================================================");

        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();
        orderId.add(id);
        if (count > 1) {//Checks if first time using addOrder() function
            for (int x = 0; x < count; x++) {//for-loop for break and continue statement
                if (orderId.get(count - 1).equals(orderId.get(x))) {// Comparing if the current order Id is equal to the previous ones
                    if (x == count - 1) {//To skip the current index of the Id
                        continue;// Skip the entire loop
                    }
                    System.out.println("----------------------------------------------------");
                    System.out.println("Error: Order ID " + orderId.get(count - 1) + " already exists.");
                    System.out.println("Use option [5] to update its status instead.");
                    System.out.println("----------------------------------------------------");
                    i++;// for while loop to not function
                    orderId.remove(count - 1);
                    --count;
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

            System.out.println("----------------------------------------------------");
            System.out.println("Order " + id + " added successfully!");
            System.out.println("Status set to: [" + status[0] + "]");
            System.out.println("----------------------------------------------------");
            i++;//Iteration to stop the loop entirely
        }

        System.out.println("Press ENTER to return to menu...");
        scanner.nextLine();
        return count;
    }

    public static void deleteOrder(Scanner scanner, String[] status, ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName, int count) {
        System.out.println("====================================================\n"
                + "                    DELETE ORDER\n"
                + "====================================================");
        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();
        System.out.println("----------------------------------------------------");

        if (count < 1) {
            System.out.println("----------------------------------------------------");
            System.out.println("Error: Order ID " + id + " not found.");
            System.out.println("Check the ID and try again.");
            System.out.println("----------------------------------------------------");
        } else {
            for (int t = 0; t < count; t++) {
                if (orderId.get(t).equals(id)) {
                    System.out.println("Order Found: ");
                    System.out.println("ID     : " + id);
                    System.out.println("Status : " + status[0]);
                    System.out.println("----------------------------------------------------");
                    System.out.print("Are you sure you want to delete order? (Y/N): ");
                    char answer = scanner.next().charAt(0);
                    if (answer == 'Y') {
                        System.out.println("----------------------------------------------------");
                        System.out.println("Order " + id + " has been deleted.");
                        System.out.println("----------------------------------------------------");
                        orderId.remove(t);
                        senderName.remove(t);
                        recipientName.remove(t);
                        break;
                    }
                } else {
                    System.out.println("----------------------------------------------------");
                    System.out.println("Error: Order ID " + id + " not found.");
                    System.out.println("Check the ID and try again.");
                    System.out.println("----------------------------------------------------");
                    break;
                }
                break;
            }
        }
        System.out.println("Press ENTER to return to menu...");
        scanner.nextLine();
    }

    public static void searchOrder(Scanner scanner, String[] status, ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName, int count1) {
        System.out.println("====================================================\n"
                + "                    SEARCH ORDER\n"
                + "====================================================");
        System.out.print("Enter Order ID: ");
        String id = scanner.nextLine();

        for (int z = 0; z < count1; z++) {
            if (orderId.get(z).equals(id)) {
                System.out.println("----------------------------------------------------\n"
                        + "                   ORDER DETAILS\n"
                        + "----------------------------------------------------");
                System.out.println("Order ID  : " + orderId.get(z));
                System.out.println("Sender    : " + senderName.get(z));
                System.out.println("Recipient : " + recipientName.get(z));
                System.out.println("Status    : " + status[2]);
            } else {
                System.out.println("----------------------------------------------------");
                System.out.println("Error: Order ID " + id + " not found.");
                System.out.println("Check the ID and try again.");
                System.out.println("----------------------------------------------------");
                break;
            }
        }
        System.out.println("Press ENTER to return to menu...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] status = {"Preparing", "Packed ", "Shipped", "Delivered",};
        ArrayList<String> orderId = new ArrayList<>();
        ArrayList<String> senderName = new ArrayList<>();
        ArrayList<String> recipientName = new ArrayList<>();
        int count = 0;
        int count1 = 0;
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            ShowMenu();
            System.out.print("Select option: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    count++;
                    count = addOrder(scanner, status, orderId, senderName, recipientName, count);
                    break;
                case 2:
                    deleteOrder(scanner, status, orderId, senderName, recipientName, count);
                    break;
                case 3:
                    count1++;
                    searchOrder(scanner, status, orderId, senderName, recipientName, count1);
                    break;
            }
        } while (option != 7);
        System.out.println(orderId);
        System.out.println(senderName);
        System.out.println(recipientName);
    }
}
