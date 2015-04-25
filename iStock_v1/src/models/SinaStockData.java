package models;

import java.io.IOException;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controller.IORW;

public class SinaStockData {
	private JSONObject jsonObj;//json��ʽ���ַ�
	
	SinaStockData(String dataString) throws JSONException{
		String[][] str = parseString(dataString);
		jsonObj = structJsonObject(str);
	}
	
	JSONObject getJsonObject(){
		return jsonObj;
	}

	private String[][] parseString(String str){
		String[][] returnStr;
		String[] stocks = str.split(";");
		int length = stocks.length;
		returnStr = new String[length][];
		for(int i = 0; i < length; ++i){
			String te = stocks[i].substring(stocks[i].lastIndexOf('_')+1, 
					stocks[i].indexOf('='));
			String imformation = stocks[i].substring(stocks[i].indexOf('\"')+1, 
					stocks[i].lastIndexOf('\"'));
			imformation = te.substring(0, 2) + "," + te.substring(2, te.length()) + "," + imformation;
			String[] stock = imformation.split(",");
			returnStr[i] = stock;
		}
		for(int i = 0; i < returnStr[0].length; ++i){
		}
		return returnStr;
		
	}
	
	private JSONObject structJsonObject(String[][] strs) throws JSONException{
		JSONObject jsonObj = new JSONObject();
		
		JSONArray jsonArr = new JSONArray();
		
		JSONObject jsonSub = new JSONObject();
		
		int stockSum = strs.length;
		for (int i = 0; i < stockSum; ++i) {
			jsonSub.put("stockExchange", strs[i][0]);
			jsonSub.put("code", strs[i][1]);
			jsonSub.put("name", strs[i][2]);
			jsonSub.put("todayOpenPrice", strs[i][3]);
			jsonSub.put("yesterdayOpenPrice", strs[i][4]);
			jsonSub.put("currentPrice", strs[i][5]);
			jsonSub.put("todayHightestPrice", strs[i][6]);
			jsonSub.put("todayLowestPrice", strs[i][7]);
			jsonSub.put("bidsPrice", strs[i][8]);
			jsonSub.put("auctionPrice", strs[i][9]);
			jsonSub.put("stockAllDealVolumes", strs[i][10]);
			jsonSub.put("stockAllDealMoney", strs[i][11]);
			jsonSub.put("buyFirstVolumes", strs[i][12]);
			jsonSub.put("buySecondVolumes", strs[i][13]);
			jsonSub.put("buyThirdVolumes", strs[i][14]);
			jsonSub.put("buyFourthVolumes", strs[i][15]);
			jsonSub.put("buyFifthVolumes", strs[i][16]);
			jsonSub.put("buyFirstPrice", strs[i][17]);
			jsonSub.put("buySecondPrice", strs[i][18]);
			jsonSub.put("buyThirdPrice", strs[i][19]);
			jsonSub.put("buyFourthPrice", strs[i][20]);
			jsonSub.put("buyFifthPrice", strs[i][21]);
			jsonSub.put("saleFirstVolumes", strs[i][22]);
			jsonSub.put("saleSecondVolumes", strs[i][23]);
			jsonSub.put("saleThirdVolumes", strs[i][24]);
			jsonSub.put("saleFourthVolumes", strs[i][25]);
			jsonSub.put("saleFifthVolumes", strs[i][26]);
			jsonSub.put("saleFirstPrice", strs[i][27]);
			jsonSub.put("saleSecondPrice", strs[i][28]);
			jsonSub.put("saleThirdPrice", strs[i][29]);
			jsonSub.put("saleFourthPrice", strs[i][30]);
			jsonSub.put("saleFifthPrice", strs[i][31]);
			jsonSub.put("date", strs[i][32]);
			jsonSub.put("time", strs[i][33]);
		}
		
		jsonArr.put(jsonSub);
		jsonObj.put("stock", jsonArr);
		return jsonObj;				
	}
	private final String FILEPATH = "data/temp/stock.json";
	public void save() throws IOException{System.out.println("ssssssssssss");
		IORW.write(FILEPATH, jsonObj.toString());
	}
	
	public static void main(String argv[]) throws JSONException, IOException{
		String str="var hq_str_sh601006=\"大秦铁路,13.41,13.69,13.38,13.69,13.00,13.36,13.37,231942521,3084288127,179894,13.36,127200,13.35,180600,13.34,211650,13.33,101100,13.32,7400,13.37,321800,13.38,169700,13.39,209979,13.40,50268,13.41,2015-04-24,15:04:04,00\"";
		new SinaStockData(str).save();
	}
	
}
