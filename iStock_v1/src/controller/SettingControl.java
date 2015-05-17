package controller;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class SettingControl {
	private JSONObject jo_AllSet;
	final private String FILEPATH = "data/set.json";
	final private String HISTORYPATH="data/record.json";

	public SettingControl() {
		// TODO Auto-generated constructor stub
		try {
			read();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			jo_AllSet=new JSONObject();
		}
	}

	public boolean getAutoHistory() {
		try {
			return jo_AllSet.getBoolean("history");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	public SettingControl setAutoHistory(boolean act) {
         try {
			jo_AllSet.put("history", act);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return this;
	}

	public void saveToLocal(){
		try {
			IORW.write(FILEPATH, jo_AllSet.toString());
			System.out.println("saving setting"+jo_AllSet.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void read() throws JSONException {
		jo_AllSet = new JSONObject(IORW.read(FILEPATH));
	}
	
	public void autoClearHistoryIfSetted(){
	    if(getAutoHistory()){
	    try {
			IORW.write(HISTORYPATH, "{}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	}
}
