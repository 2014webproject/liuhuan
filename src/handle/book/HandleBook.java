package handle.book;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import other.MyInvoke;

import db.myorder.DbOperation;
import db.myorder.Order;
/**
 * 对订单进行处理
 * @author haiyaojing
 *
 */
@SuppressWarnings("serial")
public class HandleBook extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			String s = req.getParameter("booklist");
			String address=req.getParameter("address");
			PrintWriter pw = res.getWriter();
			// TODO: 此处大量使用正则表达式的split
			String[] str = s.split(";");
			Order order = new Order();
			int sum = 0;
			for (String string : str) {
				String[] texts = string.split(":");
//				System.out.println("Name:" + texts[0] + "->" + "Num:"
//						+ texts[1] + "->" + "Total:" + texts[2]);
				// 反射获取字段的set方法
				Method method = MyInvoke.getSetter(texts[0]);
				if (method == null) {
					pw.println("ERROR");
					return;
				} else {
					// 设置字段的值
					method.invoke(order, Integer.parseInt(texts[1]));
					sum += Integer.parseInt(texts[2]);
				}
			}
			order.sum = sum;
			order.address=address;
			// 简单时间格式化
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			order.setDate(sim.format(new Date()));
			String username = (String) req.getSession().getAttribute("username");
			if (username == null) return;
			order.setId(username);
			DbOperation dp = DbOperation.db;
			// 添加到数据库中
			dp.orderDeliver(order);
			pw.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		this.doPost(req, res);
	}
}
