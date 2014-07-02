package handle.hotpage;

import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import other.MyInvoke;
import db.myorder.DbOperation;
import db.myorder.Order;


@SuppressWarnings("serial")
public class getHot extends HttpServlet {

	@Override
	public void init(ServletConfig config) {
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			PrintWriter pw = res.getWriter();
			pw.println(getHot());
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
	private static  String covert(int x)
	{
		if(x==0) return "�������";
		if(x==1) return "��Ѽ�����";
		if(x==2) return "����ţ�����";
		if(x==3) return "�����������";
		if(x==4) return "Ϻ�����";
		if(x==5) return "�������";
		if(x==6) return "�۲����";
		if(x==7) return "��ζ����";
		return null;
				
	}
	private static String getHot() {
		return new DbOperation().getHot();
	}

}
