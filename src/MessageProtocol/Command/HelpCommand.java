package MessageProtocol.Command;
import Server.Connection;

public class HelpCommand extends Command{
    public HelpCommand (Command in) {
        super(in);
    }
    final private String help = "Command list:\n" +
            "rules - show rules;\n" +
            "now - show current datetime;";
    @Override
    public void HandleCommand (Connection executor, String in) {
        if(in.equals("help")) {
            executor.sendMsgToOne(help);
            return;
        }
        super.HandleCommand(executor, in);
    }
}
