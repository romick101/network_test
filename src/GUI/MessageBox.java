package GUI;

public class MessageBox extends IMessageContainer {
    @Override
    public void addMessage (Message in) {
        Messages.getChildren().add(in.getContainer());
    }
}
