package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        Scanner sc = null;

        try {
          
            socket = new Socket("127.0.0.1", 8888);
            System.out.println("Connected to Redis Java Server!");

            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            sc = new Scanner(System.in);

            while (true) {
                System.out.print("redis> ");
                String command = sc.nextLine().trim();

               
                if (command.equalsIgnoreCase("exit")) {
                    dos.writeUTF("exit");
                    dos.flush();
                    break;
                }

                dos.writeUTF(command);
                dos.flush();

               
                String response = dis.readUTF();
                System.out.println(response);
            }

            System.out.println("Client disconnected.");

        } catch (IOException e) {
            System.out.println("Connection Error: " + e.getMessage());

        } finally {
           
            try {
                if (dis != null) dis.close();
                if (dos != null) dos.close();
                if (socket != null) socket.close();
                if (sc != null) sc.close();
            } catch (IOException e) {
                System.out.println("Closing Error: " + e.getMessage());
            }
        }
    }
}
