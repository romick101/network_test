package Client;

import GUI.Message;
import GUI.MessageBox;
import MessageProtocol.IProtocol;
import Server.Response;
import Server.UserType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.Socket;


public class Client extends Application {
    public String name = null;
    public IProtocol protocol = null;
    int serverPort = 4444;
    String address = "127.0.0.1";
    Writer _writer;
    Listener _listener;
    Socket _socket;

    public MessageBox messages;

    TextField input = new TextField();

private Parent CreateContent () {
    messages = new MessageBox();

    messages.addMessage(parceMsg("Choose a protocol."));

    input.setOnAction(event -> {
        _writer.writeMsg(input.getText());
        input.clear();
    });

    BorderPane bp = new BorderPane();
    bp.setPadding(new Insets(0,0,0,0));
    VBox root = new VBox();
    root.setPrefSize(300,525);
    root.getChildren().add(bp);
    root.getChildren().add(messages.getRoot());
    root.getChildren().add(input);
    return root;
}
    @Override
    public void start (Stage primaryStage) {
        primaryStage.setScene(new Scene(CreateContent()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    @Override
    public void stop () throws Exception {
        _socket.close();
    }

    public Client () {
        try {
            _socket = new Socket(address, serverPort);
            _writer = new Writer(_socket.getOutputStream(), this);
            _listener = new Listener(_socket.getInputStream(), this, data -> {
                Platform.runLater(() -> {
                    messages.addMessage(parceMsg((Response)data));
                });
            });
            _writer.start();
            _listener.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Message parceMsg(Response msg) {
        String name = msg.getName(); // 004
        String data = msg.getData();
        UserType type = msg.getType();
        return new Message(name + ":", data, type);
    }

    private Message parceMsg(String rawData) {
        if (rawData.contains(":")) {
            String[] parts = rawData.split(":");
            String name = parts[0]; // 004
            String data = rawData.substring(name.length() + 1, rawData.length());
            return new Message(name + ":", data, UserType.System);
        } else
        {
            return new Message("System: ", rawData, UserType.System);
        }
    }
    public static void main (String [] args) {
        launch(args);
    }


}
