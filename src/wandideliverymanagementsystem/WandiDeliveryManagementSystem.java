package wandideliverymanagementsystem;

import java.util.*;

public class WandiDeliveryManagementSystem {

    public static int addOrder(Scanner scanner, ArrayList<String> orderStatus, String[] status, ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName, int count) {
        int i = 0;
        System.out.println("====================================================\n"
                + "                      ADD ORDER\n"
                + "====================================================");
        System.out.println();
        String id = " ";
        String sender = " ";
        String reciever = " ";

        while (id.isBlank()) {
            System.out.print("Enter Order ID  : ");
            id = scanner.nextLine();

            if (id.isBlank()) {
                System.out.println("ID cannot be blank ");
                System.out.println();
            }
        }

        orderId.add(id);

        if (count > 1) {//Checks if first time using addOrder() function
            for (int x = 0; x < count; x++) {//for-loop for break and continue statement
                if (orderId.get(count - 1).equals(orderId.get(x))) {// Comparing if the current order Id is equal to the previous ones
                    if (x == count - 1) {//To skip the current index of the Id
                        continue;// Skip the entire loop
                    }
                    System.out.println("----------------------------------------------------");
                    System.out.println("            ✗ Error: Order ID " + orderId.get(count - 1) + " already exists.");
                    System.out.println("         Use option [5] to update its status instead.");
                    System.out.println("----------------------------------------------------");
                    i++;// for while loop to not function
                    orderId.remove(count - 1);
                    count--;
                    break;
                }
            }
        }

        while (i == 0) {
            while (sender.isBlank()) {
                System.out.print("Sender Name     : ");
                sender = scanner.nextLine();

                if (sender.isBlank()) {
                    System.out.println("Sender name cannot be blank ");
                    System.out.println();
                }
            }

            senderName.add(sender);

            while (reciever.isBlank()) {
                System.out.print("Recipient Name  : ");
                reciever = scanner.nextLine();

                if (reciever.isBlank()) {
                    System.out.println("Reciever name cannot be blank ");
                    System.out.println();
                }
            }
            recipientName.add(reciever);

            System.out.println();

            System.out.println("----------------------------------------------------");
            System.out.println("          ✓ Order " + id + " added successfully!");
            orderStatus.add(status[0]); // when a new order is added, its default status is automatically set to "Preparing"
            System.out.println("             Status set to: [" + orderStatus.get(orderStatus.size() - 1) + "]"); // prints the most recently added order status (last item in the list)
            System.out.println("----------------------------------------------------");
            i++;//Iteration to stop the loop entirely
        }

        System.out.println();
        System.out.println("Press ENTER to return to menu...");
        scanner.nextLine();
        return count;
    }

    public static int deleteOrder(Scanner scanner, ArrayList<String> orderStatus, String[] status, ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName, int count) {
        System.out.println("====================================================\n"
                + "                    DELETE ORDER\n"
                + "====================================================");
        System.out.println();
        int i = 0;
        String id = " ";

        while (id.isBlank()) {
            System.out.print("Enter Order ID  : ");
            id = scanner.nextLine();

            if (id.isBlank()) {
                System.out.println("ID cannot be blank ");
                System.out.println();
            }
        }

        orderId.add(id);

        if (count < 1) {
            System.out.println("----------------------------------------------------");
            System.out.println("          ✗ Error: Order ID " + id + " not found.");
            System.out.println("              Check the ID and try again.");
            System.out.println("----------------------------------------------------");
        } else {
            for (int t = 0; t < count; t++) {
                if (orderId.get(t).equals(id)) {
                    System.out.println("----------------------------------------------------\n"
                            + "                   ORDER DETAILS\n"
                            + "----------------------------------------------------");
                    System.out.println("ID      : " + id);
                    System.out.println("Status  : " + orderStatus.get(t));
                    System.out.println("----------------------------------------------------");
                    System.out.print("Are you sure you want to delete this order? (Y/N): ");
                    String answer = scanner.nextLine();
                    System.out.println();
                    if (answer.equalsIgnoreCase("Y")) {
                        System.out.println("----------------------------------------------------");
                        System.out.println("             Order " + id + " has been deleted.");
                        System.out.println("----------------------------------------------------");
                        orderId.remove(t);
                        senderName.remove(t);
                        recipientName.remove(t);
                        //can cause mismatch
                        orderStatus.remove(t);
                        count--;
                        i++;
                        break;
                    } else if (answer.equalsIgnoreCase("N")) {
                        System.out.println("----------------------------------------------------");
                        System.out.println("Delete Cancelled. ");
                        System.out.println("----------------------------------------------------");
                        System.out.println();
                        i++;

                    } else {
                        System.out.println("ERROR: Please enter Y or N only. ");
                        i++;
                    }
                }
            }
            while (i == 0) {
                System.out.println("----------------------------------------------------");
                System.out.println("            ✗ Error: Order ID " + id + " not found.");
                System.out.println("              Check the ID and try again.");
                System.out.println("----------------------------------------------------");
                i++;
            }
        }
        System.out.println("Press ENTER to return to menu...");
        scanner.nextLine();
        return count;
    }

