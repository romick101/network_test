package GUI;

import Server.Response;

public class BuilderDirector {
    MessageBuilder _builder;
    public Message build (Response msg, MessageBuilder builder) {
        _builder = builder;
        _builder.CreateMessage();
        _builder.SetType(msg.getType());
        _builder.SetName(msg.getName());
        _builder.SetData(msg.getData());
        return _builder.GetMessage();
    }
}
