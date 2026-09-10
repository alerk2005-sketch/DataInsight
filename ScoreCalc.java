public class ScoreCalc {
    public static void main(String[] args) {
        // 一条学生记录：学号 long、姓名 String、三门课成绩 int
        long id = 2023001L;
        String name = "张三";
        int math = 88;
        int chinese = 92;
        int english = 76;

        int total = math + chinese + english;
        double average = total / 3.0; // 浮点除法，避免整数除法截断
        boolean pass = average >= 60;  // 关系运算

        // 自动类型转换：int -> long -> double
        long automaticLong = math;
        double automaticDouble = automaticLong;

        // 强制类型转换：double -> int，小数部分会被截去
        int forcedInt = (int) average;

        // byte 溢出：byte 的范围是 -128 到 127，127 + 1 溢出为 -128
        byte maxByte = 127;
        byte overflowByte = (byte) (maxByte + 1);

        System.out.println("学号：" + id);
        System.out.println("姓名：" + name);
        System.out.println("总分：" + total);
        System.out.println("平均分：" + average);
        System.out.println("是否及格：" + pass);
        System.out.println("自动转换：int " + math + " -> long "
                + automaticLong + " -> double " + automaticDouble);
        System.out.println("强制转换：double " + average + " -> int " + forcedInt);
        System.out.println("byte 溢出：" + maxByte + " + 1 -> " + overflowByte);
    }
}
