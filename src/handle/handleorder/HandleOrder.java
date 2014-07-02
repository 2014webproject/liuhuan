package handle.handleorder;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import other.MyInvoke;
import db.myorder.DbOperation;
import db.myorder.Order;

@SuppressWarnings("serial")
public class HandleOrder extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		this.doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			PrintWriter pw = res.getWriter();
			StringBuffer buffer = new StringBuffer();
			buffer.append("<table class=\"table table-striped\"><tr><h1 align=\"center\" color=\"#ff0\"> ������</h1></tr>");
			ArrayList<Order> list = new DbOperation().getAllOrders();
			Field[] fields = MyInvoke.getFields();
			int k = 0;
			for (int i = 0; i < list.size(); i++) {
				Order o = list.get(i);
				buffer.append("<div><tr><td>ID:" + o.getId() + "</td></tr>");
				buffer.append("<tr><td>Date:" + o.getDate() + "</td></tr><tr><td>");
				buffer.append("��ַ��"+o.getAddress()+"</td></tr><tr><td>");
				// ͨ�������ȡ�ֶε�ֵ
				for (Field field : fields) {
					// �������ֶεĳ���С��5 TODO�����Ǻܺ���..
					if (field.getName().length() <= 4) {
						continue;
					}
					// ��ȡ�ֶε�ֵ ������0������ӵ�buffer��
					try {
						k = field.getInt(o);
						if (k > 0) {
							// ��ȡ��Ӧ����������
							String s = MyInvoke.getChName(field.getName());
							if (s == null)
								return;
							buffer.append(s + ":" + k + "��   ");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				buffer.append("</td></tr>");
				buffer.append("<tr><td>����" + o.sum + "Ԫ</td></tr>");
				String str = "\"username=" + o.id + "&date=" + o.date + "\"";
				buffer.append("<tr><td><button id=" + str + " class=\"btn btn-danger\"  data-toggle=\"modal\" data-target=\"#myModal\" onClick=\"aaa(this.id)\" id=\"button_"
						+ i + "\">ȷ���ջ�</button><!-- /example --></td></tr></div>");
			}
			buffer.append("</table>");
			pw.println(buffer.toString());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
