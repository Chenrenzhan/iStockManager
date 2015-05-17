package calTest;

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
	
		System.out.println(assets);		
		double fbe=(double) totalAsset.get("fbe");
		double beRatio=(double) totalAsset.get("beRatio");
		double fbeRatio=(double) totalAsset.get("fbeRatio");
		double cash=(double) totalAsset.get("cash");
		double dayBe=(double) totalAsset.get("dayBe");
		
		//预期数据值
		double excassets=23756.0;
		double excfbe=-1516.0;
		double excbeRatio=0.003;
		double excfbeRatio=-0.005;
		double exccash=314375.0;
		double excdayBe=1632.0;
		
		//账户资产计算核对，精确到个位
		assertEquals(excassets,assets,1);
		//浮动盈亏计算核对，精确到个位
		assertEquals(excfbe,fbe,1);
		//盈亏率计算核对，精确到百分位
		assertEquals(excbeRatio,beRatio,0.01);
		//浮动盈亏率核对，精确到万分位
		assertEquals(excfbeRatio,fbeRatio,0.0003);
		//现金计算核对，精确到个位
		assertEquals(exccash,cash,1);
		//日盈亏额核对，精确骚十分位
		assertEquals(excdayBe,dayBe,0.1);
		

	}

}
