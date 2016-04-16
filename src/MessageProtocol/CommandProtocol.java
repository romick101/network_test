package MessageProtocol;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommandProtocol extends IProtocol{
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    public String HandleMsg (String msg) {
        char identifier = msg.charAt(0);
        String data = msg.substring(1,msg.length());
        switch (identifier) {
            case '$':
                executor.setClientName(data);
                break;
            case '!':
                executor.sendMsgToAll(data);
                break;
            case '/':
                HandleCommand(data);
                break;
            default:
                executor.sendMsgToOne("No identifier in command protocol");
        }
        return "Handled by extended";
    }
    private void HandleCommand (String command) {
        switch (command) {
            case "now":
                executor.sendMsgToOne(new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance()));
                break;
            case "rul":
                executor.sendMsgToOne(rules);
                break;
            default:
                executor.sendMsgToOne("No command to handle");
        }
    }
    final String rules = "No flood, caps or abusive behaviour allowed. Feel free :)";
}
