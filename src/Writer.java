import java.io.*;

public class Writer extends Thread {
    Client _holder;

    OutputStreamWriter _out;
    public Writer (OutputStreamWriter out, Client client) {
        this._holder = client;
        this._out = out;
    }
    @Override
    public void run () {
        try {
            PrintWriter out = new PrintWriter(_out);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder toServer = new StringBuilder();

            System.out.print("Choose a nickname:");
            _holder.name = keyboard.readLine();
            out.println(_holder.name);
            out.flush();
            while (true) {
                toServer.append(_holder.name);
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
