/*package ctlTest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.GetKChartFromSina;

public class GetKChartFromSinaTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		String path = "data/temp/";
		ExcelTest.deletefile(path);
	}

	@Before
	public void setUp() throws Exception {
		String path = "data/temp/";
		ExcelTest.deletefile(path);

	}

	@Test
	public synchronized void testRun() throws InterruptedException {
		GetKChartFromSina testObj = new GetKChartFromSina("sh600784", "daily");
		Thread th = new Thread(testObj);
		th.start();
		th.join();
		File file = new File("data/temp/daily.gif");
		//无法联网时5秒后仍需生成一个空的k线图
		if (!file.exists()) {
			assertEquals(true, file.exists());
		}
		assertEquals(true, file.exists());
		;
	}

	@Test
	public void testGetKChart() throws Exception {
		GetKChartFromSina.getKChart("sh600784", "daily");
		File file1 = new File("data/temp/daily.gif");
		assertEquals(true, file1.exists());
		GetKChartFromSina.getKChart("sh600784", "weekly");
		File file2 = new File("data/temp/weekly.gif");
		assertEquals(true, file2.exists());
		GetKChartFromSina.getKChart("sh600784", "monthly");
		File file3 = new File("data/temp/monthly.gif");
		assertEquals(true, file3.exists());
	}

}
*/