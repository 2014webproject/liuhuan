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
			      mailInfo.setPassword("liu1993huan0621");//������������    
			      mailInfo.setFromAddress("liuhuan2002767@163.com");    
			      //mailInfo.setFromAddress("administrators@minghe.com");    
			      mailInfo.setToAddress(email);    
			      mailInfo.setSubject("������������");    
			      mailInfo.setContent("���������ǣ�"+password);    
			         //�������Ҫ�������ʼ�   
			      SimpleMailSender sms = new SimpleMailSender();   
			          sms.sendTextMail(mailInfo);//���������ʽ    
			          //sms.sendHtmlMail(mailInfo);//����html��ʽ  
			      pw.println("�Ѿ����������뷢�͵���ע��ʱ�����䣡������գ�");
			}
			else
			{
				pw.println("�û��������ڣ����������룡");
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

}

