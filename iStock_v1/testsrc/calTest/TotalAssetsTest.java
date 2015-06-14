package calTest;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.json.*;

import controller.TotalAssets;

public class TotalAssetsTest {
	static String account;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		account="test";
	}

	@Test
	//测试个人总值的计算
	public void testCaltotalAssets() throws JSONException {
		TotalAssets totalassets =new TotalAssets(account);
		Double totalbuy=679099.00;//买入卖空金额
		Double totalSellEmpty=23994.23;//卖出补仓金额
		Double totalvalue=23994.23;//剩余股票的市值
		Double totalAsset=totalassets.calTotalAsset(totalbuy, totalSellEmpty,totalvalue);
		Double expect=-27.302596082474828;
		assertEquals(expect,totalAsset,1);
	}
	
	
	@Test
	//测试个人资产中的浮动盈亏数据
	public void testCalfbe() throws JSONException
	{
		TotalAssets totalassets =new TotalAssets(account);
		Double totalwatse=679099.00;//总买入和卖空金额
		Double totalearn=23994.23;//总卖出和补仓金额
		Double floattotalvalue=23994.23;//剩余股票的浮动市值
		Double fbe=totalassets.Calfbe(totalwatse, totalearn, floattotalvalue
				);
		Double expect=-27.302596082474828;
	    assertEquals(expect,fbe,1);
	}
	
	
	@Test
	//测试个人资产中的盈亏率数据
	public void testCalbeRatio() throws JSONException
	{
		TotalAssets totalassets =new TotalAssets(account);
		Double benefit=679099.00;//收益
		Double captital=23994.23;//成本
		Double beRatio=totalassets.CalbeRatio(benefit, captital);
		Double expect=28.302596082474828;
		assertEquals(expect,beRatio,1);
	}
	
	
	@Test
	//测试个人资产中的浮动盈亏率数据
	public void testCalfbeRatio() throws JSONException
	{
		TotalAssets totalassets =new TotalAssets(account);
		Double floatBenefit=679099.00;//浮动盈利
		Double capital=23994.23;//成本
		Double fbeRatio=totalassets.CalfbeRatio(floatBenefit, capital);
		Double expect=28.302596082474828;
		assertEquals(expect,fbeRatio,1);
	}
	
	@Test
	//测试个人资产中的现金数据
	public void testCalcash() throws JSONException
	{
		TotalAssets totalassets =new TotalAssets(account);
		Double value=679099.00;//市值
		Double totalAssets=23994.23;//总资产
		Double cash=totalassets.Calcash(value, totalAssets);
		Double expect=-655104.77;
		assertEquals(expect,cash,1);
	}
	
	
	

}
