package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public abstract class IMessageContainer {
    BorderPane root;
    VBox Messages;
    public BorderPane getRoot () {
        return root;
    }
    public IMessageContainer() {
        System.out.println("Запахал абстрактный конструктор");
        Messages = new VBox();
        Messages.setPrefSize(300,500);
        Messages.setStyle("-fx-background-color: black;");

        ScrollPane s1 = new ScrollPane(Messages);
        s1.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        s1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Messages.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
                s1.setVvalue((Double)newValue );
            }
        });

        root = new BorderPane(s1);
        root.setPrefSize(315,550);
        root.setPadding(new Insets(0));
    }
    abstract void addMessage (Message in);
}
