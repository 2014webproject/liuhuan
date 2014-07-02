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
 * 登陆类
 * @author haiyaojing
 *
 */
@SuppressWarnings("serial")
public class CheckLogin extends HttpServlet {
	/**
	 * 组装url
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
			// 登陆成功
			if (check(uname, upass) ) {
				// 为其设置session
//				HttpSession session = req.getSession();
//				String ip = (String) session.getAttribute(uname);
//				if (ip != null) {
//					if (ip.equals(req.getRemoteAddr())) {
//						pw.println("ERROR");
//						return;
//					}
//				}
				session.setAttribute(uname, req.getRemoteAddr());
				// 存活时间300s (当前时间-上次操作时间)
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
	 * 检查cookie中是否存在该值
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
	 * 登录操作
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
