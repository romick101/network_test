package Server;

import java.util.ArrayList;
import java.util.List;

public class ConnectionDB implements IObservable {
    private ConnectionDB () {}
    private static ConnectionDB _instance;
    public static ConnectionDB getInstance () {
        if (_instance == null) {
            _instance = new ConnectionDB();
        }
        return _instance;
    }
    private List<IObserver> connections = new ArrayList<>();

    @Override
    public synchronized void addObserver (IObserver in) {
        connections.add(in);
    }
    @Override
    public synchronized void deleteObserver (IObserver in) {
        connections.remove(in);
    }
    @Override
    public void sendToOne (IObserver target, Response response) {
        try {
            target.sendMessage(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void sendToAll (Response response) {
            for (IObserver key : connections) {
                try {
                    key.sendMessage(response);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
}