package handle.book;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import other.MyInvoke;

import db.myorder.DbOperation;
import db.myorder.Order;
/**
 * ��ȡ������Ϣ
 * @author haiyaojing
 *
 */
@SuppressWarnings("serial")
public class GetMyBook extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) {
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			PrintWriter pw = res.getWriter();
			DbOperation db = DbOperation.db;
			String username = (String) req.getSession().getAttribute("username");
			if (username != null) {
				ArrayList<Order> orders = db.checkOrder(username);
				pw.println(getOrderString(orders));
			} else {
				pw.println("����usernameΪnull!");
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {

		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		this.doPost(req, res);
	}
	/**
	 * ��װ������Ϣ
	 * @param orders
	 * @return
	 */
	private static String getOrderString(ArrayList<Order> orders) {
		StringBuffer buffer = new StringBuffer(
				"<table class=\"table table-striped\"><tr><h1 align=\"center\" color=\"#ff0\">�ҵĶ���</h1></tr>");
		if (orders == null || orders.size() == 0) {
			buffer.append("�޼�¼!</table>");
			return buffer.toString();
		}
		int k = 0;
		for (Order order : orders) {
			buffer.append("<tr><td>"+ order.date + "</td></tr><tr><td>");
			// ͨ�������ȡ�ֶε�ֵ
			Field[] fields = MyInvoke.getFields();
			for (Field field : fields) {
				// �������ֶεĳ���С��5 TODO�����Ǻܺ���..
				if (field.getName().length() <= 4) {
					continue;
				}
				// ��ȡ�ֶε�ֵ  ������0������ӵ�buffer��
				try {
					k = field.getInt(order);
					if (k > 0) {
						// ��ȡ��Ӧ����������
						String s = MyInvoke.getChName(field.getName());
						if (s == null) return "";
						buffer.append(s + " " + k + " ��   ");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			buffer.append("</td></tr>");
			buffer.append("<tr><td>�ܼ�:" + order.sum + "</td></tr>");
			buffer.append("<tr><td></td></tr>");
		}
		buffer.append("</table>");
		return buffer.toString();
	}

}
