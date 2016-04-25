package GUI;

import Server.UserType;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Message {
    private Text username;
    private Text data;
    private UserType type;

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    private HBox messageContainer;

    final double _NAMESIZE = 65;
    final double _DATASIZE = 250;

    public HBox getContainer() {
        return messageContainer;
    }
    public Message () {
        messageContainer = new HBox();
        messageContainer.setPrefSize(250, 20);
    }
//    public Message(String username, String data, UserType type) {
//        messageContainer = new HBox();
//        messageContainer.setPrefSize(250, 20);
//
//        this.username = new Text();
//        this.data = new Text();
//
//        this.type = type;
//
//        switch (type) {
//            case System:
//                this.username.setFill(Color.CRIMSON);
//                break;
//            case Base:
//                this.username.setFill(Color.DARKCYAN);
//                break;
//            case Extended:
//                this.username.setFill(Color.YELLOW);
//                break;
//            default:
//                this.username.setFill(Color.AQUAMARINE);
//                break;
//        }
//
//        this.username.setWrappingWidth(_NAMESIZE);
//        this.username.setText(username);
//
//        this.data.setFill(Color.AQUA);
//        this.data.setWrappingWidth(_DATASIZE);
//        this.data.setText(data);
//
//        messageContainer.getChildren().add(this.username);
//        messageContainer.getChildren().add(this.data);
//    }
}