    public static void searchOrder(Scanner scanner, ArrayList<String> orderStatus, String[] status, ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName, int count) {
        System.out.println("====================================================\n"
                + "                    SEARCH ORDER\n"
                + "====================================================");
        System.out.println();
        String id = " ";
        int i = 0;

        while (id.isBlank()) {
            System.out.print("Enter Order ID  : ");
            id = scanner.nextLine();

            if (id.isBlank()) {
                System.out.println("ID cannot be blank ");
                System.out.println();
            }
        }

        System.out.println();
        if (count < 1) {
            System.out.println("----------------------------------------------------");
            System.out.println("            ✗ Error: Order ID " + id + " not found.");
            System.out.println("              Check the ID and try again.");
            System.out.println("----------------------------------------------------");
        } else {
            for (int z = 0; z < count; z++) {
                if (orderId.get(z).equals(id)) {
                    System.out.println("----------------------------------------------------\n"
                            + "                   ORDER DETAILS\n"
                            + "----------------------------------------------------");
                    System.out.println();
                    System.out.println("Order ID  : " + orderId.get(z));
                    System.out.println("Sender    : " + senderName.get(z));
                    System.out.println("Recipient : " + recipientName.get(z));
                    System.out.println("Status    : " + orderStatus.get(z));
                    System.out.println();
                    System.out.println("----------------------------------------------------");
                    i++;
                    break;
                }
            }
            while (i == 0) {
                System.out.println("----------------------------------------------------");
                System.out.println("            ✗ Error: Order ID " + id + " not found.");
                System.out.println("              Check the ID and try again.");
                System.out.println("----------------------------------------------------");
                i++;
            }
        }

        System.out.println("Press ENTER to return to menu...");
        scanner.nextLine();
    }

    // viewOrders method
    public static void ViewOrders(Scanner scanner, ArrayList<String> orderStatus, String[] status, ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName, int count) {

        System.out.println("====================================================\n"
                + "         WANDI TRACKING SYSTEM — ALL ORDERS\n"
                + "====================================================");
        System.out.println();

        // if there are no orders, stop the method
        if (count == 0) {
            System.out.println("No orders available.");
            return;
        }

        String[] categories = {"Preparing", "Packed", "Shipped", "Delivered"};

        // outer loop through each status category
        for (String currentStatus : categories) {
            int statusCount = 0;    // counts how many orders are in this status

            // first loop: count how many orders match this status
            for (int i = 0; i < count; i++) {
                if (orderStatus.get(i).equals(currentStatus)) {
                    statusCount++;
                }
            }

            // if statusCount has orders then print
            // print only that has a orders
            if (statusCount == 0) {
                System.out.println("No orders found under [ " + currentStatus + " ] status.");
            } else {
                System.out.println();
                System.out.println("[ " + currentStatus + " ] - " + statusCount + " orders");
                System.out.println("--------------------------------------------------");
                System.out.println("#   ORDER ID       SENDER             RECIPIENT");
                int num = 1;

                for (int i = 0; i < orderStatus.size(); i++) {
                    if (orderStatus.get(i).equals(currentStatus)) {

                        System.out.printf("%-3d %-14s %-18s %-17s%n",
                                num,
                                orderId.get(i),
                                senderName.get(i),
                                recipientName.get(i));
                        num++;
                    }
                }
            }
        }
        System.out.println();
        System.out.println("--------------------------------------------------\n"
                + "TOTAL ORDERS: " + orderId.size()
                + "\n-------------------------------------------------- ");
        System.out.println("Press ENTER to return to menu...");
        scanner.nextLine();
    }

