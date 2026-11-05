/** 分别捕获四种常见异常，并验证 return 前执行 finally。 */
public class ExceptionDemo {
    public static String returnWithFinally() {
        try {
            System.out.println("try 中准备 return");
            return "方法已返回";
        } finally {
            System.out.println("finally 在方法返回之前执行");
        }
    }

    public static void run() {
        System.out.println("=== 任务一：常见异常与 finally ===");
        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("捕获 NumberFormatException：无法将 abc 转为整数");
        }
        try {
            int[] values = {10, 20};
            System.out.println(values[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获 ArrayIndexOutOfBoundsException：数组下标越界");
        }
        try {
            int zero = 0;
            System.out.println(10 / zero);
        } catch (ArithmeticException e) {
            System.out.println("捕获 ArithmeticException：整数除数为零");
        }
        try {
            String text = null;
            System.out.println(text.length());
        } catch (NullPointerException e) {
            System.out.println("捕获 NullPointerException：对 null 调用方法");
        }
        System.out.println(returnWithFinally());
    }
}
