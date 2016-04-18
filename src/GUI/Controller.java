package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Controller {
    private IMsgStrategy _msgStrategy;

    public Scene createScene(){
        return new Scene(buildMsg(), 320, 240);
    }
    public void setStrategy (IMsgStrategy in) {
        _msgStrategy = in;
    }
    public  Label createLabel(){
        return _msgStrategy.createLabel();
    }
    public HBox buildMsg () {
        setStrategy(new UsernameStrategy());
        HBox root = new HBox();
        root.getChildren().add(createLabel());
        setStrategy(new TimeStrategy());
        root.getChildren().add(createLabel());
        setStrategy(new MessageStrategy());
        root.getChildren().add(createLabel());
        return root;
    }
}
