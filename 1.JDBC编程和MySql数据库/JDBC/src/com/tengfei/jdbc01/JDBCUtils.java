package com.tengfei.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class JDBCUtils {

	// �������ӳأ�connectiton�����ظ�ʹ�á��õ����ݿ�������ǱȽϺ�ʱ��
	private static final String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT";
	private static final String name = "root";
	private static final String password = "yao123";
	private static ArrayList<Connection> connections  = new ArrayList<Connection>();
	public static Connection Init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		  return DriverManager.getConnection(url, name, password);     //�õ����ݿ�������ǱȽϺ�ʱ��
			
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//���ӳ�
	static {
for (int i = 0; i < 5; i++) {
	Connection con = Init();
	connections.add(con);
}
	}
	//���ӳ�
	public static Connection GetConnection () {
		if(!connections.isEmpty()) {
			Connection con = connections.get(0);
			connections.remove(con);   //��ʹ����ʱ��Ҫ�黹  ����Ҫ�ر�connection
			return con;
		}
		return Init();
	}
}

