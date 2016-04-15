package MessageProtocol;

public abstract class IProtocol {
    public abstract String HandleMsg (String msg);
    public abstract String HandleAUTH (String msg);
}
