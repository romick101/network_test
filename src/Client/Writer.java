package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

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
            if(_holder.name == null) {
                System.out.print("Choose a nickname:");
                out.println("AUTH:" + (_holder.name = keyboard.readLine()));
                out.flush();
            }
            while (true) {
                StringBuilder toServer = new StringBuilder();
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
