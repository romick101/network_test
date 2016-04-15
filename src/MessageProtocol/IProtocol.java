package MessageProtocol;

import Server.Connection;

public abstract class IProtocol {
    public Connection executor;
    public abstract String HandleMsg (String msg);
}
