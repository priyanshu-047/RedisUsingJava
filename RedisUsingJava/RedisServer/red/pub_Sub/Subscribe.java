package red.pub_Sub;

import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import red.Model.Subscriber;



public class Subscribe {

     private static HashMap<String, HashSet<Subscriber>> channelSubscribers = new HashMap<>();
    public void addSubscriber(String topic, DataOutputStream out, String subscriberId) {
    channelSubscribers.putIfAbsent(topic, new HashSet<>());
    channelSubscribers.get(topic).add(new Subscriber(subscriberId, out));
}

     public void subscribeTopic(String topic, DataOutputStream out, String subscriberId) {
    addSubscriber(topic, out, subscriberId);
    }


    public static HashMap<String, HashSet<Subscriber>> getChannelSubscribers() {
        return channelSubscribers;
     }

}
