package Client;

import java.io.*;

public class Writer extends Thread {
    Client _holder;

    OutputStream _out;
    public Writer (OutputStream out, Client client) {
        this._holder = client;
        this._out = out;
    }
    @Override
    public void run () {
        try {
            ObjectOutputStream out = new ObjectOutputStream(_out);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            if(_holder.protocol == null) {
                System.out.println("Choose a protocol: ");
                out.writeObject("@" + (keyboard.readLine()));
            }
            if(_holder.name == null) {
                System.out.println("Choose a nickname: ");
                out.writeObject("$" + (_holder.name = keyboard.readLine()));
            }
            while (true) {
                StringBuilder toServer = new StringBuilder();
                toServer.append("!");
                toServer.append(keyboard.readLine());
                out.writeObject(toServer.toString());
                toServer.delete(0,toServer.length());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
