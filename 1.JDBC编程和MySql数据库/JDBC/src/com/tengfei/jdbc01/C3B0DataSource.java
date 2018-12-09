package com.tengfei.jdbc01;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3B0DataSource {
	private static final String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT";
	private static final String name = "root";
	private static final String password = "yao123";   //如何修改密码
	
	private static ComboPooledDataSource ds;
	static {  //static块比构造方法最先执行？
		//初始化dbcp数据源
	 
	    
		try {
			   ds = new ComboPooledDataSource();
			ds.setJdbcUrl(url);
			ds.setUser(name);
			ds.setPassword(password);
			ds.setInitialPoolSize(5); //初始化时会创建的连接数  放在连接池里面。
			ds.setMinPoolSize(20);   //最大的连接个数
			ds.setMinPoolSize(3);   //至少有几个空闲的连接。
			ds.setDriverClass("com.mysql.cj.jdbc.Driver");
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void TakeBack() {
		
	}
	public static void TakeBackConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection GetConnection() {
		
		try {
			return ds.getConnection();  //通过dbcp得到的连接，不需要归还，直接关闭close就可以。  这里的close并不会直接和数据库断开连接，会直接归还给连接对象池。
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
