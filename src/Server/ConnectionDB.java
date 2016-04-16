package Server;

import java.util.ArrayList;
import java.util.List;

public class ConnectionDB {
    private ConnectionDB () {}
    private static ConnectionDB _instance;
    public static ConnectionDB getInstance () {
        if (_instance == null) {
            _instance = new ConnectionDB();
        }
        return _instance;
    }

//    private List<Client.Client> connections = new ArrayList<>();
//
//
//    public synchronized void addConnection (Client.Client in) {
//        connections.add(in);
//    }
//    public synchronized void sendToAll (String line) {
//        for (Client.Client key: connections) {
//                key.connection.out.println(line);
//                key.connection.out.flush();
//        }
//    }

    private List<Connection> connections = new ArrayList<>();
    public synchronized void addConnection (Connection in) {
        connections.add(in);
    }
    public synchronized void sendToOne (Connection target, String line) {
        target.out.println(line);
        target.out.flush();
    }
    public synchronized void sendToAll (String line) {
        for (Connection key: connections) {
                key.out.println(line);
                key.out.flush();
        }
    }
}