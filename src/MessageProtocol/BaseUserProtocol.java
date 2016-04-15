package MessageProtocol;

public class BaseUserProtocol extends IProtocol {
    @Override
    public String HandleMsg (String msg) {
        if(msg.contains("AUTH"))
            return HandleAUTH(msg);
        return "NO IDENTIFIER";
    }
    @Override
    public String HandleAUTH (String msg) {
        String identifier = msg.substring(0,5);

        if (identifier.compareTo("AUTH:") == 0) {
            return identifier;
        }
        return "No AUTH identifier found";
    }
}
