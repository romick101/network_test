package GUI;

import javafx.scene.layout.BorderPane;

public class ProxyMessageBox extends IMessageContainer {
    MessageBox box = new MessageBox();
    @Override
    public void addMessage (Message in) {
        if (!in.getData().isEmpty() && in.getData().length() < 100)
        box.addMessage(in);
    }
    @Override
    public BorderPane getRoot  () {
       return box.getRoot();
    }
}
