package red.QueryProcesser;

import red.Model.QueryDto;
import red.Model.ResultDto;
import red.DataBase.Data;
import red.DataBase.ListData;
import red.DataBase.SetData;
import red.Persistence.Persistence;
import red.Resp3Pro.*;
import red.pub_Sub.Publish;
import red.pub_Sub.Subscribe;
import red.Model.Subscriber;

public class QueeryProcesser {
    Data Data = new Data();
    ListData listData = new ListData();
    SetData setData = new SetData();
    Persistence persistence = new Persistence();
    Resp3 resp3 = new Resp3();
    Subscribe subscribe = new Subscribe();
    Publish publish = new Publish();

    
    public void setDataBase() {
        persistence.retrievedValue(this.Data, this.setData, this.listData);
    }

    public ResultDto processQuery(QueryDto queryDto, Subscriber subscriber) {
        String command = queryDto.getCommandString();
        String key = queryDto.getKey();
        String value = queryDto.getValue();

        switch (command.toUpperCase()) {

            case "SET":
                this.Data.set(key, value);
                return resp3.respSimple("OK");

            case "GET":
                return resp3.respBulk(this.Data.get(key));

            case "DEL":
                boolean deleted = this.Data.del(key);
                return resp3.respInt(deleted ? 1 : 0);
            case "EXISTS":
                boolean exists = this.Data.exists(key);
                return resp3.respInt(exists ? 1 : 0);

            case "APPEND":
                int newLength = this.Data.append(key, value);
                return resp3.respInt(newLength);
            case "INCR":
                int incrementedValue = this.Data.incr(key);
                return resp3.respInt(incrementedValue);

            case "DECR":
                int decrementedValue = this.Data.decr(key);
                return resp3.respInt(decrementedValue);
            case "LPUSH":
                this.listData.setIfAbsent(key);
                int lpushLength = this.listData.lpush(key, value);
                return resp3.respInt(lpushLength);
            case "LPOP":
                return resp3.respBulk(this.listData.lpop(key));

            case "LLEN":
                int llen = this.listData.llen(key);
                return resp3.respInt(llen);
            case "PING":
                return resp3.respSimple("PONG");

            case "SADD":
                this.setData.setIfAbsentSet(key);
                boolean sadd = this.setData.sadd(key, value);
                return resp3.respInt(sadd ? 1 : 0);

            case "SREM":
                boolean srem = this.setData.srem(key, value);
                return resp3.respInt(srem ? 1 : 0);
            case "SMEMBERS":
                return resp3.respArray(this.setData.smembers(key));

            case "TTL":
                long ttl = this.Data.ttl(key);
                return resp3.respInt(ttl);
            case "EXPIRE":
                int seconds;
                try {
                    seconds = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    return resp3.respError("Invalid seconds value");
                }
                boolean expireResult = this.Data.expire(key, seconds);
                return resp3.respBool(expireResult);

            case "PERSIST":
                boolean persistResult = this.Data.persist(key);
                return resp3.respBool(persistResult);
            case "SAVE":
                persistence.deleteFileContentent();
                persistence.saveData(this.Data, this.setData, this.listData);
                return resp3.respSimple("DATA SAVED");
            case "PDEL":
                persistence.deleteFileContentent();
                return resp3.respSimple("PERSISTENCE CLEARED");
            case "SUB":
                subscribe.subscribeTopic(key, subscriber.getOut(), subscriber.getSubscriberId());
                return resp3.respSimple("SUBSCRIBED to " + key);
            case "PUB":
                 publish.publishMessage(key, value);
                return resp3.respSimple("MESSAGE PUBLISHED");

            default:
                return resp3.respError("Unknown command: " + command);
        }
    }
}
