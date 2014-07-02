package db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public abstract class Db {
	private static  String DBDRIVER;
	// MySQL配置时的用户名
	private static  String DBUSER;
	// MySQL配置时的密码
	private static  String DBPASS;
	private static  String DBURL;
	protected static Connection connect;
	// 初始化
	static {
		try {
			File f = new File(Db.class.getResource("").getPath().replaceAll("%20", " "));
			String s = f.getParentFile().getParent() + "/db.properties"; 
			Properties p = new Properties();
			p.load(new FileReader(new File(s)));
			DBDRIVER = p.getProperty("DBDRIVER");
			DBUSER = p.getProperty("DBUSER");
			DBURL = p.getProperty("DBURL");
			DBPASS = p.getProperty("DBPASS");
			Class.forName(DBDRIVER);
			connect = (Connection) DriverManager.getConnection(DBURL, DBUSER,
					DBPASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
