package controller;

/*
 * 处理收益率的数据
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import models.RecordsSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfitData {
	private static final String FILEPATH = "data/profit.json";
	
	private JSONObject recordSet;
	private JSONObject profitData;
	
	public ProfitData(){
		
		String str = IORW.read(FILEPATH);
		try {
			profitData = new JSONObject(str);
			RecordsSet rs = new RecordsSet();
			recordSet = rs.getRecordsSet();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save(){
		try {
			IORW.write(FILEPATH, profitData.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(){
		try {
			profitData = countInDate(countRecord());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		save();
	}
	
//	private void read() throws JSONException{
//	
//		RecordsSet rs = new RecordsSet();
//		recordSet = rs.getRecordsSet();
//	}
	
	public JSONObject getClosePrice(String code , String start, String end){
		GetStockHistory gsh = new GetStockHistory(code, start, end);
		try {
			return gsh.getClosePrice();
//			System.out.println(" kkk   "  );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONObject countRecord() throws JSONException{
		JSONObject nj = new JSONObject();
		Iterator<?> keys = recordSet.keys();
		
		while(keys.hasNext()){
			String code = keys.next().toString();
			JSONArray ja = recordSet.getJSONArray(code);
			JSONArray nja = new JSONArray();
			
			//获取股票历史收盘价
//			String start = ((JSONObject)ja.get(0)).getString("date");
//			String end = ((JSONObject)ja.get(ja.length()-1)).getString("date");
//			JSONObject jh = getClosePrice(code, start, end);
			
			int holdSum = 0;//持有量
			double profit = 0.0;//收益
			double fee = 0.0; //费用
			double cost = 0.0; //股票成本价
			
			for(int i = ja.length() - 1; i >= 0; --i){
//				JSONObject jsub = new JSONObject();
				JSONObject jo = ja.getJSONObject(i);
				JSONObject njo = new JSONObject();
				
				String date = jo.getString("date");
//				double close = Double.valueOf(jh.getString(date));
				
				String type = jo.getString("type");
				int volumes = jo.getInt("volumes");
				double price = jo.getDouble("price");
				double taxes = jo.getDouble("taxes");
				double commission = jo.getDouble("commission");
				
				fee += volumes * price * (taxes + commission);
				if(type.equals("买入") || type.equals("补仓")){
					holdSum += volumes;
					cost += (price * volumes + fee) / volumes;
				}
				else{
					holdSum -= volumes;
					profit += (price - cost) * volumes + fee;
					cost -= profit;
					
				}
				
				if(i > 0){
					if(date.equals(ja.getJSONObject(i-1).getString("date")))
						continue;
				}
				
//				njo.put("name", jo.getString("name"));
//				njo.put("code", jo.getString("code"));
				njo.put("date", date);
				njo.put("profit", profit);
				njo.put("fee", fee);
				njo.put("holdSum", holdSum);
//				njo.put("close", close);
				njo.put("cost", cost);
				
				nja.put(njo);
			}
			nj.put(code,  nja);
		}
		return nj;
	}
	
	//把数据根据时间整理
	public JSONObject countInDate(JSONObject jsonObj) throws JSONException{
		JSONObject njsonObj = new JSONObject();
		
		Iterator<?> keys = jsonObj.keys();
		while(keys.hasNext()){
			String code = keys.next().toString();
			JSONArray ja = jsonObj.getJSONArray(code);
			
			for(int i = ja.length()-1; i >= 0; --i){
				JSONObject njo = new JSONObject();
				JSONObject jo = ja.getJSONObject(i);
				String date = jo.getString("date");
				jo.remove("date");
				njo = jo;
				njo.put("code", code);
				try {
					njsonObj.append(date, njo);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					
					JSONArray ja1 = njsonObj.getJSONArray(date);
					ja1.put(njo);
					njsonObj.put(date, ja1);
//					e.printStackTrace();
				}
			}
		}
		Iterator<?> ks = njsonObj.keys();
		List<String> list = (List<String>) copyIterator(ks);
		JSONArray j = new JSONArray(list);
		
		JSONObject rjo = new JSONObject();
		rjo.put("hold", njsonObj);
		rjo.put("date", j);
		System.out.println(rjo.toString());
		return rjo;
	}
	
	public static <T> List<T> copyIterator(Iterator<T> iter) {
		List<T> copy = new ArrayList<T>();
		while (iter.hasNext())
			copy.add(iter.next());

		Collections.sort(copy, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return ((String) o1).compareTo((String) o2);
			}

		});

		return copy;
	}
	
	
	public JSONObject getProfitData() {
		return profitData;
	}

	public void setProfitData(JSONObject profitData) {
		this.profitData = profitData;
	}

	public static void main(String[] argv){
		ProfitData dlc = new ProfitData();
		dlc.update();
	}
	
}
