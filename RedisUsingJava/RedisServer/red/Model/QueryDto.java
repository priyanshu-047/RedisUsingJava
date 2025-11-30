package red.Model;

public class QueryDto {
    private String commandString;
    private String key;
    private String value;

    public QueryDto(String commandString, String key, String value) {
        this.commandString = commandString;
        this.key = key;
        this.value = value;
    }
    public QueryDto() {
    }
    public String getCommandString() {
        return commandString;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;   
    }
    
}
