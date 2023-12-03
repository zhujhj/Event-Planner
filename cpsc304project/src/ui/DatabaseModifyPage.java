//package ui;
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.event.*;
//
//import database.DatabaseConnectionHandler;
//import model.VenueModel;
//
//public class DatabaseModifyPage extends JFrame implements ListSelectionListener {
//
//    private DatabaseConnectionHandler dbHandler = null;
//
//
//    private static JFrame frame = new JFrame("Database Modifier");
////    private static JButton
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            DatabaseModifyPage frame = new DatabaseModifyPage();
//            frame.setVisible(true);
//        });
//    }
//    public DatabaseModifyPage() {
//        dbHandler = new DatabaseConnectionHandler();
//        dbHandler.databaseSetup();
//
//
//        setTitle("Simple GUI");
//        setSize(800, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        createAndShowUI();
//    }
//    public void createAndShowUI() {
//
//        setLayout(new GridLayout(2, 2));
//
//        // Create panels for each section
//
//        JPanel insertPanel = new JPanel();
//
//        JTextField nameTextField = new JTextField(20);
//        JTextField addressTextField = new JTextField(20);
//        JTextField capacityTextField = new JTextField(20);
//        JTextField IDTextField = new JTextField(20);
//        JButton insertSubmit = new JButton("submit");
//
//
//        JTextField deleteTextField = new JTextField(20);
//        JButton deleteSubmit = new JButton("submit");
//
//        JTextField updateNameTextField = new JTextField(20);
//        JTextField updateAddressTextField = new JTextField(20);
//        JTextField updateCapacityTextField = new JTextField(20);
//        JTextField updateIDTextField = new JTextField(20);
//        JButton updateSubmit = new JButton("submit");
//
////        String name1 = "error";
////        String add = "err";
////        int cap = 0;
////        int ID = 1;
//
//        insertPanel.add(new JLabel("Insert"));
//        insertPanel.add(Box.createHorizontalStrut(500));
//        insertPanel.add(new JLabel("Name"));
//        insertPanel.add(nameTextField);
//
//
//        insertPanel.add(Box.createHorizontalStrut(50));
//        insertPanel.add(new JLabel("Address"));
//        insertPanel.add(addressTextField);
//
//        insertPanel.add(Box.createHorizontalStrut(50));
//        insertPanel.add(new JLabel("Capacity"));
//        insertPanel.add(capacityTextField);
//
//        insertPanel.add(Box.createHorizontalStrut(65));
//        insertPanel.add(new JLabel("ID"));
//        insertPanel.add(IDTextField);
//
//        insertPanel.add(insertSubmit);
//        insertSubmit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String name = nameTextField.getText();
//                String add = addressTextField.getText();
//                int cap = Integer.parseInt(capacityTextField.getText());
//                int id = Integer.parseInt(IDTextField.getText());
//                VenueModel toadd = new VenueModel(name, add, cap, id);
//                dbHandler.insertVenue(toadd);
//                String toprint = "";
//                // will change
////                if (dbHandler.getDuplicate()) {
////                    toprint = "This venue already exist, please update if needed!";
////                } else {
////                    toprint = "Successfully Added!";
////                }
//                toprint = "successfully added";
//                JOptionPane.showMessageDialog(null, toprint);
//
//            }
//        });
//
//        JPanel deletePanel = new JPanel();
//        deletePanel.add(new JLabel("Delete"));
//        deletePanel.add(Box.createHorizontalStrut(500));
//        deletePanel.add(new JLabel("Name"));
//        deletePanel.add(deleteTextField);
//        deletePanel.add(deleteSubmit);
//
//        deleteSubmit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String name = deleteTextField.getText();
//                dbHandler.deleteVenue(name);
//                String toprint = "";
//                if (dbHandler.getRowCount() == 0) {
//                    toprint = "There is no venue with that name :(";
//                } else {
//                    toprint = "Successfully deleted";
//                }
//                JOptionPane.showMessageDialog(null, toprint);
//            }
//        });
//
//
//        JPanel updatePanel = new JPanel();
//        updatePanel.add(new JLabel("Update"));
//        updatePanel.add(Box.createHorizontalStrut(500));
//        updatePanel.add(new JLabel("Name"));
//        updatePanel.add(updateNameTextField);
//
//
//        updatePanel.add(Box.createHorizontalStrut(50));
//        updatePanel.add(new JLabel("Address"));
//        updatePanel.add(updateAddressTextField);
//
//        updatePanel.add(Box.createHorizontalStrut(50));
//        updatePanel.add(new JLabel("Capacity"));
//        updatePanel.add(updateCapacityTextField);
//
//        updatePanel.add(Box.createHorizontalStrut(65));
//        updatePanel.add(new JLabel("ID"));
//        updatePanel.add(updateIDTextField);
//
//        updatePanel.add(updateSubmit);
//
//        updateSubmit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String name = updateNameTextField.getText();
//                String add = updateAddressTextField.getText();
//                int cap = Integer.parseInt(updateCapacityTextField.getText());
//                int id = Integer.parseInt(updateIDTextField.getText());
//                dbHandler.updateVenue(name, add, cap, id);
//                String toprint = "";
//                // will change
////                if (dbHandler.getDuplicate()) {
////                    toprint = "This venue already exist, please update if needed!";
////                } else {
////                    toprint = "Successfully Added!";
////                }
//                toprint = "successfully updated";
//                JOptionPane.showMessageDialog(null, toprint);
//
//            }
//        });
//
//        JPanel switchScreenButtonPanel = new JPanel();
//
//        JButton selAndProj = new JButton("Selection and Projection");
//
//        switchScreenButtonPanel.add(selAndProj);
//
//        selAndProj.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                insertPanel.setVisible(false);
//                deletePanel.setVisible(false);
//                updatePanel.setVisible(false);
//                switchScreenButtonPanel.setVisible(false);
//            }
//        });
//
//        // Add panels to the frame
//        add(insertPanel);
//        add(deletePanel);
//        add(updatePanel);
//        add(switchScreenButtonPanel);
//
//        // aggregation with group by
//
//        JPanel groupBy = new JPanel();
//        JButton groupBySubmit = new JButton("Submit");
//        JLabel groupByDescription = new JLabel("Get total attendance per event");
//        groupBySubmit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, dbHandler.aggregateVenueTotalEventMaxAttendance());
//            }
//        });
//
//        // aggregation with having
//
//        JPanel having = new JPanel();
//        JButton havingSubmit = new JButton("Submit");
//        JLabel havingDescription = new JLabel("Enter minimum attendance");
//        JTextField havingTextField = new JTextField();
//        groupBySubmit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int minCapacity = Integer.parseInt(havingTextField.getText());
//                JOptionPane.showMessageDialog(null, dbHandler.aggregateVenueCapacityByEventHaving(minCapacity));
//            }
//        });
//
//    }
//    //This method is required by ListSelectionListener.
//    public void valueChanged(ListSelectionEvent e) {
//        if (!e.getValueIsAdjusting()) {
//
//        }
//    }
//
//
//}
package ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JCheckBox;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.JScrollPane;

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
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createAndShowUI();
    }
    public void createAndShowUI() {

        setLayout(new GridLayout(3, 4));

        // Create panels for each section

        JPanel panel1 = new JPanel();

        JTextField nameTextField = new JTextField(20);
        JTextField addressTextField = new JTextField(20);
        JTextField capacityTextField = new JTextField(20);
        JTextField IDTextField = new JTextField(20);
        JButton insertSubmit = new JButton("Submit");


        JTextField deleteTextField = new JTextField(20);
        JButton deleteSubmit = new JButton("Submit");

        JTextField updateNameTextField = new JTextField(20);
        JTextField updateAddressTextField = new JTextField(20);
        JTextField updateCapacityTextField = new JTextField(20);
        JTextField updateIDTextField = new JTextField(20);
        JButton updateSubmit = new JButton("Submit");

//        String name1 = "error";
//        String add = "err";
//        int cap = 0;
//        int ID = 1;

        panel1.add(new JLabel("Insert Venue"));
        panel1.add(Box.createHorizontalStrut(500));
        panel1.add(new JLabel("Venue Name"));
        panel1.add(nameTextField);


        panel1.add(Box.createHorizontalStrut(50));
        panel1.add(new JLabel("Venue Address"));
        panel1.add(addressTextField);

        panel1.add(Box.createHorizontalStrut(50));
        panel1.add(new JLabel("Venue Capacity"));
        panel1.add(capacityTextField);

        panel1.add(Box.createHorizontalStrut(65));
        panel1.add(new JLabel("Event ID"));
        panel1.add(IDTextField);

        panel1.add(insertSubmit);
        insertSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameTextField.getText();
                    String add = addressTextField.getText();
                    int cap = Integer.parseInt(capacityTextField.getText());
                    int id = Integer.parseInt(IDTextField.getText());
                    VenueModel toadd = new VenueModel(name, add, cap, id);
                    dbHandler.insertVenue(toadd);
                    String toprint = "";
                    if (dbHandler.getDuplicate()) {
                        toprint = "This venue already exists, please update if needed!";
                    } else {
                        toprint = "Successfully Added!";
                    }
                    JOptionPane.showMessageDialog(null, toprint);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Invalid parameters, try again");
                }

            }
        });

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Delete Venue"));
        panel2.add(Box.createHorizontalStrut(500));
        panel2.add(new JLabel("Venue Name"));
        panel2.add(deleteTextField);
        panel2.add(deleteSubmit);

        deleteSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = deleteTextField.getText();
                    dbHandler.deleteVenue(name);
                    String toprint = "";
                    if (dbHandler.getRowCount() == 0) {
                        toprint = "There is no venue with that name :(";
                    } else {
                        toprint = "Successfully deleted";
                    }
                    JOptionPane.showMessageDialog(null, toprint);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Invalid parameters, try again");
                }
            }
        });



        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Update Venue"));
