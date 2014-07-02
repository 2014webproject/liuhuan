package other;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
/**
 * ������ ��Ϊ�����ṩ����(��HandleBook GetMyBook��ʹ�� )
 * @author haiyaojing
 *
 */
public class MyInvoke {
	/**
	 * ��������
	 */
	private static final ArrayList<String> chList;
	/**
	 * Ӣ������(Order�����ݿ���)
	 */
	private static final ArrayList<String> enList;
	@SuppressWarnings("rawtypes")
	/**
	 * class����
	 */
	private static Class clazz;
	/**
	 * ʵ������class����
	 * @unuse
	 */
	public static Object object;
	/**
	 * ��ʼ��
	 */
	static {
		chList = new ArrayList<String>();
		enList = new ArrayList<String>();
		chList.add("�������"); enList.add("fanQie");
		chList.add("��Ѽ�����"); enList.add("laoYa");
		chList.add("�����������"); enList.add("xianYu");
		chList.add("����ţ�����"); enList.add("niuRou");
		chList.add("Ϻ�����"); enList.add("xiaRen");
		chList.add("�������"); enList.add("sanXian");
		chList.add("�۲����"); enList.add("qinCai");
		chList.add("��ζ����"); enList.add("guoTie");
		try {
			clazz = Class.forName("db.myorder.Order");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �������Ʋ���set����
	 * @param name
	 * @return set����
	 */
	public static Method getSetter(String name) {
		int index = chList.indexOf(name);
		if (index < 0) return null;
		String value = enList.get(index);
		try {
			PropertyDescriptor p = new PropertyDescriptor(value, clazz);
			return p.getWriteMethod();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ��ȡ���е��ֶ�
	 * @return
	 */
	public static Field[] getFields() {
		return clazz.getDeclaredFields();
	}
	/**
	 * ��ȡ�ֶζ�Ӧ����������
	 * @param name
	 * @return
	 */
	public static String getChName(String name) {
		int index = enList.indexOf(name);
		if (index < 0) return null;
		return chList.get(index);
	}
}
