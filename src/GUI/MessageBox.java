package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;

public class MessageBox {
    VBox Messages;
    ScrollBar sc;

    public VBox getMessages () {
        return Messages;
    }
    public MessageBox() {
        sc = new ScrollBar();
        sc.setMin(0);
        sc.setMax(350);
        sc.setOrientation(Orientation.VERTICAL);

        Messages = new VBox();
        Messages.setPrefSize(200,550);
        Messages.setStyle("-fx-background-color: black;");

        sc.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                Messages.setLayoutY(-new_val.doubleValue());
            }
        });

    }
    public void addMessage (Message in) {
        Messages.getChildren().add(in.getMessage());
    }
}
