// Name: Nima Memarzadeh
// Date: 05/09/2025
// Assignment: M10: Programming Assignment

// This Java program creates a GUI application that
//  allows users to display and update fan information.
// It connects to a MySQL database to retrieve and update fan data.
// The GUI consists of text fields for entering fan ID, first name,
//  last name, and favorite team,
//  along with buttons to display and update the information.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Main class for the Fan Info application, extending JFrame to create a GUI window.
public class FanInfoApp extends JFrame {

  // Text field for fan ID input.
  private JTextField txtID;
  // Text field for fan first name input.
  private JTextField txtFirstName;
  // Text field for fan last name input.
  private JTextField txtLastName;
  // Text field for fan favorite team input.
  private JTextField txtFavoriteTeam;

  // Button to trigger displaying fan information.
  private JButton btnDisplay;
  // Button to trigger updating fan information.
  private JButton btnUpdate;

  // Database handler for fan data operations.
  private FanDatabase db;

  // Constructor for the FanInfoApp.
  public FanInfoApp() {
    // Set the title of the window.
    setTitle("Fan Info");
    // Set the size of the window.
    setSize(400, 300);
    // Set the default close operation (exit when window is closed).
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    // Set the layout manager to null for absolute positioning.
    setLayout(null);

    // Create and add a label for the ID field.
    JLabel lblID = new JLabel("ID:");
    lblID.setBounds(30, 30, 100, 25);
    add(lblID);

    // Create and add the text field for ID.
    txtID = new JTextField();
    txtID.setBounds(140, 30, 200, 25);
    add(txtID);

    // Create and add a label for the First Name field.
    JLabel lblFirstName = new JLabel("First Name:");
    lblFirstName.setBounds(30, 70, 100, 25);
    add(lblFirstName);

    // Create and add the text field for First Name.
    txtFirstName = new JTextField();
    txtFirstName.setBounds(140, 70, 200, 25);
    add(txtFirstName);

    // Create and add a label for the Last Name field.
    JLabel lblLastName = new JLabel("Last Name:");
    lblLastName.setBounds(30, 110, 100, 25);
    add(lblLastName);

    // Create and add the text field for Last Name.
    txtLastName = new JTextField();
    txtLastName.setBounds(140, 110, 200, 25);
    add(txtLastName);

    // Create and add a label for the Favorite Team field.
    JLabel lblFavoriteTeam = new JLabel("Favorite Team:");
    lblFavoriteTeam.setBounds(30, 150, 100, 25);
    add(lblFavoriteTeam);

    // Create and add the text field for Favorite Team.
    txtFavoriteTeam = new JTextField();
    txtFavoriteTeam.setBounds(140, 150, 200, 25);
    add(txtFavoriteTeam);

    // Create and add the Display button.
    btnDisplay = new JButton("Display");
    btnDisplay.setBounds(70, 200, 100, 30);
    add(btnDisplay);

    // Create and add the Update button.
    btnUpdate = new JButton("Update");
    btnUpdate.setBounds(220, 200, 100, 30);
    add(btnUpdate);

    // Initialize the database connection.
    db = new FanDatabase();

    // Add an action listener to the Display button.
    btnDisplay.addActionListener(new ActionListener() {
      // Action performed when the Display button is clicked.
      public void actionPerformed(ActionEvent e) {
        displayFan();
      }
    });

    // Add an action listener to the Update button.
    btnUpdate.addActionListener(new ActionListener() {
      // Action performed when the Update button is clicked.
      public void actionPerformed(ActionEvent e) {
        updateFan();
      }
    });

    // Make the window visible.
    setVisible(true);
  }

  // Method to display fan information based on the ID entered.
  private void displayFan() {
    // Get the ID from the text field and parse it to an integer.
    int id = Integer.parseInt(txtID.getText());
    // Retrieve the fan object from the database using the ID.
    Fan fan = db.getFanById(id);

    // If a fan is found, populate the text fields with fan data.
    if (fan != null) {
      txtFirstName.setText(fan.getFirstName());
      txtLastName.setText(fan.getLastName());
      txtFavoriteTeam.setText(fan.getFavoriteTeam());
    } else {
      // If no fan is found, show an error message.
      JOptionPane.showMessageDialog(this, "No fan found with that ID.");
    }
  }

  // Method to update fan information in the database.
  private void updateFan() {
    // Create a new Fan object.
    Fan fan = new Fan();
    // Set the fan's ID, first name, last name, and favorite team from the text fields.
    fan.setId(Integer.parseInt(txtID.getText()));
    fan.setFirstName(txtFirstName.getText());
    fan.setLastName(txtLastName.getText());
    fan.setFavoriteTeam(txtFavoriteTeam.getText());

    // Attempt to update the fan information in the database.
    boolean success = db.updateFan(fan);

    // If the update is successful, show a success message.
    if (success) {
      JOptionPane.showMessageDialog(this, "Fan info updated successfully.");
    } else {
      // If the update fails, show an error message.
      JOptionPane.showMessageDialog(this, "Update failed.");
    }
  }

  // Main method to start the application.
  public static void main(String[] args) {
    // Create a new instance of FanInfoApp, which will create and show the GUI.
    new FanInfoApp();
  }
}
