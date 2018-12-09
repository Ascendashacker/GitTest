package com.tengfei.jdbc01;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPDataSource {
	private static final String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT";
	private static final String name = "root";
	private static final String password = "yao123";   //如何修改密码
	
	private static BasicDataSource ds;
	static {  //static块比构造方法最先执行？
		//初始化dbcp数据源
	    ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl(url);
		ds.setUsername(name);
		ds.setPassword(password);
		ds.setInitialSize(5); //初始化时会创建的连接数  放在连接池里面。
		ds.setMaxTotal(20);   //最大的连接个数
		ds.setMinIdle(3);   //至少有几个空闲的连接。
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
