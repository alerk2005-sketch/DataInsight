/** 实现共同的名称与输出逻辑，具体分析留给子类。 */
public abstract class BaseAnalyzer implements Analyzer {
    private final String name;

    protected BaseAnalyzer(String name) {
        this.name = name;
    }

    @Override
    public abstract String analyze(Dataset ds);

    @Override
    public void printResult(Dataset ds) {
        System.out.println(name + "：" + analyze(ds));
    }
}
