// Name: Nima Memarzadeh
// Date: 04/21/2025
// Assignment: M1: Programming Assignment


// This program displays 4 random card images from
// a specified folder and allows the user to refresh the display.  
// It uses JavaFX for the GUI and handles image loading and shuffling.
 
package src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main extends Application {

    private final ImageView[] cardViews = new ImageView[4];

    @Override
    public void start(Stage primaryStage) {
        HBox cardBox = new HBox(20);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setPadding(new Insets(20));

        // Initialize ImageView objects
        IntStream.range(0, 4).forEach(i -> {
            cardViews[i] = new ImageView();
            cardViews[i].setFitHeight(200); // Standard height for uniformity
            cardViews[i].setFitWidth(140);  // Set width for better consistency
            cardViews[i].setPreserveRatio(true);
            cardBox.getChildren().add(cardViews[i]);
        });

        // Refresh button
        Button refreshButton = new Button("Refresh Cards");
        refreshButton.setPrefWidth(160);
        refreshButton.setPrefHeight(45);
        refreshButton.setStyle(
            "-fx-background-color: #336699;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5px;"
        );
        refreshButton.setOnAction(e -> shuffleAndDisplayCards());

        // VBox to hold everything
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.getChildren().addAll(cardBox, refreshButton);

        // Set background color
        root.setBackground(new Background(new BackgroundFill(
            Color.rgb(240, 240, 245),
            CornerRadii.EMPTY,
            Insets.EMPTY
        )));

        // Shuffle and display cards initially
        shuffleAndDisplayCards();

        // Create and show scene
        Scene scene = new Scene(root, 750, 500);
        primaryStage.setTitle("Card Display");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void shuffleAndDisplayCards() {
        // Get all PNG files from "cards" folder
        File cardFolder = new File("cards");
        File[] filesInFolder = Optional.ofNullable(cardFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png")))
                                       .orElse(new File[0]);

        if (filesInFolder.length < 4) {
            showAlert("Error", "Not enough card images!", "Ensure at least 4 images are in the 'cards' folder.");
            return;
        }

        // Convert File[] to a List using Streams
        List<String> cardFilePaths = Stream.of(filesInFolder)
                                           .map(File::getPath)
                                           .collect(Collectors.toList());

        // Shuffle and select 4 random images
        Collections.shuffle(cardFilePaths);
        List<String> chosenCards = cardFilePaths.subList(0, 4);

        // Set images
        IntStream.range(0, 4).forEach(i ->
            cardViews[i].setImage(new Image("file:" + chosenCards.get(i)))
        );
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
