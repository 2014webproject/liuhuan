package handle.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import other.RandomValidateCode;
import db.myorder.DbOperation;
import db.user.SearchDatabase;

public class HandleDisplayComment extends HttpServlet {
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
			sb.delete(0, sb.length());
			PrintWriter pw = res.getWriter();
			ResultSet rs=DbOperation.db.getCommentList();
			int x= Integer.parseInt(req.getParameter("num"));
			
			 rs.last();
			 int size=rs.getRow();
			 rs.beforeFirst();
			 rs.next();
			if(size-1<x)
			{
				pw.println("�Ѿ������һ�������ˣ�");
				pw.flush();
				pw.close();
			}
			else
			{
				while(x>0)
				{
					rs.next();
					x--;
				}
				String user=rs.getString(1);
				String content=rs.getString(2);
				System.out.println(user);
				sb.append("<table class=\"table table-striped\">");
				if(user.length()==0||user.equals("null")||user==null) sb.append("<tr><td>�����û�˵</td></tr>");
				else sb.append("<tr><td>�ǳ�Ϊ"+user+"������˵��</td></tr>");
				sb.append("<tr><td>"+content+"</td></tr>");
				sb.append("</table>");
				pw.println(sb.toString());
				pw.flush();
				pw.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
