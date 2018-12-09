package com.tengfei.jdbc01;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3B0DataSource {
	private static final String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT";
	private static final String name = "root";
	private static final String password = "yao123";   //����޸�����
	
	private static ComboPooledDataSource ds;
	static {  //static��ȹ��췽������ִ�У�
		//��ʼ��dbcp����Դ
	 
	    
		try {
			   ds = new ComboPooledDataSource();
			ds.setJdbcUrl(url);
			ds.setUser(name);
			ds.setPassword(password);
			ds.setInitialPoolSize(5); //��ʼ��ʱ�ᴴ����������  �������ӳ����档
			ds.setMinPoolSize(20);   //�������Ӹ���
			ds.setMinPoolSize(3);   //�����м������е����ӡ�
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
			return ds.getConnection();  //ͨ��dbcp�õ������ӣ�����Ҫ�黹��ֱ�ӹر�close�Ϳ��ԡ�  �����close������ֱ�Ӻ����ݿ�Ͽ����ӣ���ֱ�ӹ黹�����Ӷ���ء�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
