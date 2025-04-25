// Name: Nima Memarzadeh
// Date: 04/24/2025
// Assignment: M7: Programming Assignment

// This program creates a JavaFX application that displays 
// four circles with different styles.
// The first circle is placed inside a rectangular box, 
// while the other three circles are positioned horizontally.
// The program uses CSS to style the circles and the box.
// The circles are styled with different colors and borders
// using CSS classes and IDs.

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

// Main application class for displaying styled circles
public class CircleStyleApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create an HBox to arrange the circle containers horizontally
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);
        hBox.getStyleClass().add("main-hbox");

        // Create a StackPane to act as a box with a border for the first circle
        StackPane box = new StackPane();
        box.getStyleClass().addAll("border", "cell-stack");
        // Create the first circle and add a style class
        Circle c1 = new Circle(30);     
        c1.getStyleClass().add("plaincircle");
        box.getChildren().add(c1);

        // Create a StackPane for the second circle
        StackPane cell2 = new StackPane();
        cell2.getStyleClass().add("cell-stack");
        // Create the second circle with additional border style
        Circle c2 = new Circle(30);
        c2.getStyleClass().addAll("plaincircle", "circleborder");
        cell2.getChildren().add(c2);

        // Create a StackPane for the third circle
        StackPane cell3 = new StackPane();
        cell3.getStyleClass().add("cell-stack");
        // Create the third circle and assign a red color via ID
        Circle c3 = new Circle(30);
        c3.setId("redcircle");
        cell3.getChildren().add(c3);

        // Create a StackPane for the fourth circle
        StackPane cell4 = new StackPane();
        cell4.getStyleClass().add("cell-stack");
        // Create the fourth circle and assign a green color via ID
        Circle c4 = new Circle(30);
        c4.setId("greencircle");
        cell4.getChildren().add(c4);

        // Add all StackPanes (each containing a circle) to the HBox
        hBox.getChildren().addAll(box, cell2, cell3, cell4);

        // Create the scene, apply the stylesheet, and set up the stage
        Scene scene = new Scene(hBox, 380, 240);
        scene.getStylesheets().add("mystyle.css");

        primaryStage.setTitle("Circle Style App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}
