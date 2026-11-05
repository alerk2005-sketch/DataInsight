/** 不修改主程序即可通过服务配置注册的新分析器。 */
public class DistributionAnalyzer extends BaseAnalyzer {
    public DistributionAnalyzer() { super("成绩分布"); }

    @Override
    public String analyze(Dataset ds) {
        if (ds.size() == 0) return "暂无成绩";
        int a = 0, b = 0, c = 0, d = 0;
        for (ScoreRecord record : ds.getRecords()) {
            int score = record.getScore();
            if (score >= 90) a++;
            else if (score >= 80) b++;
            else if (score >= 60) c++;
            else d++;
        }
        return "A(90–100) " + a + " 人，B(80–89) " + b
                + " 人，C(60–79) " + c + " 人，D(0–59) " + d + " 人";
    }
}
