package red.QueryProcesser;

import red.Model.QueryDto;
public class Queerygenarater {

    public QueryDto generateQuery(String input) throws IllegalArgumentException {
        System.out.println("Generating query from input: " + input);
        String[] parts = input.split(" ");
        if (parts.length>3 || parts.length<=0) throw new IllegalArgumentException("Invalid query format");
        if(parts.length==1){
            String commandString = parts[0];
            String key = "";
            String value = "";
            return new QueryDto(commandString, key, value);
        }
        if(parts.length==2){
        String commandString = parts[0];
        String key = parts[1];
        String value = "";
        return new QueryDto(commandString, key, value);
        }
        else{
        String commandString = parts[0];
        String key = parts[1];
        String value = parts[2];
        return new QueryDto(commandString, key, value);
        }
    }

}
