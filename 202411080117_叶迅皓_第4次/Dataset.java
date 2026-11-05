import java.util.Objects;

/** 保存成绩记录供分析器读取；数组结构不对外暴露。 */
public class Dataset {
    private final ScoreRecord[] records;

    public Dataset(ScoreRecord[] records) {
        Objects.requireNonNull(records, "记录数组不能为 null");
        this.records = records.clone();
        for (ScoreRecord record : this.records) {
            Objects.requireNonNull(record, "记录不能为 null");
        }
    }

    public int size() {
        return records.length;
    }

    public ScoreRecord[] getRecords() {
        return records.clone();
    }
}
