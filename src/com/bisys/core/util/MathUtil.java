package com.bisys.core.util;
import org.apache.log4j.Logger;

public class MathUtil {

	/**
	 * double类型的除法
	 * @param dividend  被除数
	 * @param divisor  除数，为0返回0
     * @return double
	 */
	private static Logger logger = Logger.getLogger(MathUtil.class);
	public static double dividedByDouble(double dividend, double divisor){
		return isZero(divisor) ? 0 : (dividend/divisor);
	}
	
	public static long dividedByLong(long dividend, long divisor){
		return divisor == 0 ? 0 : (dividend/divisor);
	}
	
	public static long modByLong(long dividend, long divisor){
		return divisor == 0 ? 0 : (dividend%divisor);
	}
	
	public static double dividedByDoubleSpec(double dividend, double divisor){
		boolean dividedFlag = isZero(dividend);
		boolean divisorFlag = isZero(divisor);
		
		if(dividedFlag)
		{
			return 0;
		}
		else if(divisorFlag && dividend > 0)
		{
			return 1;
		}
		else if(divisorFlag && dividend < 0)
		{
			return -1;
		}
		else
		{
			return dividend/divisor;
		}
	}
	
	public static boolean isZero(double value){
		return Math.abs(value)<0.00000001 ? true : false;
	}
	
	public static boolean isZero(int value){
		return value==0;
	}
	
	public static double round (double value , int precisiou){
		if(precisiou <= 0 || precisiou >= 10)
		{
			logger.error("The decimal precision do not support ! "+"The precisiou = "+ precisiou);
		}
		
		double tmp = Math.pow(10, precisiou);
		return Math.round(value*tmp)/tmp;
	}
	
	public static String round (double value){
		return String.format("%.2f", value).toString();
		//return Math.round(value*100)/100.0;
	}
	
	public static String diffRate (double src , double compare)
	{
		return MathUtil.round(MathUtil.dividedByDoubleSpec(src - compare, Math.abs(compare))*100);
	}
	
	// 表现形式为：0.18
	public static double correctRate (String answer , String base_answer)
	{
		if(answer == null || base_answer == null)
		{
			return 0;
		}
		
		answer = answer.toLowerCase();
		base_answer = base_answer.toLowerCase();
		int base_answer_length = base_answer.length();
		int answer_length = answer.length();
		int correct_num = 0;
		int index = 0;
		
		char base_anwser_char_tmp ;
		char anwser_char_tmp ;
		
		while (index >= 0 && index < base_answer_length && index < answer_length)
		{
			base_anwser_char_tmp = base_answer.charAt(index);
			anwser_char_tmp = answer.charAt(index);
			
			if(base_anwser_char_tmp == anwser_char_tmp)
			{
				correct_num ++;
			}
			
			index++;
		}
		
		return  Math.round(MathUtil.dividedByDouble(correct_num , base_answer_length)*100)/100.0;
	}
	
	// 表现形式为：“7/11” 
	public static String correctRateString (String answer , String base_answer)
	{
		if(base_answer == null || base_answer.equals("N/A"))
		{
			return "0/0";
		}
		
		if(answer == null )
		{
			int base_answer_length = base_answer.length();
			return  "0/"+base_answer_length;
		}
		
		answer = answer.toLowerCase();
		base_answer = base_answer.toLowerCase();
		int base_answer_length = base_answer.length();
		int answer_length = answer.length();
		int correct_num = 0;
		int index = 0;
		
		char base_anwser_char_tmp ;
		char anwser_char_tmp ;
		
		while (index >= 0 && index < base_answer_length && index < answer_length)
		{
			base_anwser_char_tmp = base_answer.charAt(index);
			anwser_char_tmp = answer.charAt(index);
			
			if(base_anwser_char_tmp == anwser_char_tmp)
			{
				correct_num ++;
			}
			
			index++;
		}
		
		return  correct_num+"/"+base_answer_length;
	}
}
