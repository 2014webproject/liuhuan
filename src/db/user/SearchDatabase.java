package db.user;

import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import db.Db;

/**
 * 数据库操作
 * 
 * @author haiyaojing
 * 
 */
public class SearchDatabase extends Db {
	/**
	 * 数据库的连接，准备以及结果
	 */
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	/**
	 * 查询SQL和插入SQL
	 */
	private static final String SQL_CHECK = "select * from user where uname=?";
	private static final String SQL_REGIST = "insert into user value (?,?,?)";
	
	
	/**
	 * 私有构造函数 不能实例化
	 */
	private SearchDatabase() {}

	/**
	 * 检查用户是否存在
	 * 
	 * @param name
	 *            传入需要检测的用户名
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
	 * 用户登录操作
	 * 
	 * @param name
	 *            用户名
	 * @param passwd
	 *            密码
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
	 * 注册用户
	 * 
	 * @param uname
	 *            用户名
	 * @param upass
	 *            密码
	 * @return 
	 * 			  true:注册成功 false:失败
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
	 * 关闭PreparedStatement 和 ResultSet
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
	 * 测试函数 不使用
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
