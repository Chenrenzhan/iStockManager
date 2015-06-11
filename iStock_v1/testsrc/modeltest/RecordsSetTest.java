/*package modeltest;

import static org.junit.Assert.*;
import models.RecordsSet;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.IORW;

public class RecordsSetTest {
	private final String FILEPATH = "data/record.json";
	private JSONObject recordsJsonObj;
	private RecordsSet target_;
	private RecordsSet act_;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testRecordsSet() throws JSONException {
		RecordsSet res = new RecordsSet();
		String jsonStr = IORW.read(FILEPATH);
		recordsJsonObj = new JSONObject(jsonStr);
		RecordsSet target =new RecordsSet();
		assertEquals(target_, act_);
	}

	@Test
	public void testRecordsSetStringArrayArray() throws JSONException {
		RecordsSet res = new RecordsSet(new String[][] {
				{ "t", "t", "t", "t", "0", "0", "0", "0", "t", "t", "t" },
				{ "t", "t", "t", "t", "0", "0", "0", "0", "t", "t", "t" }

		});
		String[][] target = new String[][] {
				{ "t", "t", "t", "t", "0", "0", "0", "0", "t", "t", "t" },
				{ "t", "t", "t", "t", "0", "0", "0", "0", "t", "t", "t" }

		};
		String[][] act = new String[3][12];

		
		assertEquals(target_, act_);
		
	}

	@Test
	public void testAddRecord() {
		assertEquals(target_, act_);
	}

	@Test
	public void testRemoveRecordStringInt() {
		assertEquals(target_, act_);
	}

	@Test
	public void testRemoveRecordRecord() {
		assertEquals(target_, act_);
	}

	@Test
	public void testSetRecord() {
		assertEquals(target_, act_);
	}

	@Test
	public void testRemoveJSONArray() {
		assertEquals(target_, act_);
	}

	@Test
	public void testSave() {
		assertEquals(target_, act_);
	}

	@Test
	public void testGetRecordsSet() {
		assertEquals(target_, act_);
	}

	@Test
	public void testJsonToStringArray() {
		assertEquals(target_, act_);
	}

}
*/