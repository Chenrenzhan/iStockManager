package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	
	private static String[][] excelStr = null;

	public static String[][] read(String path) {
	
		File f = new File(path);

		try {
			Workbook book = Workbook.getWorkbook(f);
			Sheet sheet = book.getSheet(0); // 获得第一个工作表对象
			int row = sheet.getRows();
			int col = sheet.getColumns();
			int index = 0;	
			String[][] tempStr = new String[row][col];
			for (int i = 0; i < row; i++) {
				if(isBlankLine(sheet, i, col)){
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
			for(int i = 0; i < index; ++i){
				excelStr[i] = tempStr[i];
			}
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excelStr;
	}

	public static Boolean isBlankLine(Sheet sheet, int row, int col){
		for(int i = 0; i < col; ++i){
			Cell cell = sheet.getCell(i, row);
			String cellContents = cell.getContents();
			if(!cellContents.isEmpty()){
				return false;
			}
		}
		return true;
	}
	
	public static void write(String path, String[][] eStr) 
			throws Exception{
		excelStr = eStr;
		File f = new File(path);
		 WritableWorkbook book= Workbook.createWorkbook(f);
         WritableSheet sheet=book.createSheet("股票记录",0);
         writeDataToSheet(sheet,excelStr);
         book.write();
         book.close();
	}
	
    public static void writeDataToSheet(
    		WritableSheet sheet, String[][] eStr) 
    		throws Exception{
        int[] columnBestWidth=new int[eStr[0].length];    //保存最佳列宽数据的数组

        for(int i=0;i<eStr.length;i++){
            String[] row=eStr[i];
            for(int j=0;j<row.length;j++){
                 sheet.addCell(new Label(j,i,row[j]));
//
//                 int width=row[j].length()+getChineseNum(row[j]);    ///汉字占2个单位长度
//                 if(columnBestWidth[j]<width)    ///求取到目前为止的最佳列宽
//                     columnBestWidth[j]=width;
            }
        }

//        for(int i=0;i<columnBestWidth.length;i++){    ///设置每列宽
//            sheet.setColumnView(i, columnBestWidth[i]);
//        }
    }

    
 
    public static int getChineseNum(String context){    ///统计context中是汉字的个数
        int lenOfChinese=0;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");    //汉字的Unicode编码范围
        Matcher m = p.matcher(context);
        while(m.find()){
            lenOfChinese++;
        }
        return lenOfChinese;
    }
	
}
