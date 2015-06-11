/*package ctlTest;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.CellView;
import jxl.Hyperlink;
import jxl.Image;
import jxl.LabelCell;
import jxl.Range;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.Excel;

public class ExcelTest {

	final String pFolder = new String("testsrc/data/testExcel");
	private String tStr[][];

	@Before
	public void setUp() throws Exception {
		tStr = new String[][] {
				{ "股票名称", "股票编号", "日期", "类型", "价格", "数量", "税率", "佣金", "说明",
						"备注", "操作" },
				{ "name", "num", "date", "type", "prize", "amount", "tax",
						"commission", "state", "note", "opera" } };
		File file = new File(pFolder);
		// 创建目录
		if (!file.exists()) {
			file.mkdirs();// 目录不存在的情况下，创建目录。
		}
	}

	@After
	public void tearDown() throws Exception {
        deletefile(pFolder);
	}

	@Test
	public void testWriteDataToSheet() throws Exception {
		File f = new File(pFolder + "/notExist.xls");
		WritableWorkbook book;
		book = Workbook.createWorkbook(f);
		WritableSheet sheet = book.createSheet("股票记录", 0);
		Excel.writeDataToSheet(sheet, tStr);
		book.write();
        book.close();
		assertEquals(tStr, readSheet(sheet));

	}


	@Test
	public void testWrite() throws Exception {
		// test not exist
		String path = new String(pFolder + "/testWrite.xls");
		Excel.write(path, tStr);

		File f = new File(path);
		Workbook book = Workbook.getWorkbook(f);
		Sheet sheet = book.getSheet( 0);
		assertEquals(tStr, readSheet(sheet));
		// test exist

		String[][] testExist = new String[][] { { "if cover%#@", "if cover" },
				{ "covered%#@", "if cover" } };
		Excel.write(path, testExist);

		f = new File(path);
		book = Workbook.getWorkbook(f);
		sheet = book.getSheet( 0);
		assertEquals(testExist, readSheet(sheet));
	}
	
	
	@Test
	public void testRead() throws Exception {
		String path = new String(pFolder + "/testRead.xls");
		String[][] testRead = new String[][] { { "if cover%#@", "if cover" },
				{ "covered%#@", "if cover" } };
		Excel.write(path, testRead);
		
		assertEquals(testRead, Excel.read(path));
	}


	private String[][] readSheet(Sheet sheet) {
		String[][] rtn = new String[sheet.getRows()][sheet.getColumns()];
		for (int i = 0; i < sheet.getRows(); i++) {
			for (int j = 0; j < sheet.getColumns(); j++) {
				Cell cell = sheet.getCell(j, i); // 获得单元格
				rtn[i][j] = cell.getContents();
			}
		}
		return rtn;

	}
	 public static boolean deletefile(String delpath) throws Exception {  
		  try {  
		  
		   File file = new File(delpath);  
		   // 当且仅当此抽象路径名表示的文件存在且 是一个目录时，返回 true  
		   if (!file.isDirectory()) {  
		    file.delete();  
		   } else if (file.isDirectory()) {  
		    String[] filelist = file.list();  
		    for (int i = 0; i < filelist.length; i++) {  
		     File delfile = new File(delpath + "\\" + filelist[i]);  
		     if (!delfile.isDirectory()) {  
		      delfile.delete();  
		      System.out  
		        .println(delfile.getAbsolutePath() + "删除文件成功");  
		     } else if (delfile.isDirectory()) {  
		      deletefile(delpath + "\\" + filelist[i]);  
		     }  
		    }  
		    System.out.println(file.getAbsolutePath()+"删除成功");  
		    file.delete();  
		   }  
		  
		  } catch (FileNotFoundException e) {  
		   System.out.println("deletefile() Exception:" + e.getMessage());  
		  }  
		  return true;  
		 }  

}
*/