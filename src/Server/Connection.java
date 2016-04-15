package Server;

import Client.Client;
import MessageProtocol.BaseUserProtocol;
import MessageProtocol.IProtocol;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Connection extends Thread {
    private Socket socket;
    BufferedReader in;
    PrintWriter out;
    Client client;
    ConnectionDB DB = ConnectionDB.getInstance();

    IProtocol protocol;

    public Connection (Socket in_socket) {
        socket = in_socket;
        protocol = new BaseUserProtocol();
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.getInstance().addConnection(this);
    }
    @Override
    public void run () {
        try {
            while (true) {
                    String line = in.readLine();
                    System.out.println("Got " + line);
                    switch (protocol.HandleMsg(line)) {
                        case "AUTH: ":
                            Pattern p = Pattern.compile("AUTH: (.+?)");
                            Matcher m = p.matcher(line);
                            setClientName(m.group());
                            break;
                        default:
                            sendMsgToAll(line);
                    }
                }
        } catch (SocketException s) {
            System.out.println("Client dropped");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setClientName (String name) {
        this.client.name = name;
    }
    public void sendMsgToAll (String msg) {
        DB.sendToAll(msg);
    }
}