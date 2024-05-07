package com.example.lab_7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    public TextField diapMin;

    @FXML
    public TextField diapMax;

    @FXML
    public TextField textInp;

    @FXML
    public TextField textOut;


    int min = Integer.parseInt(diapMin.getText());
    int max = Integer.parseInt(diapMax.getText());
    int inp = Integer.parseInt(textInp.getText());
    int temp = (max-min)/2;


    public void initialize(){
        // сняли фокусировку, чтобы не сбить промт
        restrictions(diapMax);
        restrictions(diapMin);
        restrictions(textInp);
        restrictions(textOut);


        // запретил ввод в поле для вывода
        textOut.setOnKeyTyped(keyEvent -> {
            keyEvent.consume();
        });

        guessing(min, max);
    }

    public void restrictions(TextField textField){
        textField.setFocusTraversable(false);
    }

    public void guessing(int min, int max){
        temp = (max-min)/2;
        textOut.setText(String.valueOf(temp));
    }


    public int modeMax(int min){
        min = temp;
        return min;
    }

    public int modeMin(int max){
        max = temp;
        return max;
    }

    public void check(int temp, int inp){
        if (temp == inp) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText(null);
            alert.setContentText("You've guessed the number! Would you like to play again?");
            alert.showAndWait();

            // Reset all text fields
            resetTextFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            if (inp == 0) {
                alert.setTitle("Cheating Detected!");
                alert.setContentText("Cheating detected! Would you like to start a new game?");
            } else {
                alert.setTitle("Finish Early?");
                alert.setContentText("Do you want to finish the game early?");
            }
            alert.showAndWait();

            // Reset all text fields
            resetTextFields();
        }
    }

    private void resetTextFields() {
        diapMin.clear();
        diapMax.clear();
        textInp.clear();
        textOut.clear();
    }

    @FXML
    public void onMaxClicked(ActionEvent actionEvent){
        min = modeMax(min);
        guessing(min, max);
        check(temp, inp);
    }

    @FXML
    public void onMinClicked(ActionEvent actionEvent){
        max = modeMin(max);
        guessing(min, max);
        check(temp, inp);
    }

    @FXML
    public void onRightClicked(ActionEvent actionEvent){
        guessing(min, max);
        check(temp, inp);
    }
}