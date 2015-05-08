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
	
	private String code;
	
	
//	private String fileName;
	
	public GetSingleStock(String code) {
		 this.code = code;
		 this.jsonObj = new JSONObject();
    }
	
	public static String getData(String stockCode){
		URL url = null;
		stockCode = structCode(stockCode);
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		str = removeEmpty(str);
		//写入日志
		return str;
	}
	
	//构建请求数据的股票编码
	public static String structCode(String stockCode){
		String str = "";
		
		if(stockCode.contains("sh") || stockCode.contains("sz")){
			str = stockCode;
		}
		else{
			str += "sh" + stockCode + ",sz" + stockCode;
		}
		return str;
	}
	
	//去掉错误的股票代码返回的空股票信息
	public static String removeEmpty(String str){
		String re = "var hq_str_.{8}=\"\";";
		String s = Pattern.compile(re).matcher(str).replaceAll("");
		//写入日志
		log logger =new log();
		logger.getInfo("空股票信息："+s);
		return s;
	}
	
	public String[] parseString(String str) {
		String te = str.substring(str.lastIndexOf('_') + 1, str.indexOf('='));
		String imformation = str.substring(str.indexOf('\"') + 1,
				str.lastIndexOf('\"'));
		imformation = te + "," + imformation;
		String[] stock = imformation.split(",");
		
		return stock;
	}
	
	public JSONObject structJsonObject(String[] str) 
			throws JSONException{
		for (int i = 0; i < KEYS.length; ++i) {
			jsonObj.put(KEYS[i], str[i]);
		}
		code = str[0];
		return jsonObj;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String str = getData(code);
		String[] strArr = parseString(str);
		try {
			structJsonObject(strArr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public String getCode() {
		return code;
	}

	public static void main(String argv[]){
		GetSingleStock gifs = new GetSingleStock("600784");
		Thread td = new Thread(gifs);
		td.start();
		try {
			td.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(gifs.getCode());
		System.out.println(gifs.getJsonObj().toString());
	}

}
