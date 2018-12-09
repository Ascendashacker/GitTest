package com.tengfei.jdbc01;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPDataSource {
	private static final String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT";
	private static final String name = "root";
	private static final String password = "yao123";   //����޸�����
	
	private static BasicDataSource ds;
	static {  //static��ȹ��췽������ִ�У�
		//��ʼ��dbcp����Դ
	    ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl(url);
		ds.setUsername(name);
		ds.setPassword(password);
		ds.setInitialSize(5); //��ʼ��ʱ�ᴴ����������  �������ӳ����档
		ds.setMaxTotal(20);   //�������Ӹ���
		ds.setMinIdle(3);   //�����м������е����ӡ�
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
