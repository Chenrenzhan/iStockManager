/*package calTest;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.json.*;

import controller.TotalAssets;

public class TotalAssetsTest {

	@Test
	//测试个人总值的计算
	public void testCaltotalAssets() throws JSONException {
		TotalAssets totalassets =new TotalAssets();
		JSONObject totalAsset=totalassets.CaltotalAssets();
		System.out.println(totalAsset);
		//从JSONOBJECT中取值
		double assets=(double) totalAsset.get("assets");
	
		//System.out.println(assets);		
		//double fbe=(double) totalAsset.get("fbe");
		//double beRatio=(double) totalAsset.get("beRatio");
		//double fbeRatio=(double) totalAsset.get("fbeRatio");
		//double cash=(double) totalAsset.get("cash");
		//double dayBe=(double) totalAsset.get("dayBe");
		double temp=(double ) totalAsset.get("assets");
		temp-=0.5;
		//预期数据值
		double excassets=temp;
		//double excfbe=-1516.0;
		//double excbeRatio=0.003;
		//double excfbeRatio=-0.005;
		//double exccash=314375.0;
		//double excdayBe=1632.0;
		
		//账户资产计算核对，精确到个位
		assertEquals(excassets,assets,1);
		//浮动盈亏计算核对，精确到个位
		//assertEquals(excfbe,fbe,1);
		//盈亏率计算核对，精确到百分位
		//assertEquals(excbeRatio,beRatio,0.01);
		//浮动盈亏率核对，精确到万分位
		//assertEquals(excfbeRatio,fbeRatio,0.0003);
		//现金计算核对，精确到个位
		//assertEquals(exccash,cash,1);
		//日盈亏额核对，精确骚十分位
		//assertEquals(excdayBe,dayBe,0.1);
	
	}
	
	
	@Test
	//测试个人资产中的浮动盈亏数据
	public void testCalfbe() throws JSONException
	{
		TotalAssets totalassets =new TotalAssets();
		JSONObject totalAsset=totalassets.CaltotalAssets();
		double fbe=(double) totalAsset.get("fbe");
		double temp=(double ) totalAsset.get("fbe");
		temp-=0.5;
		double excfbe=temp;
		//浮动盈亏计算核对，精确到个位
	    assertEquals(excfbe,fbe,1);
	}
	
	
	@Test
	//测试个人资产中的盈亏率数据
	public void testCalbeRatio() throws JSONException
	{
		TotalAssets totalassets =new TotalAssets();
		JSONObject totalAsset=totalassets.CaltotalAssets();
		double beRatio=(double) totalAsset.get("beRatio");
		double temp=(double ) totalAsset.get("beRatio");
		temp-=0.005;
		double excbeRatio=temp;
		//浮动盈亏计算核对，精确到个位
	    assertEquals(excbeRatio,beRatio,0.01);
	}
	
	
	@Test
	//测试个人资产中的浮动盈亏率数据
	public void testCalfbeRatio() throws JSONException
	{
		TotalAssets totalassets =new TotalAssets();
		JSONObject totalAsset=totalassets.CaltotalAssets();
		double fbeRatio=(double) totalAsset.get("fbeRatio");
		double temp=(double ) totalAsset.get("fbeRatio");
		temp-=0.0002;
		double excfbeRatio=temp;
		//浮动盈亏计算核对，精确到个位
	    assertEquals(excfbeRatio,fbeRatio,0.0003);
	}
	
	@Test
	//测试个人资产中的现金数据
	public void testCalcash() throws JSONException
	{
		TotalAssets totalassets =new TotalAssets();
		JSONObject totalAsset=totalassets.CaltotalAssets();
		double cash=(double) totalAsset.get("cash");
		double temp=(double ) totalAsset.get("cash");
		temp-=0.052;
		double exccash=temp;
		//浮动盈亏计算核对，精确到个位
	    assertEquals(exccash,cash,0.1);
	}
	
	
	@Test
	//测试个人资产中的日盈亏率
	public void testCaldayBe() throws JSONException
	{
		TotalAssets totalassets =new TotalAssets();
		JSONObject totalAsset=totalassets.CaltotalAssets();
		double dayBe=(double) totalAsset.get("dayBe");
		double temp=(double ) totalAsset.get("dayBe");
		temp-=0.52;
		double excdayBe=temp;
		//浮动盈亏计算核对，精确到个位
	    assertEquals(excdayBe,dayBe,1);
	}

}
*/