import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/** 针对脏数据分类、边界及返回时 finally 的独立检查。 */
public class Lab05Check {
    private static int checked;
    private static void check(boolean ok, String message) {
        checked++;
        if (!ok) throw new AssertionError(message);
    }
    private static void invalid(String input, InvalidDataException.Reason expected) {
        try {
            RobustLoader.parseLine(input);
            throw new AssertionError("未拒绝：" + input);
        } catch (InvalidDataException e) {
            check(e.getReason() == expected, "错误类型：" + input + " -> " + e.getReason());
        }
    }
    public static void main(String[] args) throws Exception {
        check(RobustLoader.parseLine(" 1 , 张三 , 0 ").getScore() == 0, "下界");
        check(RobustLoader.parseLine("2,李四,100").getScore() == 100, "上界");
        invalid(null, InvalidDataException.Reason.MISSING_FIELD);
        invalid("", InvalidDataException.Reason.MISSING_FIELD);
        invalid("1,张三", InvalidDataException.Reason.MISSING_FIELD);
        invalid("1,张三,", InvalidDataException.Reason.MISSING_FIELD);
        invalid("1,,60", InvalidDataException.Reason.MISSING_FIELD);
        invalid(",张三,60", InvalidDataException.Reason.MISSING_FIELD);
        invalid("1,张三,60,多余", InvalidDataException.Reason.FIELD_COUNT);
        invalid("bad,张三,60", InvalidDataException.Reason.INVALID_ID);
        invalid("999999999999999999999999,张三,60", InvalidDataException.Reason.INVALID_ID);
        invalid("1,张三,abc", InvalidDataException.Reason.NON_NUMERIC_SCORE);
        invalid("1,张三,1.5", InvalidDataException.Reason.NON_NUMERIC_SCORE);
        invalid("1,张三,-1", InvalidDataException.Reason.SCORE_OUT_OF_RANGE);
        invalid("1,张三,101", InvalidDataException.Reason.SCORE_OUT_OF_RANGE);
        invalid("1,张三,99999999999999999999999", InvalidDataException.Reason.SCORE_OUT_OF_RANGE);
        var result = RobustLoader.loadData(new String[]{"1,张三,90", "2,李四,x", null, "3,王五,60"});
        check(result.getRecords().size() == 2 && result.getFailures().size() == 2, "成功失败计数");
        check(result.getFailures().get(0).lineNumber() == 2 && result.getFailures().get(1).lineNumber() == 3, "错误行号");
        check(result.getCounts().equals(Map.of(InvalidDataException.Reason.NON_NUMERIC_SCORE, 1,
                InvalidDataException.Reason.MISSING_FIELD, 1)), "分类计数");
        check(RobustLoader.loadData(new String[0]).getRecords().equals(List.of()), "空数组");
        try { result.getRecords().clear(); throw new AssertionError("结果可变"); }
        catch (UnsupportedOperationException expected) { check(true, "结果列表不可变"); }
        try { RobustLoader.loadData(null); throw new AssertionError("null 数组"); }
        catch (NullPointerException expected) { check(true, "null 数组"); }
        PrintStream original = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        String returned;
        try (PrintStream capture = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(capture);
            returned = ExceptionDemo.returnWithFinally();
        } finally { System.setOut(original); }
        String output = bytes.toString(StandardCharsets.UTF_8);
        check(returned.equals("方法已返回") && output.indexOf("try 中准备 return") < output.indexOf("finally 在方法返回之前执行"), "finally 顺序");
        System.out.println("通过 " + checked + " 项检查。");
    }
}
