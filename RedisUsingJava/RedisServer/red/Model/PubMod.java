package red.Model;

import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.HashSet;


public class PubMod {
    private static HashMap<String, HashSet<Subscriber>> channelSubscribers = new HashMap<>();

    public void addSubscriber(String topic, Channel channel, String subscriberId) {
        channelSubscribers.putIfAbsent(topic, new HashSet<>());
        channelSubscribers.get(topic).add(new Subscriber(subscriberId, channel));
    }
    
    public HashMap<String, HashSet<Subscriber>> getChannelSubscribers() {
        return channelSubscribers;
    }

}
