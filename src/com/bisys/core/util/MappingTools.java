package com.bisys.core.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class MappingTools{
	/**
	 * 通过 源对象(DAO层生成的Bean) 以及 目的对象类型(表示层Bean的Class) 进行 字段映射
	 * @param src 源对象
	 * @param dstType 目标对象类型
	 * @return 目标对象
	 */
	public static <T> T map(Object src, Class<T> dstType){
		if( null == src || null == dstType ) { // 无效参数,跳出
			return null;
		}
 
		try {
			T dst = dstType.newInstance(); // 根据 目的类型 创建 对象
			setProperties(src, dst); // 同名同类型 字段映射
			return dst;
		} catch (Exception e) {
			return null;
		}
	}
 
	/**
	 * 不同类型对象之间的 "同名&同类型"的属性映射;
	 * @param src 源对象
	 * @param dst 目标对象
	 * @return void
	 */
	public static void setProperties(Object src, Object dst){
		if( null == src || null == dst ) { // 无效参数,跳出
			return;
		}
 
		Class<?> srcClazz = src.getClass();
		Class<?> dstClazz = dst.getClass();
 
		Set<String> fieldNameSet = new HashSet<String>(); // 将要映射的字段名
 
		Field[] srcFields = srcClazz.getDeclaredFields(); // 遍历源类型的字段
		for (Field field : srcFields) {
			fieldNameSet.add(field.getName()); // 抽取字段名, 并置入字段名集合
		}
 
 
		for (String fieldName : fieldNameSet) { 
			try {
				PropertyDescriptor srcPD = new PropertyDescriptor(fieldName, srcClazz);
				Method srcReadMethod = srcPD.getReadMethod();
				if( null == srcReadMethod || (Modifier.PUBLIC & srcReadMethod.getModifiers()) != 1 ){
					continue; // 源类型该字段的getter方法不存在或不是public, 跳过
				}
	 
				PropertyDescriptor dstPD = new PropertyDescriptor(fieldName, dstClazz);
				Method dstWriteMethod = dstPD.getWriteMethod();
				if( null == dstWriteMethod || (Modifier.PUBLIC & dstWriteMethod.getModifiers()) != 1 ){
					continue; // 目的类型该字段的setter方法不存在或不是public, 跳过
				}
	 
				Class<?> returnType = srcReadMethod.getReturnType();
				Class<?>[] parameterTypes = dstWriteMethod.getParameterTypes();
				if( parameterTypes.length != 1 || !parameterTypes[0].equals(returnType)){
					continue; // 目的类型setter方法的参数类型 和 源类型getter方法的返回类型不一致. 跳过
				}
	 
				dstWriteMethod.invoke(dst, srcReadMethod.invoke(src)); // 利用反射提供的API进行字段映射
			} catch (Exception e) {
					e.printStackTrace();
					continue; // 映射过程中出错, 跳过该字段
			}
		}
	}
	
	/**
	 * 获取对象第i个属性值
	 * @param model 对象
	 * @param i int
	 * @return Object
	 */
	public static Object getValue(Object model,int i){
		try {
			Class<?> modelClazz = model.getClass();
			
			Field[] field = model.getClass().getDeclaredFields(); 
			// 获取属性的名字  
			String name = field[i].getName();  
			//获取属性get方法
			PropertyDescriptor PD = new PropertyDescriptor(name, modelClazz);
			Method getMethod = PD.getReadMethod();
			
			getMethod.setAccessible(true);
			return getMethod.invoke(model);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 *  赋值对象第i个属性值
	 * @param model 对象
	 * @param i int
	 * @param value Object
	 * @return void
	 * 
	 */
	public static void setValue(Object model,int i,Object value){
		try {
			Class<?> modelClazz = model.getClass();
			
			Field[] field = model.getClass().getDeclaredFields(); 
			// 获取属性的名字  
			String name = field[i].getName();  
			//获取属性set方法
			PropertyDescriptor PD = new PropertyDescriptor(name, modelClazz);
			Method setMethod = PD.getWriteMethod();
			
			setMethod.setAccessible(true);
			setMethod.invoke(model,value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args)
//	{
//		try
//		{
//			TestClass test = new TestClass();
//			test.setA(1);
//			test.setB(2);
//			test.setC("123");
//			System.out.println(getValue(test, 0));
//			System.out.println(getValue(test, 1));
//			System.out.println(getValue(test, 2));
//			setValue(test, 0, (int)11);
//			setValue(test, 1, (long)22);
//			setValue(test, 2, "123123");
//			System.out.println(getValue(test, 0));
//			System.out.println(getValue(test, 1));
//			System.out.println(getValue(test, 2));
//		}
//		catch(Exception e)
//		{
//			
//		}
//	}

}

//class TestClass{
//	
//	private int a;
//	private long b;
//	private String c;
//	public int getA() {
//		return a;
//	}
//	public void setA(int a) {
//		this.a = a;
//	}
//	public long getB() {
//		return b;
//	}
//	public void setB(long b) {
//		this.b = b;
//	}
//	public String getC() {
//		return c;
//	}
//	public void setC(String c) {
//		this.c = c;
//	}
//}