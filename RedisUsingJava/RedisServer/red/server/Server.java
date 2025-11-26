package red.server;

import java.io.*;
import java.net.*;
import red.QueryProcesser.QueeryProcesser;
import red.QueryProcesser.Queerygenarater;
import red.DataBase.Data;
import red.Model.QueryDto;
import red.Model.ResultDto;

public class Server {

    private void ServerConfig() {
        Data data = new Data();   // shared data store
        final int port = 8888;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server running on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                Queerygenarater qg = new Queerygenarater();
                QueeryProcesser qp = new QueeryProcesser();

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
                        ResultDto rd = qp.processQuery(qd, data);

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
