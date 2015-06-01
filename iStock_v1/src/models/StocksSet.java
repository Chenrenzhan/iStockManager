package models;

import java.io.IOException;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

import controller.GetSingleStock;
import controller.GetSingleStockHistory;
import controller.IORW;

public class StocksSet {
	private final String ROOTPATH = "data/";
	private final String ACCOUNTNAME;
	private final String FILENAME = "/stock.json";
	private JSONObject stocksJsonObj;

	public StocksSet(String account) throws JSONException {
		ACCOUNTNAME=account;
		String jsonStr = IORW.read(ROOTPATH+ACCOUNTNAME+FILENAME);
		stocksJsonObj = new JSONObject(jsonStr);
		
	}

	// 添加一个股票
	public void addRecord(Stock stockJsonObj) throws JSONException {
		String code = stockJsonObj.getString("code");
		if (stocksJsonObj.has(code)) {
			removeStock(code);
		}
		stocksJsonObj.put(code, stockJsonObj);
	}

	public static String getStockType(String code) throws IOException {
		if (code.contains("sz")|code.contains("sh"))
			return code;
		 else {
			GetSingleStock gss = new GetSingleStock(code);//这个函数会自动尝试sh或sz并去除无数据返回的分支
			Thread td1 = new Thread(gss);
			td1.start();
			try {
				td1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String _code = gss.getJsonObj().getString("code");
				System.out.println("getStockType:"+_code);
				return _code;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			throw new IOException("haven't such stock in the internet ");
		}
	}

	// 删除一个股票
	public void removeStock(String code) throws JSONException {
		stocksJsonObj.remove(code);
	}

	public void removeStock(Stock jsonObj) throws JSONException {
		String code = jsonObj.getString("code");
		removeStock(code);
	}

	public JSONObject getStocksSets() {
		return stocksJsonObj;
	}
	
	public void setStocksSets(JSONObject jo) {
		stocksJsonObj = jo;
	}
	
	public void resetStocksSets() {
		stocksJsonObj = null;
		stocksJsonObj = new JSONObject();
	}

	public void save() throws IOException {
		IORW.write(ROOTPATH+ACCOUNTNAME+FILENAME, stocksJsonObj.toString());
	}
}
