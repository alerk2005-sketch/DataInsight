/** 数据行解析失败时抛出的受检异常。 */
public class InvalidDataException extends Exception {
    public enum Reason {
        MISSING_FIELD("字段缺失"),
        FIELD_COUNT("字段数量错误"),
        INVALID_ID("编号非数字或超出范围"),
        NON_NUMERIC_SCORE("分数非数字"),
        SCORE_OUT_OF_RANGE("分数越界");

        private final String description;
        Reason(String description) { this.description = description; }
        public String getDescription() { return description; }
    }

    private final Reason reason;

    public InvalidDataException(Reason reason) {
        super(reason.getDescription());
        this.reason = reason;
    }

    public InvalidDataException(Reason reason, Throwable cause) {
        super(reason.getDescription(), cause);
        this.reason = reason;
    }

    public Reason getReason() { return reason; }
}
