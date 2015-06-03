package controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	private final String _account;
	private TotalAssetsData taData;//
	private JSONObject totalAssets;// 账户总值
	private JSONObject recordSet;// 交易记录
	private JSONObject stockSet;// 持有股票

	private double capital; // 本金

	public TotalAssets(String account) throws JSONException {
		_account = account;
		taData = new TotalAssetsData(_account);
		totalAssets = taData.getJsonObj();

		RecordsSet rs = new RecordsSet(_account);
		recordSet = rs.getRecordsSet();
		// if(recordSet==null){
		// System.out.println("null recordset");
		// }
		StocksSet ss = new StocksSet(_account);
		stockSet = ss.getStocksSets();

		countAssets();
		save();
	}

	public JSONObject CaltotalAssets() throws JSONException {
		JSONObject totalAsset;
		countAssets();
		totalAsset = this.totalAssets;
		return totalAsset;

	}

	public void save() throws JSONException {

		taData.setJsonObj(totalAssets);

		try {
			taData.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void countAssets() throws JSONException {

		String market = "A股(¥)";// 市场
		double dayBe = 0.0;// 日盈亏额
		double fbe = 0.0;// 浮动盈亏，金额，百分比
		double fbeRatio = 0.0;
		double be = 0.0;// 盈亏，金额，百分比
		double beRatio = 0.0;
		double assets = 0.0; // 账户总资产
		double value = 0.0; // 市值
		double cash = 0.0;// 现金
		double capital = 0.0; // 本金
		// 下面的计算初步验证过的将注释为true
		Iterator<?> sKeys = stockSet.keys();
		while (sKeys.hasNext()) {
			String code = sKeys.next().toString();
			JSONObject jo = stockSet.getJSONObject(code);
			// dayBe += jo.getDouble("fbe") + jo.getDouble("fee");
			fbe += jo.getDouble("fbe");
			value += jo.getDouble("holdMoney");// 获得持有的某只股票的总市值即持有市值 true
			be += jo.getDouble("be");
		}
		if (value == 0) {
			fbeRatio = 0;
		} else {
			fbeRatio = fbe / Math.abs(value);

		}

		// if(recordSet==null){
		// System.out.println("null recordset");
		// }

		// 计算盈亏率
		if (totalAssets.has("capital")) {
			capital = totalAssets.getDouble("capital");
		} else {
			capital = 0;
			Iterator<?> rKeys = recordSet.keys();
			while (rKeys.hasNext()) {
				String code = rKeys.next().toString();
				JSONArray ja = recordSet.getJSONArray(code);
				for (int i = 0; i < ja.length(); ++i) {
					JSONObject jo = ja.getJSONObject(i);
					String type = jo.getString("type");
					if (type.equals("买入") || type.equals("卖空")) {
						capital += jo.getDouble("price")
								* jo.getDouble("volumes")
								* (1 + Double.valueOf(jo.getString("taxes")));
					}
				}
			}
		}
		if (capital == 0) {
			beRatio = 0;
		} else {
			beRatio = be / Math.abs(capital);
		}
		cash = capital + be - value;
		assets = cash + value;

		// 计算日盈亏
		try {
			dayBe = calDayBe();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dayBe = totalAssets.getDouble("dayBe");
		}
		// capital = assets;
		// System.out.println("fbeRatio  " + fbeRatio);
		totalAssets.put("market", market);
		totalAssets.put("dayBe", dayBe);
		totalAssets.put("fbe", fbe);
		totalAssets.put("fbeRatio", fbeRatio);
		totalAssets.put("be", be);
		totalAssets.put("beRatio", beRatio);
		totalAssets.put("assets", assets);
		totalAssets.put("value", value);
		totalAssets.put("cash", cash);
		// 如果本金不存在或者为0，则初始化为总值
		if (totalAssets.has("capital")) {
			if (totalAssets.getDouble("capital") == 0)
				totalAssets.put("capital", assets);
		}
		 else
		 totalAssets.put("capital", calCapital());

	}

	// 组织账户总值显示的字符串
	public String[] orgnizeAssets() throws JSONException {
		String[] str = new String[8];

		str[0] = totalAssets.getString("market");
		str[1] = StockMath.valueOf(totalAssets.getDouble("dayBe"));
		str[2] = StockMath.valueOf(totalAssets.getDouble("fbe")) + "\r\n"
				+ StockMath.doubleToPercent(totalAssets.getDouble("fbeRatio"));
		str[3] = StockMath.valueOf(totalAssets.getDouble("be")) + "\r\n"
				+ StockMath.doubleToPercent(totalAssets.getDouble("beRatio"));
		str[4] = StockMath.valueOf(totalAssets.getDouble("assets"));
		str[5] = StockMath.valueOf(totalAssets.getDouble("value"));
		str[6] = StockMath.valueOf(totalAssets.getDouble("cash"));
		str[7] = StockMath.valueOf(totalAssets.getDouble("capital"));

		return str;
	}

	public JSONObject getTotalAssets() {
		return totalAssets;
	}
	public double calCapital() throws JSONException{
		double capital = 0.0;
		JSONObject totalAssets = new TotalAssetsData(_account).getJsonObj();
		JSONObject recordSet = new RecordsSet(_account).getRecordsSet();

		if (totalAssets.has("capital")) {
			capital = totalAssets.getDouble("capital");
		} 
		if(capital==0){
			capital = 0;
			Iterator<?> rKeys = recordSet.keys();
			while (rKeys.hasNext()) {
				String code1 = rKeys.next().toString();
				JSONArray ja1 = recordSet.getJSONArray(code1);
				for (int i = 0; i < ja1.length(); ++i) {
					JSONObject jo1 = ja1.getJSONObject(i);
					String type = jo1.getString("type");
					if (type.equals("买入") || type.equals("卖空")) {
						capital += jo1.getDouble("price")
								* jo1.getDouble("volumes")
								* (1 + Double.valueOf(jo1
										.getString("taxes")));
					}
				}
			}
		}
		return capital;
	}
	public double calDayBe() throws JSONException, UnknownHostException {
		Double dayBe = 0.0;
		int dayHoldSum = 0;
		Date date = new Date();
		SimpleDateFormat DF = new SimpleDateFormat("yy-MM-dd");
		RecordsSet rds = new RecordsSet(_account);
		JSONObject rdsJsonObj = rds.getRecordsSet();
		// System.out.println("test hasNext in holdstock");
		Iterator<?> keys = rdsJsonObj.keys();
		while (keys.hasNext()) {
			String code = (String) keys.next().toString();// 股票代码
			JSONArray ja = rdsJsonObj.getJSONArray(code);// 获取一个股票的多条记录
			// 实时获取单支股票信息
			if (code.contains("sh") | code.contains("sz")) {
				code = code.substring(2, code.length());
			}
			JSONObject jo = inTimeStock(code);
			if (jo == null) {
				return 0.0;
			}
			String[] dStr = new String[5];// 暂存类型、价格、数量、税率、佣金

			for (int i = 0; i < ja.length(); ++i) {
				JSONObject sjo = (JSONObject) ja.get(i);

				dStr[0] = sjo.getString("type");
				dStr[1] = sjo.getString("price");
				dStr[2] = sjo.getString("volumes");
				dStr[3] = sjo.getString("taxes");
				dStr[4] = sjo.getString("commission");

				int volumes = Integer.valueOf(dStr[2]);
				if (sjo.getString("date").equals(DF.format(date))) {
					// 计算持股数量,以及盈亏的操作盈亏部分，市值部分在算出市值后计算
					if (dStr[0].equals("买入") || dStr[0].equals("补仓")) {
						dayBe -= Double.valueOf(dStr[1]) * volumes
								* (1 + Double.valueOf(dStr[3]));
						dayHoldSum += volumes;
					} else {
						dayBe += Double.valueOf(dStr[1]) * volumes
								* (1 - Double.valueOf(dStr[3]));
						dayHoldSum -= volumes;
					}
				}
			}
			double curPrice = StockMath.valueOf(jo.getString("currentPrice"));
			dayBe += curPrice * dayHoldSum;
		}
		return dayBe;
	}

	public JSONObject inTimeStock(String code) throws JSONException,
			UnknownHostException {

		GetInfoFromSina gifs = null;

		gifs = new GetInfoFromSina(code);

		// catch (RuntimeException e) {
		//
		// }
		JSONObject jo = new JSONObject();
		Thread td = new Thread(gifs);
		td.start();
		try {
			td.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!gifs.getDlCompleted())
			throw new UnknownHostException("can't connect to internet");
		else {
			JSONObject j = gifs.getJsonObj();
			jo = j.getJSONObject(code);

			return jo;
		}

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

//	public static double getCapital() {
//		try {
//			return new TotalAssetsData().getJsonObj().getDouble("capital");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return 0.0;
//		}
//	}

	// public static void main(String[] argv) {
	// String[] assets = null;
	// try {
	// assets = new TotalAssets().orgnizeAssets();
	// } catch (JSONException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	// for (String s : assets) {
	// System.out.println(s);
	// }
	// }
}
