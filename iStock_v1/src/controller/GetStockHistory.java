package controller;

/*
 * 从Yahoo获取股票的历史信息
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.RecordsSet;

import org.json.JSONException;
import org.json.JSONObject;

//日期、开盘价、最高价、最低价、收盘价、收盘价、成交量、复权收盘价

/*
 参数
    s — 股票名称
    a — 起始时间，月
    b — 起始时间，日
    c — 起始时间，年
    d — 结束时间，月
    e — 结束时间，日
    f — 结束时间，年
    g — 时间周期。Example: g=w, 表示周期是‘周’。d->‘日’(day), w->‘周’(week)，m->‘月’(mouth)，v->‘dividends only’ 

一定注意月份参数，其值比真实数据-1。如需要9月数据，则写为08。
 */
//http://table.finance.yahoo.com/table.csv?s=000001.sz&d=3&e=3&f=15&g=d&a=3&b=1&c=15&ignore=.csv

public class GetStockHistory implements Runnable {
	public static final SimpleDateFormat DF = new SimpleDateFormat("yy-MM-dd");
//	private static final int LENGTH = 8;
	
	
	private String[] start;
	private String[] end;
	private String code;
	private String dataString;
	
	public GetStockHistory(String code, String start, String end){
		
		//月份从0开始要减1
		this.start = start.split("-");
		this.start[1] = String.valueOf(Integer.valueOf(this.start[1])-1);
		this.end = end.split("-");
		this.end[1] = String.valueOf(Integer.valueOf(this.end[1])-1);
		
		this.code = code;
		
//		connet(structUrl(this.code, this.start, this.end));
	}
	
	public GetStockHistory(String code, String[] start, String[] end) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.start = start;
		this.end = end;
	}

	
	//s=股票代码,d=截止月份，e=截止日期，f=截止年份，a=起始月份，b=起始日期，c=起始年份，月份从0开始，日期是年份从1开始
	private String structUrl(String code, String[] s, String[] e){
		String url = "";
		
		url = "http://table.finance.yahoo.com/table.csv" 
			+ "?s=" + code + "&d=" + e[1] + "&e=" + e[2] + "&f=" + e[0]
			+ "&g=d&a=" + s[1] + "&b=" + s[2] + "&c=" + s[0] + "&ignore=.csv";
		
		return url;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		dataString = connet(structUrl(this.code, this.start, this.end));
	}


	
	public static String connet(String urlStr){
		
		URL url;  
		String str = "";
	    try {  

	    	url = new URL(urlStr);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
//			uc.connect();// 连接会话
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),
					"GBK"));
			int statusCode = uc.getResponseCode();//返回状态码
			System.out.println(statusCode);
			String line;
			while ((line = reader.readLine()) != null) {
				str += line + ";";
//				System.out.println(line);
			} 
