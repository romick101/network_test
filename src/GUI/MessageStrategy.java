package GUI;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MessageStrategy implements IMsgStrategy {
    @Override
    public Label createLabel (String content) {
        Label label = new Label();
        label.setText(content);
        label.setPrefWidth(content.length()*10);
        label.setPrefHeight(25);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setTextFill(Color.BLACK);
        label.setFont(Font.font("Helvetica", 13));
        //   label.setStyle("-fx-background-color: blue;");// зарисуем фон в оранжевый
        return label;
    }
}
