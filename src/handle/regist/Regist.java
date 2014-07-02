package handle.regist;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.user.SearchDatabase;


/**
 * ע����
 * 
 * @author haiyaojing
 */
@SuppressWarnings("serial")
public class Regist extends HttpServlet {
	/**
	 * ��get�ύ��Ϊpost�ύ
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		this.doPost(req, res);
	}

	/**
	 * post�����ύ
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
	
			String uname = req.getParameter("username");
			// ���ñ��� ��Ҫ��Ϊ��ʹ��Ajax����UTF-8��������
			String upass = req.getParameter("password");
			String email = req.getParameter("email");
			PrintWriter pw = res.getWriter();
//			if (text != null) {
//				HttpSession session = req.getSession();
//				String str = (String) session.getAttribute("checkimg");
//				if (text.equals(str)) {
//					pw.println("SUCCESS");
//				} else {
//					pw.println("FAIL");
//				}
//				return;
//			}
			System.out.println("r: username:" + uname + "-- upass:" + upass);
			if (upass != null) {
				ajaxForRegist(uname, upass,email, pw);
			} else {
				ajaxForCheckUserExist(uname, pw);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ajaxForCheckUserExist(String name, PrintWriter out) {
		boolean b = SearchDatabase.userNotExist(name);
		if (b) {
			out.println("SUCCESS");
		} else {
			out.println("FAIL");
		}
	}

	private void ajaxForRegist(String name, String password,String email, PrintWriter out) {
		boolean b = SearchDatabase.regist(name, password,email);
		if (b) {
			out.println("SUCCESS");
		} else {
			out.println("FAIL");
		}
	}
}
