## 云探天气预报APP 

是一款基于和风天气API与腾讯位置SDK的安卓天气预报APP。
Based on hefeng weather API and Tencent location SDK Android weather app

## 运行项目
1、先到 和风天气api注册，实名认证获得七天天气预报权限。
2、申请 web api 的key
3、在 tq\app\src\main\java\com\xianguoliang\tools\MyApp.java 文件下的 HEKEY 变量赋值你申请的和风天气接口Key


## 项目功能
1、定位获取当前城市

2、通过网络API 获取天气数据

3、通过网络下载对应的天气背景

4、左右滑动切换城市天气及相应的背景

5、搜索及收藏城市
通过sqlite数据库实现收藏功能
通过网络API搜索城市名

6、查看收藏城市列表 
点击列表栏目可以显示对应的城市天气界面 
长按栏目可以选择删除收藏城市

<h3>启动屏</h3>
<img src="https://user-images.githubusercontent.com/58978356/127187368-7b497863-0cda-496f-ae02-047a8b10420e.png" width="300" alt="启动屏"/><br/>

<h3>首页</h3>
<img src="https://user-images.githubusercontent.com/58978356/127187733-ab2e4247-cff0-4cc3-92d0-647ac1f5bdf3.png" width="300" alt="首页"/><br/>

第一页界面是定位得到城市天气界面，右滑是收藏城市天气的页面</br>
左右滑动可以查看 最多四个最新收藏的城市天气</br>
点击左上角图标可以进入查看收藏的城市列表</br>
点击右上角图标可以进入搜索城市页面</br>

<h3>搜索页</h3>
<img src="https://user-images.githubusercontent.com/58978356/127188040-6ea0770b-79cf-478b-88f3-fb7c4a38cc4e.png" width="300" alt="搜索页"/><br/>

输入城市名，点击搜索按钮，显示搜索到的城市列表</br>
已经收藏的城市左边五角星会变黄色</br>
点击城市列表栏目可以进入对应的城市天气界面</br>

<h3>城市天气页面</h3>
<img src="https://user-images.githubusercontent.com/58978356/127188331-5c102908-1b1e-4760-9daf-826aa8f33b10.png" width="300" alt="城市天气页面"/><br/>

点击左上角返回上一个页面</br>
点击右上角可以进行添加收藏或者取消收藏的功能</br>

<h3>已收藏城市列表页面</h3>
<img src="https://user-images.githubusercontent.com/58978356/127188384-0f83f27d-bd09-4292-81c1-5e4683d6ebbd.png" width="300" alt="已收藏城市列表页面"/><br/>

点击栏目可以进入对应的城市天气页面</br>
长按栏目可以进行删除收藏操作</br>
点击加号可以进入城市搜索页面</br>
栏目的颜色会根据天气情况设置不同的颜色</br>



