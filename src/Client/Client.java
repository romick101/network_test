package Client;

import GUI.*;
import MessageProtocol.IProtocol;
import Server.Response;
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

    public ProxyMessageBox messages;

//    private SystemMessageBuilder systemBuilder;
//    private UserMessageBuilder userBuilder;
    BuilderDirector builder = new BuilderDirector();

    TextField input = new TextField();

private Parent CreateContent () {
    messages = new ProxyMessageBox();
//    systemBuilder = new SystemMessageBuilder();
//    userBuilder = new UserMessageBuilder();


    messages.addMessage(parceMsg("Choose аccount type (base or extended)."));

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
        return builder.build(msg,new UserMessageBuilder());
    }
        private Message parceMsg(String rawData) {
        return builder.build(new Response("System",rawData), new SystemMessageBuilder());
    }
//    private Message parceMsg(String rawData) {
//        systemBuilder.CreateMessage();
//        systemBuilder.SetType(UserType.System);
//        systemBuilder.SetName("System");
//        systemBuilder.SetData(rawData);
//        return systemBuilder.GetMessage();
//    }
    public static void main (String [] args) {
        launch(args);
    }


}
