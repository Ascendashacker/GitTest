# JDBC编程和MySql数据库
### 前言
  ###### 此文虽是笔者的学习笔记，但通篇笔者根据[费曼学习法](https://wiki.mbalib.com/wiki/%E8%B4%B9%E6%9B%BC%E5%AD%A6%E4%B9%A0%E6%B3%95) ，发散式的联想和细致的梳理整个教程中读者所会遇到的各种问题，尽量解开读者在学习过程中所会碰到的疑惑，这样不仅能让读者少走一些弯路，思路更为明朗，对于笔者我而言，也更深入理解了其本质。
  ###### 本章节会简单介绍Java的jdk安装部署，集成开发环境工具Eclipse的安装部署，MySql数据库的安装部署。着重讲解Java连接MySql数据库的一些基本的操作。
  ###### 本着知其然知其所以然，授人以鱼不如授人以渔之意，写下这一篇拙作。仓促成文,不当之处,尚祈方家和读者批评指正。
### 1.Java的安装与部署
##### （1）jdk（Java Development Kit）下载：
  点击[jdk官方下载地址](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)，进入到jdk下载界面，选择 **JavaSE->downloads** ,按照自己现在的开发需要选择对应的版本，笔者本次选择是windows 64位最新的jdk版本（jdk11）。

  ![](https://i.loli.net/2018/12/09/5c0d39d5ea829.png "JDK下载界面")

  选择自己对应的安装平台，点击下载，记得勾选**Accept License Agreement**（接受许可协议）。

  ![](https://i.loli.net/2018/12/09/5c0d384044bde.png "JDK下载界面")
  ###### Q：只能安装一个jdk版本吗？
  在实际开发中，可能会碰到不同项目需要利用不同jdk版本进行开发，这时候就需要我们安装不同jdk版本，实现不同项目进行jdk切换。在这里，可参考博文[《一台电脑如何配置多个JDK》](https://blog.csdn.net/qq_26545305/article/details/66472521)[《同时安装不同版本JDK遇到的问题》](http://www.cnblogs.com/lojun/p/9664519.html)。
  ###### Q：jdk的下载包zip和exe的区别？
  jdk的zip包是绿色版本的，解压完就可以运行了，无需安装，没有帮你设置环境变量。

  ![jdk压缩包](https://i.loli.net/2018/12/10/5c0e611795fa0.png)

  而exe文件需要安装的，安装的时候会帮你设置环境变量。（本次jdk安装以exe安装为例）

  ![QQ图片20181210205002.png](https://i.loli.net/2018/12/10/5c0e61912d769.png)

##### （2）jdk安装：

  打开下载的exe文件，点击下一步。       

  ![](https://i.loli.net/2018/12/10/5c0d3f9e68c00.png "JDK安装")  

  选择自己所要安装的路径，点击下一步。  

  ![](https://i.loli.net/2018/12/10/5c0d404b9b13f.png "JDK安装")   

  安装成功。（如果安装不成功，可尝试重新安装）

  ![](https://i.loli.net/2018/12/10/5c0d40ff5a386.png)
##### （3）环境变量配置
###### Q：为什么需要配置环境变量？
