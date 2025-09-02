package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class PrimaryWindow extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Kryds og Bolle");
        primaryStage.setResizable(false);
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox);
        content(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void content(VBox vBox) {
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.BASELINE_CENTER);

        Text text = new Text("Welcome to Victors Tic Tac Toe");
        vBox.getChildren().add(text);

        Button startButton = new Button("Start Game");
        vBox.getChildren().add(startButton);
        startButton.setOnAction(event -> {
            new KrydsOgBolle().show();
        });

    }
}
