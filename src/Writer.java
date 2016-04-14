import java.io.*;

public class Writer extends Thread {
    OutputStreamWriter _out;
    public Writer (OutputStreamWriter out) {
        this._out = out;
    }
    @Override
    public void run () {
        try {
            PrintWriter out = new PrintWriter(_out);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder toServer = new StringBuilder();
            while (true) {
                toServer.append(this.getName());
                toServer.append(": ");
                toServer.append( keyboard.readLine());
                out.println(toServer.toString());
                out.flush();
                if(toServer.toString().contains("STOP")) {
                    break;
                }
                toServer.delete(0,toServer.length());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
