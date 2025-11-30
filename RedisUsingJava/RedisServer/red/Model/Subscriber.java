package red.Model;

import java.nio.channels.Channel;

public class Subscriber {
    private String subscriberId;
    private Channel channel;
    public Subscriber(String subscriberId, Channel channel) {
        this.subscriberId = subscriberId;
        this.channel = channel;
    }
    public String getSubscriberId() {
        return subscriberId;
    }
    public Channel getChannel() {
        return channel;
    }
    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
