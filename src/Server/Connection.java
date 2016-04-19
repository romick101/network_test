package Server;

import MessageProtocol.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Connection extends Thread {
    private Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    String _name;
    ConnectionDB DB = ConnectionDB.getInstance();
    ProtocolHandler handler = new ProtocolHandler();

    IProtocol protocol;

    public Connection (Socket in_socket) {
        socket = in_socket;
        setClientProtocol(new DefaultProtocol());
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConnectionDB.getInstance().addConnection(this);
    }
    @Override
    public void run () {
        try {
            while (true) {

                String line = in.readObject().toString();
                System.out.println("Got " + line + " from " + _name);
                handler.Handle(protocol, line);
            }
        } catch (SocketException s) {
            System.out.println("Client dropped");
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setClientProtocol (String pname) {
        switch (pname) {
            case "base":
                protocol = new BaseProtocol();
                handler.setProtocol(protocol, this);
                break;
            case "extended":
                protocol = new ExtendedProtocol();
                handler.setProtocol(protocol, this);
                break;
            default:
                sendMsgToOne("No protocol chosen, set base by default");
                protocol = new BaseProtocol();
                handler.setProtocol(protocol, this);
        }
    }
    public void setClientProtocol (IProtocol prot) {
        protocol = prot;
        handler.setProtocol(protocol, this);
    }
    public void setClientName (String name) {
        this._name = name;
    }
    public void sendMsgToOne (String msg) {
        DB.sendToOne(this, msg);
    }
    public void sendMsgToAll (String msg) {
        DB.sendToAll(_name + ": " + msg);
    }
    public void dropClient ()  {
        System.out.println("DO CLIENT DROPPING");
    }
}