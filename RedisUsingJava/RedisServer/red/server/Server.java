package red.server;

import java.io.*;
import java.net.*;
import red.QueryProcesser.QueeryProcesser;
import red.QueryProcesser.Queerygenarater;
import red.Model.QueryDto;
import red.Model.ResultDto;


public class Server {

    private void ServerConfig() {
     
        final int port = 8888;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
             QueeryProcesser qp = new QueeryProcesser();
             qp.setDataBase();
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                Queerygenarater qg = new Queerygenarater();
                

                while (true) {
                    try {
                        String message = in.readUTF();
                        System.out.println("Received: " + message);

                        if (message.equalsIgnoreCase("exit")) {
                            out.writeUTF("Goodbye!");
                            out.flush();
                            break;
                        }

                        QueryDto qd = qg.generateQuery(message);
                        ResultDto rd = qp.processQuery(qd);

                        out.writeUTF("Success: " + rd.isSuccess() + ", Message: " + rd.getMessage());
                        out.flush();

                    } catch (EOFException e) {
                        System.out.println("Client disconnected abruptly.");
                        break;
                    }
                }

                socket.close();
                System.out.println("Client connection closed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().ServerConfig();
    }
}
