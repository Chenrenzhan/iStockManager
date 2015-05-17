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
	
	private HoldRecord hr;
	
	public DrawStackedChart(){
//		RecordsSet rs = new RecordsSet();
//		recordSet = rs.getRecordsSet();
		hr = new HoldRecord();
		System.out.println("   ddd   " + hr.getHoldRecord().toString());
//		hr.update();
//		holdRecord = hr.getHoldRecord();
		update();
		System.out.println("   ddd   " + hr.getHoldRecord().toString());
		
		if(hr.isDataEmpty()){
			return ;
		}
		
		try {
			hold = holdRecord.getJSONObject("hold");
			date = holdRecord.getJSONArray("date");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean isDataEmpty(){
		
		if(holdRecord == null){
			return true;
		}
		String str = holdRecord.toString();
		System.out.println(str);
		if(str.equals("{}")){
			return true;
		}
		return false;
	}
	
	public void update() {
		System.out.println("q1111qqq");
//		if(hr.isDataEmpty()){
//			System.out.println("qqqqqqqqq");
//			return ;
//		}
			
		
		try {
			hr.update();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		holdRecord = hr.getHoldRecord();
	}
	
	public List<JSONArray> oneMonth(){
		if(isDataEmpty()){
			return null;
		}
		
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
		if(isDataEmpty()){
			return null;
		}
		
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
		if(isDataEmpty()){
			return null;
		}
		
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
		
		System.out.println("hold   " + hold.toString());
		int i = 0;
		int j = 0;
		for( ; i < timeseries.length; ++i){
			String dStr;
//			if(j < date.length()){
//				dStr = (String) date.get(j);
//			}
			dStr = (String) date.get(j);
			System.out.println("dStr  " + dStr + "   timeseries[i]    " + timeseries[i] 
					+ "  dd " + dStr.equals(timeseries[i]));
			if(dStr.equals(timeseries[i])){
				list.add(hold.getJSONArray(dStr));
				System.out.println("hhhhhhhhh    " + hold.getJSONArray(dStr).toString());
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
		
		System.out.println("list    " + list.size());
		for(JSONArray ja : list){
			System.out.println("sasasa             " + ja.toString());
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
