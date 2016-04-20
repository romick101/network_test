package Client;

import MessageProtocol.IProtocol;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    public TextArea messages;
    TextField input = new TextField();

    private Parent CreateContent () {
        messages = new TextArea();
        messages.setPrefSize(200,550);

        messages.appendText("Choose a protocol." + "\n");

        input.setOnAction(event -> {
            _writer.writeMsg(input.getText());
            input.clear();
        });
        VBox root = new VBox(20, messages, input);
        root.setPrefSize(300,600);
        return root;
    }
    @Override
    public void start (Stage primaryStage) {
        primaryStage.setScene(new Scene(CreateContent()));
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
                    messages.appendText(data.toString() + "\n");
                });
            });
            _writer.start();
            _listener.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String [] args) {
        launch(args);
    }


}
