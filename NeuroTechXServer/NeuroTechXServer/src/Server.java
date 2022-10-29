import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    public static void main(String args[]){
        try{
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Waiting for Client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client Connected...");

            String  message = "";

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            message = (String)dis.readUTF();
            System.out.println("Client message: " + message);

            serverSocket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}