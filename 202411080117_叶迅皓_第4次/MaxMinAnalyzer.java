public class MaxMinAnalyzer extends BaseAnalyzer {
    public MaxMinAnalyzer() { super("最高分和最低分"); }

    @Override
    public String analyze(Dataset ds) {
        if (ds.size() == 0) return "暂无成绩";
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (ScoreRecord record : ds.getRecords()) {
            max = Math.max(max, record.getScore());
            min = Math.min(min, record.getScore());
        }
        return "最高 " + max + " 分，最低 " + min + " 分";
    }
}
