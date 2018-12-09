package com.tengfei.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class JDBCUtils {

	// 创建连接池，connectiton可以重复使用。得到数据库的连接是比较耗时的
	private static final String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT";
	private static final String name = "root";
	private static final String password = "yao123";
	private static ArrayList<Connection> connections  = new ArrayList<Connection>();
	public static Connection Init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		  return DriverManager.getConnection(url, name, password);     //得到数据库的连接是比较耗时的
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//连接池
	static {
for (int i = 0; i < 5; i++) {
	Connection con = Init();
	connections.add(con);
}
	}
	//连接池
	public static Connection GetConnection () {
		if(!connections.isEmpty()) {
			Connection con = connections.get(0);
			connections.remove(con);   //当使用完时需要归还  不需要关闭connection
			return con;
		}
		return Init();
	}
}

