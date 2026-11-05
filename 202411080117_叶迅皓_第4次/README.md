# Java 实验四：继承、多态与接口

姓名：叶迅皓　学号：202411080117  
环境：JDK 17，UTF-8  
课程实验：[实验四：继承、多态与接口](https://wupeng.cn/java/实验指导/实验04-继承多态与接口.html)

## 运行

在本目录执行 `./run.sh`，脚本编译到临时目录，复制服务配置并运行程序；退出后自动清理临时文件。也可以在支持 UTF-8 的 JDK 17 环境手动运行：

```sh
mkdir -p out
javac -encoding UTF-8 --release 17 -d out *.java
cp -R META-INF out/
java -cp out DataInsight
```

IntelliJ IDEA 中需让 `META-INF/services/Analyzer` 位于运行时 classpath 根目录；例如将本目录设为资源根目录，或按上述命令运行。服务配置未进入 classpath 时，程序会提示未发现分析器。

## 完成情况

- `Record` 及三个子类重写 `summary()`；`Record[]` 多态遍历，通过 `instanceof` 提取成绩记录并求平均分。
- `Analyzer` 定义分析协议，`AvgAnalyzer`、`PassRateAnalyzer`、`MaxMinAnalyzer` 和 `DistributionAnalyzer` 分析同一成绩数据集。四个分析器继承 `BaseAnalyzer` 并复用名称及打印逻辑；主程序以 `Analyzer[]` 统一调用。
- 新增插件时，实现 `Analyzer`（或继承 `BaseAnalyzer`），提供 `public` 无参构造方法，并将类名加入 `META-INF/services/Analyzer`，编译后放入 classpath；无需修改 `DataInsight.java`。示例分布采用 90、80、60 作为分界。
- 6 条成绩为 90、85、70、55、0、60；平均分 60.00，及格 4 人（66.67%），最高 90，最低 0，A/B/C/D 人数依次为 1/1/2/2。空数据集各分析器均返回“暂无成绩”。

## 提交文件

根目录下的 `.java` 为全部源码；`META-INF/services/Analyzer` 为插件注册配置；`运行结果.txt` 为程序标准输出；`figure/运行结果截图.png` 为浏览器展示实际程序输出的截图；`思考题答案.md` 包含三道思考题答案。`实验报告.md` 汇总了实验目的、步骤和结果。

## GitHub 仓库

[DataInsight 仓库](https://github.com/alerk2005-sketch/DataInsight)；[实验四目录](https://github.com/alerk2005-sketch/DataInsight/tree/main/202411080117_%E5%8F%B6%E8%BF%85%E7%9A%93_%E7%AC%AC4%E6%AC%A1)。
