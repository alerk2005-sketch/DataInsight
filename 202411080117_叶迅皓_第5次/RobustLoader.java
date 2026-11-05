import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/** 逐行解析简化的“编号,姓名,分数”文本，记录并分类统计错误。 */
public class RobustLoader {
    public static DataRecord parseLine(String line) throws InvalidDataException {
        if (line == null || line.isBlank()) {
            throw new InvalidDataException(InvalidDataException.Reason.MISSING_FIELD);
        }
        // 保留末尾空字段，确保“编号,姓名,”被识别为缺失分数。
        String[] fields = line.split(",", -1);
        if (fields.length < 3) {
            throw new InvalidDataException(InvalidDataException.Reason.MISSING_FIELD);
        }
        if (fields.length > 3) {
            throw new InvalidDataException(InvalidDataException.Reason.FIELD_COUNT);
        }
        String idText = fields[0].strip();
        String name = fields[1].strip();
        String scoreText = fields[2].strip();
        if (idText.isEmpty() || name.isEmpty() || scoreText.isEmpty()) {
            throw new InvalidDataException(InvalidDataException.Reason.MISSING_FIELD);
        }
        long id;
        try {
            id = Long.parseLong(idText);
        } catch (NumberFormatException e) {
            throw new InvalidDataException(InvalidDataException.Reason.INVALID_ID, e);
        }
        BigInteger score;
        try {
            score = new BigInteger(scoreText);
        } catch (NumberFormatException e) {
            throw new InvalidDataException(InvalidDataException.Reason.NON_NUMERIC_SCORE, e);
        }
        if (score.signum() < 0 || score.compareTo(BigInteger.valueOf(100)) > 0) {
            throw new InvalidDataException(InvalidDataException.Reason.SCORE_OUT_OF_RANGE);
        }
        return new DataRecord(id, name, score.intValue());
    }

    /** 保存行号、原始内容和错误原因，便于定位及统计。 */
    public record Failure(int lineNumber, String line, InvalidDataException.Reason reason) { }

    public static class LoadResult {
        private final List<DataRecord> records;
        private final List<Failure> failures;
        private final Map<InvalidDataException.Reason, Integer> counts;

        private LoadResult(List<DataRecord> records, List<Failure> failures,
                           EnumMap<InvalidDataException.Reason, Integer> counts) {
            this.records = List.copyOf(records);
            this.failures = List.copyOf(failures);
            this.counts = Collections.unmodifiableMap(new EnumMap<>(counts));
        }
        public List<DataRecord> getRecords() { return records; }
        public List<Failure> getFailures() { return failures; }
        public Map<InvalidDataException.Reason, Integer> getCounts() { return counts; }
    }

    public static LoadResult loadData(String[] lines) {
        Objects.requireNonNull(lines, "输入数组不能为 null");
        List<DataRecord> records = new ArrayList<>();
        List<Failure> failures = new ArrayList<>();
        EnumMap<InvalidDataException.Reason, Integer> counts =
                new EnumMap<>(InvalidDataException.Reason.class);
        for (int i = 0; i < lines.length; i++) {
            try {
                records.add(parseLine(lines[i]));
            } catch (InvalidDataException e) {
                failures.add(new Failure(i + 1, lines[i], e.getReason()));
                counts.merge(e.getReason(), 1, Integer::sum);
            }
        }
        return new LoadResult(records, failures, counts);
    }

    public static void printResult(LoadResult result) {
        System.out.println("=== 任务二、三：脏数据解析与分类统计 ===");
        for (DataRecord record : result.getRecords()) {
            System.out.println("成功 → " + record);
        }
        for (Failure failure : result.getFailures()) {
            System.out.println("跳过第 " + failure.lineNumber() + " 行（"
                    + failure.reason().getDescription() + "）：" + failure.line());
        }
        System.out.println("成功 " + result.getRecords().size() + " 条，失败 "
                + result.getFailures().size() + " 条");
        for (InvalidDataException.Reason reason : InvalidDataException.Reason.values()) {
            System.out.println(reason.getDescription() + "："
                    + result.getCounts().getOrDefault(reason, 0) + " 条");
        }
    }

    public static void main(String[] args) {
        ExceptionDemo.run();
        System.out.println();
        String[] lines = {
                "2023001,张三,88", "2023004,赵六,abc", "2023005,钱七,",
                "2023006,孙八,150", "2023002,李四,92", "abc,周九,60",
                "2023007,,45", "2023003,王五,60"
        };
        printResult(loadData(lines));
    }
}
