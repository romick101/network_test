package Server;

import java.io.Serializable;

public class Response implements Serializable{
    String name;
    String data;
    UserType type;

    public UserType getType () {
        return type;
    }
    public String getName() {
        return name;
    }
    public String getData() {
        return data;
    }

    public Response(String name, String data) {
        this.name = name;
        this.data = data;
    }
    public Response(String name, String data, UserType type) {
        this.name = name;
        this.data = data;
        this.type = type;
    }
    @Override
    public String toString () {
        return name + " : " + data;
    }
}
