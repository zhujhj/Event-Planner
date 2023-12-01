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

    public void insertGuest() {
        System.out.println("Insert guest");
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        System.out.print("Enter email: ");
        String email = scan.nextLine();
        System.out.print("Enter ticket number: ");
        int id = scan.nextInt();
        System.out.print("Enter phone #: ");
        int phone = scan.nextInt();
        GuestModel guest = new GuestModel(name, email, id, phone);
        dbHandler.insertGuest(guest);
    }

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
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        System.out.print("Enter ticket number: ");
        int ticketNumber = scan.nextInt();
        dbHandler.deleteGuest(name, ticketNumber);
    }

    public void deleteVenue() {
        System.out.println("Delete guest");
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        dbHandler.deleteVenue(name);
    }

    public void updateVenue() {
        System.out.println("Update venue");
        System.out.print("Enter venue name to update: ");
        String name = scan.nextLine();

        System.out.print("Enter new venue name: ");
        String newName = scan.nextLine();
        System.out.print("Enter new address: ");
        String address = scan.nextLine();
        System.out.print("Enter new capacity: ");
        int capacity = scan.nextInt();
        System.out.print("Enter new event ID: ");
        int eventId = scan.nextInt();
        dbHandler.updateVenue(name, address, capacity, eventId);
    }

    public void joinVenue() {
        System.out.println("Join venues");

        dbHandler.joinVenueAndEvent();
    }

    public void aggregateByGroupBy() {
        System.out.println("Getting total attendance per event");

        dbHandler.aggregateVenueTotalEventMaxAttendance();
    }

    public void aggregateByHaving() {
        System.out.println("What is the minimum total attendance?: ");
        int minTotalAttendance = scan.nextInt();

        System.out.println("Getting all events with total attendance greater than specified amount");
        dbHandler.aggregateVenueCapacityByEventHaving(minTotalAttendance);
    }

    public void nestedAggregation() {
        System.out.println("Running nested aggregation to get average venue capacity for all events");
        int ret = dbHandler.averageVenueCapacity();
        System.out.println("Average capacity: " + ret);
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
        System.out.print("1 for insert, 2 for delete, 3 for update, 4 for join, 5 for aggregate by GROUP BY: ");
        String option = scan.nextLine();
        switch (option) {
            case "1":
//                insertGuest();
                insertVenue();
                break;
            case "2":
//                deleteGuest();
                deleteVenue();
                break;
            case "3":
                updateVenue();
                break;
            case "4":
                joinVenue();
                break;
            case "5":
                aggregateByGroupBy();
                break;
            case "8":
                aggregateByHaving();
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }
}
