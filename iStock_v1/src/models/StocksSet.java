package models;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import controller.IORW;

public class StocksSet {

	private final String FILEPATH = "data/stock.json";
	private JSONObject stocksJsonObj;
	
	public StocksSet() throws JSONException{
		String jsonStr = IORW.read(FILEPATH);
		stocksJsonObj = new JSONObject(jsonStr);
	}
	
	//添加一个股票
	public void addRecord(Stock stockJsonObj) 
			throws JSONException{
		String code = stockJsonObj.getString("code");
		if(stocksJsonObj.has(code)){
			removeStock(code);
		}
		stocksJsonObj.put(code, stockJsonObj);
	}
	
	//删除一个股票
	public void removeStock(String code) 
			throws JSONException{
		stocksJsonObj.remove(code);
	}
	
	public void removeStock(Stock jsonObj) 
			throws JSONException{
		String code = jsonObj.getString("code");
		removeStock(code);
	}
	
	public JSONObject getStocksSets(){
		return stocksJsonObj;
	}
	
	public void save() throws IOException{
		IORW.write(FILEPATH, stocksJsonObj.toString());
	}
}
