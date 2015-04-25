package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;

import controller.IORW;



public class TotalAssetsData {
	private final String FILEPATH = "data/assets.json";
	
	private JSONObject jsonObj;
	private String jsonStr;
	
	private String market;//市场
    private double dayBreakEvent;//日盈亏额
    private double floatBreakEvent_money;//{"money":-23.33, "percent":-0.02%},//浮动盈亏，金额，百分比
    private double floatBreakEvent_percent;
    private double breakEvent_money;//{"money":17623.28, "percent":+3.53%}, //盈亏，金额，百分比
    private double breakEvent_percent;
    private double totalAssets; //账户总资产
    private double marketValue; //市值
    private double cash;//现金
    private double capital; //本金
    
    TotalAssetsData() throws JSONException {
    	jsonStr = IORW.read(FILEPATH);
    	if(jsonStr == ""){
    		jsonObj = null;
        	market = "";
        	dayBreakEvent = 0.0;
        	floatBreakEvent_money = 0.0;
        	floatBreakEvent_percent = 0.0;
        	breakEvent_money = 0.0;
        	breakEvent_percent = 0.0;
        	totalAssets = 0.0;
        	marketValue = 0.0;
        	cash = 0.0;
        	capital = 0.0;
        	structJsonString();
    	}

    	jsonObj = new JSONObject(jsonStr);   		
    	initiate(jsonObj);
    }
    
    TotalAssetsData(JSONObject jsonObj) throws JSONException{
    	this.jsonObj = jsonObj;
    	
    	initiate(jsonObj);
    }
    
//    TotalAssetsData(String filePath) throws JSONException{
//    	String jsonStr = IORW.readFile(filePath);
//    	jsonObj = new JSONObject(jsonStr);
//		
//		initiate(jsonObj);
//    }
    
    TotalAssetsData(String jsonStr) throws JSONException{
		jsonObj = new JSONObject(jsonStr);
		
		initiate(jsonObj);
    }
    
    private void initiate(JSONObject jsonObj) 
    		throws JSONException{
    	if(jsonObj != null){
        	this.market = jsonObj.getString("market");
        	this.dayBreakEvent = jsonObj.getDouble("dayBreakEvent");
        	this.floatBreakEvent_money = jsonObj.getDouble("floatBreakEvent_money");
        	this.floatBreakEvent_percent = jsonObj.getDouble("floatBreakEvent_percent");
        	this.breakEvent_money = jsonObj.getDouble("breakEvent_money");
        	this.breakEvent_percent = jsonObj.getDouble("breakEvent_percent");
        	this.totalAssets = jsonObj.getDouble("totalAssets");
        	this.marketValue = jsonObj.getDouble("marketValue");
        	this.cash = jsonObj.getDouble("cash");
        	this.capital = jsonObj.getDouble("capital");
        }
    }
    
    public void save(String filePath) 
    		throws IOException{
    	IORW.write(FILEPATH, jsonObj.toString());
    }
    
    //构建json格式个人总值数据的字符串
    void structJsonString(){
    	jsonStr = "{"
    			+ "\"market:\"" + this.market + ","
    			+ "\"dayBreakEvent:\"" + this.dayBreakEvent + ","
    			+ "\"floatBreakEvent_money:\"" + this.floatBreakEvent_money + ","
    			+ "\"floatBreakEvent_percent:\"" + this.floatBreakEvent_percent + ","
    			+ "\"breakEvent_money:\"" + this.breakEvent_money + ","
    			+ "\"breakEvent_percent:\"" + this.breakEvent_percent + ","
    			+ "\"totalAssets:\"" + this.totalAssets + ","
    			+ "\"marketValue:\"" + this.marketValue + ","
    			+ "\"cash:\"" + this.cash + ","
    			+ "\"capital:\"" + this.capital + ","
    			+ "}";
//    	return jsonStr;
    }


    
	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public String getMarket() {
		return market;
	}

	public double getDayBreakEvent() {
		return dayBreakEvent;
	}

	public double getFloatBreakEvent_money() {
		return floatBreakEvent_money;
	}

	public double getFloatBreakEvent_percent() {
		return floatBreakEvent_percent;
	}

	public double getBreakEvent_money() {
		return breakEvent_money;
	}

	public double getBreakEvent_percent() {
		return breakEvent_percent;
	}

	public double getTotalAssets() {
		return totalAssets;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public double getCash() {
		return cash;
	}

	public double getCapital() {
		return capital;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}
	
	public void setJsonObj(String jsonStr) 
			throws JSONException {
		JSONObject jsonObj = new JSONObject(jsonStr);
		this.jsonObj = jsonObj;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public void setDayBreakEvent(double dayBreakEvent) {
		this.dayBreakEvent = dayBreakEvent;
	}

	public void setFloatBreakEvent_money(double floatBreakEvent_money) {
		this.floatBreakEvent_money = floatBreakEvent_money;
	}

	public void setFloatBreakEvent_percent(double floatBreakEvent_percent) {
		this.floatBreakEvent_percent = floatBreakEvent_percent;
	}

	public void setBreakEvent_money(double breakEvent_money) {
		this.breakEvent_money = breakEvent_money;
	}

	public void setBreakEvent_percent(double breakEvent_percent) {
		this.breakEvent_percent = breakEvent_percent;
	}

	public void setTotalAssets(double totalAssets) {
		this.totalAssets = totalAssets;
	}

	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}
    
	Object getIdenxOf(int index){
		switch(index){
		case 0:
			return market;
		case 1:
			return dayBreakEvent;
		case 2:
			return floatBreakEvent_money;
		case 3:
			return floatBreakEvent_percent;
		case 4:
			return breakEvent_money;
		case 5:
			return breakEvent_percent;
		case 6:
			return totalAssets;
		case 7:
			return marketValue;
		case 8:
			return cash;
		case 9:
			return capital;
		default:
			return null;					
		}
	}
	
	Boolean setIndeOf(int index, Object obj){
		switch(index){
		case 0:
			this.market = (String)obj;
			return true;
		case 1:
			this.dayBreakEvent = (double)obj;
			return true;
		case 2:
			this.floatBreakEvent_money = (double)obj;
			return true;
		case 3:
			this.floatBreakEvent_percent = (double)obj;
			return true;
		case 4:
			this.breakEvent_money = (double)obj;
			return true;
		case 5:
			this.breakEvent_percent = (double)obj;
			return true;
		case 6:
			this.totalAssets = (double)obj;
			return true;
		case 7:
			this.marketValue = (double)obj;
			return true;
		case 8:
			this.cash = (double)obj;
			return true;
		case 9:
			this.capital = (double)obj;
			return true;
		default:
			return false;					
		}
	}
}
