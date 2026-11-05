# 实验五：异常处理与脏数据健壮处理

姓名：叶迅皓　学号：202411080117  
环境：JDK 17、UTF-8；课程规定 3 学时。

## 运行代码

执行 `./run.sh`，脚本在临时目录编译并运行 `RobustLoader`，完成后清理临时目录。输入数据采用简单的 `编号,姓名,分数` 三字段格式，不处理含引号逗号或跨行的 CSV 数据。

示例输出为成功 3 条、失败 5 条：字段缺失 2 条、字段数量错误 0 条、编号非数字或超出范围 1 条、分数非数字 1 条、分数越界 1 条。`verification/Lab05Check.java` 为 23 项独立检查；`verification/验证结果.txt` 保存实际执行结果。

运行验证（在本目录中）：

```sh
mkdir -p /tmp/lab05-check
javac -encoding UTF-8 --release 17 -d /tmp/lab05-check ./*.java verification/Lab05Check.java
java -cp /tmp/lab05-check Lab05Check
```

## 报告工程

`main.tex` 为主文件，`chapter/content.tex` 包含报告正文、四份完整源码的引用、运行截图和思考题。将整个报告压缩包导入 LaTeX 平台，选择 XeLaTeX 编译 `main.tex`，并保持各文件相对路径。

模板类、原配置、参考文献文件、许可证和校徽与原压缩包逐字节一致；主文件仅修改四个封面字段，保留模板全部其余内容。模板本身要求 Times New Roman 和中文字体。原始类文件引用 `ZAFUreport.cfg`，原模板实际提供 `ZAFUReport.cfg`；为兼容区分大小写的系统，压缩包另含一份内容完全相同的 `ZAFUreport.cfg`，原文件未更改。

本机无 XeLaTeX。内置编译器无法读取系统的 Times New Roman 字体，检查因此失败；未生成或验证 PDF。为保留模板原貌，没有修改字体设置。

GitHub 仓库：[DataInsight](https://github.com/alerk2005-sketch/DataInsight)。
