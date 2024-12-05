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
- `Improper UI`依赖导入：
    - 对比该模组Github文档，下面给出的导入方式更适应开发；
    - 原`build.gradle`中已添加该依赖：
``` groovy
repositories {
    maven {
        name = "Modrinth"
        url = "https://api.modrinth.com/maven"
    }
}

dependencies {
    ...
    modImplementation "maven.modrinth:improperui:1-20-0.0.6-BETA"
}
```
