package modeltest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import models.Record;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RecordTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRecordJSONObject() throws IllegalArgumentException,
			IllegalAccessException, JSONException {
		Field[] fa = Record.class.getDeclaredFields();
		for (Field f : fa) {
			if (f.getName().equals("prs")) {
				f.setAccessible(true);
				Record t;
				t = new Record(new JSONObject());

				String code = (String) (f.get(t));
				System.out.println(code);
			}

		}
	}

	@Test
	public void testRecord() throws JSONException, IllegalArgumentException, IllegalAccessException {
             Record t=new Record();
             String[] target=new String[10];
             String[] act=getValue(t);
	}

	@Test
	public void testRecordStringArray() throws IllegalArgumentException,
			IllegalAccessException, JSONException {
		Record t = new Record(new String[] { "1000", "tName", "tdata", "ttepe",
				"1001.11111", "1002", "1002.111111", "1003.1111111", "state",
				"remark", "thandle" });
		String[] target = new String[] { "1000", "tName", "tdata", "ttepe",
				"1001.11111", "1002", "1.002111111", "1.0031111111", "state",
				"remark", "thandle" };
		String[] act = getValue(t);
		assertEquals(target, act);
	}

	@Ignore
	@Test
	public void testGetRecord() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Record t = new Record(new String[] { "1000", "tName", "tdata", "ttepe",
				"1001.11111", "1002", "1002.111111", "1003.1111111", "state",
				"remark", "thandle" });
		String[] target = new String[] { "1000", "tName", "tdata", "ttepe",
				"1001.11111", "1002", "1.002111111", "1.0031111111", "state",
				"remark", "thandle" };
		String[] act = getValue(t);
		assertEquals(target, act);
	}



	private static String[] getValue(Record t) throws IllegalArgumentException,
			IllegalAccessException {
		Field[] fa = Record.class.getDeclaredFields();

		String[] act = new String[11];
		int i = 0;
		for (Field f : fa) {
			switch (f.getName()) {
			case "code":
				f.setAccessible(true);
				String code = (String) (f.get(t));
				act[1] = code;
				break;
			case "name":
				f.setAccessible(true);
				String name = (String) (f.get(t));
				act[0] = name;
				break;
			case "date":
				f.setAccessible(true);
				String date = (String) (f.get(t));
				act[2] = date;
				break;
			case "type":
				f.setAccessible(true);
				String type = (String) (f.get(t));
				act[3] = type;
				break;
			case "price":
				f.setAccessible(true);
				String price = (f.get(t)).toString();
				act[4] = price;
				break;
			case "volumes":
				f.setAccessible(true);
				String volumes = (f.get(t)).toString();
				act[5] = volumes;
				break;
			case "taxes":
				f.setAccessible(true);
				String taxes = (f.get(t)).toString();
				act[6] = taxes;
				break;
			case "commission":
				f.setAccessible(true);
				String commission = (f.get(t)).toString();
				act[7] = commission;
				break;
			case "state":
				f.setAccessible(true);
				String state = (String) (f.get(t));
				act[8] = state;
				break;
			case "remark":
				f.setAccessible(true);
				String remark = (String) (f.get(t));
				act[9] = remark;
				break;
			case "handle":
				f.setAccessible(true);
				String handle = (String) (f.get(t));
				act[10] = handle;
				break;
			default:
				break;
			}

		}
		return act;
	}

}