    public static void updateStatus(Scanner scanner, ArrayList<String> orderStatus, String[] status, ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName, int count) {
        System.out.println("====================================================\n"
                + "                    UPDATE STATUS                 \n"
                + "====================================================");
        System.out.println();
        String id = " ";
        char answer = ' ';
        int x = 0;

        while (id.isBlank()) {
            System.out.print("Enter Order ID  : ");
            id = scanner.nextLine();

            if (id.isBlank()) {
                System.out.println("ID cannot be blank ");
                System.out.println();
            }
        }
        System.out.println();

        if (count < 1) {
            System.out.println("----------------------------------------------------");
            System.out.println("          ✗ Error: Order ID " + id + " not found.");
            System.out.println("              Check the ID and try again.");
            System.out.println("----------------------------------------------------");
        } else {
            for (int t = 0; t < count; t++) {
                if (orderId.get(t).equals(id)) {
                    System.out.println("ORDER FOUND");
                    System.out.println("ID      : " + id);
                    System.out.println("Current : " + orderStatus.get(t));
                    //"Preparing", "Packed", "Shipped", "Delivered"

                    //update: only be allowed to take y/Y/n/N, other than that it will be error.
                    if (orderStatus.get(t).equals(status[0])) {
                        System.out.println("Next    : [" + status[1] + "]   <- will be set");
                        while (true) {
                            System.out.print("Confirm update to " + status[1].toUpperCase() + "? (Y/N): ");
                            String input = scanner.nextLine();
                            if (input.equalsIgnoreCase("Y")) {
                                answer = 'Y';
                                break;

                            } else if (input.equalsIgnoreCase("N")) {
                                answer = 'N';
                                break;

                            } else {
                                System.out.println("ERROR: Please enter Y or N only.");
                            }
                        }

                    } else if (orderStatus.get(t).equals(status[1])) {
                        System.out.println("Next    : [" + status[2] + "]   <- will be set");

                        while (true) {

                            System.out.print("Confirm update to " + status[2].toUpperCase() + "? (Y/N): ");

                            String input = scanner.nextLine();

                            if (input.equalsIgnoreCase("Y")) {
                                answer = 'Y';
                                break;

                            } else if (input.equalsIgnoreCase("N")) {
                                answer = 'N';
                                break;

                            } else {
                                System.out.println("ERROR: Please enter Y or N only.");
                            }
                        }

                    } else if (orderStatus.get(t).equals(status[2])) {
                        System.out.println("Next    : [" + status[3] + "]   <- will be set");
                        System.out.println("----------------------------------------------------");
                        while (true) {

                            System.out.print("Confirm update to " + status[3].toUpperCase() + "? (Y/N): ");

                            String input = scanner.nextLine();

                            if (input.equalsIgnoreCase("Y")) {
                                answer = 'Y';
                                break;

                            } else if (input.equalsIgnoreCase("N")) {
                                answer = 'N';
                                break;

                            } else {
                                System.out.println("ERROR: Please enter Y or N only.");
                            }
                        }
                    } else {
                        System.out.println("----------------------------------------------------");
                        System.out.println("            ✗ Order " + orderId.get(t) + " is already " + status[3].toUpperCase() + ".");
                        System.out.println("               No further updates possible.");
                        System.out.println("----------------------------------------------------");
                    }

                    if (answer == 'Y') {
                        System.out.println("----------------------------------------------------");

                        if (orderStatus.get(t).equals(status[0])) {
                            System.out.println("             ✓ " + orderId.get(t) + " successfully updated!");
                            System.out.println("             [" + orderStatus.get(t) + "] → [" + status[1] + "]");

                            orderStatus.set(t, status[1]); //Changing the value of index t to desired value

                        } else if (orderStatus.get(t).equals(status[1])) {
                            System.out.println("             ✓ " + orderId.get(t) + " successfully updated!");
                            System.out.println("             [" + orderStatus.get(t) + "] → [" + status[2] + "]");

                            orderStatus.set(t, status[2]);//Changing the value of index t to desired value

                        } else {
                            System.out.println("             ✓ " + orderId.get(t) + " successfully updated!");
                            System.out.println("             [" + orderStatus.get(t) + "] → [" + status[3] + "]");

                            orderStatus.set(t, status[3]);//Changing the value of index t to desired value

                        }

                        System.out.println("----------------------------------------------------");
                        break;
                    }
                    if (answer == 'N') {
                        System.out.println("----------------------------------------------------");
                        System.out.println("Update Cancelled. ");
                        System.out.println("----------------------------------------------------");
                        System.out.println();

                    }
                    x++;//For while loop to not function
                }
                while (x == 0) {
                    System.out.println("----------------------------------------------------");
                    System.out.println("            ✗ Error: Order ID " + id + " not found.");
                    System.out.println("              Check the ID and try again.");
                    System.out.println("----------------------------------------------------");
                    x++;
                }
            }
        }
        System.out.println("Press ENTER to return to menu...");
        scanner.nextLine();
    }

