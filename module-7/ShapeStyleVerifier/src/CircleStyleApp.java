// Name: Nima Memarzadeh
// Date: 04/24/2025
// Assignment: M7: Programming Assignment

//This program creates a JavaFX application that displays 
// four circles with different styles.
// The first circle is placed inside a rectangular box, 
// while the other three circles are positioned horizontally.
// The program uses CSS to style the circles and the box.
// The circles are styled with different colors and borders
// using CSS classes and IDs.

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleStyleApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the circles
        Circle circle1 = new Circle(30);
        Circle circle2 = new Circle(30);
        Circle circle3 = new Circle(30);
        Circle circle4 = new Circle(30);

        // Apply CSS classes and IDs
        circle1.getStyleClass().addAll("plaincircle");
        circle2.getStyleClass().addAll("plaincircle", "circleborder");
        circle3.setId("redcircle");
        circle4.setId("greencircle");

        // Create rectangular box that contains only the first circle
        Pane rectangleBox = new Pane();
        rectangleBox.getStyleClass().add("border");
        rectangleBox.setPrefWidth(80);
        rectangleBox.setPrefHeight(200);
        rectangleBox.getChildren().add(circle1);

        // Position the first circle inside the box
        circle1.setCenterX(40);
        circle1.setCenterY(100);

        // Create main pane to hold everything
        Pane mainPane = new Pane();
        mainPane.setPadding(new Insets(10));

        // Position the rectangular box
        rectangleBox.setLayoutX(10);
        rectangleBox.setLayoutY(10);

        // Position the other three circles horizontally
        circle2.setCenterX(120);
        circle2.setCenterY(110);

        circle3.setCenterX(190);
        circle3.setCenterY(110);

        circle4.setCenterX(260);
        circle4.setCenterY(110);

        // Add everything to the main pane
        mainPane.getChildren().addAll(rectangleBox, circle2, circle3, circle4);

        // Create the scene
        Scene scene = new Scene(mainPane, 300, 220);
        scene.getStylesheets().add("mystyle.css");

        // Set up the stage
        primaryStage.setTitle("Circle Style App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
