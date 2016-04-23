package MessageProtocol;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandProtocol extends DecoratorProtocol {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public CommandProtocol (IProtocol in) {
        super(in);
    }
    @Override
    public void HandleMsg (String msg) {
        char identifier = msg.charAt(0);
        String data = msg.substring(1,msg.length());
        char command_identifier = '_';
        String command = "no command";
        if(!data.isEmpty()) {
            command_identifier = data.charAt(0);
            command = data.substring(1, data.length());
        }
        if (identifier == '!') {
            if (command_identifier == '/') {
                HandleCommand(command);
                return;
            }
        }
        super.HandleMsg(msg);
    }
    private void HandleCommand (String command) {
        switch (command) {
            case "now":
                executor.sendMsgToOne(sdf.format(new Date()));
                break;
            case "rul":
                executor.sendMsgToOne(rules);
                break;
            default:
                executor.sendMsgToOne("No command to handle");
                break;
        }
    }
    final String rules = "No flood, caps or abusive behaviour allowed. Feel free :)";
}
