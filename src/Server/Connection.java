package Server;

import MessageProtocol.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Connection extends Thread implements IObserver{
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
        ConnectionDB.getInstance().addObserver(this);
    }
    @Override
    public void run () {
        try {
            while (true) {
                String line = in.readObject().toString();
                System.out.println("Got " + line + " from " + _name);
                handler.Handle(protocol, line);
            }
        } catch (SocketException|EOFException s) {
            DB.deleteObserver(this);
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
                sendMsgToOne("Set base protocol OK.\n" + "Choose name (10 characters max).");
                break;
            case "extended":
                protocol = new CommandProtocol(new BaseProtocol(this));
                handler.setProtocol(protocol, this);
                sendMsgToOne("Set extended protocol OK.\n" + "Choose name (10 characters max).");
                break;
            default:
                sendMsgToOne("No protocol chosen, set base by default.\n" + "Choose name (10 characters max).");
                protocol = new BaseProtocol();
                handler.setProtocol(protocol, this);
        }
    }
    public void setClientProtocol (IProtocol prot) {
        protocol = prot;
        handler.setProtocol(protocol, this);
    }
    public void setClientName (String name) {
        this._name = CheckName(name);
        sendMsgToOne("You`ve chosen <" + _name + "> nickname.\n" + "Welcome to our chat!");
    }
    private String CheckName (String name) {
        String res = name;
        if(name.length() == 0)
            res = "default";
        if (name.length() > 10)
            res = name.substring(0,10);
        return res;
    }
    @Override
    public void sendMessage (Response response) {
        try {
            this.out.writeObject(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendMsgToOne (String msg) {
        DB.sendToOne(this, new Response("System", msg, UserType.System));
    }
    public void sendMsgToAll (String msg) {
        if (protocol instanceof BaseProtocol)
            DB.sendToAll(new Response(_name, msg, UserType.Base));
        else
            DB.sendToAll(new Response(_name, msg, UserType.Extended));
    }
}