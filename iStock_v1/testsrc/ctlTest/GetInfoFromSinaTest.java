package ctlTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.hamcrest.Matcher;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.GetInfoFromSina;

public class GetInfoFromSinaTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInfoFromSina() throws JSONException, IOException {
		GetInfoFromSina test = new GetInfoFromSina("sh600784,sh60049,sh600569");
		JSONObject jObject = test.getJsonObj();
		// String[][] result = toStringArray(jObject);
		assertNotNull(test);

	}

	// private String[][] toStringArray(JSONObject jObject) {
	// // TODO Auto-generated method stub
	// String[] jsonKeys = new String[] { "stockExchange", "code", "name",
	// "todayOpenPrice", "yesterdayClosePrice", "currentPrice",
	// "todayHightestPrice", "todayLowestPrice", "bidsPrice",
	// "auctionPrice", "stockAllDealVolumes", "stockAllDealMoney",
	// "buyFirstVolumes", "buyFirstPrice", "buySecondVolumes",
	// "buySecondPrice", "buyThirdVolumes", "buyThirdPrice",
	// "buyFourthVolumes", "buyFourthPrice", "buyFifthVolumes",
	// "buyFifthPrice", "saleFirstVolumes", "saleFirstPrice",
	// "saleSecondVolumes", "saleSecondPrice", "saleThirdVolumes",
	// "saleThirdPrice", "saleFourthVolumes", "saleFourthPrice",
	// "saleFifthVolumes", "saleFifthPrice", "date", "time" };
	// for (int i = 0; i < jsonKeys.length; ++i) {
	// }
	// }

	@Test
	public void testGetData() {
		fail("Not yet implemented");
	}

	@Test
	public void testStructCode() throws IOException {

		assertEquals("sh60078,sz60078", GetInfoFromSina.structCode("60078"));
		assertEquals("sh60078,sz60078,sh60089,sz60089",
				GetInfoFromSina.structCode("60078,60089"));
		try {
			assertEquals("sz60078",
					GetInfoFromSina.structCode("sz60078,error60089"));
			fail("Expected an IOException to be thrown");
		} catch (IOException e) {
			assertEquals(e.getMessage(), "contain error charactor");
		}
	}

	@Test
	public void testRemoveEmpty() {
		String tStr = new String(
				"var hq_str_sh60078=\"\";var hq_str_sh600784=\"鲁银投资,12.72,12.53,12.80,12.97,12.55,12.83,12.84,13925352,177527828,17000,12.83,22100,12.82,500,12.80,52000,12.77,100,12.76,10100,12.84,203200,12.85,13900,12.86,6800,12.87,25800,12.88,2015-05-08,15:03:06,00\"");
		String act = GetInfoFromSina.removeEmpty(tStr);
		String expect = new String(
				"var hq_str_sh600784=\"鲁银投资,12.72,12.53,12.80,12.97,12.55,12.83,12.84,13925352,177527828,17000,12.83,22100,12.82,500,12.80,52000,12.77,100,12.76,10100,12.84,203200,12.85,13900,12.86,6800,12.87,25800,12.88,2015-05-08,15:03:06,00\"");
		assertEquals(expect, act);

	}

	private String netResult = new String(
			"var hq_str_sh600784=\"鲁银投资,12.72,12.53,12.80,12.97,12.55,12.83,12.84,13925352,177527828,17000,12.83,22100,12.82,500,12.80,52000,12.77,100,12.76,10100,12.84,203200,12.85,13900,12.86,6800,12.87,25800,12.88,2015-05-08,15:03:06,00\";"

					+ "var hq_str_sh600569=\"安阳钢铁,4.04,4.00,4.18,4.18,4.03,4.18,4.19,41906845,172018082,21620,4.18,267980,4.17,242273,4.16,815326,4.15,60054,4.14,400130,4.19,514100,4.20,92900,4.21,38200,4.22,25200,4.23,2015-05-08,15:03:06,00\";");

	@Test
	public void testParseString() throws IOException {
		GetInfoFromSina test = new GetInfoFromSina("sh600784,sh60049,sh600569");
		String[][] expect = new String[][] {
				{ "sh", "600784", "鲁银投资", "12.72", "12.53", "12.80", "12.97",
						"12.55", "12.83", "12.84", "13925352", "177527828",
						"17000", "12.83", "22100", "12.82", "500", "12.80",
						"52000", "12.77", "100", "12.76", "10100", "12.84",
						"203200", "12.85", "13900", "12.86", "6800", "12.87",
						"25800", "12.88", "2015-05-08", "15:03:06", "00" },
				{ "sh", "600569", "安阳钢铁", "4.04", "4.00", "4.18", "4.18",
						"4.03", "4.18", "4.19", "41906845", "172018082",
						"21620", "4.18", "267980", "4.17", "242273", "4.16",
						"815326", "4.15", "60054", "4.14", "400130", "4.19",
						"514100", "4.20", "92900", "4.21", "38200", "4.22",
						"25200", "4.23", "2015-05-08", "15:03:06", "00" } };
		String[][] result = test.parseString(netResult);
		assertEquals(expect, result);
		for (int i = 0; i < result.length; i++) {
			String str = "";
			for (int j = 0; j < result[0].length; j++) {
				str += ",\"" + result[i][j] + "\"";
			}
			System.out.println("for parse" + str);
		}

	}

	@Test
	public void testStructJsonObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testRun() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetJsonObj() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
