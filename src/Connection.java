import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Connection extends Thread {
    private Socket socket;
    BufferedReader in;
    PrintWriter out;
    String name = "";
    public Connection (Socket in_socket) {
        name = "<username>";
        socket = in_socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run () {
        try {
            ConnectionDB DB = ConnectionDB.getInstance();
            while (true) {
                    String line = in.readLine();
                    System.out.println("Got " + line);
                    DB.sendToAll(line);
                }
        } catch (SocketException s) {
            System.out.println("Client dropped");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}