package red.QueryProcesser;

import red.Model.QueryDto;
import red.Model.ResultDto;
import red.DataBase.Data;
import red.DataBase.ListData;
import red.DataBase.SetData;

public class QueeryProcesser {
    Data Data = new Data();   
    ListData listData = new ListData(); 
    SetData setData = new SetData();

    public ResultDto processQuery(QueryDto queryDto) {
        String command = queryDto.getCommandString();
        String key = queryDto.getKey();
        String value = queryDto.getValue();


        switch (command.toUpperCase()) {
            case "SET":
                this.Data.set(key, value);
                return new ResultDto(true, "Key " + key + " set to " + value);
            case "GET":
                String retrievedValue = this.Data.get(key);
                return new ResultDto(true, "Value for key " + key + " is " + retrievedValue);
            case "DEL":
                boolean deleted = this.Data.del(key);
                if (deleted) {
                    return new ResultDto(true, "Key " + key + " deleted");
                } else {
                    return new ResultDto(false, "Key " + key + " not found");
                }
            case "EXISTS":
                boolean exists = this.Data.exists(key);
                return new ResultDto(true, "Key " + key + " exists: " + exists  );  
            case "APPEND":
                int newLength = this.Data.append(key, value);
                return new ResultDto(true, "New length of key " + key + " is " + newLength);
            case "INCR":
                int incrementedValue = this.Data.incr(key);
                return new ResultDto(true, "Value for key " + key + " incremented to " + incrementedValue);
            case "DECR":
                int decrementedValue = this.Data.decr(key);
                return new ResultDto(true, "Value for key " + key + " decremented to " + decrementedValue);
            case "LPUSH":
                this.listData.setIfAbsent(key);
                int lpushLength = this.listData.lpush(key, value);
                return new ResultDto(true, "After LPUSH, length of list at key " + key + " is " + lpushLength);
            case "LPOP":
                String lpopValue = this.listData.lpop(key);
                return new ResultDto(true, "LPOP from key " + key + " returned value: " + lpopValue);
            case "LLEN":
                int llenLength = this.listData.llen(key);
                return new ResultDto(true, "Length of list at key " + key + " is " + llenLength);
            case "PING":
                return new ResultDto(true, "PONG");
            case "SADD":
                this.setData.setIfAbsentSet(key);
                boolean saddResult = this.setData.sadd(key, value);
                return new ResultDto(true, "SADD result for key " + key + " is " + saddResult);
            case "SREM":
                boolean sremResult = this.setData.srem(key, value);
                return new ResultDto(true, "SREM result for key " + key + " is " + sremResult);
            case "SMEMBERS":
                String members = this.setData.smembers(key);
                return new ResultDto(true, "SMEMBERS for key " + key + ": " + members); 

            //ttl logic
            case "TTL":
                long ttl = this.Data.ttl(key);
                return new ResultDto(true, "TTL for key " + key + " is " + ttl);
            case "EXPIRE":
                int seconds;
                try {
                    seconds = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    return new ResultDto(false, "Invalid seconds value: " + value);
                }
                boolean expireResult = this.Data.expire(key, seconds);
                return new ResultDto(true, "EXPIRE result for key " + key + " is" + expireResult);
            case "PERSIST":
                boolean persistResult = this.Data.persist(key);
                return new ResultDto(true, "PERSIST result for key " + key + " is " + persistResult);
            default:
                return new ResultDto(false, "Unknown command: " + command);
        }
    }
  
}