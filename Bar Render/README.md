# 第一次任务：状态栏渲染Lib模组编写

## 任务目标：
参考以下两个状态栏渲染模组，完成一个状态栏渲染Lib模组的编写：
### MagicBarLib(Fabric): 
- https://www.curseforge.com/minecraft/mc-mods/magicbarlib
- https://github.com/Deadlydiamond98/MagicBarLib
### Classic Bars(Forge)
- https://www.curseforge.com/minecraft/mc-mods/classic-bars
- https://github.com/Tfarcenim/ClassicBar

## 功能要求：
1. 自动化状态栏创建。
2. 使用该Lib的开发者仅需传入以下参数，即可自动生成新的状态栏：
    - 状态信息ID：指定需要监听的状态；
    - 状态栏颜色：指定状态栏显示的颜色；
    - 状态栏ICON：状态栏的图标。
3. Lib模组应能根据所提供的状态信息自适应布局，无需额外调整。
4. 可选功能：
    - 多种状态栏布局；
	- 提供多种状态栏布局供玩家选择；
	- 状态栏材质可在 https://itch.io/ 上自行选择，也可以沿用以上参考模组中的材质资源。
