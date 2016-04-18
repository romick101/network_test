package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UsernameStrategy implements IMsgStrategy {
    @Override
    public Label createLabel () {
        Label label = new Label();
        label.setText("</username>");
        label.setPrefWidth(95);
        label.setPrefHeight(30);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setTextFill(Color.RED);
        label.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
     //   label.setStyle("-fx-background-color: blue;");// зарисуем фон в оранжевый
        return label;
    }
}