    // FILTER
    public static void FilterByStatus(Scanner scanner, ArrayList<String> orderStatus, String[] status, ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName) {
        System.out.println("====================================================\n"
                + "                  FILTER BY STATUS\n"
                + "====================================================");
        System.out.println();

        System.out.println("  [1]  Preparing             [3]  Shipped \n"
                + "  [2]  Packed                [4]  Delivered\n"
                + "        \t   [5]  Exit\n"
                + "----------------------------------------------------");
        System.out.print("Select status: ");
        int statusChoice = scanner.nextInt();
        System.out.println("");
        scanner.nextLine();

        if (statusChoice == 5) {
            System.out.println("Returning to menu...");
            return;
        }

        // Variable that will store the selected status text
        String selectedStatus = "";

        // Convert number choice into a status name
        switch (statusChoice) {
            case 1:
                selectedStatus = "Preparing";
                break;
            case 2:
                selectedStatus = "Packed";
                break;
            case 3:
                selectedStatus = "Shipped";
                break;
            case 4:
                selectedStatus = "Delivered";
                break;
            default:
                System.out.println("Invalid Input. Please try again. ");
                break;
        }

        // Variable used to count matching orders
        int statusCount = 0;

        for (int i = 0; i < orderStatus.size(); i++) {

            if (orderStatus.get(i).equals(selectedStatus)) {
                statusCount++;
            }
        }

        if (statusCount == 0) {
            // Message if no orders match the selected status
            System.out.println("No orders found under [ " + selectedStatus + " ] status.");
        } else {

            System.out.println("[ " + selectedStatus + " ] - " + statusCount + " orders");

            System.out.println("--------------------------------------------------");
            System.out.println("#   ORDER ID       SENDER             RECIPIENT");
            int num = 1;
            for (int s = 0; s < orderId.size(); s++) {

                if (orderStatus.get(s).equals(selectedStatus)) {

                    // display orders under this status
                    System.out.printf("%-3d %-14s %-18s %-17s%n",
                            num,
                            orderId.get(s),
                            senderName.get(s),
                            recipientName.get(s));
                    num++;
                }
            }

        }

        System.out.println();
        System.out.println("Press ENTER to return to menu...");
        scanner.nextLine();
    }

    public static void exit() {
        System.out.println("====================================================\n"
                + "      Thank you for using Wandi Tracking System\n"
                + "====================================================");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] status = {"Preparing", "Packed", "Shipped", "Delivered"};
        ArrayList<String> orderId = new ArrayList<>();
        ArrayList<String> senderName = new ArrayList<>();
        ArrayList<String> recipientName = new ArrayList<>();
        ArrayList<String> orderStatus = new ArrayList<>();
        int count = 0;
        int option = 0;

        do {

            System.out.println("====================================================\n"
                    + "            Wandi Delivery Tracking System\n"
                    + "====================================================");

            System.out.println("  [1]  Add Order             [4]  View Orders \n"
                    + "  [2]  Delete Order          [5]  Update Status\n"
                    + "  [3]  Search Order          [6]  Filter by Status\n"
                    + "        \t   [7]  Exit\n"
                    + "----------------------------------------------------");
            while (true) {
                System.out.print("Select option: ");
                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Invalid input! Please try again. ");
                    scanner.next();
                }

            }

            /*               
            while (id.isBlank()) {
            System.out.print("Enter Order ID  : ");
            id = scanner.nextLine();

            if (id.isBlank()) {
                System.out.println("ID cannot be blank ");
                System.out.println();
            }
        }
             */
            System.out.println();
            scanner.nextLine();

            switch (option) {
                case 1:
                    count++;
                    count = addOrder(scanner, orderStatus, status, orderId, senderName, recipientName, count);
                    break;

                case 2:
                    count = deleteOrder(scanner, orderStatus, status, orderId, senderName, recipientName, count);
                    break;

                case 3:
                    searchOrder(scanner, orderStatus, status, orderId, senderName, recipientName, count);
                    break;

                case 4:
                    ViewOrders(scanner, orderStatus, status, orderId, senderName, recipientName, count);
                    break;

                case 5:
                    updateStatus(scanner, orderStatus, status, orderId, senderName, recipientName, count);
                    break;

                case 6:
                    FilterByStatus(scanner, orderStatus, status, orderId, senderName, recipientName);
                    break;
                case 7:
                    exit();
                    break;
                default:
                    System.out.println("Invalid input! Please try again. ");
                    break;
            }
        } while (option != 7);

        //        System.out.println(orderId);
        //        System.out.println(senderName);
        //        System.out.println(recipientName);
    }
}
