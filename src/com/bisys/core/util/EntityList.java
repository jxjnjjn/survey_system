package com.bisys.core.util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.apache.log4j.Logger;

public class EntityList {
	private static Logger logger = Logger.getLogger(EntityList.class);
	static public <T extends EntityListInterface> T sumList(final List<T> src , Class<T> type)
	{
		T result = null;
		
		try
		{
			result = type.newInstance(); 
		}
		catch (Exception e)
		{
			logger.error("newInstance() Faild!");
			return null;
		}
		
		if(null != src)
		{
			for(T bean : src)
			{
				if(null != bean)
				{
					result.add(bean);
				}
			}
		}
		
		return result;
	}
	
	static public <T extends EntityListInterface> T meanList(final List<T> src , Class<T> type)
	{
		T result = null;
		
		try
		{
			result = type.newInstance(); 
		}
		catch (Exception e)
		{
			logger.error("newInstance() Faild!");
			return null;
		}
		
		if(null != src)
		{
			T sum = EntityList.sumList(src , type);
			
			if(null != sum)
			{
				sum.divide(src.size());
				result = sum;
			}
		}
		
		return result;
	}
	
	static public <T extends EntityListInterface> T sumDate(final T par1 , final T par2 , Class<T> type)
	{
		T result = null;
		
		try
		{
			result = type.newInstance(); 
		}
		catch (Exception e)
		{
			logger.error("newInstance() Faild!");
			return null;
		}
		
		
		if(null != par1 && null != par2)
		{
			result.add(par1);
			result.add(par2);
		}
		
		return result;
	}
	
	static public <T extends EntityListInterface> T subTractDate(final T par1 , final T par2 , Class<T> type) 
	{
		T result = null;
		
		try
		{
			result = type.newInstance(); 
		}
		catch (Exception e)
		{
			logger.error("newInstance() Faild!"); 
			return null;
		}
		
		if(null != par1 && null != par2)
		{
			result.add(par1);
			result.subtractSpec(par2);
		}
		
		return result;
	}
	
	/*
	 * get the date before the last day
	 * src[0,1,2,3........,100]
	 * if size == 2 means get (src[99]+src[98])/2
	 * if size == 3 means get (src[99],src[98],src[97])/3
	 * */
	static public <T extends EntityListInterface> T meanListFromTail(final List<T> src , Class<T> type , int size)
	{
		T result = null;
		
		try
		{
			result = type.newInstance(); 
		}
		catch (Exception e)
		{
			logger.error("newInstance() Faild!"); 
			return null;
		}
		
		if(null != src && size > 0)
		{
			//Is hard to understand , must rewrite it.
			int endIndex = src.size() - 1 -1;
			int beginIndex = (endIndex - size +1)<0?0:(endIndex - size +1);
			int count = 0;
			
			for(int i = beginIndex ; i <= endIndex ; i++)
			{
				result.add(src.get(i));
				count+=1;
			}
			
			result.divide(count);
		}
		
		return result;
	}
	
	static public <T extends EntityListInterface> T getByIndexFromTail(final List<T> src , Class<T> type , int index)
	{
		T result = null;
		
		try
		{
			result = type.newInstance(); 
		}
		catch (Exception e)
		{
			logger.error("newInstance() Faild!");
			return null;
		}
		
		if(null != src)
		{
			int resultIndex = src.size() - 1 - index;
			
			if(resultIndex >= 0)
			{
				//logger.info(new Gson().toJson(src.get(resultIndex)));
				result.copy(src.get(resultIndex));
			}
		}
		
		return result;
	}
	
	static public <T extends EntityListInterface> List<T> getListByPlatformID(final List<T> src, int platformID)
	{
		List<T> resultList = new ArrayList<T>(); 

		if(null != src )
		{
			for(T bean : src)
			{
				if(null != bean)
				{
					if(platformID == bean.getPlatform())
					{
						resultList.add(bean);
					}
				}
			}
		}
		
		return resultList;
	}
	
	static public  <T extends EntityListInterface> void calculateParameter(List<T> src)
	{	
	  if(null != src)
	  {
		  for(T bean: src)
		  {
			  if(null != bean)
			  {
				  bean.calculate();
			  }
		  }
	  }
	}
	
	static public <T extends EntityListInterface> List<T> sortListByDate(List<T> DBResultList)
	{
		if(null != DBResultList)
		{
			Collections.sort(DBResultList , new Comparator<T>()
			{
				@Override
				public int compare(T par1, T par2) 
				{
					if(null != par1 && null != par2)
					{
						Date date1 = par1.getDate();
						Date date2 = par2.getDate();
						
						if(null != date1 && null != date2)
						{
							return date1.compareTo(date2);
						}
					}
					
					return 0;
				}
			});
		}
		
		return DBResultList;
	}
	
	static public <T extends EntityListInterface & EntityListCompareInterface> List<T> sortListByDateAndKey(List<T> DBResultList)
	{
		if(null != DBResultList)
		{
			Collections.sort(DBResultList , new Comparator<T>()
			{
				@Override
				public int compare(T par1, T par2) 
				{
					if(null != par1 && null != par2)
					{
						Date date1 = par1.getDate();
						Date date2 = par2.getDate();
						
						if(null != date1 && null != date2)
						{
							if(0 ==  date1.compareTo(date2))
							{
								return par1.getKey() - par2.getKey();
							}
							else
							{
								return date1.compareTo(date2);
							}
						}
					}
					
					return 0;
				}
			});
		}
		
		return DBResultList;
	}
	
	static public<T> List<T> getListByPageInfo(int pageNo , int onePageCount ,final List<T> srcList , Class<T> type)
	{
		List<T> tableList = new ArrayList<T>();
		
		if(null != srcList)
		{
			int srcSize = srcList.size();
			int starlow = onePageCount*(pageNo-1);

			if(starlow < srcSize && starlow >= 0)
			{
				int endlow = ((starlow + onePageCount)>srcSize)?srcSize:(starlow + onePageCount);
						
				for(int i=starlow;i<endlow;i++)
				{
					tableList.add(srcList.get(i));
				}
			}
		}

		return tableList;
	}
	
	static public <T extends EntityListInterface> List<T> sumByDate(final List<T> src , Class<T> type)
	{	
		List<T> result = new ArrayList<T>();
		Map<Date, T> map = new TreeMap<Date, T>(new MapKeyComparator());
		
		if(null != src)
		{
			for (T bean : src) 
			{
				if(null != bean)
				{
					if (map.containsKey(bean.getDate())) 
					{
						map.put(bean.getDate(), EntityList.sumDate(map.get(bean.getDate()),bean , type));
					} 
					else
					{
						map.put(bean.getDate(), bean);
					}
				}
			}

			for (Entry<Date, T> entry : map.entrySet()) 
			{
				if(null != entry)
				{
					result.add(entry.getValue());
				}
			}
		}
		
		return result;
	}
}


