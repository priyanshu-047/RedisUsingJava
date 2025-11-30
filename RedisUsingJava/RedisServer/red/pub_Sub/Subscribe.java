package red.pub_Sub;

import java.nio.channels.Channel;
import red.Model.PubMod;

public class Subscribe {
    private PubMod pubMod = new PubMod();
    public Subscribe() {
        
    }
    public void SubscribeTopic(String topic,Channel channel, String subscriberId) {
        pubMod.addSubscriber(topic, channel, subscriberId);
    }
}