//        panel3.add(Box.createHorizontalStrut(500));
        panel3.add(new JLabel("Venue Name"));
        panel3.add(updateNameTextField);


        panel3.add(Box.createHorizontalStrut(50));
        panel3.add(new JLabel("Venue Address"));
        panel3.add(updateAddressTextField);

        panel3.add(Box.createHorizontalStrut(50));
        panel3.add(new JLabel("Venue Capacity"));
        panel3.add(updateCapacityTextField);

        panel3.add(Box.createHorizontalStrut(65));
        panel3.add(new JLabel("Event ID"));
        panel3.add(updateIDTextField);

        panel3.add(updateSubmit);

        updateSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String name = updateNameTextField.getText();
                    String add = updateAddressTextField.getText();
                    int cap = Integer.parseInt(updateCapacityTextField.getText());
                    int id = Integer.parseInt(updateIDTextField.getText());
                    dbHandler.updateVenue(name, add, cap, id);
                    String toprint = "";
                if (dbHandler.getEventExists()) {
                    toprint = "The event has been updated";
                } else {
                    toprint = "This event doesnt exist yet :(";
                }
                    JOptionPane.showMessageDialog(null, toprint);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Invalid parameters, try again");
                }

            }
        });

        JPanel selectionPanel = new JPanel();

        JTextField selectionTextField = new JTextField(20);
        JButton selectionSubmit = new JButton("Submit");

        selectionSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = selectionTextField.getText();
                String toprint = "";
                if (dbHandler.selectVenue(name) == null) {
                    toprint = "This venue name does not exist";
                } else {
                    VenueModel currVenue = dbHandler.selectVenue(name);

                    if (currVenue != null) {
                        toprint = "Here is the venue you selected: \nVenue name: " + currVenue.getName() + ", Venue address: " + currVenue.getAddress()
                                + ", Venue Capacity: " + currVenue.getCapacity() + ", Event ID: " + currVenue.getId();
                    }
//                    else {
//                        toprint = "this venue name does not exist";
//                    }
                }
                JOptionPane.showMessageDialog(null, toprint);
            }
        });

        selectionPanel.add(new JLabel("Select a venue to view"));
        selectionPanel.add(Box.createHorizontalStrut(500));
        selectionPanel.add(new JLabel("Venue Name"));
        selectionPanel.add(selectionTextField);
        selectionPanel.add(selectionSubmit);

        JPanel projectionPanel = new JPanel();
        JCheckBox namebox = new JCheckBox("Venue Name");
        JCheckBox addressbox = new JCheckBox("Venue Address");
        JCheckBox capbox = new JCheckBox("Venue Capacity");
        JCheckBox idbox = new JCheckBox("Event ID");

        JButton projectionSubmit = new JButton("Submit");

        projectionPanel.add(new JLabel("Choose columns to view in the venue table"));
        projectionPanel.add(namebox);
        projectionPanel.add(addressbox);
        projectionPanel.add(capbox);
        projectionPanel.add(idbox);
        projectionPanel.add(projectionSubmit);

        projectionSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean nameSelected = namebox.isSelected();
                    boolean addressSelected = addressbox.isSelected();
                    boolean capSelected = capbox.isSelected();
                    boolean idSelected = idbox.isSelected();

                    List<String> listOfString = new ArrayList<>();

                    if (nameSelected) {
                        listOfString.add("venue_name");
                    }

                    if (addressSelected) {
                        listOfString.add("venue_address");
                    }

                    if (capSelected) {
                        listOfString.add("venue_capacity");
                    }

                    if (idSelected) {
                        listOfString.add("event_id");
                    }

                    List<List> toSelect = dbHandler.projectVenue("", listOfString);

                    int maxLength = Integer.max(toSelect.get(0).size(), Integer.max(toSelect.get(1).size(), Integer.max(toSelect.get(2).size(), toSelect.get(3).size())));

                    int counter = 0;
                    String message = "Here are your specified columns in the venue table: \n";

                    while (counter < maxLength) {
                        if (!toSelect.get(0).isEmpty()) {
                            message = message.concat("Venue name: " + toSelect.get(0).get(counter) + ", \t\t");
                        }
                        if (!toSelect.get(1).isEmpty()) {
                            message = message.concat("Venue address: " + toSelect.get(1).get(counter) + ", \t\t");
                        }
                        if (!toSelect.get(2).isEmpty()) {
                            message = message.concat("Venue capacity: " + toSelect.get(2).get(counter) + ", \t\t");
                        }
                        if (!toSelect.get(3).isEmpty()) {
                            message = message.concat("Event ID: " + toSelect.get(3).get(counter) + ", \t\t");
                        }
                        message = message.concat("\n");
                        counter++;
                    }

                    System.out.println(toSelect.get(0));
                    JOptionPane.showMessageDialog(null, message);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Please check at least one box");
                    exception.printStackTrace();
                }

            }
        });

        // aggregation with group by

        JPanel groupBy = new JPanel();
        JButton groupBySubmit = new JButton("Submit");
        JLabel groupByDescription = new JLabel("Get total capacity for each event");
        groupBySubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(dbHandler.aggregateVenueTotalEventMaxAttendance());
                String message = "Here are max capacities for each event: \n";
                message = message.concat(dbHandler.aggregateVenueTotalEventMaxAttendance());
                JOptionPane.showMessageDialog(null, message);
            }
        });

        groupBy.add(groupByDescription);
        groupBy.add(groupBySubmit);

        // aggregation with having

        JPanel having = new JPanel();
        JButton havingSubmit = new JButton("Submit");
        JLabel havingDescription = new JLabel("Enter minimum attendance of the event");
        JTextField havingTextField = new JTextField(20);

        having.add(havingDescription);
        having.add(havingTextField);
        having.add(Box.createHorizontalStrut(50));
        having.add(havingSubmit);
        havingSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int minCapacity = Integer.parseInt(havingTextField.getText());
                    String message = "These events have a higher attendance than the number you specified: \n";
                    message = message.concat(dbHandler.aggregateVenueCapacityByEventHaving(minCapacity));
                    JOptionPane.showMessageDialog(null, message);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Invalid parameters, try again");
                }
            }
        });

        // division

        JPanel division = new JPanel();
        JButton divisionSubmit = new JButton("Submit");
        JLabel divisionDescription = new JLabel("Average capacity for each event by venue");

        division.add(divisionDescription);
        division.add(divisionSubmit);

        divisionSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Integer, Double> divisionResult = dbHandler.calculateAverageCapacityPerEvent();
                String message = "Here are the average attendances for each event: \n";
                for (Map.Entry<Integer, Double> entry : divisionResult.entrySet()){
                    message = message.concat("EventID: " + entry.getKey() + " Average Capacity: " + entry.getValue() + "\n");
                }
                JOptionPane.showMessageDialog(null, message);
            }
        });

        JPanel nested = new JPanel();
        JButton nestedSubmit = new JButton("Submit");
        JLabel nestedDescription = new JLabel("Minimum average venue capacity");

        nested.add(nestedDescription);
        nested.add(nestedSubmit);

        nestedSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String message = "Minimum average venue capacity: \n" + dbHandler.averageVenueCapacity();
                    JOptionPane.showMessageDialog(null, message);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "didn't work");
                }
            }
        });

        JPanel join = new JPanel();
        JButton joinSubmit = new JButton("Submit");
        JLabel joinDescription = new JLabel("Join venue and event");
        join.add(joinSubmit);
        join.add(joinDescription);

        joinSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dbHandler.joinVenueAndEvent();
                    System.out.println("join function");
                    String message = "Joined venue and event";
                    JOptionPane.showMessageDialog(null, message);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "didn't work");
                }
            }
        });


        // Add panels to the frame
        add(panel1);
        add(panel2);
        add(panel3);
        add(selectionPanel);
        add(projectionPanel);
        add(groupBy);
        add(having);
        add(division);
        add(nested);
        add(join);


    }
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

        }
    }


}