package user;

import java.io.Serializable;

/**
 * jsp + javabean @unuse
 * @author haiyaojing
 *
 */
@SuppressWarnings("serial")
public class UserBean implements Serializable{
	private String username;
	private boolean isAlive;
	private String userinfo;
	
	public UserBean(){}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isAlive() {
		return isAlive;
	}
	/**
	 * ���������Ƿ���ת�Ƶ� get������
	 * @param isAlive
	 */
	public void setAlive(boolean isAlive) {
	}
	public String getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(String userinfo) {
		this.userinfo = userinfo;
	}
	
	
}
