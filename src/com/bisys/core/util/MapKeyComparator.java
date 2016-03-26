package com.bisys.core.util;

import java.util.Comparator;
import java.util.Date;

public class MapKeyComparator implements Comparator<Date>{
    @Override
    public int compare(Date str1, Date str2) 
    {
    	if(null != str1 && null != str2)
    	{
    		return str1.compareTo(str2);
    	}
    	else
    	{
    		return 0;
    	}
    }
}
