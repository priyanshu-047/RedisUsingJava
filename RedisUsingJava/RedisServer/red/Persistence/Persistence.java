package red.Persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import red.DataBase.Data;
import red.DataBase.ListData;
import red.DataBase.SetData;
import red.Model.QueryDto;
import red.QueryProcesser.QueeryProcesser;
import red.QueryProcesser.Queerygenarater;

public class Persistence {
    public Persistence() {
    }

    public void deleteFileContentent(){
        // To clear the file content
        try {
            FileOutputStream fos = new FileOutputStream("Rds.txt");
            fos.write("".getBytes());
            fos.close();
        } catch (Exception e) {
            System.out.println(e);  
        }
    }
     
    public void retrievedValue(Data data, SetData setData, ListData listData) {
        Queerygenarater qg = new Queerygenarater();
        QueeryProcesser qp = new QueeryProcesser();
         QueryDto queryDto;
       FileInputStream fin=null;
       try{
        fin=new FileInputStream("Rds.txt");
        byte[] b = new byte[fin.available()];
        fin.read(b);
        String content = new String(b);
        String[] lines = content.split("\n");
        for (String line : lines) {
            queryDto = qg.generateQuery(line);
            qp.processQuery(queryDto);
            }
        fin.close();
       } catch (Exception e) {
        System.out.println(e);
       }
    }
    public void saveData(Data data, SetData setData, ListData listData) {
        FileOutputStream fos1 = null;
        try {
            fos1 = new FileOutputStream("Rds.txt", true);

            if (!data.isEmpty()) {
                for (String key : data.getData().keySet()) {
                    String value = data.getData().get(key);
                    try {
                        String toWrite = "SET " + key + " " + value + "\n";
                        byte[] b = toWrite.getBytes();
                        fos1.write(b);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }
            }
            if (!setData.isEmpty()) {
                for (String key : setData.getSet().keySet()) {
                    for (String value : setData.getSet().get(key)) {
                        try {
                            String toWrite = "SADD " + key + " " + value + "\n";
                            byte[] b = toWrite.getBytes();
                            fos1.write(b);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }
            }
            if (!listData.isEmpty()) {
            }

            fos1.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
