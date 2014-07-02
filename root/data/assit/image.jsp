<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Font"%>
<%@page import="java.awt.Graphics2D"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.Color"%>
<%@page import="java.util.Random"%>
<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%
	// 以当前时间初始化随机种子
	Random rd = new Random(System.currentTimeMillis());
	// 颜色集合
	Color[] colors = { Color.RED, Color.ORANGE, Color.YELLOW,
			Color.GREEN, Color.CYAN, Color.PINK, Color.GRAY,
			Color.BLACK, Color.BLUE };
	// 创建缓冲图像 用于画图
	BufferedImage bi = new BufferedImage(90, 30,
			BufferedImage.TYPE_4BYTE_ABGR_PRE);
	// 获取画笔
	Graphics2D g = (Graphics2D) bi.getGraphics();
	// 设置字体
	Font font = new Font("Times New Roman", Font.ROMAN_BASELINE, 26);
	g.setFont(font);
	StringBuffer str = new StringBuffer();
	// 画
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
	// 存取session
	session.setAttribute("checkimg", str.toString());
	// 图像生效
	g.dispose();
	// 输出到响应端
	//设置页面不缓存
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	ImageIO.write(bi, "png", response.getOutputStream());
%>