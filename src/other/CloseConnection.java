package other;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import db.user.SearchDatabase;

/**
 * 关闭数据库连接- -
 * 关闭后再访问会出现异常TODO
 * @author haiyaojing
 *
 */
@SuppressWarnings("serial")
public class CloseConnection extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		this.doPost(req, res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		PrintWriter pw;
		System.out.println("ssession");
		res.setCharacterEncoding("GBK");
		HttpSession session = req.getSession();
		session.setAttribute("username", "");
		System.out.println("aaaaaaaaa");
		System.out.println("session"+session.getAttribute("username"));
	}
}
