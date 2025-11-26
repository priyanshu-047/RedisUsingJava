package red.Model;

public class ResultDto {
    private boolean success;
    private String message;

    public ResultDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
