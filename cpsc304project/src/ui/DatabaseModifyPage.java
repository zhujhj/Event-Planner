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

        JPanel insertPanel = new JPanel();

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

        insertPanel.add(new JLabel("Insert"));
        insertPanel.add(Box.createHorizontalStrut(500));
        insertPanel.add(new JLabel("Name"));
        insertPanel.add(nameTextField);


        insertPanel.add(Box.createHorizontalStrut(50));
        insertPanel.add(new JLabel("Address"));
        insertPanel.add(addressTextField);

        insertPanel.add(Box.createHorizontalStrut(50));
        insertPanel.add(new JLabel("Capacity"));
        insertPanel.add(capacityTextField);

        insertPanel.add(Box.createHorizontalStrut(65));
        insertPanel.add(new JLabel("ID"));
        insertPanel.add(IDTextField);

        insertPanel.add(insertSubmit);
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

        JPanel deletePanel = new JPanel();
        deletePanel.add(new JLabel("Delete"));
        deletePanel.add(Box.createHorizontalStrut(500));
        deletePanel.add(new JLabel("Name"));
        deletePanel.add(deleteTextField);
        deletePanel.add(deleteSubmit);

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


        JPanel updatePanel = new JPanel();
        updatePanel.add(new JLabel("Update"));
        updatePanel.add(Box.createHorizontalStrut(500));
        updatePanel.add(new JLabel("Name"));
        updatePanel.add(updateNameTextField);


        updatePanel.add(Box.createHorizontalStrut(50));
        updatePanel.add(new JLabel("Address"));
        updatePanel.add(updateAddressTextField);

        updatePanel.add(Box.createHorizontalStrut(50));
        updatePanel.add(new JLabel("Capacity"));
        updatePanel.add(updateCapacityTextField);

        updatePanel.add(Box.createHorizontalStrut(65));
        updatePanel.add(new JLabel("ID"));
        updatePanel.add(updateIDTextField);

        updatePanel.add(updateSubmit);

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

        JPanel switchScreenButtonPanel = new JPanel();

        JButton selAndProj = new JButton("Selection and Projection");

        switchScreenButtonPanel.add(selAndProj);

        selAndProj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertPanel.setVisible(false);
                deletePanel.setVisible(false);
                updatePanel.setVisible(false);
                switchScreenButtonPanel.setVisible(false);
            }
        });

        // Add panels to the frame
        add(insertPanel);
        add(deletePanel);
        add(updatePanel);
        add(switchScreenButtonPanel);

    }
    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

        }
    }


}