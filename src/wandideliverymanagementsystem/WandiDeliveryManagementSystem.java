package wandideliverymanagementsystem;

import java.util.*;

public class WandiDeliveryManagementSystem {

    // reusable method to get a valid non-blank Order ID from the user
    public static String getOrderId(Scanner scanner) {
        String id = "";
        while (id.isBlank()) {
            System.out.println();
            System.out.print("Enter Order ID  : ");
            id = scanner.nextLine().trim().toUpperCase();
            if (id.isBlank()) {
                System.out.println("\n----------------------------------------------------\n"
                        + "             ✗ Order ID cannot be empty.\n"
                        + "            Please enter a valid Order ID.\n"
                        + "----------------------------------------------------");
            }
        }
        return id; // returns the valid ID back to whoever called this method
    }

    // reusable method to display a "not found" error for a given Order ID
    public static void printNotFound(String id, Scanner scanner) {
        // display this message when the entered Order ID is not found in the system
        System.out.println();
        System.out.println(
                "----------------------------------------------------\n"
                + "       ✗ No order found with ID \"" + id + "\".\n"
                + "      Please verify the Order ID and try again.\n"
                + "----------------------------------------------------\n");
        pause(scanner);
    }

    // reusable method to display the details of a specific order at index i
    public static void printOrderDetails(int i, ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName, ArrayList<String> orderStatus) {
        System.out.println();
        System.out.println("----------------------------------------------------\n"
                + "                   ORDER DETAILS\n"
                + "----------------------------------------------------");
        System.out.println("Order ID  : " + orderId.get(i));
        System.out.println("Sender    : " + senderName.get(i));
        System.out.println("Recipient : " + recipientName.get(i));
        System.out.println("Status    : " + orderStatus.get(i));
        System.out.println("----------------------------------------------------\n");
    }

    // pause method, to stops the screen so the user has time to read the result before returning to menu
    public static void pause(Scanner scanner) {
        System.out.println("Press ENTER to return to menu...");
        scanner.nextLine(); // Holds the screen until the user presses ENTER
    }

//     Adds a new delivery order.
//     
//      Steps:
//     1. Ask for a unique Order ID
//     2. Ask for sender and recipient names
//     3. Automatically set status to "Preparing"
    public static void addOrder(Scanner scanner, ArrayList<String> orderStatus, String[] status,
            ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName) {

        System.out.println("====================================================\n"
                + "                      ADD ORDER\n"
                + "====================================================");

        String sender = "";
        String receiver = "";

        // getOrderId() handles all the input validation for Order ID (blank check, trim, uppercase)
        // instead of repeating the same while loop in every method, we call this once and get back a clean, valid ID
        String id = getOrderId(scanner);

        // Check if the Order ID already exists because each order must be unique.
        // If duplicates are allowed, it would be confusing when searching, updating, or deleting orders.
        boolean exists = false;

        // Loop through all stored Order IDs to see if any match the new one
        for (int x = 0; x < orderId.size(); x++) {
            if (orderId.get(x).equals(id)) {
                exists = true;   // A match was found, so the ID already exists in the system, mark it as true
                break;         // Stop searching immediately, no need to check further
            }
        }

        // if the exist value is true (duplicate was found), then show an error and exit
        if (exists) {
            System.out.println();
            System.out.println("----------------------------------------------------\n"
                    + "       ✗ Order ID \"" + id + "\" already exists.\n"
                    + " Choose a different ID or update the existing order.\n"
                    + "----------------------------------------------------\n");
            pause(scanner); // pauses so the user can read the error message before continuing
            return; // exit the method
        }

        // If no duplicate was found, the ID is safe to store in the system
        orderId.add(id);

        // Get sender name and keep asking until a valid (non-blank) input is entered
        while (sender.isBlank()) {
            System.out.print("Sender Name     : ");
            sender = scanner.nextLine();

            // If the user just presses ENTER or types spaces,
            // display an error message and ask again
            if (sender.isBlank()) {
                System.out.println();
                System.out.println("----------------------------------------------------\n"
                        + "           ✗ Sender name cannot be empty.\n"
                        + "            Please enter the sender's name.\n"
                        + "----------------------------------------------------\n");
            }
        }
        senderName.add(sender); // store sender name only after valid input is entered

        // get receiver name and keep asking until a valid (non-blank) input is entered
        while (receiver.isBlank()) {
            System.out.print("Recipient Name  : ");
            receiver = scanner.nextLine();

            // If the user just presses ENTER or types spaces,
            // display an error message and ask again
            if (receiver.isBlank()) {
                System.out.println();
                System.out.println("----------------------------------------------------\n"
                        + "          ✗ Recipient name cannot be empty.\n"
                        + "          Please enter the recipient's name.\n"
                        + "----------------------------------------------------\n");
            }
        }
        recipientName.add(receiver); // store recipient name only after valid input is entered

        // set the default status of a new order
        orderStatus.add(status[0]);   // status[0] = "Preparing" — all new orders start at this stage

        System.out.println();
        System.out.println("----------------------------------------------------\n"
                + "      ✓ Order \"" + id + "\" added successfully!");

        // orderStatus.size() - 1 gets the most recently added status
        // (the last element in the list)
        System.out.println("             Status set to: [" + orderStatus.get(orderStatus.size() - 1) + "]\n"
                + "----------------------------------------------------\n");

        pause(scanner); // give time for user to read the success message
    }

