/** 成绩记录，成绩限制在 0 至 100 分。 */
public class ScoreRecord extends Record {
    private final int score;

    public ScoreRecord(long id, int score) {
        super(id);
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("成绩必须在 0 至 100 之间");
        }
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String summary() {
        return "成绩 #" + id + " 分数=" + score;
    }
}
