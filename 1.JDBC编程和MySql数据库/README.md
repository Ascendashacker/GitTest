# JDBC编程和MySql数据库
### 前言
  ###### 此文虽是笔者的学习笔记，但我会根据[费曼学习法](https://wiki.mbalib.com/wiki/%E8%B4%B9%E6%9B%BC%E5%AD%A6%E4%B9%A0%E6%B3%95) ，发散式的联想和细致的梳理整个教程中读者所会遇到的各种问题，尽量解开读者在学习过程中所会碰到的疑惑，这样不仅能让读者少走一些弯路，思路更为明朗，对于我而言，也更深入理解了其本质。
  ###### 本章节会简单介绍Java的jdk安装部署，集成开发环境工具Eclipse的安装部署，MySql数据库的安装部署。着重讲解Java连接MySql数据库的一些基本的操作。
  ###### 本着知其然知其所以然，授人以鱼不如授人以渔之意，写下这一篇拙作。仓促成文,不当之处,尚祈方家和读者批评指正。
### 1.Java的安装与部署
##### （1）jdk（Java Development Kit）下载：
  点击[jdk官方下载地址](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)，进入到jdk下载界面，选择 **JavaSE->downloads** ,按照自己现在的开发需要选择对应的版本，笔者本次选择最新的jdk版本（现在版本为jdk11）。
  ![](https://i.loli.net/2018/12/09/5c0d39d5ea829.png "JDK下载界面")
  选择自己对应的安装平台，记得勾选Accept License Agreement。
  ![](https://i.loli.net/2018/12/09/5c0d384044bde.png "JDK下载界面")
##### （2）jdk安装：
  点击下一步。       
  ![](https://i.loli.net/2018/12/10/5c0d3f9e68c00.png "JDK安装")           
  选择自己所有安装的路径，点击下一步。          
  ![](https://i.loli.net/2018/12/10/5c0d404b9b13f.png "JDK安装")          
  安装成功                       
  ![](https://i.loli.net/2018/12/10/5c0d40ff5a386.png)
##### （3）环境配置
