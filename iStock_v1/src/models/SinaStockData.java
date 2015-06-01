//package models;
//
///*
// * 构建从新浪API获取的单支股票信息的Json对象
// */
//
//import java.io.IOException;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import controller.IORW;
//
//public class SinaStockData extends JSONObject{
//	public static final String FILEPATH = "data/temp/";
//	public SinaStockData(String dataString, String fileName) 
//			throws JSONException, IOException{
//		String[][] str = parseString(dataString);
//		structJsonObject(str);
////		save(FILEPATH+fileName);
//	}
//	
////	
////	private void read() throws JSONException{
////		String jsonStr = IORW.read(FILEPATH);
////		recordsJsonObj = new JSONObject(jsonStr);
////	}
//	
//	public JSONObject getJsonObject(){
//		return this;
//	}
//
//	private String[][] parseString(String str){
//		String[][] returnStr;
//		String[] stocks = str.split(";");
//		int length = stocks.length;
//		returnStr = new String[length][];
//		for(int i = 0; i < length; ++i){
//			String te = stocks[i].substring(stocks[i].lastIndexOf('_')+1, 
//					stocks[i].indexOf('='));
//			String imformation = stocks[i].substring(stocks[i].indexOf('\"')+1, 
//					stocks[i].lastIndexOf('\"'));
//			imformation = te.substring(0, 2) + "," + te.substring(2, te.length()) 
//					+ "," + imformation;
//			String[] stock = imformation.split(",");
//			returnStr[i] = stock;
//		}
//		for(int i = 0; i < returnStr[0].length; ++i){
//		}
//		return returnStr;
//		
//	}
//	
//	private void structJsonObject(String[][] strs) 
//			throws JSONException{
//		int stockSum = strs.length;
//		JSONArray jsonArray = new JSONArray();
//		
//		String[] jsonKeys = new String[]{"stockExchange", "code","name","todayOpenPrice", 
//				"yesterdayOpenPrice","currentPrice","todayHightestPrice","todayLowestPrice",
//				"bidsPrice","auctionPrice","stockAllDealVolumes","stockAllDealMoney",
//				"buyFirstVolumes","buyFirstPrice", "buySecondVolumes","buySecondPrice",
//				"buyThirdVolumes","buyThirdPrice","buyFourthVolumes","buyFourthPrice",
//				"buyFifthVolumes","buyFifthPrice","saleFirstVolumes","saleFirstPrice",
//				"saleSecondVolumes","saleSecondPrice","saleThirdVolumes","saleThirdPrice",
//				"saleFourthVolumes","saleFourthPrice","saleFifthVolumes","saleFifthPrice",
//				"date","time"};
//		for (int i = 0; i < stockSum; ++i) {
//			JSONObject jsonObjSub = new JSONObject();
//			for(int j = 0; j < jsonKeys.length; ++j){
//				jsonObjSub.put(jsonKeys[j], strs[i][j]);
//			}
//			
//			jsonArray.put(jsonObjSub);
//		}
//		this.put("stock", jsonArray);
//	}
//	
//	public void save(String path) throws IOException{
//		IORW.write(path, this.toString());
//	}
//	
////	public static void main(String argv[]) throws JSONException, IOException{
////		String str="var hq_str_sh601006=\"大秦铁路,13.41,13.69,13.38,13.69,13.00,13.36,13.37,231942521,3084288127,179894,13.36,127200,13.35,180600,13.34,211650,13.33,101100,13.32,7400,13.37,321800,13.38,169700,13.39,209979,13.40,50268,13.41,2015-04-24,15:04:04,00\"";
////		
////		new SinaStockData(str, "stock.json");
////	}
//	
//}
