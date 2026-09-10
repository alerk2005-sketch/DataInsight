public class ScoreCalc {
    /**
     * 根据平均分返回等级。
     * 边界分数按较高等级处理：90 分为优秀，80 分为良好，60 分为及格。
     */
    public static String getGrade(double average) {
        if (average >= 90) {
            return "优秀";
        } else if (average >= 80) {
            return "良好";
        } else if (average >= 60) {
            return "及格";
        } else {
            return "不及格";
        }
    }

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
        String grade = getGrade(average);

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
        System.out.println("等级：" + grade);
        System.out.println("自动转换：int " + math + " -> long "
                + automaticLong + " -> double " + automaticDouble);
        System.out.println("强制转换：double " + average + " -> int " + forcedInt);
        System.out.println("byte 溢出：" + maxByte + " + 1 -> " + overflowByte);
    }
}
