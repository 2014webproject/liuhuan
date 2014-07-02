package handle.logout;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import other.RandomValidateCode;
import db.user.SearchDatabase;

public class LogOut extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		session.invalidate();
	}
	@Override
	public void destroy() {
		super.destroy();
		SearchDatabase.close();
	}
}

