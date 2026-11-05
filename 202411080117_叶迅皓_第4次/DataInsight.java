import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ServiceLoader;

/** 实验四：继承、多态、接口及抽象类。 */
public class DataInsight {
    public static void main(String[] args) {
        Record[] records = {
                new ScoreRecord(1, 90), new ScoreRecord(2, 85),
                new SensorRecord(3, 25.6), new ScoreRecord(4, 70),
                new SalesRecord(5, 1288.0), new ScoreRecord(6, 55),
                new ScoreRecord(7, 0), new ScoreRecord(8, 60)
        };

        System.out.println("=== 任务一：继承与多态 ===");
        int total = 0;
        List<ScoreRecord> scores = new ArrayList<>();
        for (Record record : records) {
            System.out.println(record.summary());
            if (record instanceof ScoreRecord scoreRecord) {
                scores.add(scoreRecord);
                total += scoreRecord.getScore();
            }
        }
        if (scores.isEmpty()) {
            System.out.println("成绩记录平均分：暂无成绩");
        } else {
            System.out.printf(Locale.ROOT, "成绩记录平均分：%.2f 分%n",
                    (double) total / scores.size());
        }

        Dataset dataset = new Dataset(scores.toArray(new ScoreRecord[0]));
        // 新增实现类时只需注册服务，不需修改本主程序。
        List<Analyzer> loaded = new ArrayList<>();
        ServiceLoader.load(Analyzer.class).forEach(loaded::add);
        Analyzer[] analyzers = loaded.toArray(new Analyzer[0]);
        System.out.println("\n=== 任务二、三：接口与抽象类分析器 ===");
        if (analyzers.length == 0) {
            System.out.println("未发现分析器，请检查 META-INF/services/Analyzer");
        }
        for (Analyzer analyzer : analyzers) analyzer.printResult(dataset);
    }
}
