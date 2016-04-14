import java.net.*;
import java.io.*;

public class Client {
    private String name = this.toString();
    public String getName () { return name;}
    public void setName (String value) { name = value;}
    public void setName (BufferedReader in) throws IOException {name = in.readLine();}

    public static void main(String[] ar) {
        Client client = new Client();
        try {
            client.runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void runClient () throws IOException {
        int serverPort = 4444;
        String address = "127.0.0.1";
        InetAddress ipAddress = InetAddress.getByName(address);
        Socket socket = new Socket(ipAddress, serverPort);

        try {
//            InputStream sin = socket.getInputStream();
//            OutputStream sout = socket.getOutputStream();
//            DataInputStream in = new DataInputStream(sin);
//            DataOutputStream out = new DataOutputStream(sout);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));



            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Choose a nickname: ");
            this.setName(keyboard);
            StringBuilder toServer = new StringBuilder();
            StringBuilder fromServer = new StringBuilder();
            while (true) {
                toServer.append(this.getName());
                toServer.append(": ");
                toServer.append( keyboard.readLine());
                out.println(toServer.toString());
                out.flush();
                if(toServer.toString().contains("STOP")) {
                    break;
                }
                toServer.delete(0,toServer.length());
                fromServer.append(in.readLine());
                System.out.println("Responce: " + fromServer);
                fromServer.delete(0,fromServer.length());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
