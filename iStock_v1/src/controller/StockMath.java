package controller;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StockMath {

	
	public static final DecimalFormat DF = new DecimalFormat("##0.00");  
	
	//double型转成千分数
	public static String doubleToMilli(double d) {
		return (DF.format(d * 1000) + "‰");
	}
	//千分数转double
	public static double milliToDouble(String milli){
		String ds = milli.replace("‰", "");
		return Double.valueOf(ds);
	}
	
	//double 转百分数
	public static String doubleToPercent(double d){
		return (DF.format(d * 100) + "%");
	}
	//百分数转double
	public static double percentToDouble(String milli){
		String ds = milli.replace("‰", "");
		return Double.valueOf(ds);
	}
	
	//格式化double型
	public static String doubleFormat(double d){
		return DF.format(d);
	}
	
	//字符串转double
	public static double valueOf(String str){
		String re = ".*?(\\d+\\.?\\d*).*";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(str);
		if(matcher.find()){
			double d = Double.valueOf(matcher.group(1));
				return d;
		}
		else
			return 0.00;
	}
	
	//double转字符串
	public static String valueOf(double d){
		return DF.format(d);
	}
	
	public static void main(String[] argv){
		StockMath.valueOf("2.30");
		
//		Pattern pattern = Pattern.compile("b*g");  
//        Matcher matcher = pattern.matcher("bbg");  
//        System.out.println(matcher.matches());  
//        System.out.println(pattern.matches("b*g","bbg"));  
//		System.out.println(pattern.matches("[0-9]{6}", "200038"));  
//        System.out.println(pattern.matches(".*(\\d+\\.?\\d*).*", "3.4"));  
		
		String re = ".*?(\\d+\\.?\\d*).*";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher("4.5");
//		Boolean b = matcher.find();
//		System.out.println(matcher.find());
//		System.out.println(b);
		if(matcher.find()){
			System.out.println(matcher.group(1));
		}
	}
	public static String doubleFormat(String str) {
		// TODO Auto-generated method stub
		
		return doubleFormat(Double.valueOf(str));
	}
}