    // DELETE ORDER
    public static void deleteOrder(Scanner scanner, ArrayList<String> orderStatus,
            ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName) {

        System.out.println("====================================================\n"
                + "                    DELETE ORDER\n"
                + "====================================================");

        // Validate input until a non-blank Order ID is entered
        // with this getOrderId() which handles all the input validation 
        String id = getOrderId(scanner);

        // search for the order by comparing the entered ID with all stored IDs
        for (int i = 0; i < orderId.size(); i++) {

            if (orderId.get(i).equals(id)) { // check if the entered ID matches a stored Order ID

                // display order details first before before deletion
                // printOrderDetails() is a reusable method that displays the full details of an order
                // (Order ID, Sender, Recipient, Status) using the index i to find the right order across all lists
                printOrderDetails(i, orderId, senderName, recipientName, orderStatus);

                // confirmation loop -- ask user for confirm deletion
                // keeps asking until Y or N is entered
                while (true) {
                    System.out.print("Are you sure you want to delete this order? (Y/N): ");
                    String input = scanner.nextLine();
                    System.out.println();

                    if (input.equalsIgnoreCase("Y")) {

                        // if user enters "Y", remove all data related to this order at index i
                        orderId.remove(i);
                        senderName.remove(i);
                        recipientName.remove(i);
                        orderStatus.remove(i);

                        // display successfull message
                        System.out.println("----------------------------------------------------\n"
                                + "      ✓ Order \"" + id + "\" deleted successfully.\n"
                                + "----------------------------------------------------\n");
                        pause(scanner);
                        return; // Exit after successful deletion

                    } else if (input.equalsIgnoreCase("N")) { // If user enters "N", cancel the deletion process
                        // no changes are made to any data, and display cancellation
                        System.out.println("----------------------------------------------------\n"
                                + "                Operation cancelled.\n"
                                + "               No orders were deleted.\n"
                                + "----------------------------------------------------\n");
                        pause(scanner);
                        return; // exit without making any changes

                    } else {
                        // neither Y nor N, ask user again until valid input is entered
                        System.out.println("----------------------------------------------------\n"
                                + "                 ✗ Invalid input.\n"
                                + "         Please enter Y for Yes or N for No.\n"
                                + "----------------------------------------------------\n");
                    }
                }
            }
        }

        // reached here means no order matched the entered ID
        // printNotFound() is a reusable method that displays the "order not found" error message
        // and pauses the screen used whenever a searched ID does not exist in the system
        printNotFound(id, scanner);
    }

    // SEARCH ORDER
    public static void searchOrder(Scanner scanner, ArrayList<String> orderStatus,
            ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName) {

        System.out.println("====================================================\n"
                + "                    SEARCH ORDER\n"
                + "====================================================");

        // Validate input until a non-blank Order ID is entered
        // with this getOrderId() which handles all the input validation 
        String id = getOrderId(scanner);

        // Search through all stored orders
        for (int i = 0; i < orderId.size(); i++) {

            if (orderId.get(i).equals(id)) { // if match found, display order details
                // reuse printOrderDetails() to display the found order's full details
                printOrderDetails(i, orderId, senderName, recipientName, orderStatus);

                pause(scanner);
                return; // early exit
            }
        }

        // reuse printNotFound() to display the standard "order not found" error
        printNotFound(id, scanner);
    }

