package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Set;

public class KrydsOgBolle extends Stage {
    private boolean playerTurn = true;
    private Text whosTurnText = new Text();
    private Button[][] buttons = new Button[3][3];
    private HBox[] hBoxes = new HBox[3];

    public KrydsOgBolle(){
        setResizable(false);
        setTitle("Tic Tac Toe");
        VBox vBox = new VBox();
        content(vBox);
        Scene scene = new Scene(vBox);
        setScene(scene);
        this.show();

    }

    public void content(VBox vBox){
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.BASELINE_CENTER);


        vBox.getChildren().add(whosTurnText);
        updateWhosTurnText();

        HBox hBoxTop = new HBox();
        hBoxTop.setSpacing(20);
        HBox hBoxMiddel = new HBox();
        hBoxMiddel.setSpacing(20);
        HBox hBoxBottom = new HBox();
        hBoxBottom.setSpacing(20);
        hBoxes[0] = hBoxTop;
        hBoxes[1] = hBoxMiddel;
        hBoxes[2] = hBoxBottom;

        vBox.getChildren().add(hBoxTop);
        vBox.getChildren().add(hBoxMiddel);
        vBox.getChildren().add(hBoxBottom);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Button button = new Button();
                button.setOnAction(event -> {
                    setButtonAndplayerTurn(button);
                });
                button.setPrefSize(100, 100);
                buttons[i][j] = button;
                hBoxes[i].getChildren().add(button);
            }
        }


    }

    public void updateWhosTurnText(){
        if(playerTurn){
            whosTurnText.setText("Player X's turn");
        }else{
            whosTurnText.setText("Player O's turn");
        }
    }

    public void setButtonAndplayerTurn(Button button){
        if (button.getText() != null && !button.getText().isEmpty()) return;

        if (playerTurn){
            button.setText("X");
            playerTurn = false;
        } else {
            button.setText("O");
            playerTurn = true;
        }
        button.setDisable(true);

        if (!checkForWinner()) {
            updateWhosTurnText();
        }
    }

    private String at(int r, int c) {
        return buttons[r][c].getText();
    }

    public boolean checkForWinner() {
        // Rows
        for (int r = 0; r < 3; r++) {
            String a = at(r, 0);
            if (!a.isEmpty() && a.equals(at(r, 1)) && a.equals(at(r, 2))) {
                endGame(a);
                return true;
            }
        }
        // Columns
        for (int c = 0; c < 3; c++) {
            String a = at(0, c);
            if (!a.isEmpty() && a.equals(at(1, c)) && a.equals(at(2, c))) {
                endGame(a);
                return true;
            }
        }
        // Diagonals
        String center = at(1, 1);
        if (!center.isEmpty()) {
            if (center.equals(at(0, 0)) && center.equals(at(2, 2))) {
                endGame(center);
                return true;
            }
            if (center.equals(at(0, 2)) && center.equals(at(2, 0))) {
                endGame(center);
                return true;
            }
        }

        // Draw: no empty cells left
        boolean anyEmpty = false;
        for (int r = 0; r < 3 && !anyEmpty; r++) {
            for (int c = 0; c < 3; c++) {
                if (at(r, c).isEmpty()) {
                    anyEmpty = true;
                    break;
                }
            }
        }
        if (!anyEmpty) {
            endGame("draw");
            return true;
        }
        return false;
    }

    private void endGame(String result) {
        if ("draw".equals(result)) {
            whosTurnText.setText("Draw!");
        } else {
            whosTurnText.setText("Winner: " + result);
        }
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c].setDisable(true);
            }
        }
    }

}
