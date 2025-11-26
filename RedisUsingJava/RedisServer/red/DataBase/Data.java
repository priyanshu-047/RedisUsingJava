package red.DataBase;

import java.util.HashMap;

public  class  Data {
    private static HashMap<String, String> data;
    public Data() {
        Data.data = new HashMap<>();
    }
    public String get(String key) {
        Object value = data.get(key);
        return value instanceof String ? (String) value : null;
    }

    public void set(String key, String value) {
        data.put(key, value);
    }

    public boolean del(String key) {
        return data.remove(key) != null;
    }

    public boolean exists(String key) {
        return data.containsKey(key);
    }

    public int append(String key, String value) {
        String current = get(key);
        if (current == null) current = "";
        String newValue = current + value;
        set(key, newValue);
        return newValue.length();
    }

    public int incr(String key) {
        String val = get(key);
        int num = (val == null) ? 0 : Integer.parseInt(val);
        num++;
        set(key, String.valueOf(num));
        return num;
    }

    public int decr(String key) {
        String val = get(key);
        int num = (val == null) ? 0 : Integer.parseInt(val);
        num--;
        set(key, String.valueOf(num));
        return num;
    }

}
