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
    private double dayBe;//日盈亏额
    private double fbe;//浮动盈亏，金额，百分比
    private double fbeRatio;
    private double be;//盈亏，金额，百分比
    private double beRatio;
    private double totalAssets; //账户总资产
    private double value; //市值
    private double cash;//现金
    private double capital; //本金
    
    private String[] KEYS = new String[]{"market", "dayBe", "fbe", "fbeRatio",
    		"be", "beRatio", "totalAssets", "value", "cash", "capital"};
    
    public TotalAssetsData() throws JSONException {
    	jsonStr = IORW.read(FILEPATH);
    	if(jsonStr == ""){
    		jsonObj = null;
        	market = "";
        	dayBe = 0.0;
        	fbe = 0.0;
        	fbeRatio = 0.0;
        	be = 0.0;
        	beRatio = 0.0;
        	totalAssets = 0.0;
        	value = 0.0;
        	cash = 0.0;
        	capital = 0.0;
//        	structJsonString();
    	}

    	jsonObj = new JSONObject(jsonStr);   		
//    	initiate();
    }
    
    TotalAssetsData(JSONObject jsonObj) throws JSONException{
    	this.jsonObj = jsonObj;
    	
    	initiate();
    }
    
    TotalAssetsData(String jsonStr) throws JSONException{
		jsonObj = new JSONObject(jsonStr);
		
		initiate();
    }
    
    public TotalAssetsData(String[] str) throws JSONException{
    	jsonObj = new JSONObject();
    	structJsonObject(str);
    	initiate();
    }
    
    private void initiate() 
    		throws JSONException{
    	if(jsonObj != null){
        	this.market = jsonObj.getString("market");
        	this.dayBe = jsonObj.getDouble("dayBe");
        	this.fbe = jsonObj.getDouble("fbe");
        	this.fbeRatio = jsonObj.getDouble("fbeRatio");
        	this.be = jsonObj.getDouble("be");
        	this.beRatio = jsonObj.getDouble("beRatio");
        	this.totalAssets = jsonObj.getDouble("totalAssets");
        	this.value = jsonObj.getDouble("value");
        	this.cash = jsonObj.getDouble("cash");
        	this.capital = jsonObj.getDouble("capital");
        }
    }
    
    public void save() 
    		throws IOException{
    	IORW.write(FILEPATH, jsonObj.toString());
    }
    
    //构建json格式个人总值数据的字符串
    void structJsonObject(String[] str){
    	for(int i = 0; i < str.length; ++i){
    		try {
				jsonObj.put(KEYS[i], str[i]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }


    
	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public String getMarket() {
		return market;
	}

	public double getDayBreakEvent() {
		return dayBe;
	}

	public double getFloatBreakEvent_money() {
		return fbe;
	}

	public double getFloatBreakEvent_percent() {
		return fbeRatio;
	}

	public double getBreakEvent_money() {
		return be;
	}

	public double getBreakEvent_percent() {
		return beRatio;
	}

	public double getTotalAssets() {
		return totalAssets;
	}

	public double getMarketValue() {
		return value;
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
		this.dayBe = dayBreakEvent;
	}

	public void setFloatBreakEvent_money(double floatBreakEvent_money) {
		this.fbe = floatBreakEvent_money;
	}

	public void setFloatBreakEvent_percent(double floatBreakEvent_percent) {
		this.fbeRatio = floatBreakEvent_percent;
	}

	public void setBreakEvent_money(double breakEvent_money) {
		this.be = breakEvent_money;
	}

	public void setBreakEvent_percent(double breakEvent_percent) {
		this.beRatio = breakEvent_percent;
	}

	public void setTotalAssets(double totalAssets) {
		this.totalAssets = totalAssets;
	}

	public void setMarketValue(double marketValue) {
		this.value = marketValue;
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
			return dayBe;
		case 2:
			return fbe;
		case 3:
			return fbeRatio;
		case 4:
			return be;
		case 5:
			return beRatio;
		case 6:
			return totalAssets;
		case 7:
			return value;
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
			this.dayBe = (double)obj;
			return true;
		case 2:
			this.fbe = (double)obj;
			return true;
		case 3:
			this.fbeRatio = (double)obj;
			return true;
		case 4:
			this.be = (double)obj;
			return true;
		case 5:
			this.beRatio = (double)obj;
			return true;
		case 6:
			this.totalAssets = (double)obj;
			return true;
		case 7:
			this.value = (double)obj;
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
