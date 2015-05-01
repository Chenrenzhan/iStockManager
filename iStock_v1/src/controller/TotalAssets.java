package controller;

import java.io.IOException;
import java.util.Iterator;

import models.RecordsSet;
import models.StocksSet;
import models.TotalAssetsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * 个人账户总值控制类
 */


public class TotalAssets {
	
	private TotalAssetsData taData;//
	private JSONObject totalAssets;//账户总值
	private JSONObject recordSet;//交易记录
	private JSONObject stockSet;//持有股票
	
	private double capital; //本金
	
	public TotalAssets() throws JSONException {
		
		taData = new TotalAssetsData();
		totalAssets = taData.getJsonObj();
		
		RecordsSet rs = new RecordsSet();
		recordSet = rs.getRecordsSet();
		
		StocksSet ss = new StocksSet();
		stockSet = ss.getStocksSets();
		
		countAssets();
		save();
	}
	
	public void save() throws JSONException{
		taData.setJsonObj(totalAssets);
		
		try {
			taData.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void countAssets() throws JSONException{
		
		String market = "A股(¥)";//市场
	    double dayBe = 0.0;//日盈亏额
	   	double fbe = 0.0;//浮动盈亏，金额，百分比
	    double fbeRatio = 0.0;
	    double be = 0.0;//盈亏，金额，百分比
	    double beRatio = 0.0;
	    double assets = 0.0; //账户总资产
	    double value = 0.0; //市值
	    double cash = 0.0;//现金
	    double capital = 0.0; //本金
	    
		
		Iterator<?> sKeys = stockSet.keys();
		while(sKeys.hasNext()){
			String code = sKeys.next().toString();
			JSONObject jo = stockSet.getJSONObject(code);
			dayBe += jo.getDouble("fbe") + jo.getDouble("fee");
			fbe += jo.getDouble("fbe");
			value += jo.getDouble("holdMoney");
			be += jo.getDouble("be");
		}
		fbeRatio = fbe / Math.abs(value);
		beRatio = be / Math.abs(value);
		
		Iterator<?> rKeys = recordSet.keys();
		while(rKeys.hasNext()){
			String code = rKeys.next().toString();
			JSONArray ja = recordSet.getJSONArray(code);
			for(int i = 0; i < ja.length(); ++i){
				JSONObject jo = ja.getJSONObject(i);
				String type = jo.getString("type");
				if(type.equals("买入") || type.equals("补仓")){
					assets -= jo.getDouble("price") * jo.getDouble("volumes");
				}
				else{
					assets += jo.getDouble("price") * jo.getDouble("volumes");
				}
			}
		}
		assets += value;
		cash = assets - value;
		//capital = assets;
		
		totalAssets.put("market", market);
		totalAssets.put("dayBe", dayBe);
		totalAssets.put("fbe", fbe);
		totalAssets.put("fbeRatio", fbeRatio);
		totalAssets.put("be", be);
		totalAssets.put("beRatio", beRatio);
		totalAssets.put("assets", assets);
		totalAssets.put("value", value);
		totalAssets.put("cash", cash);
		//如果本金不存在或者为0，则初始化为总值
		if(totalAssets.has("capital")){
			if(totalAssets.getDouble("capital") == 0)
				totalAssets.put("capital", assets);
		}
		else
			totalAssets.put("capital", assets);
				
	}
	
	//组织账户总值显示的字符串
	public String[] orgnizeAssets() throws JSONException{
		String[] str = new String[8];
		
		str[0] = totalAssets.getString("market");
		str[1] = StockMath.valueOf(totalAssets.getDouble("dayBe"));
		str[2] = StockMath.valueOf(totalAssets.getDouble("fbe"))
				+ "\r\n" + StockMath.doubleToPercent(totalAssets.getDouble("fbeRatio"));
		str[3] = StockMath.valueOf(totalAssets.getDouble("be"))
				+ "\r\n" + StockMath.doubleToPercent(totalAssets.getDouble("beRatio"));
		str[4] = StockMath.valueOf(totalAssets.getDouble("assets"));
		str[5] = StockMath.valueOf(totalAssets.getDouble("value"));
		str[6] = StockMath.valueOf(totalAssets.getDouble("cash"));
		str[7] = StockMath.valueOf(totalAssets.getDouble("capital"));
		
		return str;
	}

	public JSONObject getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(JSONObject totalAssets) {
		this.totalAssets = totalAssets;
		try {
			save();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCapital(double capital) {
		this.capital = capital;
		try {
			totalAssets.put("capital", capital);
			save();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] argv){
		String[] assets = null;
		try {
			assets = new TotalAssets().orgnizeAssets();
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(String s : assets){
			System.out.println(s);
		}
	}
}
