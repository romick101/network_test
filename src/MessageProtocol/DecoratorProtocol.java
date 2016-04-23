package MessageProtocol;

public class DecoratorProtocol extends IProtocol {
    protected IProtocol protocol;

    public IProtocol getProtocol() {
        return protocol;
    }

    public void setProtocol(IProtocol protocol) {
        this.protocol = protocol;
    }

    public DecoratorProtocol (IProtocol in) {
        this.protocol = in;
    }

    @Override
    public void HandleMsg (String msg) {
        protocol.HandleMsg(msg);
    }
}
