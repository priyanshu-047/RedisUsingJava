package red.pub_Sub;



import red.Model.Subscriber;

public class Publish {

    public void publishMessage(String topic, String message) {
    if (!Subscribe.getChannelSubscribers().containsKey(topic)) return;
    for (Subscriber sub : Subscribe.getChannelSubscribers().get(topic)) {
        sub.sendMessage("[PUB] " + topic + ": " + message);
    }
}
}