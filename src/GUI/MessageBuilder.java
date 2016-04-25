package GUI;

import Server.UserType;

public abstract class MessageBuilder {
    final double _NAMESIZE = 65;
    final double _DATASIZE = 250;
    protected Message message;
    public void CreateMessage () {
        message = new Message();
    }
    public Message GetMessage () {
        if (message == null)
            CreateMessage();
        return message;
    }
    public abstract void SetName (String name);
    public abstract void SetData (String data);
    public abstract void SetType (UserType type);
}
