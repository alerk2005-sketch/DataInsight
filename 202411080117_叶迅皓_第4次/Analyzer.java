/** 分析器协议；实现类只需给出分析结果。 */
public interface Analyzer {
    String analyze(Dataset ds);

    default void printResult(Dataset ds) {
        System.out.println(analyze(ds));
    }
}
