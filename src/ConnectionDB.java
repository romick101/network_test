import java.util.ArrayList;
import java.util.List;


public class ConnectionDB {
    private static ConnectionDB _instance;
    private ConnectionDB () {}
    public static ConnectionDB getInstance () {
        if (_instance == null) {
            _instance = new ConnectionDB();
        }
        return _instance;
    }
    private List<Connection> connections = new ArrayList<>();
    public synchronized void addConnection (Connection in) {
        connections.add(in);
    }
    public synchronized void sendToAll (String line) {
        for (Connection c: connections) {
            c.out.println(line);
            c.out.flush();
        }
    }
}