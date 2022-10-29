import java.net.*;
import java.io.*;

public class Client {

    // socket initialization

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    public Client(String address, int port){

        try{
            socket = new Socket(address, port);
            System.out.println("Connected");

            input = new DataInputStream(System.in);

            out = new DataOut
        }
    }

}
