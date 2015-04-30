package controller;

/*
 * 从新浪获取单支股票数据并临时保存到本地
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import models.SinaStockData;

public class GetInfoFromSina implements Runnable{

//	private static final String FILEPATH = "data/temp";
	
	private JSONObject jsonObj;
	
	private String code;
	
//	private String fileName;
	
	public GetInfoFromSina(String code) {
		 this.code = code;
		 this.jsonObj = new JSONObject();
//		 this.fileName = fileName;
		 
		 try {
			structJsonObject(parseString(getData(code)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		return str;
	}
	
	//构建请求数据的股票编码
	public static String structCode(String stockCode){
		String str = "";
		String[] ca = stockCode.split(",");
		for(int i = 0; i < ca.length; ++i){
			if(ca[i].contains("sh") || ca[i].contains("sz")){
				if(i == 0)
					str += ca[i];
				else
					str += "," + ca[i];
				continue;
			}
			else{
				if(i == 0)
					str += "sh" + ca[i] + ",sz" + ca[i];
				else
					str += ",sh" + ca[i] + ",sz" + ca[i];
			}
		}
		return str;
	}
	
	//去掉错误的股票代码返回的空股票信息
	public static String removeEmpty(String str){
		String re = "var hq_str_.{8}=\"\";";
		String s = Pattern.compile(re).matcher(str).replaceAll("");
		return s;
	}
	
	public String[][] parseString(String str){
		String[][] returnStr;
		String[] stocks = str.split(";");
		int length = stocks.length;
		returnStr = new String[length][];
		for(int i = 0; i < length; ++i){
			String te = stocks[i].substring(stocks[i].lastIndexOf('_')+1, 
					stocks[i].indexOf('='));
			String imformation = stocks[i].substring(stocks[i].indexOf('\"')+1, 
					stocks[i].lastIndexOf('\"'));
			imformation = te.substring(0, 2) + "," + te.substring(2, te.length()) 
					+ "," + imformation;
			String[] stock = imformation.split(",");
			returnStr[i] = stock;
		}
		for(int i = 0; i < returnStr[0].length; ++i){
		}
		return returnStr;
		
	}
	
	public JSONObject structJsonObject(String[][] strs) 
			throws JSONException{
		int stockSum = strs.length;
		
		String[] jsonKeys = new String[]{"stockExchange", "code","name","todayOpenPrice", 
				"yesterdayClosePrice","currentPrice","todayHightestPrice","todayLowestPrice",
				"bidsPrice","auctionPrice","stockAllDealVolumes","stockAllDealMoney",
				"buyFirstVolumes","buyFirstPrice", "buySecondVolumes","buySecondPrice",
				"buyThirdVolumes","buyThirdPrice","buyFourthVolumes","buyFourthPrice",
				"buyFifthVolumes","buyFifthPrice","saleFirstVolumes","saleFirstPrice",
				"saleSecondVolumes","saleSecondPrice","saleThirdVolumes","saleThirdPrice",
				"saleFourthVolumes","saleFourthPrice","saleFifthVolumes","saleFifthPrice",
				"date","time"};
		for (int i = 0; i < stockSum; ++i) {
			JSONObject jsonObjSub = new JSONObject();
			for(int j = 0; j < jsonKeys.length; ++j){
				jsonObjSub.put(jsonKeys[j], strs[i][j]);
			}
			
			jsonObj.put(jsonObjSub.getString("code"), jsonObjSub);
		}
		
		return jsonObj;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String str = getData(code);
		String[][] strArr = parseString(str);
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
		GetInfoFromSina gifs = new GetInfoFromSina("sh600784,sh600496,sh600569");
		Thread td = new Thread(gifs);
		td.start();
	}
}
