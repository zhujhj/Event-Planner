package consoleui;

import database.DatabaseConnectionHandler;
import model.GuestModel;
import model.VenueModel;

import java.util.Scanner;

public class ConsoleUI {

    private DatabaseConnectionHandler dbHandler = null;
    private final Scanner scan;

    public ConsoleUI() {
        dbHandler = new DatabaseConnectionHandler();
        scan = new Scanner(System.in);
        databaseSetup();
//        runUI();
    }

    public void insertVenue() {
        System.out.println("Insert venue");
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        System.out.print("Enter email: ");
        String email = scan.nextLine();
        System.out.print("Enter ticket number: ");
        int id = scan.nextInt();
        System.out.print("Enter phone #: ");
        int phone = scan.nextInt();
        VenueModel venue = new VenueModel(name, email, id, phone);
        dbHandler.insertVenue(venue);
    }

<<<<<<< HEAD
    public void deleteVenue() {
        System.out.println("Delete venue");
=======
    public void insertVenue() {
        System.out.println("Insert venue");
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        System.out.print("Enter address: ");
        String email = scan.nextLine();
        System.out.print("Enter capacity: ");
        int id = scan.nextInt();
        System.out.print("Enter event ID: ");
        int phone = scan.nextInt();
        VenueModel venue = new VenueModel(name, email, id, phone);
        dbHandler.insertVenue(venue);
    }

    public void deleteGuest() {
        System.out.println("Delete guest");
>>>>>>> c18ca0df0ff69308d3f22052b277182e33ec0fbb
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        dbHandler.deleteVenue(name);
    }

    public void deleteVenue() {
        System.out.println("Delete guest");
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        dbHandler.deleteVenue(name);
    }

    /**
     * TermainalTransactionsDelegate Implementation
     *
     * Update the branch name for a specific ID
     */

    public void updateBranch(int branchId, String name) {
        dbHandler.updateBranch(branchId, name);
    }

    /**
     * TermainalTransactionsDelegate Implementation
     *
     * Displays information about varies bank branches.
     */
    public void showBranch() {
//        BranchModel[] models = dbHandler.getBranchInfo();
//
//        for (int i = 0; i < models.length; i++) {
//            BranchModel model = models[i];
//
//            // simplified output formatting; truncation may occur
//            System.out.printf("%-10.10s", model.getId());
//            System.out.printf("%-20.20s", model.getName());
//            if (model.getAddress() == null) {
//                System.out.printf("%-20.20s", " ");
//            } else {
//                System.out.printf("%-20.20s", model.getAddress());
//            }
//            System.out.printf("%-15.15s", model.getCity());
//            if (model.getPhoneNumber() == 0) {
//                System.out.printf("%-15.15s", " ");
//            } else {
//                System.out.printf("%-15.15s", model.getPhoneNumber());
//            }
//
//            System.out.println();
//        }
    }

    /**
     * TerminalTransactionsDelegate Implementation
     *
     * The TerminalTransaction instance tells us that it is done with what it's
     * doing so we are cleaning up the connection since it's no longer needed.
     */
    public void terminalTransactionsFinished() {
        dbHandler.close();
        dbHandler = null;

        System.exit(0);
    }

    /**
     * TerminalTransactionsDelegate Implementation
     *
     * The TerminalTransaction instance tells us that the user is fine with dropping any existing table
     * called branch and creating a new one for this project to use
     */
    public void databaseSetup() {
        dbHandler.databaseSetup();;

    }

    public void runUI() {
        Scanner scan = new Scanner(System.in);
        // which action
        System.out.print("1 for insert, 2 for delete, 3 for update, 4 for display: ");
        String option = scan.nextLine();
        switch (option) {
            case "1":
<<<<<<< HEAD
                insertVenue();
                break;
            case "2":
=======
//                insertGuest();
                insertVenue();
                break;
            case "2":
//                deleteGuest();
>>>>>>> c18ca0df0ff69308d3f22052b277182e33ec0fbb
                deleteVenue();
                break;
            case "3":
                //code
                break;
            case "4":
                //code
                break;
            default:
                System.out.println("Invalid input");
        }
    }
}
