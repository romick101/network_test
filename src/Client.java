import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public String name = "<username>";
    int serverPort = 4444;
    String address = "127.0.0.1";

    public Connection connection;
    Writer _writer;
    Listener _listener;
    Socket _socket;

    public Client () {
        try {
            _socket = new Socket(address, serverPort);
            _writer = new Writer(new OutputStreamWriter(_socket.getOutputStream()), this);
            _writer.start();
            _listener = new Listener(new InputStreamReader(_socket.getInputStream()));
            _listener.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String [] args) {
        Client c = new Client();
    }


}
