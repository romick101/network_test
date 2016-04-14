import java.io.IOException;
import java.net.ServerSocket;

public class Server
{
    private  int port = 4444;
    public void setPort (int value) {
        port = value;
    }
    public  int getPort () {
        return port;
    }

    public Server () throws IOException {
        ServerSocket server = new ServerSocket(getPort());
        ConnectionDB DB = ConnectionDB.getInstance();
        while (true) {
            System.out.println("Waiting for connections");
            Connection con = new Connection(server.accept());
            con.start();
            System.out.println("Got a client");
        }
    }
    public static void main (String [] args) {
        try {
            Server server = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



















