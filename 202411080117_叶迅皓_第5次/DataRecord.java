/** 一条通过校验的成绩记录。 */
public class DataRecord {
    private final long id;
    private final String name;
    private final int score;

    public DataRecord(long id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public int getScore() { return score; }

    public String toString() {
        return id + ", " + name + ", " + score + " 分";
    }
}
