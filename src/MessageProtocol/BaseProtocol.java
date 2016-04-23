package MessageProtocol;

import Server.Connection;

public class BaseProtocol extends IProtocol {
    public BaseProtocol (Connection executor) {
        this.executor = executor;
    }
    public BaseProtocol () {}
    @Override
    public void HandleMsg (String msg) {
        char identifier = msg.charAt(0);
        String data = msg.substring(1, msg.length());
        switch (identifier) {
            case '$':
                executor.setClientName(data);
                break;
            case '!':
                executor.sendMsgToAll(data);
                break;
            default:
                executor.sendMsgToOne("No identifier in base protocol");
                break;
        }
    }
}
