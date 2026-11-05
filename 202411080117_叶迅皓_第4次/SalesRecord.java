/** 销售记录。 */
public class SalesRecord extends Record {
    private final double amount;

    public SalesRecord(long id, double amount) {
        super(id);
        this.amount = amount;
    }

    @Override
    public String summary() {
        return "销售 #" + id + " 金额=" + amount + "元";
    }
}
