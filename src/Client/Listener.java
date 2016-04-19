package Client;

import java.io.InputStream;
import java.io.ObjectInputStream;

public class Listener extends Thread {
    InputStream _in;
    public Listener (InputStream in) {
        this._in = in;
    }
    @Override
    public void run () {
        try {
            ObjectInputStream in = new ObjectInputStream(_in);
            while (true) {
                System.out.println(in.readObject().toString());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
