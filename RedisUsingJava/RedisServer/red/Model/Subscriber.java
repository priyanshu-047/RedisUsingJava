package red.Model;

import java.io.DataOutputStream;
import java.io.IOException;

public class Subscriber {
    private String subscriberId;
    private DataOutputStream out;

    public Subscriber(String subscriberId, DataOutputStream out) {
        this.subscriberId = subscriberId;
        this.out = out;
    }

    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            System.out.println("Failed to send message to " + subscriberId);
        }
    }

    public String getSubscriberId() {
        return subscriberId;
    }
    public DataOutputStream getOut() {
        return out;
    }
}
