package ctlTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import models.Record;
import models.RecordsSet;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.Excel;
import controller.ImEx_port;

public class ImEx_portTest {

	@Before
	public void setUp() throws Exception {
		File file = new File(pFolder);
		// 创建目录
		if (!file.exists()) {
			file.mkdirs();// 目录不存在的情况下，创建目录。
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testImport() {
		fail("Not yet implemented");
	}

	private String[] tRec=new String[]{"tName","tCode","15-5-10","tType","100","1","1%","2%","tState","tRemark","tHandle"};
	private String pFolder="testsrc/data/testExcel";
	@Test
	public void testExport() throws JSONException, IOException {
	String path=pFolder+"/testExport.xls";
	RecordsSet rs=new RecordsSet();
	Record rec=new Record(tRec);
//	rs.addRecord(rec);
//	rs.save();
	ImEx_port.Export(path);
	
	String[][] result=Excel.read(path);
	assertEquals(tRec,result[result.length-2] );
	
	//recover
	JSONObject jaJsonObject=new JSONObject();
	rs.removeRecord(rec );
	rs.save();
	}

}
