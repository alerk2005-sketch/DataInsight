/** 各类数据记录的共同父类。 */
public class Record {
    protected final long id;

    public Record(long id) {
        this.id = id;
    }

    public String summary() {
        return "记录 #" + id;
    }
}
