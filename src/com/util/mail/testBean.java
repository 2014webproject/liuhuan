package com.util.mail;

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
public class testBean extends HttpServlet {

	@Override
	public void init(ServletConfig config) {
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			
			PrintWriter pw = res.getWriter();
			String uname= req.getParameter("username");
			System.out.println(uname);
			String email=DbOperation.getEmail(uname);
			String password=DbOperation.getPassword(uname);
			System.out.println("email:"+email+"password"+password);
			if(email!="")
			{
				 MailSenderInfo mailInfo = new MailSenderInfo();    
			      mailInfo.setMailServerHost("smtp.163.com");    
			      mailInfo.setMailServerPort("25");    
			      mailInfo.setValidate(true);    
			      mailInfo.setUserName("liuhuan2002767@163.com");    
			      mailInfo.setPassword("liu1993huan0621");//您的邮箱密码    
			      mailInfo.setFromAddress("liuhuan2002767@163.com");    
			      //mailInfo.setFromAddress("administrators@minghe.com");    
			      mailInfo.setToAddress(email);    
			      mailInfo.setSubject("这是您的密码");    
			      mailInfo.setContent("您的密码是："+password);    
			         //这个类主要来发送邮件   
			      SimpleMailSender sms = new SimpleMailSender();   
			          sms.sendTextMail(mailInfo);//发送文体格式    
			          //sms.sendHtmlMail(mailInfo);//发送html格式  
			      pw.println("已经把您的密码发送到您注册时的邮箱！敬请查收！");
			}
			else
			{
				pw.println("用户名不存在！请重新输入！");
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

}

