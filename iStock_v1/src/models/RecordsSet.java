package models;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controller.IORW;
import controller.StockMath;

/*
 * 股票历史记录的集合
 */

public class RecordsSet {

	private final String ROOTPATH = "data/";
	private final String ACCOUNTNAME;
	private final String FILENAME = "/record.json";
	private static final String[] KEYS = new String[] { 
			"name", "code", "date",
			"type", "price", "volumes", "taxes", "commission", "state",
			"remark", "handle" };

	private JSONObject recordsJsonObj;

	public RecordsSet(String account) throws JSONException {
//		System.out.println("RecordSet:"+"before read()");
		ACCOUNTNAME=account;
		read();

	}

	public RecordsSet(String account,String[][] strArray) throws JSONException {
		read();
		ACCOUNTNAME=account;
		int row = strArray.length;
		int col = strArray.length;
		for (int i = 1; i < row; ++i) {
			String code = strArray[i][1];

			Record record = new Record(strArray[i]);
//			System.out.println("Recordset:"+record.getRecord().toString());
			// recordsJsonObj.put(code, record);
			addRecord(record);
		}
		save();
	}

	public Boolean isDataEmpty(){
		String str = recordsJsonObj.toString();
		if(str.equals("{}")){
			return true;
		}
		if(recordsJsonObj == null){
			return true;
		}
		return false;
	}
	
	private void read() throws JSONException {
		String jsonStr = IORW.read(ROOTPATH+ACCOUNTNAME+FILENAME);
//		System.out.println("read->"+ROOTPATH+ACCOUNTNAME+FILENAME+":"+jsonStr);
		if(jsonStr==null){
			jsonStr="{}";
	
		}
		try{recordsJsonObj = new JSONObject(jsonStr);
		
//			System.out.println("null recordset");
		
		}catch(JSONException e){
			System.out.println("Record.json is null");	
		}
	}

	// 添加一条记录
	public Boolean addRecord(JSONObject jo) throws JSONException {
		JSONArray array;
		String code = jo.getString("code");
		if (recordsJsonObj.has(code)) {
			array = recordsJsonObj.getJSONArray(code);
			//按时间升序插入该记录
			int i = 0;
			for (; i < array.length(); i++) {
				System.out.println("sorting");
				System.out.println(array.getJSONObject(i).getString("date"));
				System.out.println(jo.getString("date"));
				if(array.getJSONObject(i).getString("date").compareTo(jo.getString("date"))<0){
					for (int j = array.length(); j > i; j--) {
						array.put(j,array.get(j-1));
						
					}
					array.put(i,jo);
					
					break;
					
				}
			}
			if (i==array.length()) {
				array.put(jo);
			}
			
			// array.append(recordJsonObj);
		} else {
			array = new JSONArray();
			array.put(jo);
			
		}
		recordsJsonObj.putOpt(code, array);
		return true;

	}

	// 删除一条记录
	public Boolean removeRecord(String code, int position) throws JSONException {
		JSONArray array;
		array = recordsJsonObj.getJSONArray(code);
		if (array == null) {
			return false;
		}
		if (array.isNull(position)) {
			return false;
		} else {
			array = removeJSONArray(array, position);
			recordsJsonObj.put(code, array);
			return true;
		}
	}

	public Boolean removeRecord(JSONObject jsonObj) throws JSONException {
		String code = jsonObj.getString("code");
		JSONArray array;
		array = recordsJsonObj.getJSONArray(code);

		if (array == null) {
			return false;
		}
		JSONArray Njarray = new JSONArray();
		Boolean flag = true;
		for (int i = 0; i < array.length(); i++) {

			if (!array.get(i).equals(jsonObj))
				Njarray.put(array.get(i));
			else {
//				flag = false;//返回值可能出错了，不应该修改flag
				System.out.println("delete:   " + array.get(i).equals(jsonObj)+"array.length:"+array.length());
			}
		}
		// array = Njarray;
		recordsJsonObj.put(code, Njarray);
		if (flag)
			return true;
		else
			return false;
	}

	public Boolean setRecord(Record jsonObj, int position) throws JSONException {
		String code = jsonObj.getString("code");
		JSONArray array;
		array = recordsJsonObj.getJSONArray(code);
		if (array == null) {
			return false;
		}
		if (array.isNull(position)) {
			return false;
		} else {
			((Record) array.optJSONObject(position)).setRecord(jsonObj);
			return true;
		}
	}

	// 删除pos位置的JSONArray
	public static JSONArray removeJSONArray(JSONArray jarray, int pos) {
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
	
	//删除一支股票，即把改股票的所有记录都删掉
	public void removeOneStock(String code){
		recordsJsonObj.remove(code);

			save();
	
	}

	public void save(){
//		System.out.println("dd");
		try {
//			System.out.println(ROOTPATH+ACCOUNTNAME+FILENAME);
//			System.out.println("save:"+recordsJsonObj.toString());
			IORW.write(ROOTPATH+ACCOUNTNAME+FILENAME, recordsJsonObj.toString());
//	        System.out.println("save_exist"+new File(ROOTPATH+ACCOUNTNAME+FILENAME).exists());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public JSONObject getRecordsSet() {
		if(recordsJsonObj==null)
			recordsJsonObj =new JSONObject();
		return recordsJsonObj;
	}

	public String[][] jsonToStringArray() throws JSONException {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// int len = recordsJsonObj.length();

		int len = 0;
		Iterator<?> keys1 = recordsJsonObj.keys();
		// 计数记录数目
		while (keys1.hasNext()) {
			String key = (String) keys1.next().toString();
			JSONArray ja = recordsJsonObj.getJSONArray(key);
			len += ja.length();
			len += 1;
		}
		System.out.println(len);
		String[][] strArray = new String[len][11];

		int row = 0;
		Iterator<?> keys2 = recordsJsonObj.keys();
		while (keys2.hasNext()) {// 遍历JSONObject
			String key = (String) keys2.next().toString();
			JSONArray ja = recordsJsonObj.getJSONArray(key);

			// 遍历数组
			for (int i = 0; i < ja.length(); i++) {
				JSONObject rd = (JSONObject) ja.get(i);
				// for(int j = 1; j < KEYS.length; ++j){System.out.println(j);
				// strArray[row++][j] = rd.getString(KEYS[j]);
				// }
				strArray[row][0] = rd.getString("name");
				strArray[row][1] = rd.getString("code");
				strArray[row][2] = rd.getString("date");
				strArray[row][3] = rd.getString("type");
				strArray[row][4] = String.valueOf(rd.getDouble("price"));
				strArray[row][5] = String.valueOf(rd.getInt("volumes"));
				strArray[row][6] = StockMath.doubleToMilli(rd
						.getDouble("taxes"));
				strArray[row][7] = StockMath.doubleToMilli(rd
						.getDouble("commission"));
				strArray[row][8] = rd.getString("state");
				strArray[row][9] = rd.getString("remark");
				strArray[row][10] = rd.getString("handle");

				++row;
			}
			strArray[row] = new String[] {};
			++row;
		}
		return strArray;
	}

	

	// public String transDoubleToPercent(double d) {
	// return (d * 1000 + "‰");
	// }

}
