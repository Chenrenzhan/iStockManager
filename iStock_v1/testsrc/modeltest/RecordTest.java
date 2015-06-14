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
		Record t = new Record(new String[] { "1000", "tName", "15-06-04", "ttepe",
				"1001.11111", "1002", "1002.111111", "1003.1111111", "state",
				"remark", "thandle" });
		String[] target = new String[] {  "tName", "1000","15-06-04", "ttepe",
				"1001.11111", "1002", "1.002111111", "1.0031111111", "state",
				"remark", "thandle" };
		String[] act = getValue(t);
		assertEquals(target, act);
	}

	@Ignore
	@Test
	public void testGetRecord() throws JSONException, IllegalArgumentException, IllegalAccessException {
		Record t = new Record(new String[] { "1000", "tName", "15-06-04", "ttepe",
				"1001.11111", "1002", "1002.111111", "1003.1111111", "state",
				"remark", "thandle" });
		String[] target = new String[] { "1000", "tName", "15-6-4", "ttepe",
				"1001.11111", "1002", "1.002111111", "1.0031111111", "state",
				"remark", "thandle" };
		String[] act = getValue(t);
		assertEquals(target, act);
	}



	private static String[] getValue(Record t) throws IllegalArgumentException,
			IllegalAccessException {
		Field[] fa = Record.class.getDeclaredFields();

		String[] act = new String[11];
		 act[0]=t.code;//编号
		 act[1]=t.name;//股票名字	
		 act[2]=t.date;//日期
		 act[3]=t.type;//操作类型
		 act[4]=String.valueOf(t.price);//价格
		 act[5]=String.valueOf(t.volumes);//数量
		 act[6]=String.valueOf(t.taxes);//税率
		 act[7]=String.valueOf(t.commission);//佣金
		 act[8]=t.state;//说明
		 act[9]=t.remark;//备注
		 act[10]=t.handle;//操作
		return act;
	}

}
