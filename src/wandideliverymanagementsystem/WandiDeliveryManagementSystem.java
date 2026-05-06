package wandideliverymanagementsystem;

import java.util.*;

public class WandiDeliveryManagementSystem {
    
    

    // showMenu method
    public static void ShowMenu() {
        System.out.println("====================================================\n"
                + "            Wandi Delivery Tracking System\n"
                + "====================================================");

        System.out.println("  [1]  Add Order             [4]  View Orders \n"
                + "  [2]  Delete Order          [5]  Update Status\n"
                + "  [3]  Search Order          [6]  Filter by Status\n"
                + "        \t   [7]  Exit\n"
                + "-----------------------------------------------------");
    }

    // viewOrders method
    public static void ViewOrders() {
        System.out.println();
        System.out.println("====================================================\n"
                + "         WANDI TRACKING SYSTEM — ALL ORDERS\n"
                + "====================================================");
        System.out.println();

//        for 
//        System.out.println("[ Preparing ]                              3 orders ");
//        System.out.println("-----------------------------------------------------");
//        System.out.println("  #1  SPX100267       Juan dela Cruz");
//        System.out.println("-----------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ShowMenu();
        System.out.print("Select option: ");
        int option = sc.nextInt();

        switch (option) {
//            case 1:
//                AddOrder();
//                break;
//
//            case 2:
//                DeleteOrder();
//                break;

//            case 3:
//                SearchOrder();
//                break;

            case 4:
                ViewOrders();
                break;

//            case 5:
//                UpdateStatus;
//                break;
//               
//            case 6:
//                FilterByStatus();
//                break;
        }

    }

}
