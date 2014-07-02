package other;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
/**
 * 反射类 ：为代码提供方便(在HandleBook GetMyBook中使用 )
 * @author haiyaojing
 *
 */
public class MyInvoke {
	/**
	 * 中文名称
	 */
	private static final ArrayList<String> chList;
	/**
	 * 英文名称(Order，数据库中)
	 */
	private static final ArrayList<String> enList;
	@SuppressWarnings("rawtypes")
	/**
	 * class对象
	 */
	private static Class clazz;
	/**
	 * 实例化的class对象
	 * @unuse
	 */
	public static Object object;
	/**
	 * 初始化
	 */
	static {
		chList = new ArrayList<String>();
		enList = new ArrayList<String>();
		chList.add("番茄馄饨"); enList.add("fanQie");
		chList.add("老鸭煲馄饨"); enList.add("laoYa");
		chList.add("名禾鲜鱼馄饨"); enList.add("xianYu");
		chList.add("奇香牛肉馄饨"); enList.add("niuRou");
		chList.add("虾仁馄饨"); enList.add("xiaRen");
		chList.add("三鲜馄饨"); enList.add("sanXian");
		chList.add("芹菜馄饨"); enList.add("qinCai");
		chList.add("美味锅贴"); enList.add("guoTie");
		try {
			clazz = Class.forName("db.myorder.Order");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据名称查找set方法
	 * @param name
	 * @return set方法
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
	 * 获取所有的字段
	 * @return
	 */
	public static Field[] getFields() {
		return clazz.getDeclaredFields();
	}
	/**
	 * 获取字段对应的中文名称
	 * @param name
	 * @return
	 */
	public static String getChName(String name) {
		int index = enList.indexOf(name);
		if (index < 0) return null;
		return chList.get(index);
	}
}
