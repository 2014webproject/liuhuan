<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Font"%>
<%@page import="java.awt.Graphics2D"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.Color"%>
<%@page import="java.util.Random"%>
<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%
	// �Ե�ǰʱ���ʼ���������
	Random rd = new Random(System.currentTimeMillis());
	// ��ɫ����
	Color[] colors = { Color.RED, Color.ORANGE, Color.YELLOW,
			Color.GREEN, Color.CYAN, Color.PINK, Color.GRAY,
			Color.BLACK, Color.BLUE };
	// ��������ͼ�� ���ڻ�ͼ
	BufferedImage bi = new BufferedImage(90, 30,
			BufferedImage.TYPE_4BYTE_ABGR_PRE);
	// ��ȡ����
	Graphics2D g = (Graphics2D) bi.getGraphics();
	// ��������
	Font font = new Font("Times New Roman", Font.ROMAN_BASELINE, 26);
	g.setFont(font);
	StringBuffer str = new StringBuffer();
	// ��
	for (int i = 0; i < 4; i++) {
		String s;
		g.setColor(colors[rd.nextInt(colors.length)]);
		if (rd.nextBoolean()) {
			s = Integer.toString(rd.nextInt(10));
		} else {
			s = Character.toString((char) ('A' + rd.nextInt(26)));
		}
		str.append(s);
		g.drawString(s, 5 + 20 * i, 25);
	}
	// ��ȡsession
	session.setAttribute("checkimg", str.toString());
	// ͼ����Ч
	g.dispose();
	// �������Ӧ��
	//����ҳ�治����
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	ImageIO.write(bi, "png", response.getOutputStream());
%>