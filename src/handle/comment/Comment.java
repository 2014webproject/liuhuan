package handle.comment;

public class Comment {
	private String user;
	private String content;
	
	public String getUser(){return user;}
	public String getContent(){return content;}
	public void setUser(String user){this.user=user;}
	public void setContent(String content){this.content=content;}
	public static void main(String[] args)
	{
	    String testStr = "��";
	    try {
	        // �õ�ָ��������ֽ����� �ַ���--->�ֽ�����
	        byte[] t_iso = testStr.getBytes("ISO8859-1");
	        byte[] t_gbk = testStr.getBytes("GBK");
	        byte[] t_utf8 = testStr.getBytes("UTF-8");
	        System.out.println("ʹ��ISO����..." + t_iso.length);
	        System.out.println("ʹ��GBK����..." + t_gbk.length);
	        System.out.println("ʹ��UTF8����..." + t_utf8.length);
	        // ���������װ
	        String ut_iso = new String(t_iso, "ISO8859-1");
	        String ut_gbk = new String(t_gbk, "GBK");
	        String ut_utf8 = new String(t_utf8, "UTF-8");
	        System.out.println("ʹ��ISO���������ISO��װ..." + ut_iso);
	        System.out.println("ʹ��GBK���������GBK��װ..." + ut_gbk);
	        System.out.println("ʹ��UTF8���������UTF8��װ..." + ut_utf8);
	        // ��ʱ��Ҫ�������iso�ַ���������
	        // ��������GBK/UTF8�������ISO8859-1��װ���ַ���������ʱ���򼴿ɻ����ȷ�����ַ�
	        String t_utf8Toiso = new String(t_utf8, "ISO8859-1");
	        // ��iso������ַ������л�ԭ
	        String ut_utf8Toiso = new String(t_utf8Toiso.getBytes("ISO8859-1"),"UTF-8");
	        System.out.println("ʹ��ISO��װutf8�����ַ�..." + t_utf8Toiso);
	        System.out.println("ʹ��ISO����utf8�����ַ�..." + ut_utf8Toiso);
	    }
	    catch(Exception e)
	    {
	    	
	    }
	}
	
}
