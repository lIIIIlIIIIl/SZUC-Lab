# 第二次任务：基于Improper UI的交互UI编写

## 任务目标：
阅读**Improper UI的文档**，完成一个基于`HTML+CSS+JS`的**维度传送UI模组**的编写。
- https://github.com/ItziSpyder/ImproperUI/tree/main

## 功能要求：
1. 注册新物品：**维度书**；
2. 让玩家右键打开维度书时可以获得打开如下的维度选择界面：

![demo](./resources/demo.gif)

3. 实现**按钮交互**事件；
4. 可选：
    - 参考`resources`文件夹下，XYZ的项目`UI-template.zip`(react)；
    - `react`学习资料：https://zh-hans.react.dev/learn ；
	- 其他你觉得有意义的功能和组件。

## 注意事项：
- 图片资源在`resources`文件夹下：
    - overworld.jpg
    - nether.jpg
    - the_end.jpg
- 使用jar导入Improper UI依赖（当前文件夹）：
    - 复制`libs`文件夹到你的个人工作目录
    - 原`build.gradle`中已添加该依赖：
``` build.gradle 20-26
dependencies {
    ...
    compileOnly files("libs/ImproperUI-1.20.1.jar")
}
```
