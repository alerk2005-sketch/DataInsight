/** 传感器记录。 */
public class SensorRecord extends Record {
    private final double temperature;

    public SensorRecord(long id, double temperature) {
        super(id);
        this.temperature = temperature;
    }

    @Override
    public String summary() {
        return "传感器 #" + id + " 温度=" + temperature + "℃";
    }
}
