package GUI;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeStrategy implements IMsgStrategy {
    @Override
    public javafx.scene.control.Label createLabel () {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
  //  System.out.println( sdf.format(cal.getTime()) );

    javafx.scene.control.Label label = new javafx.scene.control.Label();
    label.setText("[" + sdf.format(cal.getTime()) + "]");
    label.setPrefWidth(65);
    label.setPrefHeight(30);
    label.setAlignment(Pos.CENTER_LEFT);
    label.setTextFill(Color.BLUE);
    label.setFont(javafx.scene.text.Font.font("Helvetica", 14));
    return label;
    }
}
