package Client;

import GUI.Message;
import GUI.MessageBox;
import MessageProtocol.IProtocol;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public MessageBox messages;

   // public TextArea messages; //OLD
    TextField input = new TextField();

//    private Parent CreateContent () { //OLD
//        messages = new TextArea();
//        messages.setPrefSize(200,550);
//        messages.setWrapText(true);
//
//        messages.appendText("Choose a protocol." + "\n");
//
//        input.setOnAction(event -> {
//            _writer.writeMsg(input.getText());
//            input.clear();
//        });
//        VBox root = new VBox(20, messages, input);
//        root.setPrefSize(300,600);
//        return root;
//    }
private Parent CreateContent () {
    messages = new MessageBox();

    messages.addMessage(parceMsg("Choose a protocol."));

    input.setOnAction(event -> {
        _writer.writeMsg(input.getText());
        input.clear();
    });
    VBox root = new VBox(20, messages.getMessages(), input);
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
//                    messages.appendText(data.toString() + "\n"); //OLD
                    messages.addMessage(parceMsg(data.toString()));
                });
            });
            _writer.start();
            _listener.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Message parceMsg(String rawData) {
        if (rawData.contains(":")) {
            String[] parts = rawData.split(":");
            String name = parts[0]; // 004
            String data = parts[1];
            return new Message(name, data);
        } else
        {
            return new Message("System", rawData);
        }
    }
    public static void main (String [] args) {
        launch(args);
    }


}