//			System.out.println(str);
	         System.out.println("连接可用");  
	    } catch (Exception e1) {  
	         System.out.println("连接打不开!");  
	         url = null;  
	    }  
	    return str;
	}
	
	public String getDataString(){
		return dataString;
	}
	
	/*
	 * 返回查询股票信息的历史记录的收盘价
	 */
	public JSONObject getClosePrice() throws InterruptedException{
		GetStockHistory gsh = new GetStockHistory("000001.sz", 
				"15-03-11", "15-04-11");
		if(code.contains("sh") || code.contains("sz")){
			Thread td = new Thread(gsh);
			td.start();
			td.join();
			dataString = gsh.getDataString();
			System.out.println("gsh");
		}
		else{
			GetStockHistory gsh1 = 
					new GetStockHistory(code + ".sz", start, end);
			Thread td1 = new Thread(gsh1);
			td1.start();
			
			GetStockHistory gsh2 = 
					new GetStockHistory(code + ".sh", start, end);
			Thread td2 = new Thread(gsh1);
			td2.start();
			
			try {
				td1.join();
				td2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String str1 = gsh1.getDataString();
			String str2 = gsh2.getDataString();
			
			if(str1 != null)
				dataString = str1;
			else 
				dataString = str2;
			System.out.println("gsh1");
		}
		return parseString(dataString);
//		return dataString;
	}
	
	//解析从网上获取返回的字符串
	public JSONObject parseString(String str) {
		JSONObject jo = new JSONObject();
		//"(.*?;)?((\\d{2,4}-\\d{1,2}-\\d{1,2}){1},(.*?,){3}(.*){1},(.*?,){2};)*";
//		str = "Date,Open,High,Low,Close,Volume,Adj Close;2015-04-10,15.00,16.50,14.875,16.50,400825100,13.605;2015-04-09,14.9583,15.875,14.775,15.00,380767600,12.36818;2015-04-08,16.92996,18.08004,16.65,17.91996,404597500,14.77582;2015-04-07,16.14996,16.95996,16.14996,16.80996,355256600,13.86058;2015-04-06,15.84996,15.84996,15.84996,15.84996,000,13.06901;2015-04-03,15.69996,15.93996,15.60996,15.84996,172192900,13.06901;2015-04-02,16.08996,16.14,15.62004,15.80004,168700400,13.02785;2015-04-01,15.81996,16.14,15.54996,15.96,197197900,13.15975;2015-03-31,16.04004,16.59996,15.63,15.75,327981900,12.98659;2015-03-30,15.23004,15.93996,15.09996,15.66,309913600,12.91238;2015-03-27,15.09996,15.29004,14.85996,15.08004,125716600,12.43418;2015-03-26,14.85996,15.42,14.70996,15.11004,178546900,12.45891;2015-03-25,15.27996,15.32004,14.88996,14.91,190976100,12.29397;2015-03-24,15.42996,15.59004,15.21996,15.35004,187103200,12.65681;2015-03-23,15.33,15.56004,15.33,15.42996,213846700,12.7227;2015-03-20,15.18996,15.54996,15.06,15.32004,237167300,12.63207;2015-03-19,15.48,15.48,15.12,15.18996,189636000,12.52481;2015-03-18,15.36,15.57996,15.29004,15.51,219720000,12.7887;2015-03-17,15.51,15.80004,15.24996,15.39996,235181000,12.69797;2015-03-16,15.09996,15.50004,14.91996,15.41004,266216700,12.70628;2015-03-13,14.90004,15.60996,14.79996,14.99004,379678600,12.35997;2015-03-12,14.19996,14.96004,14.07996,14.60004,396616400,12.0384;2015-03-11,13.79004,14.07996,13.79004,13.86,108402500,11.4282;";
		String[] sa = str.split(";");
		
		for(int i = 1; i < sa.length; ++i){
//			System.out.println(sa[i]);
			String[] s = sa[i].split(",");
			try {
				jo.put(s[0].substring(2), s[4]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(jo.toString());
		return jo;
	}
	
	public static void main(String[] args) {
		
//		GetStockHistory gsh1 = new GetStockHistory("000001.sz", "15-03-11", "15-04-11");
//		Thread td1 = new Thread(gsh1);
//		td1.start();
//		
//		GetStockHistory gsh2 = new GetStockHistory("000001.sz", "15-03-11", "15-04-11");
//		Thread td2 = new Thread(gsh1);
//		td2.start();
//		
//		try {
//			td1.join();
//			td2.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("end");
//		
//		String str1 = gsh1.getDataString();
//		String str2 = gsh2.getDataString();
		
//		
//		
//		System.out.println(str1);
//		System.out.println(str2);
		
		System.out.println(" kkk   "  );
		GetStockHistory gsh = new GetStockHistory("000001", "15-05-1", "15-05-1");
		try {gsh.getClosePrice();
			System.out.println(" kkk   " + gsh.getClosePrice() );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		JSONObject jo = new JSONObject();
//		//"(.*?;)?((\\d{2,4}-\\d{1,2}-\\d{1,2}){1},(.*?,){3}(.*){1},(.*?,){2};)*";
//		String str = "Date,Open,High,Low,Close,Volume,Adj Close;2015-04-10,15.00,16.50,14.875,16.50,400825100,13.605;2015-04-09,14.9583,15.875,14.775,15.00,380767600,12.36818;2015-04-08,16.92996,18.08004,16.65,17.91996,404597500,14.77582;2015-04-07,16.14996,16.95996,16.14996,16.80996,355256600,13.86058;2015-04-06,15.84996,15.84996,15.84996,15.84996,000,13.06901;2015-04-03,15.69996,15.93996,15.60996,15.84996,172192900,13.06901;2015-04-02,16.08996,16.14,15.62004,15.80004,168700400,13.02785;2015-04-01,15.81996,16.14,15.54996,15.96,197197900,13.15975;2015-03-31,16.04004,16.59996,15.63,15.75,327981900,12.98659;2015-03-30,15.23004,15.93996,15.09996,15.66,309913600,12.91238;2015-03-27,15.09996,15.29004,14.85996,15.08004,125716600,12.43418;2015-03-26,14.85996,15.42,14.70996,15.11004,178546900,12.45891;2015-03-25,15.27996,15.32004,14.88996,14.91,190976100,12.29397;2015-03-24,15.42996,15.59004,15.21996,15.35004,187103200,12.65681;2015-03-23,15.33,15.56004,15.33,15.42996,213846700,12.7227;2015-03-20,15.18996,15.54996,15.06,15.32004,237167300,12.63207;2015-03-19,15.48,15.48,15.12,15.18996,189636000,12.52481;2015-03-18,15.36,15.57996,15.29004,15.51,219720000,12.7887;2015-03-17,15.51,15.80004,15.24996,15.39996,235181000,12.69797;2015-03-16,15.09996,15.50004,14.91996,15.41004,266216700,12.70628;2015-03-13,14.90004,15.60996,14.79996,14.99004,379678600,12.35997;2015-03-12,14.19996,14.96004,14.07996,14.60004,396616400,12.0384;2015-03-11,13.79004,14.07996,13.79004,13.86,108402500,11.4282;";
//		String[] sa = str.split(";");
//		
//		for(int i = 1; i < sa.length; ++i){
//			System.out.println(sa[i]);
//			String[] s = sa[i].split(",");
//			try {
//				jo.put(s[0], s[4]);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		System.out.println(jo.toString());
	
	}
}
