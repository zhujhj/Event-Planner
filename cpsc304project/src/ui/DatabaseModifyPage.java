package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import database.DatabaseConnectionHandler;
import model.VenueModel;

public class DatabaseModifyPage extends JFrame implements ListSelectionListener {

    private DatabaseConnectionHandler dbHandler = null;


    private static JFrame frame = new JFrame("Database Modifier");
//    private static JButton

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DatabaseModifyPage frame = new DatabaseModifyPage();
            frame.setVisible(true);
        });
    }
    public DatabaseModifyPage() {
        dbHandler = new DatabaseConnectionHandler();
        dbHandler.databaseSetup();


        setTitle("Simple GUI");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createAndShowUI();
    }
    public void createAndShowUI() {

        setLayout(new GridLayout(2, 2));

        // Create panels for each section

        JPanel panel1 = new JPanel();

        JTextField nameTextField = new JTextField(20);
        JTextField addressTextField = new JTextField(20);
        JTextField capacityTextField = new JTextField(20);
        JTextField IDTextField = new JTextField(20);
        JButton insertSubmit = new JButton("submit");


        JTextField deleteTextField = new JTextField(20);
        JButton deleteSubmit = new JButton("submit");

        JTextField updateNameTextField = new JTextField(20);
        JTextField updateAddressTextField = new JTextField(20);
        JTextField updateCapacityTextField = new JTextField(20);
        JTextField updateIDTextField = new JTextField(20);
        JButton updateSubmit = new JButton("submit");

//        String name1 = "error";
//        String add = "err";
//        int cap = 0;
//        int ID = 1;

        panel1.add(new JLabel("Insert"));
        panel1.add(Box.createHorizontalStrut(500));
        panel1.add(new JLabel("Name"));
        panel1.add(nameTextField);


        panel1.add(Box.createHorizontalStrut(50));
        panel1.add(new JLabel("Address"));
        panel1.add(addressTextField);

        panel1.add(Box.createHorizontalStrut(50));
        panel1.add(new JLabel("Capacity"));
        panel1.add(capacityTextField);

        panel1.add(Box.createHorizontalStrut(65));
        panel1.add(new JLabel("ID"));
        panel1.add(IDTextField);

        panel1.add(insertSubmit);
        insertSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String add = addressTextField.getText();
                int cap = Integer.parseInt(capacityTextField.getText());
                int id = Integer.parseInt(IDTextField.getText());
                VenueModel toadd = new VenueModel(name, add, cap, id);
                dbHandler.insertVenue(toadd);
                String toprint = "";
                // will change
//                if (dbHandler.getDuplicate()) {
//                    toprint = "This venue already exist, please update if needed!";
//                } else {
//                    toprint = "Successfully Added!";
//                }
                toprint = "successfully added";
                JOptionPane.showMessageDialog(null, toprint);

            }
        });

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Delete"));
        panel2.add(Box.createHorizontalStrut(500));
        panel2.add(new JLabel("Name"));
        panel2.add(deleteTextField);
        panel2.add(deleteSubmit);

        deleteSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = deleteTextField.getText();
                dbHandler.deleteVenue(name);
                String toprint = "";
                if (dbHandler.getRowCount() == 0) {
                    toprint = "There is no venue with that name :(";
                } else {
                    toprint = "Successfully deleted";
                }
                JOptionPane.showMessageDialog(null, toprint);
            }
        });


        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Update"));
        panel3.add(Box.createHorizontalStrut(500));
        panel3.add(new JLabel("Name"));
        panel3.add(updateNameTextField);


        panel3.add(Box.createHorizontalStrut(50));
        panel3.add(new JLabel("Address"));
        panel3.add(updateAddressTextField);

        panel3.add(Box.createHorizontalStrut(50));
        panel3.add(new JLabel("Capacity"));
        panel3.add(updateCapacityTextField);

        panel3.add(Box.createHorizontalStrut(65));
        panel3.add(new JLabel("ID"));
        panel3.add(updateIDTextField);

        panel3.add(updateSubmit);

        updateSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = updateNameTextField.getText();
                String add = updateAddressTextField.getText();
                int cap = Integer.parseInt(updateCapacityTextField.getText());
                int id = Integer.parseInt(updateIDTextField.getText());
                dbHandler.updateVenue(name, add, cap, id);
                String toprint = "";
                // will change
//                if (dbHandler.getDuplicate()) {
//                    toprint = "This venue already exist, please update if needed!";
//                } else {
//                    toprint = "Successfully Added!";
//                }
                toprint = "successfully updated";
                JOptionPane.showMessageDialog(null, toprint);

            }
        });


        // Add panels to the frame
        add(panel1);
        add(panel2);
        add(panel3);

    }
    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

        }
    }


}