package red.DataBase;

import java.util.HashMap;
import java.util.HashSet;


public class SetData {
    private HashMap<String, HashSet<String>> setdata;

    public SetData() {
        this.setdata = new HashMap<>();
    }
    public void setIfAbsentSet(String key) {
        if (!setdata.containsKey(key))
            setdata.put(key, new HashSet<>());
    }
    public boolean sadd(String key, String value) {
        return setdata.get(key).add(value);
    }
    public boolean srem(String key, String value) {
        if (!setdata.containsKey(key)||setdata.get(key).isEmpty())  return false;
        else return setdata.get(key).remove(value);
    }
    public boolean sismember(String key, String value) {
        return setdata.get(key).contains(value);
    }
    public String smembers(String key){
        StringBuilder members = new StringBuilder();
        for (String member : setdata.get(key)) {
            members.append(member).append(" ");
        }
        return members.toString().trim();
    }
    public boolean isEmpty() {
        return setdata.isEmpty();
    }

}
