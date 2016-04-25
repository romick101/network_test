package GUI;

import Server.UserType;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class UserMessageBuilder extends MessageBuilder{
    @Override
    public void SetName(String inName) {
        Text username = new Text();
        username.setWrappingWidth(_NAMESIZE);
        username.setText(inName);
        if (message.getType() == UserType.Base)
            username.setFill(Color.DARKCYAN);
        if (message.getType() == UserType.Extended)
            username.setFill(Color.GOLD);
        if (message.getType() == UserType.System)
            username.setFill(Color.CRIMSON);
        message.getContainer().getChildren().add(username);
    }
    @Override
    public void SetData(String inData) {
        Text data = new Text();
        data.setWrappingWidth(_DATASIZE);
        data.setText(inData);
        data.setFill(Color.AQUA);
        message.getContainer().getChildren().add(data);
    }
    @Override
    public void SetType(UserType type) {
        message.setType(type);
    }
}
