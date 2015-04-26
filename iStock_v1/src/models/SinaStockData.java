package models;

/*
 * 构建从新浪API获取的单支股票信息的Json对象
 */

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import controller.IORW;

public class SinaStockData extends JSONObject{
	public static final String FILEPATH = "data/temp/stock.json";
	public SinaStockData(String dataString) 
			throws JSONException, IOException{
		String[][] str = parseString(dataString);
		structJsonObject(str);
		save();
	}
	
	JSONObject getJsonObject(){
		return this;
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
	
	private void structJsonObject(String[][] strs) 
			throws JSONException{
		int stockSum = strs.length;
		for (int i = 0; i < stockSum; ++i) {
			this.put("stockExchange", strs[i][0]);
			this.put("code", strs[i][1]);
			this.put("name", strs[i][2]);
			this.put("todayOpenPrice", strs[i][3]);
			this.put("yesterdayOpenPrice", strs[i][4]);
			this.put("currentPrice", strs[i][5]);
			this.put("todayHightestPrice", strs[i][6]);
			this.put("todayLowestPrice", strs[i][7]);
			this.put("bidsPrice", strs[i][8]);
			this.put("auctionPrice", strs[i][9]);
			this.put("stockAllDealVolumes", strs[i][10]);
			this.put("stockAllDealMoney", strs[i][11]);
			this.put("buyFirstVolumes", strs[i][12]);
			this.put("buySecondVolumes", strs[i][13]);
			this.put("buyThirdVolumes", strs[i][14]);
			this.put("buyFourthVolumes", strs[i][15]);
			this.put("buyFifthVolumes", strs[i][16]);
			this.put("buyFirstPrice", strs[i][17]);
			this.put("buySecondPrice", strs[i][18]);
			this.put("buyThirdPrice", strs[i][19]);
			this.put("buyFourthPrice", strs[i][20]);
			this.put("buyFifthPrice", strs[i][21]);
			this.put("saleFirstVolumes", strs[i][22]);
			this.put("saleSecondVolumes", strs[i][23]);
			this.put("saleThirdVolumes", strs[i][24]);
			this.put("saleFourthVolumes", strs[i][25]);
			this.put("saleFifthVolumes", strs[i][26]);
			this.put("saleFirstPrice", strs[i][27]);
			this.put("saleSecondPrice", strs[i][28]);
			this.put("saleThirdPrice", strs[i][29]);
			this.put("saleFourthPrice", strs[i][30]);
			this.put("saleFifthPrice", strs[i][31]);
			this.put("date", strs[i][32]);
			this.put("time", strs[i][33]);
		}
	}
	
	public void save() throws IOException{
		IORW.write(FILEPATH, this.toString());
	}
	
	public static void main(String argv[]) throws JSONException, IOException{
		String str="var hq_str_sh601006=\"大秦铁路,13.41,13.69,13.38,13.69,13.00,13.36,13.37,231942521,3084288127,179894,13.36,127200,13.35,180600,13.34,211650,13.33,101100,13.32,7400,13.37,321800,13.38,169700,13.39,209979,13.40,50268,13.41,2015-04-24,15:04:04,00\"";
		
		new SinaStockData(str);
	}
	
}