    // VIEW ORDERS
    public static void viewOrders(Scanner scanner, ArrayList<String> orderStatus,
            ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName) {

        System.out.println("====================================================\n"
                + "    WANDI DELIVERY TRACKING SYSTEM — ALL ORDERS\n"
                + "====================================================");

        // If no orders have been added yet, inform the user and exit
        if (orderId.isEmpty()) {
            System.out.println("\n    -----------  No orders available  -----------\n");
            pause(scanner);
            return;
        }

        // delivery stages in order
        String[] categories = {"Preparing", "Packed", "Shipped", "Delivered"};

        // Loop through each status category and display its orders
        for (String currentStatus : categories) {

            int statusCount = 0;

            // loop through all orders and count how many match the current status
            // each time a matching status is found, increase statusCount by 1            
            for (int i = 0; i < orderId.size(); i++) {
                if (orderStatus.get(i).equals(currentStatus)) {
                    statusCount++; // add 1 for every matching order
                }
            }

            // display the current status and number of matching orders
            System.out.println();
            System.out.println("[ " + currentStatus + " ] - " + statusCount + " orders");
            System.out.println("--------------------------------------------------");

            // If no orders exist under this status, show a message
            if (statusCount == 0) {
                System.out.println("No orders found under [ " + currentStatus + " ] status.");
            } else {
                // if it has a statuscount then 
                int num = 1; // Row number for displayed orders

                // column headers for the order table
                System.out.println("#   ORDER ID       SENDER             RECIPIENT");

                // print only orders that match this status
                for (int i = 0; i < orderId.size(); i++) {
                    if (orderStatus.get(i).equals(currentStatus)) {

                        // printf keeps columns aligned regardless of text length:
                        // %-3d  = row number, left-aligned in 3 characters
                        // %-14s = Order ID, left-aligned in 14 characters
                        // %-18s = Sender name, left-aligned in 18 characters
                        // %-17s = Recipient name, left-aligned in 17 characters
                        System.out.printf("%-3d %-14s %-18s %-17s%n",
                                num,
                                orderId.get(i),
                                senderName.get(i),
                                recipientName.get(i));
                        num++; // increase row number only for displayed orders
                    }
                }
            }
        }

        // ---- DELIVERY SUMMARY ----
        int delivered = 0;
        int pending = 0;

        // count how many orders are fully delivered vs still in progress
        for (int i = 0; i < orderId.size(); i++) {
            if (orderStatus.get(i).equals("Delivered")) {
                delivered++; // plus 1 when order is completed/set to delivered
            } else {
                pending++; // Preparing, Packed, or Shipped = still pending
            }
        }

        // calculate completion rate as a percentage
        // formula: (number of delivered orders ÷ total orders) × 100
        // 100.0 is used to force decimal calculation so results are accurate (not rounded down)
        double completionRate = (delivered * 100.0) / orderId.size();

        System.out.println("\n====================================================\n"
                + "                  DELIVERY SUMMARY\n"
                + "====================================================");
        System.out.println("  Total Orders    : " + orderId.size());
        System.out.println("  Completed       : " + delivered);
        System.out.println("  Pending         : " + pending);

        // shows completion rate with 1 decimal place and adds % sign in output
        System.out.printf("  Completion Rate : %.1f%%%n", completionRate);
        System.out.println("====================================================\n");

        pause(scanner);
    }

