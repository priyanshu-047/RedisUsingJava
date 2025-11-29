package red.DataBase;

import java.util.HashMap;

public class Data {

    private static HashMap<String, String> data;
    private static HashMap<String, Long> expiry; // stores expiry times (ms)

    public Data() {
        Data.data = new HashMap<>();
        Data.expiry = new HashMap<>();
    }


   
    private boolean isExpired(String key) {
        if (!expiry.containsKey(key)) return false;

        long expireAt = expiry.get(key);
        if (System.currentTimeMillis() > expireAt) {
            data.remove(key);
            expiry.remove(key);
            return true;
        }
        return false;
    }

  
    public String get(String key) {
        if (isExpired(key)) return null;
        return data.get(key);
    }

    

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void set(String key, String value) {
        data.put(key, value);
        expiry.remove(key); // remove expiry if exists (same as Redis SET)
    }
    public HashMap<String, String> getData() {
        return data;
    }

   
    public boolean del(String key) {
        expiry.remove(key);
        return data.remove(key) != null;
    }

  
    public boolean exists(String key) {
        if (isExpired(key)) return false;
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

    

    
    public boolean expire(String key, int seconds) {
        if (!data.containsKey(key)) return false;
        expiry.put(key, System.currentTimeMillis() + seconds * 1000L);
        return true;
    }

    
    public long ttl(String key) {
        if (!data.containsKey(key)) return -2; // key not exist
        if (!expiry.containsKey(key)) return -1; // no expire

        long expireAt = expiry.get(key);
        long ttlMs = expireAt - System.currentTimeMillis();

        if (ttlMs <= 0) {
            data.remove(key);
            expiry.remove(key);
            return -2; // expired
        }
        return ttlMs / 1000;
    }

    
    public boolean persist(String key) {
        if (!expiry.containsKey(key)) return false;
        expiry.remove(key);
        return true;
    }
}
