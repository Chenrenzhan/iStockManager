package controller;

/*
 * 抓取单支股票信息
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

public class GetSingleStock implements Runnable{
	
	private String[] KEYS = new String[]{"code","name","todayOpenPrice", 
			"yesterdayClosePrice","currentPrice","todayHightestPrice","todayLowestPrice",
			"bidsPrice","auctionPrice","stockAllDealVolumes","stockAllDealMoney",
			"buyFirstVolumes","buyFirstPrice", "buySecondVolumes","buySecondPrice",
			"buyThirdVolumes","buyThirdPrice","buyFourthVolumes","buyFourthPrice",
			"buyFifthVolumes","buyFifthPrice","saleFirstVolumes","saleFirstPrice",
			"saleSecondVolumes","saleSecondPrice","saleThirdVolumes","saleThirdPrice",
			"saleFourthVolumes","saleFourthPrice","saleFifthVolumes","saleFifthPrice",
			"date","time"};
	
	private JSONObject jsonObj;
	private boolean dl_completed=true;
	private String code;
	
	private Boolean isError = false; //标记是否获取股票错误
	
	
	
	
//	private String fileName;
	
	public GetSingleStock(String code) {
		 this.code = code;
		 this.jsonObj = new JSONObject();
    }
	
	public static String getData(String stockCode) throws UnknownHostException{
		URL url = null;
		try {
			stockCode = structCode(stockCode);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			return "";
		}
		String urlStr = "http://hq.sinajs.cn/list=" + stockCode;
		String str = "";
		BufferedReader reader = null;
		try {
//			ur = new URL("http://hq.sinajs.cn/list=sh601006,sh601003,sh601001");
			url = new URL(urlStr);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			reader = new BufferedReader(new InputStreamReader(url.openStream(),
					"GBK"));
			
			String line;
			while ((line = reader.readLine()) != null) {
				str += line;
			}                                              
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (UnknownHostException e) {
			throw new UnknownHostException("can't connect to internet");} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		str = removeEmpty(str);
		//写入日志
		return str;
	}
	
	//构建请求数据的股票编码
	private static String structCode(String stockCode) throws IOException{
		String str = "";
		
		if(stockCode.contains("sh") || stockCode.contains("sz")){
			str = stockCode;
		}
		else if(isNum(stockCode)){
			str += "sh" + stockCode + ",sz" + stockCode;
		}else
			throw new IOException("contain error charactor");
		return str;
	}
	
	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) { // 循环遍历字符串

			if (Character.isLetter(str.charAt(i))) { // 用char包装类中的判断字母的方法判断每一个字符
				return false;
			}
		}
		return true;
	}
	//去掉错误的股票代码返回的空股票信息
	public static String removeEmpty(String str){
//		System.out.println("ssss  " + str);
		String re = "var hq_str_.{0,20}=\"\";";
		String s = Pattern.compile(re).matcher(str).replaceAll("");
//		System.out.println("sssss1   " + s);
		//写入日志
		log logger =new log();
		logger.getInfo("空股票信息："+s);
		return s;
	}
	
	public String[] parseString(String str) {
		if (str == "")
		return null;
		else{
		String te = str.substring(str.lastIndexOf('_') + 1, str.indexOf('='));
		String imformation = str.substring(str.indexOf('\"') + 1,
				str.lastIndexOf('\"'));
		imformation = te + "," + imformation;
		String[] stock = imformation.split(",");
		
		return stock;
		}
	}
	
	public JSONObject structJsonObject(String[] str) 
			throws JSONException{
		if(jsonObj == null){
			jsonObj = new JSONObject();
		}
		for (int i = 0; i < KEYS.length; ++i) {
			jsonObj.put(KEYS[i], str[i]);
		}
		code = str[0];
		return jsonObj;
	}
	
	public JSONObject structNullJsonObject() 
			throws JSONException{
		if(jsonObj == null){
			jsonObj = new JSONObject();
		}
		for (int i = 0; i < KEYS.length; ++i) {
			jsonObj.put(KEYS[i], "");
		}
		code = "";
		return jsonObj;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String str;
		try {
			str = getData(code);
			if(!str.isEmpty()){
				String[] strArr = parseString(str);
				structJsonObject(strArr);
			}
			else{
//				jsonObj = null;
				isError = true;
			}
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			dl_completed=false;
		}
          catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dl_completed=false;
		}
		
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public String getCode() {
		return code;
	}
	public boolean getDlCompleted(){
		//获取run线程的执行情况
		return dl_completed;
	}

	
	public Boolean getIsError() {
		return isError;
	}

	public static void main(String argv[]){
		GetSingleStock gifs = new GetSingleStock("60784");
		Thread td = new Thread(gifs);
		td.start();
		try {
			td.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(gifs.getCode());
		if(gifs.getJsonObj() == null){
//			System.out.println("dddddddddd");
		}
//		else
//			System.out.println(gifs.getJsonObj().toString());
	}

}
