/**
 * 
 */
package com.bisys.core.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有实体bean与表名称对应关系
 * 1. 操作数据库时，会从这里取对应表名称，如有新建表，请维护该类
 * 2. 操作数据库时，也会从这里取对应表的主键字段名称
 */
public class TableConstant {

	/**
	 * tableBean 实体类与表名称的对应关系map
	 * key：实体类全名
	 * value：表名称
	 */
	public static final Map<String, String> TABLE_BEAN = new HashMap<String, String>();
	static{
		//TABLE_BEAN.put("com.bisys.core.entity.bo.SysUserBo", "sys_user");
		TABLE_BEAN.put("com.bisys.core.entity.vo.SysUserVo", "vip_base_info_table");
	}
	
	/**
	 * TABLE_PRIMARY_KEY 表名称与该表主键字段的对应关系map
	 * key：表名称
	 * value：表主键字段名称
	 */
	public static final Map<String, String> TABLE_PRIMARY_KEY = new HashMap<String, String>();
	static{
		//TABLE_PRIMARY_KEY.put("sys_user", "id");
		TABLE_PRIMARY_KEY.put("vip_base_info_table", "user_name");
	}
}
