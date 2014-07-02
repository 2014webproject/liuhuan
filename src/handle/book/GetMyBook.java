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
 * 获取订单消息
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
				pw.println("传入username为null!");
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
	 * 组装订单消息
	 * @param orders
	 * @return
	 */
	private static String getOrderString(ArrayList<Order> orders) {
		StringBuffer buffer = new StringBuffer(
				"<table class=\"table table-striped\"><tr><h1 align=\"center\" color=\"#ff0\">我的订单</h1></tr>");
		if (orders == null || orders.size() == 0) {
			buffer.append("无记录!</table>");
			return buffer.toString();
		}
		int k = 0;
		for (Order order : orders) {
			buffer.append("<tr><td>"+ order.date + "</td></tr><tr><td>");
			// 通过反射获取字段的值
			Field[] fields = MyInvoke.getFields();
			for (Field field : fields) {
				// 非数量字段的长度小于5 TODO：不是很合适..
				if (field.getName().length() <= 4) {
					continue;
				}
				// 获取字段的值  若大于0则将其添加到buffer中
				try {
					k = field.getInt(order);
					if (k > 0) {
						// 获取对应的中文名称
						String s = MyInvoke.getChName(field.getName());
						if (s == null) return "";
						buffer.append(s + " " + k + " 份   ");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			buffer.append("</td></tr>");
			buffer.append("<tr><td>总价:" + order.sum + "</td></tr>");
			buffer.append("<tr><td></td></tr>");
		}
		buffer.append("</table>");
		return buffer.toString();
	}

}
