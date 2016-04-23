package Client;

import MessageProtocol.BaseProtocol;
import MessageProtocol.CommandProtocol;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class Writer extends Thread {
    Client _holder;

    OutputStream _out;
    ObjectOutputStream out;
    public Writer (OutputStream out, Client client) {
        this._holder = client;
        this._out = out;
        try {
            this.out = new ObjectOutputStream(_out);
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.setDaemon(true);
    }

    @Override
    public void run () {
    }
    public void writeMsg (Serializable data) {
        try {
            if(_holder.protocol == null) {
                switch (data.toString()) {
                    case "base":
                        _holder.protocol = new BaseProtocol();
                        break;
                    case "extended":
                        _holder.protocol = new CommandProtocol(new BaseProtocol());
                        break;
                    default:
                        _holder.protocol = new BaseProtocol();
                }
                out.writeObject("@" + data.toString());
                return;
            }
            if(_holder.name == null) {
                _holder.name = data.toString();
                out.writeObject("$" + (_holder.name = data.toString()));
                return;
            }
                StringBuilder toServer = new StringBuilder();
                toServer.append("!");
                toServer.append(data.toString());
                out.writeObject(toServer.toString());
                toServer.delete(0,toServer.length());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
