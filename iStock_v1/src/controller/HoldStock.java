package controller;

/*
 * 持仓情况的控制类
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import models.Record;
import models.RecordsSet;
import models.Stock;
import models.StocksSet;

public class HoldStock {

	// private JSONObject rdsJsonObj;
	// private String[][] holdStocks;
	private StocksSet stockSet;

	public HoldStock() throws IOException {
		try {
			stockSet = new StocksSet();
			countStockFromRecord();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 从历史记录统计持股情况
	public void countStockFromRecord() throws JSONException, IOException {

		// List<String[]> list = new ArrayList<String[]>();
		RecordsSet rds = new RecordsSet();
		JSONObject rdsJsonObj = rds.getRecordsSet();

		Iterator<?> keys = rdsJsonObj.keys();
		while (keys.hasNext()) {
			String code = (String) keys.next().toString();// 股票代码
			JSONArray ja = rdsJsonObj.getJSONArray(code);// 获取一个股票的多条记录

			Stock skJson = new Stock();

			// 实时获取单支股票信息
			JSONObject jo = inTimeStock(code);
			if (jo == null) {
				return;
			}
			String[] str = new String[9];

			// 计算摊薄成本参数的数组，买入股票总数费用，总手续费，股票总数
			double[] dilution = new double[] { 0.0, 0.0, 0.0 };
			String[] dStr = new String[5];// 暂存类型、价格、数量、税率、佣金
			int holdSum = 0;// 持有股票数量

			for (int i = 0; i < ja.length(); ++i) {
				JSONObject sjo = (JSONObject) ja.get(i);
				dStr[0] = sjo.getString("type");
				dStr[1] = sjo.getString("price");
				dStr[2] = sjo.getString("volumes");
				dStr[3] = sjo.getString("taxes");
				dStr[4] = sjo.getString("commission");

				dilution = countDilutedCost(dilution, dStr);

				// 计算持股数量
				if (dStr[0].equals("买入") || dStr[0].equals("补仓")) {
					holdSum += Integer.valueOf(dStr[2]);
				} else {
					holdSum -= Integer.valueOf(dStr[2]);
				}
			}

			if (holdSum == 0)
				continue;
			// 当前价
			double curPrice = StockMath.valueOf(jo.getString("currentPrice"));
			// 昨收价
			double yPrice = StockMath.valueOf(jo
					.getString("yesterdayClosePrice"));
			// 涨跌=当前价-昨收,涨跌率=涨跌/昨收
			double risefall = curPrice - yPrice;
			double risefallRatio = risefall / yPrice;
			// 持有市值
			double holdMoney = holdSum * curPrice;
			// 摊薄成本
			double dilu = (dilution[0] + dilution[1]) / dilution[2];
			// 持仓成本
			double holdCost = (holdMoney + dilution[1]) / holdSum;
			// 浮动盈亏=（当前价 - 持仓成本）*持有量 - 手续费
			double floatBE = (curPrice - holdCost)
					* holdSum
					* (1 - StockMath.percentToDouble("1‰") + StockMath
							.percentToDouble("0.3‰"));
			double flatBERatio = floatBE / holdMoney;

			// 盈亏（简化）= （现价*0.9955 - 成本*1.0035）*持有量
			// ，系数：0.0035和0.995分别是印花税。券商佣金、杂费的折合值
			double be = (curPrice * 0.9955 - holdCost * 1.0035) * holdSum;
			double beRatio = be / holdMoney;

			skJson.put("code", code);
			skJson.put("name", jo.getString("name"));
			skJson.put("risefall", risefall);
			skJson.put("risefallRatio", risefallRatio);
			skJson.put("currentPrice", curPrice);
			skJson.put("dilutedCost", dilu);
			skJson.put("holdCost", holdCost);
			skJson.put("holdSum", holdSum);
			skJson.put("holdMoney", holdMoney);
			skJson.put("fbe", floatBE);
			skJson.put("fbeRatio", flatBERatio);
			skJson.put("be", be);
			skJson.put("beRatio", beRatio);
			skJson.put("fee", dilution[1]);// 手续费

			stockSet.addRecord(skJson);

		}

		try {
			stockSet.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 计算摊薄成本
	public double[] countDilutedCost(double[] dilution, String[] str) {
		double stockCost = 0.0;// 购买股票花费（不包括手续费）
		double fee = 0.0;// 购买股票手续费
		double sum = 0.0;// 购买股票数量
		if (str[0].equals("买入") || str[0].equals("补仓")) {
			sum = Integer.valueOf(str[2]);
			stockCost = sum * StockMath.valueOf(str[1]);
		}
		fee = Integer.valueOf(str[2]) * StockMath.valueOf(str[1])
				* (Double.valueOf(str[3]) + Double.valueOf(str[4]));
		dilution[0] += stockCost;
		dilution[1] += fee;
		dilution[2] += sum;
		return dilution;
	}

	// public String[][] getHoldStocks() {
	// return holdStocks;
	// }

	// 组织持仓情况显示信息
	public String[][] organizeHoldStock()  {
		List<String[]> list = new ArrayList<String[]>();
		String[][] stockStr;
		JSONObject jsonObj = stockSet.getStocksSets();
		Iterator<?> keys = jsonObj.keys();
		while (keys.hasNext()) {
			String code = keys.next().toString();
			String[] str = new String[9];

			try {
				JSONObject jso = jsonObj.getJSONObject(code);

				// if(jso == null){
				// return null;
				// }

				// 获取实时股票信息
				JSONObject jo = inTimeStock(code);
				// if(jo == null){
				// return null;
				// }

				// 持股数量
				int holdSum = jso.getInt("holdSum");
				// 当前价
				double curPrice = StockMath.valueOf(jo
						.getString("currentPrice"));
				// 昨收价
				double yPrice = StockMath.valueOf(jo
						.getString("yesterdayClosePrice"));
				// 涨跌=当前价-昨收,涨跌率=涨跌/昨收
				double risefall = curPrice - yPrice;
				double risefallRatio = risefall / yPrice;
				// 持有市值
				double holdMoney = holdSum * curPrice;
				// 总手续费
				double fee = jso.getDouble("fee");
				// 摊薄成本
				double dilu = jso.getDouble("dilutedCost");
				// 持仓成本
				double holdCost = jso.getDouble("holdCost");
				// 浮动盈亏=（当前价 - 持仓成本）*持有量 - 手续费
				double floatBE = (curPrice - holdCost)
						* holdSum
						* (1 - StockMath.valueOf("1‰") + StockMath
								.valueOf("0.3‰"));
				double flatBERatio = floatBE / holdMoney;

				// 盈亏（简化）= （现价*0.9955 - 成本*1.0035）*持有量
				// ，系数：0.0035和0.995分别是印花税。券商佣金、杂费的折合值
				double be = (curPrice * 0.9955 - holdCost * 1.0035) * holdSum;
				double beRatio = be / holdMoney;

				str[0] = code;// 股票代码
				str[1] = jo.getString("name");// 股票名字
				str[2] = jo.getString("currentPrice");// 股票当前价
				// 涨跌
				str[3] = StockMath.valueOf(risefall) + "("
						+ StockMath.doubleToPercent(risefallRatio) + ")";
				// 摊薄/持仓成本
				str[4] = StockMath.valueOf(dilu) + "/"
						+ StockMath.valueOf(holdCost);
				str[5] = String.valueOf(holdSum);// 持有量
				str[6] = StockMath.valueOf(holdMoney);// 持有市值
				// 浮动盈亏
				str[7] = StockMath.valueOf(floatBE) + "("
						+ StockMath.doubleToPercent(flatBERatio) + ")";
				// 盈亏
				str[8] = StockMath.valueOf(be) + "("
						+ StockMath.doubleToPercent(beRatio) + ")";
				list.add(str);
			} catch (JSONException e) {
				// 当底层出现JSonException就会在此处处理，有问题的股票会被清除而不加入持股构成中
				new JSONException(e.getMessage()).printStackTrace();
			}
			;
		}
		stockStr = new String[list.size()][];
		for (int i = 0; i < list.size(); ++i) {
			stockStr[i] = list.get(i);
		}

		return stockStr;
	}

	// 实时获取单支股票信息
	public JSONObject inTimeStock(String code) throws JSONException {

		GetInfoFromSina gifs = null;
		try {
			gifs = new GetInfoFromSina(code);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (RuntimeException e) {

		}
		JSONObject jo = new JSONObject();
		Thread td = new Thread(gifs);
		td.start();
		try {
			td.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			JSONObject j = gifs.getJsonObj();
			// if(j == null){
			// return null;
			// }
			// if(!j.has(code))
			// {
			// return null;
			// }
			jo = j.getJSONObject(code);
		

		return jo;
	}

	// public static void main(String[] argv){
	// String[][] str = null;
	// HoldStock hs = new HoldStock();
	// try {
	// // hs.countStockFromRecord();
	// str = hs.organizeHoldStock();
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// for(int i = 0; i < str.length; ++i){
	// for(int j = 0; j < str[i].length; ++j){
	// System.out.print(str[i][j] + "    ");
	// }
	// System.out.println();
	// }
	// }
}
