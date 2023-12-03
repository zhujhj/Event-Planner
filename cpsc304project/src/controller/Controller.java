package controller;

import consoleui.ConsoleUI;
import database.DatabaseConnectionHandler;
import model.GuestModel;
import ui.DatabaseModifyPage;
import util.PrintablePreparedStatement;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Controller {
    private DatabaseConnectionHandler dbHandler = null;
    private ConsoleUI cui;
    private DatabaseModifyPage frame;
//    private LoginWindow loginWindow = null;

    public Controller() {
        dbHandler = new DatabaseConnectionHandler();
        cui = new ConsoleUI();
        SwingUtilities.invokeLater(() -> {
            frame = new DatabaseModifyPage();
            frame.setVisible(true);
        });
    }

    private void start() {
//        loginWindow = new LoginWindow();
//        loginWindow.showFrame(this);
        System.out.println("Hello");
//        cui.runUI();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.start();
    }

}