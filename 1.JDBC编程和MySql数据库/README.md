# JDBC编程和MySql数据库
### 前言
  ###### 此文虽是笔者的学习笔记，但通篇笔者根据 [费曼学习法](https://wiki.mbalib.com/wiki/%E8%B4%B9%E6%9B%BC%E5%AD%A6%E4%B9%A0%E6%B3%95)，发散式的联想和细致的梳理整个教程中读者所会遇到的各种问题，尽量解开读者在学习过程中所会碰到的疑惑，这样不仅能让读者少走一些弯路，思路更为明朗，对于笔者我而言，也更深入理解了其本质。
  ###### 本章节会简单介绍Java的jdk安装部署，集成开发环境工具Eclipse的安装部署，MySql数据库的安装部署。着重讲解Java连接MySql数据库的一些基本的操作。
  ###### 本着知其然知其所以然，授人以鱼不如授人以渔之意，写下这一篇拙作。仓促成文,不当之处,尚祈方家和读者批评指正。
### 1.Java的安装与部署
##### （1）jdk（Java Development Kit）下载：
  - 点击 [jdk官方下载地址](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)，进入到jdk下载界面，选择 **JavaSE->downloads** ,按照自己现在的开发需要选择对应的版本，笔者本次选择是windows 64位最新的jdk版本（jdk11）。

  ![](https://i.loli.net/2018/12/09/5c0d39d5ea829.png "JDK下载界面")

  - 选择自己对应的安装平台，点击下载，记得勾选**Accept License Agreement**（接受许可协议）。

  ![](https://i.loli.net/2018/12/09/5c0d384044bde.png "JDK下载界面")

###### Q1：只能安装一个jdk版本吗？
  在实际开发中，可能会碰到不同项目需要利用不同jdk版本进行开发，这时候就需要我们安装不同jdk版本，实现不同项目进行jdk切换。在这里，可参考博文[《一台电脑如何配置多个JDK》](https://blog.csdn.net/qq_26545305/article/details/66472521)[《同时安装不同版本JDK遇到的问题》](http://www.cnblogs.com/lojun/p/9664519.html)。

###### Q2：jdk的下载包zip和exe的区别？
  jdk的zip包是绿色版本的，解压完就可以运行了，无需安装，该方式不会帮你设置环境变量。

  ![jdk压缩包](https://i.loli.net/2018/12/10/5c0e611795fa0.png)

  而exe文件需要安装的，安装的时候会帮你设置环境变量。（本次jdk安装以exe安装为例）

  ![jdk exe文件](https://i.loli.net/2018/12/10/5c0e61912d769.png)

##### （2）jdk（开发工具包）安装：

  - 打开下载的exe文件，点击下一步。       

  ![](https://i.loli.net/2018/12/10/5c0d3f9e68c00.png "JDK安装")  

  - 选择自己所要安装的路径，点击下一步。  

  ![](https://i.loli.net/2018/12/10/5c0d404b9b13f.png "JDK安装")   

  - 安装成功。（如果安装不成功，可尝试重新安装）

  ![](https://i.loli.net/2018/12/10/5c0d40ff5a386.png)

##### （3）环境变量配置
###### Q1：为什么需要配置环境变量？
   首先，我们需要理解什么是 **path（环境变量）** ，环境变量是在操作系统中一个具有特定名字的对象，它包含了一个或者多个应用程序所将使用到的信息，环境变量是dos以前的内部命令。用作运行某个命令的时候，本地找不到某个命令或文件，系统会到这个声明的目录中去查找。系统的运行界面，所有能够直接输入找到的程序都能在path那边找到对应的配置路径。eg:当我们在运行界面输入calc，操作系统会去环境变量已保存的路径中找是否存在calc（计算器）程序，打开该程序。如果没有则会提示windows找不到‘calc’，此时，我们需要在path中配置calc的路径。

  ![系统运行界面](https://i.loli.net/2018/12/10/5c0e696f550ac.png)

  环境变量记录了一些执行程序的位置，方便用户直接查找调用。当安装的软件越来越多时，我们很难记住所有已安装软件的路径。

  而java编程中，因为java必须经过编译才能运行，编译就要通过常用命令，比如：编译的命令为javac等。而这些常用的编译命令就存放在jdk的bin目录。而windows运行java编译命令的时候，本地查找不到某个命令或文件的时候，会到path中去查找。所以，不可能每个java脚本文件所在目录都放置jdk，故把jdk放置path（环境变量）中，这样每个java脚本文件都可以通过path中设定jdk目录找到编译命令进行编译，编译后的class类就要通过 **jre（java runtime environment，java运行环境）** 运行环境进行运行实现。一般设定java的时候为了在任何目录下都可以运行bin文件夹下的命令，就将java的bin目录声明到path中。这只是文本编辑java的时候需要设置，如果用公开发工具[myeclipse](http://www.myeclipsecn.com/)就不需要设置环境变量，自带jdk。而[eclipse](https://www.eclipse.org/)启动时候会要求path。

###### Q2：我们需要配置哪些环境参数？
  通常情况我们需要配置以下三个变量：

   **JAVA_HOME**：指向Jdk的安装目录，作用是一些基于Java开发的工具会用到，比如tomcat,Eclipse，如果不用这些工具不需要配置。

   **Path**：指向jdk安装目录下的bin目录，作用是指定命令搜索路径，bin目录下有编译、启动（javac/java）等命令，为了任何目录位置下都可以直接输入命令，而不用输入长长的路径了。如果配置了JAVA_HOME ，直接把%JAVA_HOME%/bin追加到PATH中即可。

   **CLASSPATH**：在于告诉Java执行环境，在哪些目录下可以找到我们所要执行的Java程序所需要的类或者包。不过在JDK1.5之后的版本完全可以不用设置classpath环境变量就能正常运行程序。

大家可参考几篇博文：[《path与classpath区别》](https://blog.csdn.net/zhaihao1996/article/details/78387676)，[《JAVA环境变量JAVA_HOME、CLASSPATH、PATH设置详解》](https://www.cnblogs.com/Wjh794010585/articles/6582079.html)，[《JAVA为什么要配置环境变量，怎样配置》](https://www.cnblogs.com/zhangpengshou/p/4232204.html)

- 右键我的电脑-属性-高级系统设置，你就会看到下面的界面：

  ![系统属性面板](https://i.loli.net/2018/12/10/5c0e76186920d.png)

- 点击环境变量，进入到环境变量界面，如下：

  ![环境变量界面](https://i.loli.net/2018/12/10/5c0e77086eaa5.png)

- 点击用户变量中的新建，进入到新建用户变量界面，输入变量名：**JAVA_HOME**,点击浏览目录，选择目录为你jdk所安装的目录，笔者这里的路径为 **C:\Program Files\Java\jdk-11.0.1**，这个值只要到这个目录就可以。

  ![新建环境变量界面](https://i.loli.net/2018/12/10/5c0e7b340336b.png)

- 继续在系统变量里面新建一个 **CLASSPATH** 变量，变量值：其变量值如下图所示：

  ![CLASSPATH](https://i.loli.net/2018/12/10/5c0e82341ec08.png)

- 在你的系统变量里面找一个变量名是PATH的变量，需要在它的值域里面追加一段如下的代码：
> %JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;

- 最后，测试自己所配置的环境变量是否正确
  - win+R,输入cmd，进入命令行界面。输入 java -version，如下图显示正确版本：

    ![Java版本](https://i.loli.net/2018/12/10/5c0e8c47d086e.png)

  - 命令行输入 javac，如下图所示：

    ![Javac](https://i.loli.net/2018/12/10/5c0e8cc60837c.png)

  - 命令行输入 java，如下图所示：

    ![QQ图片20181210235704.png](https://i.loli.net/2018/12/10/5c0e8d69ab34b.png)

  - 写一段代码验证一下，我将写的java脚本放在 **e:/HelloWorld.java** ,

    ![QQ图片20181211000214.png](https://i.loli.net/2018/12/11/5c0e8e9f6983d.png)

  -  接下来咱们来测试一下。往命令行依次输入以下命令（在cmd中，Tab键有补全字段的作用），结果如下图所示：
  > cd e:/      
  > javac HelloWorld.java    
  > java HelloWorld

   ![QQ图片20181211000658.png](https://i.loli.net/2018/12/11/5c0e8fbdc4d63.png)
