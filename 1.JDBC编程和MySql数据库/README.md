# JDBC编程和MySql数据库
## 前言
  #### 此文虽是笔者的学习笔记，但通篇笔者根据 [费曼学习法](https://wiki.mbalib.com/wiki/%E8%B4%B9%E6%9B%BC%E5%AD%A6%E4%B9%A0%E6%B3%95)，发散式的联想和细致的梳理整个教程中读者所会遇到的各种问题，尽量解开读者在学习过程中所会碰到的疑惑，这样不仅能让读者少走一些弯路，思路更为明朗，对于笔者我而言，也更深入理解了其本质。
  #### 本章节会简单介绍Java的jdk安装部署，集成开发环境工具Eclipse的安装部署，MySql数据库的安装部署。着重讲解Java连接MySql数据库的一些基本的操作。
  #### 本着知其然知其所以然，授人以鱼不如授人以渔之意，写下这一篇拙作。仓促成文,不当之处,尚祈方家和读者批评指正。
## 1.Java的安装与部署
### （1）jdk（Java Development Kit）下载
  - 点击 [jdk官方下载地址](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)，进入到jdk下载界面，选择 **JavaSE->downloads** ,按照自己现在的开发需要选择对应的版本，笔者本次选择是windows 64位最新的jdk版本（jdk11）。

  ![](https://i.loli.net/2018/12/09/5c0d39d5ea829.png "JDK下载界面")

  - 选择自己对应的安装平台，点击下载，记得勾选**Accept License Agreement**（接受许可协议）。

  ![](https://i.loli.net/2018/12/09/5c0d384044bde.png "JDK下载界面")

#### Q1：只能安装一个jdk版本吗？
  在实际开发中，可能会碰到不同项目需要利用不同jdk版本进行开发，这时候就需要我们安装不同jdk版本，实现不同项目进行jdk切换。在这里，可参考博文[《一台电脑如何配置多个JDK》](https://blog.csdn.net/qq_26545305/article/details/66472521)[《同时安装不同版本JDK遇到的问题》](http://www.cnblogs.com/lojun/p/9664519.html)。

#### Q2：jdk的下载包zip和exe的区别？
  jdk的zip包是绿色版本的，解压完就可以运行了，无需安装，该方式不会帮你设置环境变量。

  ![jdk压缩包](https://i.loli.net/2018/12/10/5c0e611795fa0.png)

  而exe文件是需要安装的，安装的时候会帮你设置环境变量。（本次jdk安装以exe安装为例）

  ![jdk exe文件](https://i.loli.net/2018/12/10/5c0e61912d769.png)

### （2）jdk（开发工具包）安装

  - 打开下载的exe文件，点击下一步。       

  ![](https://i.loli.net/2018/12/10/5c0d3f9e68c00.png "JDK安装")  

  - 选择自己所要安装的路径，点击下一步。  

  ![](https://i.loli.net/2018/12/10/5c0d404b9b13f.png "JDK安装")   

  - 安装成功。（如果安装不成功，可尝试重新安装）

  ![](https://i.loli.net/2018/12/10/5c0d40ff5a386.png)

### （3）环境变量配置
#### Q1：为什么需要配置环境变量？
   首先，我们需要理解什么是 **path（环境变量）** ，环境变量是在操作系统中一个具有特定名字的对象，它包含了一个或者多个应用程序所将使用到的信息，环境变量是dos以前的内部命令。用作运行某个命令的时候，本地找不到某个命令或文件，系统会到这个声明的目录中去查找。系统的运行界面，所有能够直接输入找到的程序都能在path那边找到对应的配置路径。eg:当我们在运行界面输入calc，操作系统会去环境变量已保存的路径中找是否存在calc（计算器）程序，打开该程序。如果没有则会提示windows找不到‘calc’，此时，我们需要在path中配置calc的路径。

  ![系统运行界面](https://i.loli.net/2018/12/10/5c0e696f550ac.png)

  环境变量记录了一些执行程序的位置，方便用户直接查找调用。当安装的软件越来越多时，我们很难记住所有已安装软件的路径。

  而java编程中，因为java必须经过编译才能运行，编译就要通过常用命令，比如：编译的命令为javac等。而这些常用的编译命令就存放在jdk的bin目录。而windows运行java编译命令的时候，本地查找不到某个命令或文件的时候，会到path中去查找。所以，不可能每个java脚本文件所在目录都放置jdk，故把jdk放置path（环境变量）中，这样每个java脚本文件都可以通过path中设定jdk目录找到编译命令进行编译，编译后的class类就要通过 **jre（java runtime environment，java运行环境）** 运行环境进行运行实现。一般设定java的时候为了在任何目录下都可以运行bin文件夹下的命令，就将java的bin目录声明到path中。这只是文本编辑java的时候需要设置，如果用公开发工具[Myeclipse](http://www.myeclipsecn.com/)就不需要设置环境变量，自带jdk。而[Eclipse](https://www.eclipse.org/)启动时候会要求path。

#### Q2：我们需要配置哪些环境参数？
  通常情况我们需要配置以下三个变量：

   **JAVA_HOME**：指向Jdk的安装目录，作用是一些基于Java开发的工具会用到，比如tomcat,Eclipse，如果不用这些工具不需要配置。

   **Path**：指向jdk安装目录下的bin目录，作用是指定命令搜索路径，bin目录下有编译、启动（javac/java）等命令，为了任何目录位置下都可以直接输入命令，而不用输入长长的路径了。如果配置了JAVA_HOME ，直接把%JAVA_HOME%/bin追加到PATH中即可。

   **CLASSPATH**：在于告诉Java执行环境，在哪些目录下可以找到我们所要执行的Java程序所需要的类或者包。不过在JDK1.5之后的版本完全可以不用设置classpath环境变量就能正常运行程序。

大家可参考几篇博文：[《path与classpath区别》](https://blog.csdn.net/zhaihao1996/article/details/78387676)，[《JAVA环境变量JAVA_HOME、CLASSPATH、PATH设置详解》](https://www.cnblogs.com/Wjh794010585/articles/6582079.html)，[《JAVA为什么要配置环境变量，怎样配置》](https://www.cnblogs.com/zhangpengshou/p/4232204.html)

- 右键 **我的电脑-属性-高级系统** 设置，你就会看到以下的界面：

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

    ![cmd-java -version](https://i.loli.net/2018/12/10/5c0e8c47d086e.png)

  - 命令行输入 **javac**，如下图所示：

    ![cmd-Javac](https://i.loli.net/2018/12/10/5c0e8cc60837c.png)

  - 命令行输入 **java**，如下图所示：

    ![cmd-java](https://i.loli.net/2018/12/10/5c0e8d69ab34b.png)

  - 写一段代码验证，我将写的java脚本放在 **e:/HelloWorld.java** 路径下。

    ![Java脚本](https://i.loli.net/2018/12/11/5c0e8e9f6983d.png)

  -  接下来咱们来测试一下。往命令行依次输入以下命令（在cmd中，Tab键有补全字段的作用），结果如下图所示：

    `e:`     
    `javac HelloWorld.java`   
    `java HelloWorld`

   ![运行结果](https://i.loli.net/2018/12/11/5c0e8fbdc4d63.png)

## 2.Eclipse（集成开发环境）的安装与部署
### （1）Eclipse简介
- Eclipse 是一个开放源代码的、基于Java的可扩展开发平台。
- Eclipse 是 Java 的集成开发环境（IDE），当然Eclipse也可以作为其他开发语言的集成开发环境，如C，C++，PHP，和 Ruby 等。
- Eclipse 附带了一个标准的插件集，包括Java开发工具（Java Development Kit，JDK）。

  想深入学习的同学可以参考
[《Eclipse 教程 | 菜鸟教程》](http://www.runoob.com/eclipse/eclipse-tutorial.html)，里面介绍的非常详细。

#### Q1:为什么需要安装Eclipse？
- IDE集成代码编辑、代码生成、界面设计、调试、编译等功能，目前还融合了建模功能。大大的提高了我们的码代码的效率。而Eclipse就是IDE的一种，可以根据自己的爱好需求下载不同的IDE。
- 当然对于初学者而言，建议尽量不要直接用IDE作为入门学习的工具，多用[Notepad++](https://notepad-plus-plus.org/)文本编辑器练习和记忆一些最基础的语法和命令。

### （2）下载与安装
- 点击 [Eclipse官网](https://www.eclipse.org/) 进入Eclipse官网界面，点击download。

  ![Eclipse官网](https://i.loli.net/2018/12/11/5c0fb61a91544.png)

- 点击download。

  ![Eclipse官网](https://i.loli.net/2018/12/11/5c0fb7dd577eb.png)

- 点击download。

  ![Eclipse官网](https://i.loli.net/2018/12/11/5c0fb83b7aa32.png)

- 下载完成后，点击exe安装文件。

  ![Eclipse 安装界面](https://i.loli.net/2018/12/11/5c0fb92253407.png)

- 进入安装界面，选择 **Eclipse IDE for Java Developers**。

  ![Eclipse 安装界面](https://i.loli.net/2018/12/11/5c0fb980ec497.png)

- 选择Eclipse所要安装的目录，点击install（安装），接下来，我们等待安装完就可以使用了。

  ![Eclipse 安装界面](https://i.loli.net/2018/12/11/5c0fba1953c23.png)

## 3.MySql数据库

### （1）MySql数据库简介
- 什么是数据库？数据库（Database）是按照数据结构来组织、存储和管理数据的仓库，
每个数据库都有一个或多个不同的API用于创建，访问，管理，搜索和复制所保存的数据。
- 而MySQL 是最流行的关系型数据库管理系统，在WEB应用方面 MySQL 是最好的RDBMS(Relational Database Management System：关系数据库管理系统)应用软件之一。

### （2）MySql数据库下载与安装
- 点击[MySql官网](https://dev.mysql.com/downloads/windows/)进入下载页面，如下图所示，点击Downloads下的windows下的MySql Installer。

  ![MySql官网下载页面](https://i.loli.net/2018/12/11/5c0fc6ce96f33.png)
- 点击download下载

  ![MySql官网下载页面](https://i.loli.net/2018/12/11/5c0fc87af12ab.png)

- 直接跳过注册和登陆，点击下载，接下来就开始等待安装包下载完。

  ![MySql官网下载页面](https://i.loli.net/2018/12/11/5c0fc9152f70b.png)

- 下载完，打开安装软件，一直点击下一步，安装过程需要设置数据库账号和密码，一般账号为root，密码也为root。还可以设置是否开机自动启动（数据库是后台程序），这里由于笔者之前已经安装过了，这边就不再重复安装（后期补进！！！）。

### （3）MySql数据库的基本操作

- 安装完MySql后，找到mySql安装路径里面一些执行程序。
- 在这里，登陆MySql数据库有几种方式：
  - 将mysql.exe拖入到cmd命令面板， 输入 -uroot -pyao123  -u为用户名  -p 为密码（老版本）。如下图所示，cmd命令：
  `"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -pyao123`

    ![cmd命令行控制台](https://i.loli.net/2018/12/11/5c0fce97456ae.png)

  - 新版本直接打开MySql 8.0 Command Line Client  然后输入密码

    ![MySql Command Line Client 控制台](https://i.loli.net/2018/12/11/5c0fd080caff7.png)

  - 使用mySql Workbench（mySql可视化界面），打开进入到如下图的界面，可以选择创建或打开已经有的数据库。（后期补用cmd的mysql命令创建数据库以及对数据库进行增删改查）

    ![mySql Workbench](https://i.loli.net/2018/12/11/5c0fd12b36acc.png)

- 使用mySql workbench（工作台）创建第一个数据库：
  - 如下图，输入名称和字符编码格式。一般使用utf-8格式。

    ![clipboard.png](https://i.loli.net/2018/12/11/5c0fd1f0a4a33.png)
  - 点击apply（应用），会弹出提示不区分大小写。数据库不区分大小写。（如下图）

    ![clipboard1.png](https://i.loli.net/2018/12/11/5c0fd29a6be4d.png)
- 使用cmd控制台创建第一个数据库：
  - 登陆数据库,输入一下命令，光标前显示mysql证明登陆成功。

    `"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -uroot -pyao123`
  - 先用命令`show databases;` 查看当前所有的数据库，方便等下创建后校验。

    ![show databases](https://i.loli.net/2018/12/11/5c0fd64c386d7.png)
  - 在命令行输入以下命令创建数据库。

    `create schema mygamedb1 default character set utf8;`  

  - 校验是否创建成功 `show databases`,如下图所示，数据库多了mygamedb1的数据库。

    ![create dabase](https://i.loli.net/2018/12/11/5c0fd7b5477de.png)

- 表的创建

  表字段：
  - datatype : int 整数,
  - varchar 字符串，
  - float 小数  
  - datatime 日期 2016-12-12 00:00:00   
  - varchar(length)   限制字符串不能超过length长度
  - pk 主键
  - nn 不能为空  
  - ai 自动增长 一般设置为主键的时候勾选
  - uq  不可重复的
  - default/expression  默认值

  在控制台输入` create table myganmedb1.user(username varchar(50) null ,password varchar(45) null);`  //null表示可以为空

  ![create table](https://i.loli.net/2018/12/11/5c0fd9eef3b80.png)

  在表里面创建username 和password 两行字段，如下图所示，创建成功

    ![表](https://i.loli.net/2018/12/11/5c0fdaecdb7d8.png)

- 右键表  table inspect 可以查看表的属性
- alter table 修改表结构    表一定需要有一个主键，否则没办法插入
- 主键（Primary key） 当提交的时候会自动生成
  - 每一行数据独一无二的标识
  - 一个表必须有主键（只能有一个主键）
  - 不循序为NULL （空值）
  - 由一列或者多列组成(可以有多个列设置为主键，但是这种情况为这多个列一起构成主键，只要这些列构成起来不相同的可以)
- Unique key
  - 表示该数据不能重复
  - 允许一条可以为Null
- 外键
  - 这列数据引用了另外一个表的主键
  - 外键关联

    ![外键关联](https://i.loli.net/2018/12/11/5c0fdc89dccda.png)
  - 在foreignkey中设置外键， foreignkeyname可以随便起名字，表示这个外键。然后设置要关联的那张表，设置这张表的哪个属性和外表的哪个属性相连。
- 通过中间表来解决多对多的关系，方便扩展
- `desc tablename;` 查看表结构 eg: desc mytable;
- `drop table tablename;` 删除表 eg:drop mytable;
- `quit`：退出数据库
- `help`：帮助
- `show databasees;`     : 显示所有的数据库
- `use dbname；`: 切换到哪个数据库  use mygamedb;
- `show tables;`:  显示该数据库下的所有表
-  `create database dbname;`:  创建一个数据库 create database tengfei_testdb;
- `drop database dbname;`   删除一个数据库   drop database tengfei_testdb;
- 往表里面插入数据: `insert into tablename(col_name1,col_name2,...) values(value1,value2,...);`
eg:insert into mytable (username,password) values('tengfei','123456');
关键字与关键字之间需要有空格分割，其他不做要求。
- 查询数据:
  - `select * form tablename;`查询这个表所有列，`*`代表所有列
  - `select * form tablename limit num;`查询这个表所有列 只显示前num条数据。
  - `select * form tablename limit index num;`查询这个表所有列 从第index条开始，查询nun条数据。
  - `select col_name,colname form tablename where condition;`查询几个列 限制条件
  - `select col_name,colname form tablename where condition limit num;`   //limit 语句必须写在最后面
  - `select col_name,colname form tablename where condition order by col_name desc  limit num;`   //order by 放在limit前面  where后面 否则会报错  desc（降序） :默认是desc   asc升序   descend  ascend
- 修改数据
  - `update tablename set col_name = value,set col_name = value where condition;`
    //修改值 没有where语句时，直接在赋值语句加冒号
    eg: update mytable set username='tengfei.yao';
 - `update tablename set col_name = value,set col_name = value where condition;`
    eg: update mytable set username='tengfei.yao'  where id = 1;
- 删除数据
    - `delete from tablename;` //删除这个表的所有数据
    - `delete from tablename where condition;`
- 查询静态值:在查询语句后面可以跟上一些静态值
  - `select 'some string';`
  - `select 1+1;`
  - `select now();`//当前时间 2018-12-02 23:56:55
  - `select curdate();`  2018-12-02
  - `select curtime();` 00:00:27
  - `select col_name as rename,col_name as rename from tabelname;`查询的时候可以通过as 修改一列的列名。eg: select username as name from mytable;

    ![select-19](https://i.loli.net/2018/12/12/5c0fe0f5c6b7c.png)
