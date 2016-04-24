package MessageProtocol;

import MessageProtocol.Command.Command;
import MessageProtocol.Command.HelpCommand;
import MessageProtocol.Command.NowCommand;
import MessageProtocol.Command.RulesCommand;

import java.text.SimpleDateFormat;

public class CommandProtocol extends DecoratorProtocol {
    Command rulescmd = new RulesCommand(null);
    Command nowcmd = new NowCommand(rulescmd);
    Command helpcmd = new HelpCommand(nowcmd);

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
        helpcmd.HandleCommand(executor, command);
    }
    final String rules = "No flood, caps or abusive behaviour allowed. Feel free :)";
}
