// Name: Nima Memarzadeh
// Date: 04/21/2025
// Assignment: M1: Programming Assignment

// This program displays 4 random card images from a specified folder and allows the user to refresh the display.
// It ensures only cards numbered 1 to 52 are used, excluding any back or joker images.

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

public class Main extends Application {

    private final ImageView[] cardViews = new ImageView[4];
    private static final String CARD_DIRECTORY = "cards";

    @Override
    public void start(Stage primaryStage) {
        HBox cardBox = new HBox(20);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setPadding(new Insets(20));

        // Initialize ImageView objects using lambda expression
        IntStream.range(0, 4).forEach(i -> {
            cardViews[i] = new ImageView();
            cardViews[i].setFitHeight(200);
            cardViews[i].setFitWidth(140);
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
        File cardFolder = new File(CARD_DIRECTORY);
        if (!cardFolder.exists() || !cardFolder.isDirectory()) {
            showAlert("Error", "Card directory not found!", "Ensure the 'cards' folder exists.");
            return;
        }

        // Filter only valid card numbers (1-52) and ignore jokers or back images
        List<String> validCards = Arrays.stream(Objects.requireNonNull(cardFolder.listFiles((dir, name) -> name.endsWith(".png"))))
                .map(File::getName)
                .filter(name -> {
                    try {
                        int num = Integer.parseInt(name.replace(".png", ""));
                        return num >= 1 && num <= 52; // Only allow 1-52
                    } catch (NumberFormatException e) {
                        return false; // Ignore non-numeric files
                    }
                })
                .map(name -> new File(cardFolder, name).getPath()) // Convert to full file path
                .collect(Collectors.toList());

        if (validCards.size() < 4) {
            showAlert("Error", "Not enough card images!", "Ensure at least 4 valid card images (1-52.png) are in the 'cards' folder.");
            return;
        }

        // Shuffle and select 4 random images
        Collections.shuffle(validCards);
        List<String> chosenCards = validCards.subList(0, 4);

        // Set images using lambda expression
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
