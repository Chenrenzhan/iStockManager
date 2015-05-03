package models;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * 单股股票
 */

public class Stock extends JSONObject{
	
	private String code; //股票编码
//	private String marketType; //股票类型
	private String name; //股票名字
	private double risefall; //涨跌
	private double risefallRatio;//涨跌率
	private double currentPrice; //当前价格
	private double dilutedCost; //摊薄成本
	private double holdCost; //持仓成本
	private int holdSum; //持有量
	private double holdMoney; //持有市值
	private double fbe;//浮动盈亏，金额
	private double fbeRatio;//浮动盈亏,百分比
	private double be;//盈亏，金额
	private double beRatio;//盈亏,百分比
	private double fee;//手续费
	
	
	 
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
//    	this.put("marketType", marketType);
    	this.put("risefall", risefall);
    	this.put("risefallRatio", risefallRatio);
    	this.put("name", name);
    	this.put("currentPrice", currentPrice);
    	this.put("dilutedCost", dilutedCost);
    	this.put("holdCost", holdCost);
    	this.put("holdSum", holdSum);
    	this.put("holdMoney", holdMoney);
    	this.put("fbe", fbe);
    	this.put("fbeRatio", fbeRatio);
    	this.put("be", be);
    	this.put("beRatio", beRatio);
    	this.put("fee", fee);
    }
    
    public void setStock(JSONObject jsonObj) 
    		throws JSONException{
    	this.code = jsonObj.getString("code");
//    	this.marketType = jsonObj.getString("marketType");
    	this.name = jsonObj.getString("name");
    	this.currentPrice = jsonObj.getDouble("currentPrice");
    	this.dilutedCost = jsonObj.getDouble("dilutedCost");
    	this.holdCost = jsonObj.getInt("holdCost");
    	this.holdSum = jsonObj.getInt("holdSum");
    	this.holdMoney = jsonObj.getDouble("holdMoney");
    	this.fbe = jsonObj.getDouble("fbe");
    	this.fbeRatio = jsonObj.getDouble("fbeRatio");
    	this.be = jsonObj.getDouble("be");
    	this.beRatio = jsonObj.getDouble("beRatio");
    	this.fee = jsonObj.getDouble("fee");
    	initiate();
    }
}


