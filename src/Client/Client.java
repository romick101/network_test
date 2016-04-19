package Client;

import MessageProtocol.IProtocol;

import java.net.Socket;

public class Client  {
    public String name = null;
    public IProtocol protocol = null;
    int serverPort = 4444;
    String address = "127.0.0.1";
    Writer _writer;
    Listener _listener;
    Socket _socket;

//    private TextArea messages = new TextArea();
//
//    private Parent CreateContent () {
//        messages.setPrefHeight(550);
//        TextField input = new TextField();
//        VBox root = new VBox(20, messages, input);
//        root.setPrefSize(600,600);
//        return root;
//    }
//    @Override
//    public void start (Stage primaryStage) {
//        primaryStage.setScene(new Scene(CreateContent()));
//        primaryStage.show();
//    }

    public Client () {
        try {
            _socket = new Socket(address, serverPort);
            _writer = new Writer(_socket.getOutputStream(), this);
            _writer.start();
            _listener = new Listener(_socket.getInputStream());
            _listener.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String [] args) {
        Client c = new Client();
    }


}
