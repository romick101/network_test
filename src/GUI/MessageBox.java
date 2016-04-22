package GUI;

import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;

public class MessageBox {
    VBox root;
    VBox Messages;
    ScrollBar sc;

    public VBox getMessages () {
        return Messages;
    }
    public VBox getRoot () {
        return root;
    }
    public MessageBox() {

//        sc = new ScrollBar();
//       // sc.setLayoutX(300);
//        sc.setPrefHeight(550);
//        sc.setMin(0);
//        sc.setMax(550);
//        sc.setOrientation(Orientation.VERTICAL);

        Messages = new VBox();
        Messages.setPrefSize(250,550);
        Messages.setStyle("-fx-background-color: black;");

//        sc.valueProperty().addListener(new ChangeListener<Number>() {
//            public void changed(ObservableValue<? extends Number> ov,
//                                Number old_val, Number new_val) {
//                Messages.setLayoutY(-new_val.doubleValue());
//            }
//        });
        root = new VBox();
        root.getChildren().add(Messages);
      //  root.getChildren().add(sc);
    }
    public void addMessage (Message in) {
        Messages.getChildren().add(in.getMessage());
    }
}
