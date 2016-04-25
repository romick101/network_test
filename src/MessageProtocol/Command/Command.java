package MessageProtocol.Command;

import Server.Connection;

public abstract class Command {
    public Command cmd;
    protected Command (Command in) {
        this.cmd = in;
    }
    public void HandleCommand (Connection exec, String in) {
        if(cmd != null) {
            cmd.HandleCommand(exec, in);
        }
    }
}
