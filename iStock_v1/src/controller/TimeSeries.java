package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * 设置纵轴时间
 */

public class TimeSeries {

	public static final SimpleDateFormat DF = new SimpleDateFormat("yy-MM-dd");
	private static final int LENGTH = 8;
	
	public static void main(String[] argv) throws ParseException{
		oneMonth();
		
		String[] str = sixMonth();
		for(String s : str){
			System.out.print(s+ "    ");
			Date d = DF.parse(s);
			System.out.print(d+ "    ");
			System.out.println();
		}
		
		Date d1 = new Date();
		Date d2 = new Date();
		d2.setMonth(d2.getMonth());
		d2.setDate(d2.getDate() -10);
		System.out.println(daysBetween(d2,d1));
	}
	
	   
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 
	
	/** 
	*字符串的日期格式的计算 
	*/  
	    public static int daysBetween(String smdate,String bdate) throws ParseException{  
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar cal = Calendar.getInstance();    
	        cal.setTime(sdf.parse(smdate));    
	        long time1 = cal.getTimeInMillis();                 
	        cal.setTime(sdf.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        long between_days=(time2-time1)/(1000*3600*24);  
	            
	       return Integer.parseInt(String.valueOf(between_days));     
	   }  
	  

	
	public static String[] oneMonth() {
		String[] oneMonth = new String[LENGTH];
		Date date=new Date();
		for(int i = LENGTH-1; i >= 0; --i){
			oneMonth[i] = DF.format(date);
			date.setDate(date.getDate()-4);
//			System.out.println(oneMonth[i]);
//			System.out.print(DF.format(DF.parse(oneMonth[i]))+ "    ");
		}
		
		return oneMonth;
	}
	
	public static String[] threeMonth(){
		String[] threeMonth = new String[LENGTH];
		Date date = new Date();
		threeMonth[LENGTH-1] = DF.format(date);
		if(date.getDate() > 15){
			date.setDate(15);
		}
		else if(date.getDate() > 1){
			date.setDate(1);
		}
		else{
			date.setMonth(date.getMonth() - 1);
			date.setDate(15);
		}
		threeMonth[LENGTH-2] = DF.format(date);
		for(int i = LENGTH-3; i >= 0; --i){
			if(date.getDate() == 1){
				date.setMonth(date.getMonth() - 1);
				date.setDate(15);
			}
			else{
				date.setDate(1);
			}
			threeMonth[i] = DF.format(date);
		}
		return threeMonth;
	}
	
	public static String[] sixMonth(){
		String[] sixMonth = new String[LENGTH];
		Date date = new Date();
		
		sixMonth[LENGTH-1] = DF.format(date);
		if(date.getDate() == 1)
			date.setMonth(date.getMonth()-1);
		else
			date.setDate(1);
		
		for(int i = LENGTH - 2; i >= 0; --i){
			sixMonth[i] = DF.format(date);
			date.setMonth(date.getMonth()-1);
		}
		
		return sixMonth;
	}
}
