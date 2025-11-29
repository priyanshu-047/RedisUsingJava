package red.Persistence;

import red.DataBase.Data;
import red.DataBase.ListData;
import red.DataBase.SetData;

public class Persistence {
    public void saveData(Data data,SetData setData, ListData listData) {
        if(!data.isEmpty()){
         for (String key : data.getData().keySet()) {
             String value = data.get(key);
             
         }
        }
        if(!setData.isEmpty()){

        }
        if(!listData.isEmpty()){
        }
        
       

    }
}
