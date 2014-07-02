package handle.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.myorder.DbOperation;

public class HandleInsertComment extends HttpServlet {
	/**
	 * ×é×°url
	 */
	private StringBuffer sb = new StringBuffer();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		this.doPost(req, res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			System.out.println("jinlaile");
			PrintWriter pw = res.getWriter();
		    String uname = (String) req.getSession().getAttribute("username");
		    String content=req.getParameter("content");
		    
		    System.out.println("asdfsdfafsdsdfsfda:"+uname+"asdfdsdfsfda:"+content);
		    DbOperation.db.insertComment(uname, content);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

