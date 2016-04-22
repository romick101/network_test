package GUI;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Message {
    private Text username;
    private Text data;
    private HBox message;

    final double _NAMESIZE = 65;
    final double _DATASIZE = 250;

    public Text getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username.setFill(Color.CRIMSON);
        this.username.setWrappingWidth(_NAMESIZE);
        this.username.setText(username);
    }
    public Text getData() {
        return data;
    }
    public void setData(String data) {
        this.data.setFill(Color.YELLOW);
        this.data.setWrappingWidth(_DATASIZE);
        this.data.setText(data);
    }
    public HBox getMessage() {
        return message;
    }

    public Message(String username, String data) {
        message = new HBox();
        message.setPrefSize(250, 20);
        this.username = new Text();
        this.data = new Text();
        this.username.setFill(Color.CRIMSON);
        this.username.setWrappingWidth(_NAMESIZE);
        this.username.setText(username);
        this.data.setFill(Color.AQUA);
        this.data.setWrappingWidth(_DATASIZE);
        this.data.setText(data);
        message.getChildren().add(this.username);
        message.getChildren().add(this.data);
    }
}
