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
	private JSONArray accountArray;
//	private ArrayList<String> accountList;

	public Account() {
		String str = IORW.read(FILEPATH);
		str = empty(str);
		try {
			accountArray = new JSONArray(str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String empty(String str){
		if(str.isEmpty() || str.equals("{}")){
			str = "[]";
		}
		return str;
	}
	
	public ArrayList<String> getAccounts() {
		int len = accountArray.length();
		ArrayList<String> accountList = new ArrayList<String>();
		for(int i = 0; i < len; ++i){
			String name;
			try {
				name = accountArray.getString(i);
				accountList.add(name);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return accountList;

	}

	public void addAccount(String acc) {
		accountArray.put(acc);
		save();
	}

	public void deleteAccount(String acc) {
		JSONArray ja = new JSONArray();
		int len = accountArray.length();
		for(int i = 0; i < len; ++i){
			String account = "";
			try {
				account = (String) accountArray.get(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(account.equals(acc)){
				continue;
			}
			else{
				ja.put(account);
			}
		}
		accountArray = null;
		accountArray = ja;
		save();
	}

	public void save(){
		try {
			IORW.write(FILEPATH, accountArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	private JSONObject toJsonObject(String jsonStr) {
//		JSONArray accJO;
//		if (jsonStr == null) {
//			jsonStr = "[]";
//
//		}
//		try {
//			accJO = new JSONObject(jsonStr);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Accounts.json is null");
//			accJO = new JSONObject();
//		}
//		return accJO;
//	}
}
