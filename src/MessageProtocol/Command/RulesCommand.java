package MessageProtocol.Command;

import Server.Connection;

public class RulesCommand extends Command{
    public RulesCommand (Command in) {
        super(in);
    }
    private String rules = "No flood, caps or abusive behaviour allowed. Feel free :)";
    @Override
    public void HandleCommand (Connection executor, String in) {
        if(in.equals("rules")) {
            executor.sendMsgToOne(rules);
            return;
        }
        executor.sendMsgToOne("No handler for the command");
    }
}
