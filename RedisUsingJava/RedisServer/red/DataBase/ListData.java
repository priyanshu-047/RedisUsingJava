package red.DataBase;

import java.util.HashMap;
import java.util.LinkedList;

public class ListData {

    // key key key
    // value value value
    // value value value
    private HashMap<String, LinkedList<String>> listdata;

    public ListData() {
        this.listdata = new HashMap<>();
    }

    public LinkedList<?> get(String key) {
        return listdata.get(key);
    }

    public void setIfAbsent(String key) {
        if (!listdata.containsKey(key))
            listdata.put(key, new LinkedList<>());

    }

    public int lpush(String key, String value) {
        listdata.get(key).add(value);
        return listdata.get(key).size();
    }

    public String lpop(String key){
        if(!listdata.containsKey(key)) return "";
        else return listdata.get(key).pop();
    }

    public int llen(String key){
        if(!listdata.containsKey(key)) return 0;
        else return listdata.get(key).size();
    }

}
