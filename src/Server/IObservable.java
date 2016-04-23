package Server;

public interface IObservable {
    void addObserver (IObserver in);
    void deleteObserver (IObserver in);
    void sendToAll (Response response);
    void sendToOne (IObserver target, Response response);
}
