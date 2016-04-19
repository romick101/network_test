package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Controller {
    private IMsgStrategy _msgStrategy;
    public Scene createScene(){
        return new Scene(buildMsg("Name", "Time", "Content"), 320, 240);
    }
    private void setStrategy (IMsgStrategy in) {
        _msgStrategy = in;
    }
    private Label createLabel(String content){
        return _msgStrategy.createLabel(content);
    }
    public HBox buildMsg (String usr, String time,String msg) {
        setStrategy(new UsernameStrategy());
        HBox root = new HBox();
        root.getChildren().add(createLabel(usr));
        setStrategy(new TimeStrategy());
        root.getChildren().add(createLabel(time));
        setStrategy(new MessageStrategy());
        root.getChildren().add(createLabel(msg));
        return root;
    }
}
