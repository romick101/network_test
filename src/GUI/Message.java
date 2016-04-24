package GUI;

import Server.UserType;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Message {
    private Text username;
    private Text data;
    private UserType type;
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

    public Message(String username, String data, UserType type) {
        message = new HBox();
        message.setPrefSize(250, 20);

        this.username = new Text();
        this.data = new Text();

        this.type = type;

        switch (type) {
            case System:
                this.username.setFill(Color.CRIMSON);
                break;
            case Base:
                this.username.setFill(Color.DARKCYAN);
                break;
            case Extended:
                this.username.setFill(Color.YELLOW);
                break;
            default:
                this.username.setFill(Color.AQUAMARINE);
                break;
        }

  //      this.username.setFill(Color.CRIMSON);
        this.username.setWrappingWidth(_NAMESIZE);
        this.username.setText(username);

        this.data.setFill(Color.AQUA);
        this.data.setWrappingWidth(_DATASIZE);
        this.data.setText(data);

        message.getChildren().add(this.username);
        message.getChildren().add(this.data);
    }
}
