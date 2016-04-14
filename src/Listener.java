import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Listener extends Thread {
    InputStreamReader _in;
    public Listener (InputStreamReader in) {
        this._in = in;
    }
    @Override
    public void run () {
        try {
            BufferedReader in = new BufferedReader(_in);
            StringBuilder fromServer = new StringBuilder();
            while (true) {
                fromServer.append(in.readLine());
                System.out.println(fromServer);
                fromServer.delete(0,fromServer.length());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
