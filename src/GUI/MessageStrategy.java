package GUI;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MessageStrategy implements IMsgStrategy {
    @Override
    public Label createLabel () {
        Label label = new Label();
        label.setText("<message>");
        label.setPrefWidth(80);
        label.setPrefHeight(30);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Helvetica", 13));
        //   label.setStyle("-fx-background-color: blue;");// зарисуем фон в оранжевый
        return label;
    }
}
