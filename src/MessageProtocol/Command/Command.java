package MessageProtocol.Command;

import Server.Connection;
import Server.IObservable;

public abstract class Command {
    IObservable executor;
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
