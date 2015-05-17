package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DrawLineChart {
	private static final String FILE = "data/profit_linechart.json";
	public static final SimpleDateFormat 
		DF = new SimpleDateFormat("yyyy-MM-dd");
	
	private static final int ONEMONTH = 1;
	private static final int THREEMONTH = 2;
	private static final int SIXMONTH = 3;
	
	private JSONObject profitLC;//存在本地的收益率
	private JSONObject profitData;
	private JSONObject hold;
	private JSONArray date;
	
	private ProfitData pd;
	
	public DrawLineChart(){
		String str = IORW.read(FILE);
		System.out.println("read  " + str);
		
		pd = new ProfitData();
		pd.update();
		profitData = pd.getProfitData();
		
		
		try {
			profitLC = new JSONObject(str);
			hold = profitData.getJSONObject("hold");
			date = profitData.getJSONArray("date");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			jsonData();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(){
		pd.update();
		profitData = pd.getProfitData();
	}
	
	public List<Double> oneMonth(){
		List<Double> list = null;
		try {
			JSONArray ja = profitLC.getJSONArray("one");
			list = JSONArray2List(ja);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
	}
	
	public List<Double> threeMonth(){
		List<Double> list = null;
		try {
			JSONArray ja = profitLC.getJSONArray("three");
			list = JSONArray2List(ja);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
	}
	
	public List<Double> sixMonth(){
		List<Double> list = null;
		try {
			JSONArray ja = profitLC.getJSONArray("six");
			list = JSONArray2List(ja);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return list;
	}
	
	public void jsonData()
			throws JSONException, InterruptedException{
		List<Double> list = new ArrayList<Double>();
//		String[] str = new String[]{"one", "three", "six"};
		System.out.println("profit     " + profitLC.toString());
		//先判断是否已经有数据
		Date date = new Date();
		if(profitLC.has("date")){
			if(profitLC.getString("date").equals(DF.format(date)))
				System.out.println("本地已经保存有最新数据，不需重新统计");
				return ;
		}
		
		System.out.println("重新统计数据");
		
		profitLC = null;
		profitLC = new JSONObject();
		
		if(!pd.isDataEmpty()){
			profitLC.put("date", DF.format(date));
		}
		
		
		
		list = data(TimeSeries.oneMonth());
		JSONArray ja1 = new JSONArray(list);
		profitLC.put("one", ja1);
		
		list = data(TimeSeries.threeMonth());
		JSONArray ja2 = new JSONArray(list);
		profitLC.put("three", ja2);
		
		list = data(TimeSeries.oneMonth());
		JSONArray ja3 = new JSONArray(list);
		profitLC.put("six", ja3);
		
		System.out.println("profit  after   " + profitLC.toString());
		
		try {
			IORW.write(FILE, profitLC.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Double> JSONArray2List(JSONArray ja){
		List<Double> list = new ArrayList<Double>();
		for(int i = 0; i < ja.length(); ++i){
			try {
				list.add(ja.getDouble(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	private List<Double> data(String[] timeseries) 
			throws JSONException, InterruptedException{
		List<Double> list = new ArrayList<Double>();
		int i = 0;
		int j = 0;
		for( ; i < timeseries.length; ++i){
			String dStr;
//			if(j < date.length()){
//				dStr = (String) date.get(j);
//			}
			
			if(date.isNull(j))
				continue;
			dStr = (String) date.get(j);
			
			
			if(dStr.compareTo(timeseries[i]) == 0){
//				list.add(hold.getJSONArray(dStr));
				JSONArray ja = hold.getJSONArray(dStr);
				list.add(countRatio(ja, dStr));
			}
			else if(dStr.compareTo(timeseries[i]) > 0){
				if(j == 0)
					list.add(0.0);
				else{
					String s = (String) date.get(j-1);
//					list.add(hold.getJSONArray(s));
					JSONArray ja = hold.getJSONArray(s);
					list.add(countRatio(ja, dStr));
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
		if(date.length() == 0){
			return list;
		}
		String s = (String) date.get((date.length()-1));
		for( ++i; i < timeseries.length; ++i){
//			list.add(hold.getJSONArray(s));
			String ss = timeseries[i];
			JSONArray ja = hold.getJSONArray(s);
			list.add(countRatio(ja, ss));
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

	//计算收益率
	public double countRatio(final JSONArray ja, final String dStr) 
			throws InterruptedException, JSONException{
		double ratio = 0.0;// 收益率
		double m = 0.0;
		double d = 0.0;
//		System.out.println(ja.toString());
		
		Thread[] tds = new Thread[ja.length()];
		CountRatio[] crs = new CountRatio[ja.length()];
		
		for(int i = 0; i < ja.length(); ++i){
			JSONObject jo = ja.getJSONObject(i);
			crs[i] = new CountRatio(jo, dStr);
			tds[i] = new Thread(crs[i]);
			tds[i].start();
//			tds[i].join();
//			m += crs[i].getM();
//			d += crs[i].getD();
//			System.out.println(m + "  m   " + d + "     d");
			
		}
		
//		Shell shell = getShell();
//		//存储老的光标
//		Cursor oldCursor = Display.getCurrent().getCursorControl().getCursor();
//		//设置等待光标
//		Cursor cursor = Display.getCurrent().getSystemCursor(SWT.CURSOR_WAIT);
//		shell.setCursor(cursor);
		
		for(int i = 0; i < ja.length(); ++i){
			tds[i].join();
			m += crs[i].getM();
			d += crs[i].getD();
		}
		//设置会原来的光标样式
//		shell.setCursor(oldCursor);
		
//		System.out.println("dddddddddd");
		ratio = m / d;
		return ratio;
	}
	
	public JSONObject getHoldRecord() {
		return profitData;
	}

	public void setHoldRecord(JSONObject holdRecord) {
		this.profitData = holdRecord;
	}

	public static void main(String[] argv){
		DrawLineChart dlc = new DrawLineChart();
		JSONObject jo = dlc.getHoldRecord();
//		System.out.println(jo.toString());
		
		dlc.oneMonth();
	}
	
	//计算收益率
	public class CountRatio implements Runnable {
		private double m;
		private double d;
//		private JSONArray ja;
		private JSONObject jo;
		private String code;
		private String date;
		
		CountRatio(JSONObject jo, String date){
			this.m = 0.0;
			this.d = 0.0;
//			this.code = code;
			this.jo = jo;
			this.date = date;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//获取股票历史收盘价
//			String date = ((JSONObject)ja.get(i)).getString("date");
			String code = "";
			try {
				code = jo.getString("code");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			String end = ((JSONObject)ja.get(ja.length()-1)).getString("date");
//			System.out.println(code + "       " + date);
//			GetSingleStockHistory gssh = new GetSingleStockHistory(code, dStr);
			GetSingleStockHistory gssh = new GetSingleStockHistory(code, date);
			try {
				gssh.getData();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			double close = gssh.getClosePrice();
			
			double profit;
			try {
				profit = jo.getDouble("profit");
				double cost = jo.getDouble("cost");
				Double holdSum = (double) jo.getInt("holdSum");
//				System.out.println("close   " + gssh.getClosePrice() + "    cost" + cost);
				if(close == 0){
					close = cost;
				}
				
				m += profit + (close - cost) * holdSum;
				d += cost * holdSum;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public double getM() {
			return m;
		}

		public double getD() {
			return d;
		}
		
	}
}


