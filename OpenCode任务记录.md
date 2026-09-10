# ScoreCalc 等级方法任务记录

## 提问原文

给 `ScoreCalc.java` 增加一个方法，根据平均分输出等级：90 及以上优秀，80–89 良好，60–79 及格，60 以下不及格。

## 实现内容

在 `ScoreCalc.java` 中增加 `getGrade(double average)` 方法：

```java
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
```

主程序调用该方法并输出等级。

## 自主验证与修改

- 检查了 90、80、60 三个边界：分别归入“优秀”“良好”“及格”。
- 检查了 60 以下分数归入“不及格”。
- 使用 `javac ScoreCalc.java` 编译，并运行 `java ScoreCalc` 检查输出。
- 保留原有的自动类型转换、强制类型转换和 `byte` 溢出示例。