    // UPDATE STATUS
    public static void updateStatus(Scanner scanner, ArrayList<String> orderStatus, String[] status,
            ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName) {

        System.out.println("====================================================\n"
                + "                    UPDATE STATUS                 \n"
                + "====================================================");

        // Validate input until a non-blank Order ID is entered
        // with this getOrderId() which handles all the input validation 
        String id = getOrderId(scanner);

        // Search through all orders to find a matching ID
        for (int i = 0; i < orderId.size(); i++) {

            if (orderId.get(i).equals(id)) { // Found the order at index i

                System.out.println("\n----------------------------------------------------\n"
                        + "                  Order Details\n"
                        + "----------------------------------------------------");
                System.out.println("ID      : " + id);
                System.out.println("Status  : " + orderStatus.get(i));
                System.out.println("----------------------------------------------------\n");

                String current = orderStatus.get(i); // current status of the order
                String next;             // will store the next status in the delivery process

                // Determine the next status based on where the order currently is
                // status[] = {"Preparing", "Packed", "Shipped", "Delivered"}
                if (current.equals(status[0])) {        // Preparing → Packed
                    next = status[1];
                } else if (current.equals(status[1])) { // Packed → Shipped
                    next = status[2];
                } else if (current.equals(status[2])) { // Shipped → Delivered
                    next = status[3];
                } else {
                    // order is already delivered, so it cannot be updated further, inform the user
                    System.out.println(
                            "----------------------------------------------------\n"
                            + "            No further updates possible.\n"
                            + "----------------------------------------------------\n");
                    pause(scanner);
                    return;
                }

                // Ask user for confirmation before updating status
                while (true) {
                    System.out.print("Confirm update to [" + next + "]? (Y/N): ");
                    String input = scanner.nextLine();
                    System.out.println();

                    if (input.equalsIgnoreCase("Y")) {
                        // replace the current status at index i with the next status
                        orderStatus.set(i, next);

                        System.out.println("----------------------------------------------------\n"
                                + "            ✓ Status updated successfully.\n"
                                + "       \"" + id + "\" : [" + current + "] → [" + next + "]\n"
                                + "----------------------------------------------------\n");
                        pause(scanner);
                        return;

                    } else if (input.equalsIgnoreCase("N")) {
                        // if user cancel update
                        System.out.println(
                                "----------------------------------------------------\n"
                                + "               Status update cancelled.\n"
                                + "                 No changes applied.\n"
                                + "----------------------------------------------------\n");
                        pause(scanner);
                        return;

                    } else {
                        // Invalid input handling
                        System.out.println("----------------------------------------------------\n"
                                + "                 ✗ Invalid input.\n"
                                + "         Please enter Y for Yes or N for No.\n"
                                + "----------------------------------------------------\n");
                    }
                }
            }
        }

        // reuse printNotFound() to display the standard "order not found" error
        printNotFound(id, scanner);
    }

    // FILTER
    public static void filterByStatus(Scanner scanner, ArrayList<String> orderStatus,
            ArrayList<String> orderId, ArrayList<String> senderName, ArrayList<String> recipientName) {

        System.out.println("====================================================\n"
                + "                  FILTER BY STATUS\n"
                + "====================================================");

        System.out.println("  [1]  Preparing             [3]  Shipped \n"
                + "  [2]  Packed                [4]  Delivered\n"
                + "        \t   [5]  Exit\n"
                + "----------------------------------------------------");

        int statusChoice;

        // Keep asking until a valid integer between 1 and 5 is entered
        while (true) {
            System.out.print("Select status: ");

            try {
                // read the number input, store in status choice
                statusChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();  // if not a number, clear invalid input
                System.out.println("\n----------------------------------------------------\n"
                        + "                 ✗ Invalid input.\n"
                        + "          Please select from [1 - 5].\n"
                        + "----------------------------------------------------\n");
                continue; // ask user again 
            }
            scanner.nextLine(); // consume the leftover newline after reading the integer
            System.out.println();

            if (statusChoice >= 1 && statusChoice <= 5) { // check if number is within valid range
                break;
            }
            // display if number is not 1-5
            System.out.println("----------------------------------------------------\n"
                    + "                 ✗ Invalid input.\n"
                    + "       Please select a number from [1] to [5]\n"
                    + "----------------------------------------------------\n");
        }

        // if user input is 5 then go back to the main menu
        if (statusChoice == 5) {
            System.out.println("Returning to menu...\n");
            return; // exit method
        }

        // convert number choice into actual status text
        String selectedStatus = "";
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
        }

        // count how many orders match the selected status
        int statusCount = 0;
        for (int i = 0; i < orderId.size(); i++) {
            if (orderStatus.get(i).equals(selectedStatus)) {
                statusCount++; // increase count if match found
            }
        }

