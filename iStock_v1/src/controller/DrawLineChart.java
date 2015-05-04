package controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DrawLineChart {
	private JSONObject profitData;
	private JSONObject hold;
	private JSONArray date;
	
	public DrawLineChart(){
//		RecordsSet rs = new RecordsSet();
//		recordSet = rs.getRecordsSet();
		ProfitData pd = new ProfitData();
		profitData = pd.getProfitData();
		
		try {
			hold = profitData.getJSONObject("hold");
			date = profitData.getJSONArray("date");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Double> oneMonth(){
		String[] timeseries = TimeSeries.oneMonth();
		try {
			return data(timeseries);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Double> threeMonth(){
		String[] timeseries = TimeSeries.threeMonth();
		try {
			return data(timeseries);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Double> sixMonth(){
		String[] timeseries = TimeSeries.sixMonth();
		try {
			return data(timeseries);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private List<Double> data(String[] timeseries) 
			throws JSONException, InterruptedException{
		List<Double> list = new ArrayList<Double>();
		int i = 0;
		int j = 0;
		for( ; i < timeseries.length; ++i){
			String dStr;
//			if(j < date.length()){
//				dStr = (String) date.get(j);
//			}
			dStr = (String) date.get(j);
			
			if(dStr.compareTo(timeseries[i]) == 0){
//				list.add(hold.getJSONArray(dStr));
				JSONArray ja = hold.getJSONArray(dStr);
				list.add(countRatio(ja, dStr));
			}
			else if(dStr.compareTo(timeseries[i]) > 0){
				if(j == 0)
					list.add(0.0);
				else{
					String s = (String) date.get(j-1);
//					list.add(hold.getJSONArray(s));
					JSONArray ja = hold.getJSONArray(s);
					list.add(countRatio(ja, dStr));
				}
					
			}
			else{
				++j;
				--i;
			}
			
			
			
			if(j >= date.length()){
				break;
			}
		}
		String s = (String) date.get((date.length()-1));
		for( ++i; i < timeseries.length; ++i){
//			list.add(hold.getJSONArray(s));
			String ss = timeseries[i];
			JSONArray ja = hold.getJSONArray(s);
			list.add(countRatio(ja, ss));
		}
		
		return list;
//		for(String s1 : timeseries)
//			System.out.println(s1);
//		for(JSONArray ja : list){
//			if(ja != null)
//				System.out.println(ja.toString());
//			else
//				System.out.println("null");
//		}
	}

	//计算收益率
	public double countRatio(JSONArray ja, String dStr) 
			throws InterruptedException, JSONException{
		double ratio = 0.0;// 收益率
		double m = 0.0;
		double d = 0.0;
		System.out.println(ja.toString());
		for(int i = 0; i < ja.length(); ++i){
			//获取股票历史收盘价
//			String date = ((JSONObject)ja.get(i)).getString("date");
			String code = ((JSONObject)ja.get(i)).getString("code");
//			String end = ((JSONObject)ja.get(ja.length()-1)).getString("date");
			
			GetStockHistory gsh = new GetStockHistory(code, dStr, dStr);
			JSONObject jo = gsh.getClosePrice();
			double close = jo.getDouble(dStr);
			
			double profit = ((JSONObject)ja.get(i)).getDouble("profit");
			double cost = ((JSONObject)ja.get(i)).getDouble("cost");
			Double holdSum = (double) ((JSONObject)ja.get(i)).getInt("holdSum");
			
			m += profit + (close - cost) * holdSum;
			d += cost * holdSum;
		}
		
		ratio = m / d;
		return ratio;
	}
	
	public JSONObject getHoldRecord() {
		return profitData;
	}

	public void setHoldRecord(JSONObject holdRecord) {
		this.profitData = holdRecord;
	}

	public static void main(String[] argv){
		DrawLineChart dlc = new DrawLineChart();
		JSONObject jo = dlc.getHoldRecord();
//		System.out.println(jo.toString());
		
		dlc.oneMonth();
	}
}
