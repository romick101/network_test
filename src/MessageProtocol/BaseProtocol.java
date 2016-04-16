package MessageProtocol;

public class BaseProtocol extends IProtocol {
    @Override
    public String HandleMsg (String msg) {
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
        return "Handled by base";
    }
}
