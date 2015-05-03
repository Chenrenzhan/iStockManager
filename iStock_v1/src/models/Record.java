package models;
/*
 * 一条股票交易记录
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import controller.IORW;

public class Record extends JSONObject{


	
	private String code;//编号
	private String name;//股票名字	
    private String date;//日期
    private String type;//操作类型
    private double price;//价格
    private int volumes;//数量
    private double taxes;//税率
    private double commission;//佣金
    private String state;//说明
    private String remark;//备注
    private String handle;//操作
    
//    private String jsonStr;
//    private JSONObject jsonObj;
    
    public Record(JSONObject jsonObj) throws JSONException{
//    	this.jsonObj = jsonObj;
    	setRecord(jsonObj);
    }
    
    public Record() throws JSONException{
    	super();
    }
    
    public Record(String[] strArray) throws JSONException{
    	initiate(strArray);
    	initiate();
    }
    
    public JSONObject getRecord() throws JSONException{
    	return this;
    }
    
    public void initiate() throws JSONException{
//    	this.code = jsonObj.getString("code");
//    	this.name = jsonObj.getString("name");
//    	this.date = (Date) jsonObj.get("date");
//    	this.type = jsonObj.getString("type");
//    	this.price = jsonObj.getDouble("price");
//    	this.volumes = jsonObj.getInt("volumes");
//    	this.taxes = jsonObj.getDouble("taxes");
//    	this.commission = jsonObj.getDouble("commission");
//    	this.state = jsonObj.getString("state");
//    	this.remark = jsonObj.getString("remark");
    	
    	this.put("code", code);
    	this.put("name", name);
    	this.put("date", date);
    	this.put("type", type);
    	this.put("price", price);
    	this.put("volumes", volumes);
    	this.put("taxes", taxes);
    	this.put("commission", commission);
    	this.put("state", state);
    	this.put("remark", remark);
    	this.put("handle", handle);
    }
    
    public void initiate(String[] strArray){
    	SimpleDateFormat sdf =  new SimpleDateFormat("yy-MM-dd");
    	this.code = strArray[1];
    	this.name = strArray[0];
    	try {
			this.date = sdf.format(sdf.parse(strArray[2]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.type = strArray[3];
    	this.price = Double.valueOf(strArray[4]);
    	this.volumes = Integer.valueOf(strArray[5]);
    	this.taxes = transPercentToDouble(strArray[6],1000.0);
    	this.commission = transPercentToDouble(strArray[7],1000.0);
    	this.state = strArray[8];
    	this.remark = strArray[9];
    	this.handle = strArray[10];
    }
    
    public void setRecord(JSONObject jsonObj) 
    		throws JSONException{
    	this.code = jsonObj.getString("code");
    	this.name = jsonObj.getString("name");
    	this.date = jsonObj.getString("date");
    	this.type = jsonObj.getString("type");
    	this.price = jsonObj.getDouble("price");
    	this.volumes = jsonObj.getInt("volumes");
    	this.taxes = jsonObj.getDouble("taxes");
    	this.commission = jsonObj.getDouble("commission");
    	this.state = jsonObj.getString("state");
    	this.remark = jsonObj.getString("remark");
    	this.handle = jsonObj.getString("handle");
    	
    	initiate();
    }
    
    public double transPercentToDouble(String str, double denom){
    	String REGEX = "([-+]?\\d+\\.?\\d*)";
    	Pattern pattern = Pattern.compile(REGEX);
    	Matcher matcher = pattern.matcher(str); 
    	if(matcher.find()){
    		return (Double.valueOf(matcher.group(1)) / denom);
    	  } 
    	return 0.0;
    }
}
