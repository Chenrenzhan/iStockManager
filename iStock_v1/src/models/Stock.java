package models;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * 单股股票
 */

public class Stock extends JSONObject{
	
	private String code; //股票编码
	private String marketType; //股票类型
	private String name; //股票名字
	private double currentPrice; //当前价格
	private double dilutedCost; //摊薄成本
	private double holdCost; //持仓成本
	private double holdSum; //持有量
	private double holdMoney; //持有市值
	private double floatBreakEvent_money;//浮动盈亏，金额
	private double floatBreakEvent_percent;//浮动盈亏,百分比
	private double breakEvent_money;//盈亏，金额
	private double breakEvent_percent;//盈亏,百分比
	
	
	 
	public Stock(JSONObject jsonObj) throws JSONException{
    	setStock(jsonObj);
    }
    
	public Stock() throws JSONException{
    	super();
    }
    
    public JSONObject getStock() throws JSONException{
    	return this;
    }
    
    public void initiate() throws JSONException{
    	this.put("code", code);
    	this.put("marketType", marketType);
    	this.put("name", name);
    	this.put("currentPrice", currentPrice);
    	this.put("dilutedCost", dilutedCost);
    	this.put("holdCost", holdCost);
    	this.put("holdSum", holdSum);
    	this.put("holdMoney", holdMoney);
    	this.put("floatBreakEvent_money", floatBreakEvent_money);
    	this.put("floatBreakEvent_percent", floatBreakEvent_percent);
    	this.put("breakEvent_money", breakEvent_money);
    	this.put("breakEvent_percent", breakEvent_percent);
    }
    
    public void setStock(JSONObject jsonObj) 
    		throws JSONException{
    	this.code = jsonObj.getString("code");
    	this.marketType = jsonObj.getString("marketType");
    	this.name = jsonObj.getString("name");
    	this.currentPrice = jsonObj.getDouble("currentPrice");
    	this.dilutedCost = jsonObj.getDouble("dilutedCost");
    	this.holdCost = jsonObj.getInt("holdCost");
    	this.holdSum = jsonObj.getDouble("holdSum");
    	this.holdMoney = jsonObj.getDouble("holdMoney");
    	this.floatBreakEvent_money = 
    			jsonObj.getDouble("floatBreakEvent_money");
    	this.floatBreakEvent_percent = 
    			jsonObj.getDouble("floatBreakEvent_percent");
    	this.breakEvent_money = 
    			jsonObj.getDouble("breakEvent_money");
    	this.breakEvent_percent = 
    			jsonObj.getDouble("breakEvent_percent");
    	initiate();
    }
}


