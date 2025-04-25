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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleStyleApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // 1) HBox with 20px horizontal gap, 10px padding, and CENTER alignment on the cross-axis (vertical)
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);
        hBox.getStyleClass().add("main-hbox");

        // 2) First "cell": Replace Pane with StackPane for consistent centering
        StackPane box = new StackPane();
        // box.setPrefSize(80, 200);
        box.getStyleClass().addAll("border", "cell-stack");
        Circle c1 = new Circle(30);      // No need for x,y coordinates in StackPane
        c1.getStyleClass().add("plaincircle");
        box.getChildren().add(c1);

        // 3) Cells 2–4: StackPanes (all 80×200) to auto-center each circle vertically
        StackPane cell2 = new StackPane();
        // cell2.setPrefSize(80, 200);
        cell2.getStyleClass().add("cell-stack");
        Circle c2 = new Circle(30);
        c2.getStyleClass().addAll("plaincircle", "circleborder");
        cell2.getChildren().add(c2);

        StackPane cell3 = new StackPane();
        // cell3.setPrefSize(80, 200);
        cell3.getStyleClass().add("cell-stack");
        Circle c3 = new Circle(30);
        c3.setId("redcircle");
        cell3.getChildren().add(c3);

        StackPane cell4 = new StackPane();
        // cell4.setPrefSize(80, 200);
        cell4.getStyleClass().add("cell-stack");
        Circle c4 = new Circle(30);
        c4.setId("greencircle");
        cell4.getChildren().add(c4);

        // Add all containers to the parent HBox layout
        hBox.getChildren().addAll(box, cell2, cell3, cell4);

        // Configure scene with dimensions and attach external stylesheet
        Scene scene = new Scene(hBox, 380, 240);
        scene.getStylesheets().add("mystyle.css");

        primaryStage.setTitle("Circle Style App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
