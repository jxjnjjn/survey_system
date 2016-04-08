package com.bisys.core.util;

public class JsonPageInfo 
{
	long pageNo;
	long pageColum;
	long pageCount;
	long rowsCount;
	
	public JsonPageInfo(int pageNo , int pageColum , int srcListSize)
	{
		this.pageNo = pageNo;
		this.pageColum = pageColum;
		this.rowsCount = srcListSize;
		this.pageCount = MathUtil.dividedByLong(srcListSize, pageColum)+(MathUtil.modByLong(srcListSize , pageColum)==0?0:1);
	}
}
