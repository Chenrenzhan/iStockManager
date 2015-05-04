package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import models.Record;
import models.RecordsSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * 持股历史记录
 */
public class HoldRecord {

	private static final String FILEPATH = "data/holdrecord.json";

	private JSONObject holdRecord;
	private JSONObject recordSet;

	public HoldRecord() {

		RecordsSet rs;
		try {
			rs = new RecordsSet();
			recordSet = rs.getRecordsSet();
			read();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void read() throws JSONException {
		String jsonStr = IORW.read(FILEPATH);
		holdRecord = new JSONObject(jsonStr);
	}

	// 从股票操作记录里统计持股历史记录
	public void counHoldRecord() throws JSONException {
		List<String[]> list = new ArrayList<String[]>();

		JSONObject jsonObj = new JSONObject();

		Iterator<?> keys = recordSet.keys();
		while (keys.hasNext()) {
			String code = keys.next().toString();
			String[] str = new String[9];
			int holdSum = 0;

			JSONArray ja = recordSet.getJSONArray(code);
			
			String dateFlag = "";
			for (int i = ja.length() - 1; i >= 0; --i) {
				JSONObject jo = ja.getJSONObject(i);
				String type = jo.getString("type");
				int sum = jo.getInt("volumes");
				String date = jo.getString("date");
				// 计算持股数量
				if (type.equals("买入") || type.equals("补仓")) {
					holdSum += sum;
				} else {
					holdSum -= sum;
				}
				if (i > 0) {
					if (dateFlag.equals(date)) {
						if (dateFlag.equals(ja.getJSONObject(i - 1)
										.getString("date"))) {

							continue;
						} else {
							dateFlag = ja.getJSONObject(i - 1)
									.getString("date");
//							continue;
						}

					} else {
						if(dateFlag.isEmpty() && 
								(ja.getJSONObject(i).getString("date"))
								.equals(ja.getJSONObject(i - 1)
										.getString("date"))){
							dateFlag = ja.getJSONObject(i).getString("date");
							continue;
						}
							
						dateFlag = ja.getJSONObject(i - 1)
									.getString("date");
					}
				}
//				if(holdSum == 0)
//					continue;
				JSONObject js = new JSONObject();
				js.put("name", jo.getString("name"));
				js.put("holdSum", holdSum);
				
				
//				System.out.println(ja1.toString());
				try {
					jsonObj.append(date, js);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					
					JSONArray ja1 = jsonObj.getJSONArray(date);
					ja1.put(js);
					jsonObj.put(date, ja1);
//					e.printStackTrace();
				}
			}
			holdRecord.put("hold", jsonObj);

			Iterator<?> k = jsonObj.keys();
			List<String> l = (List<String>) copyIterator(k);
//			Collections.sort(l, new Comparator<Object>() {
//
//				@Override
//				public int compare(Object o1, Object o2) {
//					// TODO Auto-generated method stub
//					return ((String) o1).compareTo((String) o2);
//				}
//
//			});
			JSONArray j = new JSONArray(l);
			holdRecord.put("date", j);
			
		}
		System.out.println("holdRecord1       " + holdRecord.toString());
		holdRecord = check(holdRecord);
		System.out.println("holdRecord2       " + holdRecord.toString());
		removeEmpty();
		System.out.println("holdRecord3       " + holdRecord.toString());
	}
	
	public JSONObject check(JSONObject jsonObj) throws JSONException{
		JSONObject hold = jsonObj.getJSONObject("hold");
		JSONArray date = jsonObj.getJSONArray("date");
		
//		JSONObject hold2 = new JSONObject();
		
		int len = date.length();
		
		String dStr = date.getString(0);
		JSONArray ja = hold.getJSONArray(dStr);
//		hold2.put(dStr, ja);
			
		for(int i = 1; i < len; ++i){
			String s1 = date.getString(i-1);
			String s2 = date.getString(i);
			
			JSONArray ja1 = hold.getJSONArray(s1);
			JSONArray ja2 = hold.getJSONArray(s2);
			
			for(int j1 = 0; j1 < ja1.length(); ++j1){
				
				Boolean flag = false;
				JSONObject jo1 = ja1.getJSONObject(j1);
				
				for(int j2 = 0; j2 < ja2.length(); ++j2){
					JSONObject jo2 = ja2.getJSONObject(j2);
					if(jo1.getString("name")
							.equals(jo2.getString("name"))){
						flag = true;
						break;
					}
						
				}
				if(flag == false){
					ja2.put(jo1);
				}
				
//				ja1 = removeEmpty(ja1);
			}
			System.out.println("eeee    " + i);
//			ja1 = removeEmpty(ja1);
		}
		
		JSONObject jo = new JSONObject();
		jo.put("hold", hold);
		jo.put("date", date);
		return jo;
	}
	
	
	public void removeEmpty() 
			throws JSONException{
//		JSONObject nJo = new JSONObject();
		
		
		JSONObject hold = holdRecord.getJSONObject("hold");
		JSONArray date = holdRecord.getJSONArray("date");
		for(int i = 0; i < date.length(); ++i){
			String dStr = date.getString(i);
			JSONArray ja = hold.getJSONArray(dStr);
			JSONArray nja = new JSONArray();
			for(int j = 0; j < ja.length(); ++j){
				JSONObject jo = ja.getJSONObject(j);
				if(jo.getInt("holdSum") != 0){
					nja.put(jo);
				}
			}
			hold.put(dStr, nja);
		}
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

	public void save() throws IOException {
		IORW.write(FILEPATH, holdRecord.toString());
	}
	
	public JSONObject getHoldRecord() {
		return holdRecord;
	}

	public void setHoldRecord(JSONObject holdRecord) {
		this.holdRecord = holdRecord;
	}

	public static void main(String[] argv){

		HoldRecord hr = new HoldRecord();
		try {
			hr.counHoldRecord();
			hr.save();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		List<String> l = new ArrayList<String>();
//		String[] s = new String[]{"15-3-10","15-3-11","15-3-12","15-3-13","15-3-5","15-3-9"};
//		for( String a : s){
//			l.add(a);
//		}
//		
//		
//		Collections.sort(l, new Comparator<String>() {
//
//			@Override
//			public int compare(String o1, String o2) {
//				// TODO Auto-generated method stub
//				System.out.println(o1 + "   " + o2 + "   lllll   " 
//				+ ((String) o1).compareTo((String) o2));
//				return ((String) o1).compareTo((String) o2);
//			}
//
//		});
//		
//		for(String a : l){
//			System.out.println(a);
//		}
//		System.out.println("15-3-05".compareTo("15-3-13"));
	}
}
