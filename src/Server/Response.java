package Server;

import java.io.Serializable;

public class Response implements Serializable{
    String name;
    String data;

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
    @Override
    public String toString () {
        return name + " : " + data;
    }
}
