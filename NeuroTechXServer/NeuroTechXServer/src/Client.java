import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

class client {
    public static void main(String args[]){
        String message;
        Scanner sc = new Scanner(System.in);
        try{
            Socket socket = new Socket("localhost", 5000);

            System.out.println("Connected with Server...");
            System.out.println("enter a message: ");
            message = sc.nextLine();

            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF(message);
            dout.flush();
            dout.close();
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}