package MessageProtocol;

public class BaseUserProtocol extends IProtocol {
    @Override
    public String HandleMsg (String msg) {
        String identifier = msg.substring(0,5);

        switch (identifier) {
            case "AUTH":
                executor.setClientName(msg.substring(5, msg.length()));
                break;
            case "MSSG":
                executor.sendMsgToAll(msg);
                break;
            case "EXIT":
                executor.dropClient();
                break;
            default:
                executor.sendMsgToAll(msg);
                break;

        }

        return "Action performed";
    }
}
