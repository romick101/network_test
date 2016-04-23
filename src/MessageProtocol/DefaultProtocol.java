package MessageProtocol;

public class DefaultProtocol extends  IProtocol {
    @Override
    public void HandleMsg (String msg) {
        char identifier = msg.charAt(0);

        if( identifier == '@')
            executor.setClientProtocol(msg.substring(1, msg.length()));
        else
            executor.sendMsgToOne("Wrong protocol!");
    }
}
