package com.bisys.core.entity;

import java.io.Serializable;
import java.util.List;

import com.bisys.core.util.JsonPageInfo;
/**
 * 返回前台的json数据
 */
public class JsonResult<T> implements Serializable{

	private static final long serialVersionUID = 4273005680206220420L;
	/**
	 * 返回结果码
	 * 0成功，非0失败
	 */
	private int resultCode = 0;
	/**
	 * 操作结果信息
	 */
	private String resultMessage = "获取数据成功";
	/**
	 * 返回的数据
	 */
	private List<T> data;
	
	
	private JsonPageInfo pageInfo;
	
	public int getResultCode() {
		return resultCode;
	}
	
	public void setPageInfo(JsonPageInfo result) {
		this.pageInfo = result;
	}
	
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMessage() {
		return resultMessage;
	}
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
