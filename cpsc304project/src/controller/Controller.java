


package controller;

//import consoleui.ConsoleUI;
import ui.DatabaseModifyPage;
import database.DatabaseConnectionHandler;
import model.GuestModel;
import util.PrintablePreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Controller {
    private DatabaseConnectionHandler dbHandler = null;
    //    private ConsoleUI cui;
    private DatabaseModifyPage gui;
//    private LoginWindow loginWindow = null;

    public Controller() {
        dbHandler = new DatabaseConnectionHandler();

    }

    private void start() {
//        loginWindow = new LoginWindow();
//        loginWindow.showFrame(this);
        DatabaseModifyPage gui = new DatabaseModifyPage();
        gui.setVisible(true);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.start();
    }

}