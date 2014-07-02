package db.user;

import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import db.Db;

/**
 * ���ݿ����
 * 
 * @author haiyaojing
 * 
 */
public class SearchDatabase extends Db {
	/**
	 * ���ݿ�����ӣ�׼���Լ����
	 */
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	/**
	 * ��ѯSQL�Ͳ���SQL
	 */
	private static final String SQL_CHECK = "select * from user where uname=?";
	private static final String SQL_REGIST = "insert into user value (?,?,?)";
	
	
	/**
	 * ˽�й��캯�� ����ʵ����
	 */
	private SearchDatabase() {}

	/**
	 * ����û��Ƿ����
	 * 
	 * @param name
	 *            ������Ҫ�����û���
	 * @return
	 */
	synchronized public static boolean userNotExist(String name) {
		try {
			ps = (PreparedStatement) connect.prepareStatement(SQL_CHECK);
			ps.setString(1, name);
			rs = (ResultSet) ps.executeQuery();
			if (rs.next())
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closePsAndRs();
		}
		return true;
	}

	/**
	 * �û���¼����
	 * 
	 * @param name
	 *            �û���
	 * @param passwd
	 *            ����
	 * @return
	 */
	synchronized public static boolean userLogin(String name, String passwd) {
		try {
			ps = (PreparedStatement) connect.prepareStatement(SQL_CHECK);
			ps.setString(1, name);
			rs = (ResultSet) ps.executeQuery();
			if (rs.next()) {
				String pass = rs.getString(2);
				if (pass.equals(passwd))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closePsAndRs();
		}
		return false;
	}

	/**
	 * ע���û�
	 * 
	 * @param uname
	 *            �û���
	 * @param upass
	 *            ����
	 * @return 
	 * 			  true:ע��ɹ� false:ʧ��
	 */
	synchronized public static boolean regist(String uname, String upass,String email) {
		if (uname.length() < 6 || uname.length() > 16) return false;
		if (upass.length() < 6 || upass.length() > 16) return false;
		if (!userNotExist(uname))
			return false;
		try {
			ps = (PreparedStatement) connect.prepareStatement(SQL_REGIST);
			ps.setString(1, uname);
			ps.setString(2, upass);
			ps.setString(3, email);
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePsAndRs();
		}
		return false;
	}

	/**
	 * �ر�PreparedStatement �� ResultSet
	 */
	public static void closePsAndRs() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	/**
	 * ���Ժ��� ��ʹ��
	 * 
	 * @throws SQLException
	 *//*
	public void test() throws SQLException {
		ps = (PreparedStatement) connect.prepareStatement(SQL_CHECK);
		ps.setString(1, "haiyaojing");
		rs = (ResultSet) ps.executeQuery();
		if (rs.next()) {
			System.out.println(rs.getString(1));
		}
		String[] name = { "haiyaojing", "shuiyaojing", "user", "luo" };
		String[] pass = { "luo", "shuiyaojing", "ee", "luo" };
		for (int i = 0; i < 4; i++) {
			System.out.println(regist(name[i], pass[i],""));
		}
	}*/
	
	public static void close() {
		closePsAndRs();
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
