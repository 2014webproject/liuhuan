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
	 * ×é×°¶©µ¥ÏûÏ¢
	 * @param orders
	 * @return
	 */
	private static  String covert(int x)
	{
		if(x==0) return "·¬ÇÑâÆâ½";
		if(x==1) return "ÀÏÑ¼ìÒâÆâ½";
		if(x==2) return "ÆæÏãÅ£ÈââÆâ½";
		if(x==3) return "ÃûºÌÏÊÓãâÆâ½";
		if(x==4) return "ÏºÈÊâÆâ½";
		if(x==5) return "ÈıÏÊâÆâ½";
		if(x==6) return "ÇÛ²ËâÆâ½";
		if(x==7) return "ÃÀÎ¶¹øÌù";
		return null;
				
	}
	private static String getHot() {
		return new DbOperation().getHot();
	}

}
