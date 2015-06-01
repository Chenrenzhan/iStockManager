package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Excel {

	private static HashMap<String, String[][]> excelHashMap = new HashMap<String, String[][]>();

	public static HashMap<String, String[][]> read(String path) {

		File f = new File(path);
		String[][] excelStr = null;
		try {
			Workbook book = Workbook.getWorkbook(f);
			for (int count = 0; count < book.getNumberOfSheets(); count++) {

				Sheet sheet = book.getSheet(count); // 获得第一个工作表对象
				// sheet.
				int row = sheet.getRows();
				int col = sheet.getColumns();
				int index = 0;
				String[][] tempStr = new String[row][col];
				for (int i = 0; i < row; i++) {
					if (isBlankLine(sheet, i, col)) {
						continue;
					}
					List<String> listSub = new ArrayList<String>();
					for (int j = 0; j < col; j++) {
						Cell cell = sheet.getCell(j, i); // 获得单元格
						tempStr[index][j] = cell.getContents();
						listSub.add(cell.getContents());
					}
					++index;
				}
				excelStr = new String[index][col];
				for (int i = 0; i < index; ++i) {
					excelStr[i] = tempStr[i];
				}
				excelHashMap.put(sheet.getName(),excelStr);// 第一行为用户名
			}
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			// 写入日志
			log logger = new log();
			logger.getError("Excel中的Read方法有问题");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log logger = new log();
			logger.getError("Excel中的Read方法有问题");
			e.printStackTrace();
		}
		// for(String[] a : excelStr){
		// for(String b : a){
		// System.out.print(b + "    ");
		// }
		// System.out.println();
		// }
		// 写入日志
		log logger = new log();
		logger.getInfo(excelStr);

		return excelHashMap;

	}

	public static Boolean isBlankLine(Sheet sheet, int row, int col) {
		for (int i = 0; i < col; ++i) {
			Cell cell = sheet.getCell(i, row);
			String cellContents = cell.getContents();
			if (!cellContents.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public static void write(String path,ArrayList<String> accounts,ArrayList<String[][]>eArray)
			throws Exception {
		File f = new File(path);
		WritableWorkbook book = Workbook.createWorkbook(f);
		for (int i = 0; i < eArray.size(); i++) {
			String[][] excelStr = eArray.get(i);
			WritableSheet sheet = book.createSheet(accounts.get(i), i);
			writeDataToSheet(sheet, excelStr);
		}

		book.write();
		book.close();
	}

	// public static void main(String[] args) throws Exception {
	// write("data/测试.xls", "1", new String[][]{{"1","2"},{"3","4"}});
	// write("data/测试.xls", "2", new String[][]{{"1","2"},{"3","4"}});
	// write("data/测试.xls", "3", new String[][]{{"1","2"},{"3","4"}});
	// write("data/测试.xls", "4", new String[][]{{"1","2"},{"3","4"}});
	// write("data/测试.xls", "5", new String[][]{{"1","2"},{"3","4"}});
	// }
	//
	public static void writeDataToSheet(WritableSheet sheet, String[][] eStr)
			throws Exception {
		int[] columnBestWidth = new int[eStr[0].length]; // 保存最佳列宽数据的数组

		for (int i = 1; i < eStr.length; i++) // 第一行保存的是账户名，所以由1开始
		{
			String[] row = eStr[i];
			for (int j = 0; j < row.length; j++) {
				sheet.addCell(new Label(j, i, row[j]));
				//
				// int width=row[j].length()+getChineseNum(row[j]); ///汉字占2个单位长度
				// if(columnBestWidth[j]<width) ///求取到目前为止的最佳列宽
				// columnBestWidth[j]=width;
			}
		}

		// for(int i=0;i<columnBestWidth.length;i++){ ///设置每列宽
		// sheet.setColumnView(i, columnBestWidth[i]);
		// }
	}

	//
	// public static int getChineseNum(String context){ ///统计context中是汉字的个数
	// int lenOfChinese=0;
	// Pattern p = Pattern.compile("[\u4e00-\u9fa5]"); //汉字的Unicode编码范围
	// Matcher m = p.matcher(context);
	// while(m.find()){
	// lenOfChinese++;
	// }
	// //写入日志
	// log logger=new log();
	// logger.getInfo("汉字的个数"+lenOfChinese);
	// return lenOfChinese;
	// }
	//
}
