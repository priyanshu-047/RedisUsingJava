package red.server;

import java.io.*;
import java.net.*;
import red.QueryProcesser.QueeryProcesser;
import red.QueryProcesser.Queerygenarater;
import red.Model.QueryDto;
import red.Model.ResultDto;
import red.pub_Sub.Subscribe;


public class Server {
    private Subscribe subscribe = new Subscribe();

    private void ServerConfig() {
     
        final int port = 8888;
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
             QueeryProcesser qp = new QueeryProcesser();
             qp.setDataBase();
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                Threadclass thread = new Threadclass();
                thread.setSocket(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server closed.");
            try {
                if(serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
                
        } 
    }

    public static void main(String[] args) {
        new Server().ServerConfig();
    }
}
