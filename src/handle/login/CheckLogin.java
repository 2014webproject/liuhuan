package handle.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import other.RandomValidateCode;

import db.user.SearchDatabase;
/**
 * ��½��
 * @author haiyaojing
 *
 */
@SuppressWarnings("serial")
public class CheckLogin extends HttpServlet {
	/**
	 * ��װurl
	 */
	private StringBuffer sb = new StringBuffer();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		this.doPost(req, res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			String uname = req.getParameter("username");
			String upass = req.getParameter("password");
			String inputCode=req.getParameter("inputCode");
		
			PrintWriter pw = res.getWriter();
			//System.out.println("user login:" + uname + "-" + upass);
			String index = req.getParameter("location");
			sb.setLength(0);
			sb.append("index.jsp?");
			HttpSession session = req.getSession();
			if (index != null && index.length() > 0) {
				sb.append("location=" + index + "&");
//				session.setAttribute("location", index);
			}
			// ��½�ɹ�
			if (check(uname, upass) ) {
				// Ϊ������session
//				HttpSession session = req.getSession();
//				String ip = (String) session.getAttribute(uname);
//				if (ip != null) {
//					if (ip.equals(req.getRemoteAddr())) {
//						pw.println("ERROR");
//						return;
//					}
//				}
				session.setAttribute(uname, req.getRemoteAddr());
				// ���ʱ��300s (��ǰʱ��-�ϴβ���ʱ��)
				session.setMaxInactiveInterval(300);
				session.setAttribute("username", uname);
				if (uname.equals("haiyaojing")) {
					session.setAttribute("manager", uname);
				}
				sb.append("state=user");
			} else {
				sb.append("state=login");
			}
			if(inputCode.toLowerCase().equals(RandomValidateCode.randomString.toLowerCase())==false)
			{
				sb.append("+inputCodeError");
			}
			pw.println(sb.toString());
			System.out.println("IP:" + req.getRemoteAddr());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**onclick="document.getElementById('checktext').src ='data/assit/image.jsp?temp=' + (new Date().getTime().toString(36));return false;"
	 * ���cookie���Ƿ���ڸ�ֵ
	 * @param name
	 * @param cookies
	 * @return
	 */
//	private boolean searchCookie(String name, Cookie[] cookies) {
//		for (Cookie cookie : cookies) {
//			System.out.println(cookie.getName());
//		}
//		for (Cookie cookie : cookies) {
//			if (cookie.getName().equals(name)) return true;
//		}
//		return false;
//	}
	/**
	 * ��¼����
	 * @param name
	 * @param passwd
	 * @return
	 */
	public boolean check(String name, String passwd) {
//		return SearchDatabase.check(name, passwd, SearchDatabase.SEARCH);
		return SearchDatabase.userLogin(name, passwd);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		SearchDatabase.close();
	}
}
