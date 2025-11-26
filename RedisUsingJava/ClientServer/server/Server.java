package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Server {

     public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);

            System.out.println("Connected to Redis Java Server!");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("redis> ");
                String command = sc.nextLine();

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

            sc.close();
            socket.close();
            System.out.println("Client disconnected.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}