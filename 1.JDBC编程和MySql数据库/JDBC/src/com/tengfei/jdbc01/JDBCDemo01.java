package com.tengfei.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo01 {
	
	//web01 ���ݿ� tengfei_user ��   ������username password
	public static void main(String[] args) {
//		SelectAll();
//		System.out.println(SelectByName2("maic","1234")) ;
//		System.out.println(SelectByName("maic","41234' or '1'='1")) ;
		//SelectByPage(2,4);
		//Insert("�����","53weaf");
		//Delect(17);
		//Update("��������","awf");
		Transfer("��������", "�����", 1000);
		}
	//ת��   1ת��2
	public static void Transfer(String username1,String username2,int n) {
		Connection con = null;
		PreparedStatement stat1 = null;
		PreparedStatement stat2 = null;
		//con = JDBCUtils.Init();
		//con = DBCPDataSource.GetConnection();
		con = C3B0DataSource.GetConnection();
		try {
			con.setAutoCommit(false);  //��������
			String sql = "Update tengfei_user set money = money - ? where username = ?";
			stat1 = con.prepareStatement(sql);
			stat1.setInt(1,n);
			stat1.setString(2, username1);
			int i = stat1.executeUpdate();
			if (i>0) {
				System.out.println("ת��1�ɹ�");
			}else
			{System.out.println("ת��1ʧ��");}
			sql = "Update tengfei_user set money = money + ? where username = ?";
			stat2 = con.prepareStatement(sql);
			stat2.setInt(1,n);
			stat2.setString(2, username2);
			i = stat2.executeUpdate();
			if (i>0) {
				System.out.println("ת��2�ɹ�");
			}else
			{System.out.println("ת��2ʧ��");}
			con.commit();  //�ύ����
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//�޸�����
	public static void Update(String username,String password) {
		Connection con = null;
		PreparedStatement stat = null;
		con = JDBCUtils.Init();
		String sql = "Update tengfei_user set password = ? where username = ?";
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1,password);
			stat.setString(2, username);
			int i = stat.executeUpdate();
			if (i>0) {
				System.out.println("�޸ĳɹ�");
			}else
			{System.out.println("�޸�ʧ��");}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//ɾ������
	public static void Delect (int id) {
		Connection con = null;
		PreparedStatement stat = null;
		con = JDBCUtils.Init();
		String sql = "Delete from tengfei_user where id = ?";
		try {
			stat = con.prepareStatement(sql);
			stat.setInt(1,id);
			int i = stat.executeUpdate();
			if (i>0) {
				System.out.println("ɾ���ɹ�");
			}else
			{System.out.println("ɾ��ʧ��");}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	//��������
	public static void Insert(String userName,String password) {
		Connection con = null;
		PreparedStatement stat = null;
		con = JDBCUtils.Init();
		String sql = "Insert into tengfei_user(username,password) values (?,?)";
		try {
			stat = con.prepareStatement(sql);
			stat.setString(1, userName);
			stat.setString(2, password);
			int i = stat.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//��ҳ��ѯ
	public static void SelectByPage(int pageNo,int pageSize) 
	{
		Connection con = null;
	PreparedStatement stat =null;
	ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT";
			con = DriverManager.getConnection(url, "root", "yao123");
			String sql = "Select * from tengfei_user limit ?,?";
			stat =con.prepareStatement(sql);
			stat.setInt(1, pageNo);
			stat.setInt(2, pageSize);
			rs = stat.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1) +"   "+ rs.getString(2));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)  con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(stat!=null) stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}
	//У���û��������룬���sqlע��
	public static boolean SelectByName2(String username,String password) {
		Connection con = null;
		PreparedStatement stat =null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT";
			con = DriverManager.getConnection(url, "root", "yao123");
			String sql = "Select * from tengfei_user where username=? and password = ?";
			stat =con.prepareStatement(sql);
			stat.setString(1, username);
			stat.setString(2, password);
			rs = stat.executeQuery();
			
			if (rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return false;
	}
	//�û���������У��
	public static boolean SelectByName(String userName,String password) {
		Connection con = null;
		Statement stam = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");   //�п����Ҳ������࣬��Ҫ�쳣����
			String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT";
			con = DriverManager.getConnection(url, "root", "yao123");
			stam = con.createStatement();
			String sql = "Select * from tengfei_user where username = '"+ userName + "' and password ='"+password+"'" ;
			System.out.println(sql);
			rs = stam.executeQuery(sql);
			if (rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(con!=null)  con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(stam!=null) stam.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	//��ѯ
	public static void SelectAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//ʹ��ʲô�������ݿ�
			//String url = "jdbc:mysql://localhost:3306/web01";
			String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF8&useSSL=false&serverTimezone=GMT";
			String user = "root";
			String password = "yao123";
		    con = DriverManager.getConnection(url, user, password);
		    stmt = con.createStatement();
		    result =  stmt.executeQuery("select * from tengfei_user");
			while(result.next()) {
				System.out.println(result.getInt(1)+","+result.getString(2)+","+result.getString(3));
				System.out.println(result.getInt("id")+","+result.getString("username")+","+result.getString("password"));
			}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			
					try {
						if (result!=null) result.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					try {
						if(stmt!= null) stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					try {
						if(con!=null) con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
	}
	}
