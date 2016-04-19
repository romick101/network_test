package GUI;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeStrategy implements IMsgStrategy {
    @Override
    public javafx.scene.control.Label createLabel (String content) {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    javafx.scene.control.Label label = new javafx.scene.control.Label();
    label.setText("[" + sdf.format(new Date()) + "]");
    label.setPrefWidth(75);
    label.setPrefHeight(25);
    label.setAlignment(Pos.CENTER_LEFT);
    label.setTextFill(Color.BLUE);
    label.setFont(javafx.scene.text.Font.font("Helvetica", 14));
    return label;
    }
}
