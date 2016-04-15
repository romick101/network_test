package MessageProtocol;

import Server.Connection;

public class ProtocolHandler {
    public void setProtocol (IProtocol prot, Connection exec) {
        prot.executor = exec;
    }
    public void Handle (IProtocol prot, String msg) {
        prot.HandleMsg(msg);
    }
}
