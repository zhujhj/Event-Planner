package controller;

import database.DatabaseConnectionHandler;
import model.GuestModel;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Controller {
    private DatabaseConnectionHandler dbHandler = null;
//    private LoginWindow loginWindow = null;

    public Controller() {
        dbHandler = new DatabaseConnectionHandler();
    }

    private void start() {
//        loginWindow = new LoginWindow();
//        loginWindow.showFrame(this);
        System.out.println("Hello");
        databaseSetup();
    }

    public void insertGuest(GuestModel model) {
        dbHandler.insertGuest(model);
    }

    /**
     * TermainalTransactionsDelegate Implementation
     *
     * Delete branch with given branch ID.
     */
    public void deleteBranch(int branchId) {
        dbHandler.deleteBranch(branchId);
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

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.start();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter email: ");
        String email = scan.nextLine();
        System.out.println("Enter name: ");
        String name = scan.nextLine();
        System.out.println("Enter ID: ");
        int id = scan.nextInt();
        System.out.println("Enter phone #: ");
        int phone = scan.nextInt();
        GuestModel guest = new GuestModel(email, name, id, phone);
        controller.insertGuest(guest);
    }

}