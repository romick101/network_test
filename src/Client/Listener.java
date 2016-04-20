package Client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.SocketException;
import java.util.function.Consumer;

public class Listener extends Thread {

    Client _holder;
    InputStream _in;
    public Consumer<Serializable> onReceiveCallback;

    public Listener (InputStream in, Client holder, Consumer<Serializable> onReceiveCallback) {
        this._in = in;
        this._holder = holder;
        this.onReceiveCallback = onReceiveCallback;
        this.setDaemon(true);
    }
    @Override
    public void run () {
        try {
            ObjectInputStream in = new ObjectInputStream(_in);
            while (true) {
                Serializable data = (Serializable) in.readObject();
                onReceiveCallback.accept(data);
                System.out.println(data.toString());
            }
        }catch (SocketException e) {
            System.out.println("Connection closed");
        }
        catch (Exception s) {
            s.printStackTrace();
        }
    }
}
