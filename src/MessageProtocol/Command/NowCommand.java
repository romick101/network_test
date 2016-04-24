package MessageProtocol.Command;

import Server.Connection;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NowCommand extends Command {
    public NowCommand (Command in) {
        super(in);
    }
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private String now = sdf.format(new Date());
    @Override
    public void HandleCommand (Connection executor, String in) {
        if(in.equals("now")) {
            executor.sendMsgToOne(now);
            return;
        }
        super.HandleCommand(executor, in);
    }
}
