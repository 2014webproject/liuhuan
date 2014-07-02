package db.myorder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.Db;

/**
 * 
 * @author hucangxin
 * 
 */
public class DbOperation extends Db {

	public static DbOperation db;
	
	static {
		db = new DbOperation();
	}

	public boolean isClose() {
		return connect.isClosed();
	}

	private String covert(int x)
	{
		if(x==0) return "番茄馄饨";
		if(x==1) return "老鸭煲馄饨";
		if(x==2) return "奇香牛肉馄饨";
		if(x==3) return "名禾鲜鱼馄饨";
		if(x==4) return "虾仁馄饨";
		if(x==5) return "三鲜馄饨";
		if(x==6) return "芹菜馄饨";
		if(x==7) return "美味锅贴";
		return null;
				
	}
	/* deliver order to database */
	public boolean orderDeliver(Order o) {
		try {
			Statement sta = connect.createStatement();
			String query = "insert into myorder values('"+o.id+"','"
					+ o.date + "'," + o.fanQie + "," + o.laoYa + "," + o.niuRou
					+ "," + o.xiaRen + "," + o.sanXian + "," + o.qinCai + ","
					+ o.guoTie + "," + o.xianYu + "," + o.sum + ",'"+o.address+"')";
			sta.execute(query);
			query="update total set num=num+"+o.getFanQie()+" where s=0";
			sta.executeUpdate(query);
			query="update total set num=num+"+o.getLaoYa()+" where s=1";
			sta.executeUpdate(query);
			query="update total set num=num+"+o.getNiuRou()+" where s=2";
			sta.executeUpdate(query);
			query="update total set num=num+"+o.getXianYu()+" where s=3";
			sta.executeUpdate(query);
			query="update total set num=num+"+o.getXiaRen()+" where s=4";
			sta.executeUpdate(query);
			query="update total set num=num+"+o.getSanXian()+" where s=5";
			sta.executeUpdate(query);
			query="update total set num=num+"+o.getQinCai()+" where s=6";
			sta.executeUpdate(query);
			query="update total set num=num+"+o.getGuoTie()+" where s=7";
			sta.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/* 用户查看个人订单信息 */
	public ArrayList<Order> checkOrder(String id) {
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			Statement sta = connect.createStatement();
			String query = "select * from myorder where id='" + id + "'";
			ResultSet rst = sta.executeQuery(query);
			while (rst.next()) {
				Order o = new Order();
				o.id = rst.getString("id");
				o.fanQie = rst.getInt("fanQie");
				o.laoYa = rst.getInt("laoYa");
				o.xianYu = rst.getInt("xianYu");
				o.guoTie = rst.getInt("guoTie");
				o.niuRou = rst.getInt("niuRou");
				o.qinCai = rst.getInt("qinCai");
				o.sanXian = rst.getInt("sanXian");
				o.xiaRen = rst.getInt("xiaRen");
				o.sum = rst.getInt("total");
				o.date = rst.getString("date");
			
				orders.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return orders;
	}

	public ArrayList<Order> getAllOrders() {
		ArrayList<Order> orders = new ArrayList<Order>();
		String sql = "select * from myorder";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connect.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order o = new Order();
				o.id = rs.getString("id");
				o.fanQie = rs.getInt("fanQie");
				o.laoYa = rs.getInt("laoYa");
				o.xianYu = rs.getInt("xianYu");
				o.guoTie = rs.getInt("guoTie");
				o.niuRou = rs.getInt("niuRou");
				o.qinCai = rs.getInt("qinCai");
				o.sanXian = rs.getInt("sanXian");
				o.xiaRen = rs.getInt("xiaRen");
				o.sum = rs.getInt("total");
				o.date = rs.getString("date");
				o.address=rs.getString("address");
				orders.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return orders;
	}
	
	
	public  String getHot() {
		int []num=new int[14];
		StringBuffer buffer=new StringBuffer("<table class=\"table table-striped\"><tr><h1 align=\"center\" color=\"#ff0\">" +
				" 产品排行榜</h1></tr>");
		try {
			Statement sta = connect.createStatement();
			String query="select num  from total";
			ResultSet rs=sta.executeQuery(query);
			int i=0;
			while(rs.next())
			{
				num[i++]+=rs.getInt(1);
			}
			int i1,j,max=0;
			for(i1=1;i1<=4;i1++)
			{
				for(j=0;j<=7;j++)
					if(num[j]>num[max]) max=j;
				if(i1==1) buffer.append(" <tr><td><h1>第一名</h1></td> <td><h1>"+covert(max)+"</h1></td><td><h1>销量："+num[max]+"</h1></td></tr>");
				if(i1==2) buffer.append(" <tr><td><h2>第二名</h2></td> <td><h2>"+covert(max)+"</h2></td><td><h2>销量："+num[max]+"</h2></td></tr>");
				if(i1==3) buffer.append(" <tr><td><h3>第三名</h3></td> <td><h3>"+covert(max)+"</h3></td><td><h3>销量："+num[max]+"</h3></td></tr>");
				if(i1==4) buffer.append(" <tr><td><h4>第四名</h4></td> <td><h4>"+covert(max)+"</h4></td><td><h4>销量："+num[max]+"</h4></td></tr>");
				num[max]=-1;
				max=0;
			}
			buffer.append("</table>");
			return buffer.toString();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return buffer.toString();
	}
	public boolean deleteOrder(String username, String date) {
		String sql = "delete from myorder where id=? and date=?";
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, date);
			int k = ps.executeUpdate();
			return k > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return false;
	}
	public static String getEmail(String username) throws SQLException
	{
		
		String sql="select mail from user where uname='"+username+"'";
		
		Statement sta = connect.createStatement();
		
		ResultSet rs=sta.executeQuery(sql);
		System.out.println("a"+sql);
		System.out.println("adsf");
		if(rs.next())
		{
			return rs.getString(1);
		}
		
		return "";
		
	}
	
	public static String getPassword(String username) throws SQLException
	{
		String sql="select upass from user where uname='"+username+"'";
		Statement sta = connect.createStatement();
		ResultSet rs=sta.executeQuery(sql);
		if(rs.next())
		{
			String result=rs.getString(1);
			return result;
		}
		return "";
	}
	
	public ResultSet getCommentList() throws SQLException
	{
		String sql="select * from comments";
		Statement sta = connect.createStatement();
		ResultSet rs=sta.executeQuery(sql);
		return rs;
	}
	
	public void insertComment(String uname,String content) throws SQLException
	{
		String sql="insert into comments values('"+uname+"','"+content+"')";
		Statement sta = connect.createStatement();
		sta.executeUpdate(sql);
	}
	
	public static void main(String[] args)
	{
		String sql="select * from user";
		Statement sta;
		try {
			sta = connect.createStatement();
			ResultSet rs=sta.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
