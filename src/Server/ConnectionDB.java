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
    private List<Connection> connections = new ArrayList<>();
    public synchronized void addConnection (Connection in) {
        connections.add(in);
    }
    public synchronized void rmConnection (Connection in) {
        connections.remove(in);
    }

    public void sendToOne (Connection target, Response response) {
        try {
            target.out.writeObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendToAll (Response response) {
            for (Connection key : connections) {
                try {
                    key.out.writeObject(response);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
}