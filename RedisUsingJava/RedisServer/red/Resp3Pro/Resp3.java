package red.Resp3Pro;

import red.Model.ResultDto;

public class Resp3 {
    public Resp3() {
    }
    public ResultDto respSimple(String s) {
        return new ResultDto(true, "+" + s);
    }

    public ResultDto respError(String s) {
        return new ResultDto(false, "-ERR " + s);
    }

    public ResultDto respInt(long n) {
        return new ResultDto(true, ":" + n);
    }

    public ResultDto respBool(boolean b) {
        return new ResultDto(true, b ? "#t" : "#f");
    }

    public ResultDto respBulk(String s) {
        if (s == null) return new ResultDto(true, "_");
        return new ResultDto(true, "\"" + s + "\"");
    }

    public ResultDto respArray(String members) {
        if (members == null || members.isEmpty())
            return new ResultDto(true, "_");

        String[] arr = members.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("*").append(arr.length);
        for (String m : arr) sb.append("\n\"").append(m).append("\"");
        return new ResultDto(true, sb.toString());
    }
}
