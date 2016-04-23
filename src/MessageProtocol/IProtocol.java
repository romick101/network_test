package MessageProtocol;

import Server.Connection;

public abstract class IProtocol {
    public Connection executor;
    public abstract void HandleMsg (String msg);
}
