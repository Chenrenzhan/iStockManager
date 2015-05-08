package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import models.RecordsSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class DrawStackedChart {
	
//	private JSONObject recordSet; 
	
	private JSONObject holdRecord;
	private JSONObject hold;
	private JSONArray date;
	
	public DrawStackedChart(){
//		RecordsSet rs = new RecordsSet();
//		recordSet = rs.getRecordsSet();
		HoldRecord hr = new HoldRecord();
		holdRecord = hr.getHoldRecord();
		
		try {
			hold = holdRecord.getJSONObject("hold");
			date = holdRecord.getJSONArray("date");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<JSONArray> oneMonth(){
		String[] timeseries = TimeSeries.oneMonth();
		try {
			return data(timeseries);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<JSONArray> threeMonth(){
		String[] timeseries = TimeSeries.threeMonth();
		try {
			return data(timeseries);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<JSONArray> sixMonth(){
		String[] timeseries = TimeSeries.sixMonth();
		try {
			return data(timeseries);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private List<JSONArray> data(String[] timeseries) throws JSONException{
		List<JSONArray> list = new ArrayList<JSONArray>();
		int i = 0;
		int j = 0;
		for( ; i < timeseries.length; ++i){
			String dStr;
//			if(j < date.length()){
//				dStr = (String) date.get(j);
//			}
			dStr = (String) date.get(j);
//			System.out.println("dStr  " + dStr + "   timeseries[i]    " + timeseries[i]);
			if(dStr.compareTo(timeseries[i]) == 0){
				list.add(hold.getJSONArray(dStr));
			}
			else if(dStr.compareTo(timeseries[i]) > 0){
				if(j == 0)
					list.add(null);
				else{
					String s = (String) date.get(j-1);
					list.add(hold.getJSONArray(s));
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
			list.add(hold.getJSONArray(s));
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

	public JSONObject getHoldRecord() {
		return holdRecord;
	}

	public void setHoldRecord(JSONObject holdRecord) {
		this.holdRecord = holdRecord;
	}

	public static void main(String[] argv){
		DrawStackedChart dsc = new DrawStackedChart();
		JSONObject jo = dsc.getHoldRecord();
//		System.out.println(jo.toString());
		
		dsc.oneMonth();
	}
}