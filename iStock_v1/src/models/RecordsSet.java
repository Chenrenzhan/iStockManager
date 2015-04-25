package models;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controller.IORW;

/*
 * 股票历史记录的集合
 */

public class RecordsSet {

	private final String FILEPATH = "data/record.json";
	
	private JSONObject recordsJsonObj;
	
	public RecordsSet() throws JSONException{
		read();
	}
	
	public RecordsSet(String[][] strArray) throws JSONException{
		read();
		
		int row = strArray.length;
		int col = strArray.length;
		for(int i = 1; i < row; ++i){
			String code = strArray[i][1];
			Record record = new Record(strArray[i]);
//			recordsJsonObj.put(code, record);
			addRecord(record);
		}
	}
	
	private void read() throws JSONException{
		String jsonStr = IORW.read(FILEPATH);
		recordsJsonObj = new JSONObject(jsonStr);
	}
	
	//添加一条记录
	public Boolean addRecord(Record record) 
			throws JSONException{
		JSONArray array;
		String code = record.getString("code");
		if(recordsJsonObj.has(code)){
			array = recordsJsonObj.getJSONArray(code);
			array.put(record);
//			array.append(recordJsonObj);
		}
		else{
			array = new JSONArray();
			array.put(record);
			recordsJsonObj.putOpt(code, array);
		}
		return true;
	}
	
	//删除一条记录
	public Boolean removeRecord(String code, int position) 
			throws JSONException{
		JSONArray array;
		array = recordsJsonObj.getJSONArray(code);
		if(array == null){
			return false;
		}
		if(array.isNull(position)){
			return false;
		} else {
			array = RemoveJSONArray(array, position);
			return true;
		}
	}
	
	public Boolean removeRecord(Record jsonObj) 
			throws JSONException{
		String code = jsonObj.getString("code");
		JSONArray array;
		array = recordsJsonObj.getJSONArray(code);
		if(array == null){
			return false;
		}
		JSONArray Njarray = new JSONArray();
		Boolean flag = true;
		for (int i = 0; i < array.length(); i++) {
			if (!array.get(i).equals(jsonObj))
				Njarray.put(array.get(i));
			else
				flag = false;
		}
		array = Njarray;
		if(flag)
			return true;
		else
			return false;
	}
	
	public Boolean setRecord(Record jsonObj, int position)
			throws JSONException{
		String code = jsonObj.getString("code");
		JSONArray array;
		array = recordsJsonObj.getJSONArray(code);
		if(array == null){
			return false;
		}
		if(array.isNull(position)){
			return false;
		} else {
			((Record)array.optJSONObject(position)).setRecord(jsonObj);
			return true;
		}
	}

	// 删除pos位置的JSONArray
	public static JSONArray RemoveJSONArray(JSONArray jarray, int pos) {
		JSONArray Njarray = new JSONArray();
		try {
			for (int i = 0; i < jarray.length(); i++) {
				if (i != pos)
					Njarray.put(jarray.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Njarray;
	}

	public void save() throws IOException{
		IORW.write(FILEPATH, recordsJsonObj.toString());
	}

	public JSONObject getRecordsSet(){
		return recordsJsonObj;
	}
	
	public String[][] jsonToStringArray() throws JSONException {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		int len = recordsJsonObj.length();
		
		int len = 0;
		Iterator<?> keys1 = recordsJsonObj.keys();
		//计数记录数目
		while (keys1.hasNext()) {
			String key = (String) keys1.next().toString();
			JSONArray ja = recordsJsonObj.getJSONArray(key);
			len += ja.length();
			len += 1;
		}
		
		String[][] strArray = new String[len][11];
		
		int row = 0;
		Iterator<?> keys2 = recordsJsonObj.keys();
		while (keys2.hasNext()) {// 遍历JSONObject
			String key = (String) keys2.next().toString();
			JSONArray ja = recordsJsonObj.getJSONArray(key);
			
			// 遍历数组
			for (int i = 0; i < ja.length(); i++) {
				JSONObject rd = (JSONObject) ja.get(i);
				strArray[row][0] = rd.getString("name");
				strArray[row][1] = rd.getString("code");
				strArray[row][2] = rd.getString("date");
				strArray[row][3] = rd.getString("type");
				strArray[row][4] = String.valueOf(rd.getDouble("price"));
				strArray[row][5] = String.valueOf(rd.getInt("volumes"));
				strArray[row][6] = 
						transDoubleToPercent(rd.getDouble("taxes"));
				strArray[row][7] = 
						transDoubleToPercent(rd.getDouble("commission"));
				strArray[row][8] = rd.getString("state");
				strArray[row][9] = rd.getString("remark");
				strArray[row][10] = rd.getString("handle");
				
				++row;
			}
			strArray[row] = new String[]{};
			++row;
		}
		return strArray;
	}

	public String transDoubleToPercent(double d) {
		return (d * 1000 + "‰");
	}

}
