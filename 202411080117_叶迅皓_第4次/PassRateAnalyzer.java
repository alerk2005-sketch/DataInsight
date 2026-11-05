import java.util.Locale;

public class PassRateAnalyzer extends BaseAnalyzer {
    public PassRateAnalyzer() { super("及格率"); }

    @Override
    public String analyze(Dataset ds) {
        if (ds.size() == 0) return "暂无成绩";
        int pass = 0;
        for (ScoreRecord record : ds.getRecords()) {
            if (record.getScore() >= 60) pass++;
        }
        return String.format(Locale.ROOT, "%d/%d = %.2f%%", pass, ds.size(),
                100.0 * pass / ds.size());
    }
}
