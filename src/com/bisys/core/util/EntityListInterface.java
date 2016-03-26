package com.bisys.core.util;
import java.util.Date;

public interface EntityListInterface {
	public  void add(final EntityListInterface src);
	public  void copy(final EntityListInterface src);
	public  void divide(int size);
	public  void subtractSpec(final EntityListInterface src);
	public  void calculate();
	public  Date getDate() ;
	public int getPlatform();
}