        if (statusCount == 0) {
            // if no orders found under this status, inform user
            System.out.println("No orders available under [ " + selectedStatus + " ] status.");
        } else {
            // if there are matching orders, display the results for the selected status
            System.out.println("--------------------------------------------------");
            System.out.println("[ " + selectedStatus + " ] - " + statusCount + " orders");
            System.out.println("--------------------------------------------------");
            System.out.println("#   ORDER ID       SENDER             RECIPIENT");

            int num = 1; // Row counter

            // Print only orders that match selected status
            for (int i = 0; i < orderId.size(); i++) {
                if (orderStatus.get(i).equals(selectedStatus)) {

                    System.out.printf("%-3d %-14s %-18s %-17s%n",
                            num,
                            orderId.get(i),
                            senderName.get(i),
                            recipientName.get(i));
                    num++; // increase row number
                }
            }
        }

        System.out.println("--------------------------------------------------\n");
        pause(scanner);
    }

    // EXIT
    public static void exit() {
        System.out.println(
                "====================================================\n"
                + "            WANDI DELIVERY TRACKING SYSTEM\n"
                + "====================================================\n"
                + "            Thank you for using the system!\n"
                + "              Session ended Successfully.\n"
                + "===================================================");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Order goes through these stages in order
        String[] status = {"Preparing", "Packed", "Shipped", "Delivered"};

        // Parallel lists: same index = same order information
        ArrayList<String> orderId = new ArrayList<>();       // Unique ID for each order
        ArrayList<String> senderName = new ArrayList<>();    // Who sent the package
        ArrayList<String> recipientName = new ArrayList<>(); // Who receives the package
        ArrayList<String> orderStatus = new ArrayList<>();   // Current stage of the order

        int option = 0; // stores the user's menu choice

        // Main loop: keep showing menu until user chooses Exit
        // display menu first before evaluate the user input
        do {
            System.out.println("====================================================\n"
                    + "            WANDI DELIVERY TRACKING SYSTEM\n"
                    + "====================================================");

            System.out.println("  [1]  Add Order             [4]  View Orders \n"
                    + "  [2]  Delete Order          [5]  Update Status\n"
                    + "  [3]  Search Order          [6]  Filter by Status\n"
                    + "        \t   [7]  Exit\n"
                    + "----------------------------------------------------");

            // Input validation (only accept numbers 1–7)
            while (true) {
                System.out.print("Select option: ");

                try {
                    option = scanner.nextInt();
                } catch (InputMismatchException e) {
                    scanner.nextLine();  // if not a number, clear invalid input
                    System.out.println();
                    System.out.println("----------------------------------------------------\n"
                            + "              ✗ Invalid menu option\n"
                            + "       Please select a number from [1] to [7]\n"
                            + "----------------------------------------------------\n");
                    continue; // ask user again 
                }

                scanner.nextLine(); // consume leftover newline after nextInt()
                System.out.println();

                if (option >= 1 && option <= 7) { // check is valid range
                    break; // if valid, exit loop to proceed switch
                }

                // range error message
                System.out.println("----------------------------------------------------\n"
                        + "                 ✗ Invalid input.\n"
                        + "             Please select from [1 - 7].\n"
                        + "----------------------------------------------------\n");
            }

            // Route to the selected feature
            switch (option) {
                case 1:
                    addOrder(scanner, orderStatus, status, orderId, senderName, recipientName);
                    break;
                case 2:
                    deleteOrder(scanner, orderStatus, orderId, senderName, recipientName);
                    break;
                case 3:
                    searchOrder(scanner, orderStatus, orderId, senderName, recipientName);
                    break;
                case 4:
                    viewOrders(scanner, orderStatus, orderId, senderName, recipientName);
                    break;
                case 5:
                    updateStatus(scanner, orderStatus, status, orderId, senderName, recipientName);
                    break;
                case 6:
                    filterByStatus(scanner, orderStatus, orderId, senderName, recipientName);
                    break;
                case 7:
                    exit();
                    break;
            }

        } while (option != 7); // keep running until user selects exit [7]
        scanner.close(); // stop scanner since we no longer need user input
    }
}
