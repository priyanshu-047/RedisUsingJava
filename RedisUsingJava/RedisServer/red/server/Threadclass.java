package red.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import red.Model.QueryDto;
import red.Model.ResultDto;
import red.QueryProcesser.Queerygenarater;
import java.net.Socket;
import red.Model.Subscriber;
import red.QueryProcesser.QueeryProcesser;


public class Threadclass extends Thread {
    private String subscriberId;
    private DataOutputStream out;
    private DataInputStream in;
    private Subscriber subscriber;
    

    public void setSocket(Socket socket) {
        try {
            this.out = new DataOutputStream(socket.getOutputStream());
            this.in = new DataInputStream(socket.getInputStream());
             this.subscriberId = "sub_" + Server.count++;
             this.subscriber = new Subscriber(this.subscriberId, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
       

    }

    @Override
    public void run() {

        try {
            Queerygenarater qg = new Queerygenarater();
            QueeryProcesser qp = new QueeryProcesser();

            while (true) {
                try {
                    String message = in.readUTF();
                    System.out.println( message);

                    if (message.equalsIgnoreCase("exit")) {
                        out.writeUTF("Goodbye!");
                        out.flush();
                        break;
                    }

                    QueryDto qd = qg.generateQuery(message);
                    ResultDto rd = qp.processQuery(qd, this.subscriber);

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
