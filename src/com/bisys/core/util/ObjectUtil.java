package com.bisys.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.bisys.core.constant.TableConstant;

/**
 * 对象工具类
 */
public class ObjectUtil {
	
	private static <T> String getClassFieldStr(Class<T> beanClass){
		Field[] fields = beanClass.getDeclaredFields();
		StringBuilder str = new StringBuilder();
		for(Field field: fields){
			if(!field.getName().endsWith("Str")){
				str.append(field.getName() + ",");
			}
		}
		String fieldStr = str.substring(0, str.length()-1);
		//replace方法无需判断是否包含该字符串
		fieldStr = str.toString().replace("serialVersionUID,", "");
		fieldStr = fieldStr.replace(
				TableConstant.TABLE_PRIMARY_KEY.get(
						TableConstant.TABLE_BEAN.get(beanClass.getName())) + ",", "");
		return fieldStr;
	}
	
	/**
	 * 为框架的GeneralDao提供工具方法
	 * 拿到实体类后，返回需要insert到数据库中的字段数组，该数组不包括（serialVersionUID，主键）两个字段
	 */
	public static <T> String[] getEntityFiled(Class<T> beanClass){
		return getClassFieldStr(beanClass).split(",");
	}
	
	public static <T> List<T> reverseList(final List<T> para)
	{
		List<T> result = new ArrayList<T>();
		
		for(int i = para.size() -1 ; i >= 0 ; i-- )
		{
			result.add(para.get(i));
		}
		
		return result;
	}

}
