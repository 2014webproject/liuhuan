package handle.handleorder;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.myorder.DbOperation;

@SuppressWarnings("serial")
public class DeleteOrder extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		this.doPost(req, res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			String username = req.getParameter("username");
			String date= req.getParameter("date");
			System.out.println(username + ":" + date);
			PrintWriter pw = res.getWriter();
			boolean b = new DbOperation().deleteOrder(username, date);
			pw.print(b);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
