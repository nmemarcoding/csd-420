package src;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

    // ImageViews for displaying the 4 cards
    private final ImageView[] cardViews = new ImageView[4];

    @Override
    public void start(Stage primaryStage) {
        // HBox to hold card images
        HBox cardBox = new HBox(20);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setPadding(new Insets(20));

        // Initialize ImageView objects using a lambda
        IntStream.range(0, 4).forEach(i -> {
            cardViews[i] = new ImageView();
            cardViews[i].setFitHeight(200);
            cardViews[i].setPreserveRatio(true);
            cardBox.getChildren().add(cardViews[i]);
        });

        // Refresh button with Lambda event handler
        Button refreshButton = new Button("Refresh Cards");
        refreshButton.setPrefWidth(150);
        refreshButton.setPrefHeight(40);
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
        primaryStage.setTitle("Professional Card Display");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void shuffleAndDisplayCards() {
        // Get all PNG files from "cards" folder using a Lambda
        File cardFolder = new File("cards");
        File[] filesInFolder = Optional.ofNullable(cardFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png")))
                                       .orElse(new File[0]);

        if (filesInFolder.length == 0) {
            System.out.println("No PNG files found in 'cards' folder!");
            return;
        }

        // Convert File[] to a List using Streams and Lambdas
        List<String> cardFilePaths = Stream.of(filesInFolder)
                                           .map(File::getPath)
                                           .collect(Collectors.toList());

        // Shuffle and select 4 random images
        Collections.shuffle(cardFilePaths);
        List<String> chosenCards = cardFilePaths.subList(0, 4);

        // Set images in a functional way
        IntStream.range(0, 4).forEach(i -> 
            cardViews[i].setImage(new Image("file:" + chosenCards.get(i)))
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
