package GUI;

import Server.UserType;
import javafx.scene.layout.HBox;

public class Message {
    private UserType type;
    private String data;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    private HBox messageContainer;

    public HBox getContainer() {
        return messageContainer;
    }
    public Message () {
        messageContainer = new HBox();
        messageContainer.setPrefSize(250, 20);
    }
}
