package red.QueryProcesser;

import red.Model.QueryDto;
import red.Model.ResultDto;
import red.DataBase.Data;

public class QueeryProcesser {
    public ResultDto processQuery(QueryDto queryDto,Data Data) {
        String command = queryDto.getCommandString();
        String key = queryDto.getKey();
        String value = queryDto.getValue();


        switch (command.toUpperCase()) {
            case "SET":
                Data.set(key, value);
                return new ResultDto(true, "Key " + key + " set to " + value);
            case "GET":
                String retrievedValue = Data.get(key);
                return new ResultDto(true, "Value for key " + key + " is " + retrievedValue);
            case "DEL":
                boolean deleted = Data.del(key);
                if (deleted) {
                    return new ResultDto(true, "Key " + key + " deleted");
                } else {
                    return new ResultDto(false, "Key " + key + " not found");
                }
            case "EXISTS":
                boolean exists = Data.exists(key);
                return new ResultDto(true, "Key " + key + " exists: " + exists  );  
            case "APPEND":
                int newLength = Data.append(key, value);
                return new ResultDto(true, "New length of key " + key + " is " + newLength);
            case "INCR":
                int incrementedValue = Data.incr(key);
                return new ResultDto(true, "Value for key " + key + " incremented to " + incrementedValue);
            case "DECR":
                int decrementedValue = Data.decr(key);
                return new ResultDto(true, "Value for key " + key + " decremented to " + decrementedValue);
            default:
                return new ResultDto(false, "Unknown command: " + command);
        }
    }
  
}