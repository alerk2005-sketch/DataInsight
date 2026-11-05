import java.util.Locale;

public class AvgAnalyzer extends BaseAnalyzer {
    public AvgAnalyzer() { super("平均分"); }

    @Override
    public String analyze(Dataset ds) {
        if (ds.size() == 0) return "暂无成绩";
        long sum = 0;
        for (ScoreRecord record : ds.getRecords()) sum += record.getScore();
        return String.format(Locale.ROOT, "%.2f 分", (double) sum / ds.size());
    }
}
