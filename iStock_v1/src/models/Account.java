package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import controller.IORW;
import controller.StockMath;

public class Account {
	private final static String FILEPATH = "data/accounts.json";
	private JSONObject accountJsonObject;

	public Account() {
		accountJsonObject = toJsonObject(IORW.read(FILEPATH));
	}

	public ArrayList<String> getAccounts() {
		int len = 0;
		ArrayList<String> strArray = new ArrayList<String>();
		Iterator<?> keys1 = accountJsonObject.keys();
		// 计数记录数目
		while (keys1.hasNext()) {
			String key = (String) keys1.next().toString();
			strArray.add(key);

		}
		System.out.println(len);
		
//		int row = 0;
//		Iterator<?> keys2 = accountJsonObject.keys();
//		while (keys2.hasNext()) {// 遍历JSONObject
//			String key = (String) keys2.next().toString();
//			JSONArray ja = accountJsonObject.getJSONArray(key);
//
//			// 遍历数组
//			for (int i = 0; i < ja.length(); i++) {
//				JSONObject rd = (JSONObject) ja.get(i);
//				// for(int j = 1; j < KEYS.length; ++j){System.out.println(j);
//				// strArray[row++][j] = rd.getString(KEYS[j]);
//				// }
//				strArray[row][0] = rd.getString("name");
//				++row;
//			}
//			
//		}
		return strArray;

	}

	public void addAccount(String acc) {
		try {
			accountJsonObject.put(acc, "");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		save();
	}

	public void deleteAccount(String acc) {
		accountJsonObject.remove(acc);
		save();
	}

	public void save(){
		try {
			IORW.write(FILEPATH, accountJsonObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private JSONObject toJsonObject(String jsonStr) {
		JSONObject accJO;
		if (jsonStr == null) {
			jsonStr = "{}";

		}
		try {
			accJO = new JSONObject(jsonStr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("Accounts.json is null");
			accJO = new JSONObject();
		}
		return accJO;
	}
}
