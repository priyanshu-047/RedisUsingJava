package red.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;

import red.Model.QueryDto;
import red.Model.ResultDto;
import red.QueryProcesser.Queerygenarater;
import java.net.Socket;
import red.QueryProcesser.QueeryProcesser;

public class Threadclass extends Thread {
    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
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
                    ResultDto rd = qp.processQuery(qd);

                    out.writeUTF("Success: " + rd.isSuccess() + ", Message: " + rd.getMessage());
                    out.flush();

                } catch (EOFException e) {
                    System.out.println("Client disconnected abruptly.");
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
