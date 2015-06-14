package models;

import java.io.File;
import java.io.FileNotFoundException;
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
	private final static String TEMPFILEPATH = "data/";
//	private JSONObject accountJsonObject;
	private JSONArray accountArray;

	// private ArrayList<String> accountList;

	public Account() {
		String str = IORW.read(FILEPATH);
		str = empty(str);
		try {
			accountArray = new JSONArray(str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			accountArray = new JSONArray();
		}
	}

	private String empty(String str) {
		if (str.isEmpty() || str.equals("{}")) {
			str = "[]";
		}
		return str;
	}

	public ArrayList<String> getAccounts() {
		int len = accountArray.length();
		ArrayList<String> accountList = new ArrayList<String>();
		for (int i = 0; i < len; ++i) {
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

	public ArrayList<String> addAccount(String acc) {
		accountArray.put(acc);
		save();
		
		return getAccounts();
	}

	public ArrayList<String> deleteAccount(String acc) {
		JSONArray ja = new JSONArray();
		int len = accountArray.length();
		for (int i = 0; i < len; ++i) {
			String account = "";
			try {
				account = (String) accountArray.get(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (account.equals(acc)) {
				continue;
			} else {
				ja.put(account);
			}
		}
		accountArray = null;
		accountArray = ja;
		save();
		deleteAccountHistory(acc);
		
		return getAccounts();
	}
	
	public void deleteAllAccount(){
//		accountArray = null;
//		accountArray = new JSONArray();
//		save();
		
		try {
			IORW.write(FILEPATH, "[]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除用户记录文件
	 */
	public void deleteAccountHistory(String account) {
		try {
			deletefile(TEMPFILEPATH+account);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("delete false");
		}
	

	}
	public void deleteAllAccountHistory(){
		try {
			deletefile(TEMPFILEPATH);
			accountArray=new JSONArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	 public static boolean deletefile(String delpath) throws Exception {  
		  try {  
		  
		   File file = new File(delpath);  
		   // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true  
		   if (!file.isDirectory()) {  
		    file.delete();  
		   } else if (file.isDirectory()) {  
		    String[] filelist = file.list();  
		    for (int i = 0; i < filelist.length; i++) {  
		     File delfile = new File(delpath + "\\" + filelist[i]);  
		     if (!delfile.isDirectory()) {  
		      delfile.delete();  
//		      System.out  
//		        .println(delfile.getAbsolutePath() + "删除文件成功");  
		     } else if (delfile.isDirectory()) {  
		      deletefile(delpath + "\\" + filelist[i]);  
		     }  
		    }  
//		    System.out.println(file.getAbsolutePath()+"删除成功");  
		    file.delete();  
		   }  
		  
		  } catch (FileNotFoundException e) {  
//		   System.out.println("deletefile() Exception:" + e.getMessage());  
		  }  
		  return true;  
		 }  
	public void save() {
		try {
			IORW.write(FILEPATH, accountArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Account().deleteAccountHistory("华发证券");
	}
}
