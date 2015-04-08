package testExcel;

import java.io.File;  
import java.io.IOException;  
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException; 

public class ReadExcel {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File f=new File("data\\test.xls");//"E:"+File.separator+"dataAnalysis"+File.separator+"test.xls");  
		//这是我自己建的，位于E:\dataAnalysis文件夹中的test.xls  
		    try {  
		        Workbook book=Workbook.getWorkbook(f);//  
		        Sheet sheet=book.getSheet(0);   //获得第一个工作表对象  
		        for(int i=0;i<sheet.getRows();i++){  
		            for(int j=0;j<sheet.getColumns();j++){  
		                Cell cell=sheet.getCell(j, i);  //获得单元格  
		                System.out.print(cell.getContents()+" ");   
		            }  
		            System.out.print("\n");  
		        }  
		    } catch (BiffException e) {  
		        // TODO Auto-generated catch block  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        // TODO Auto-generated catch block  
		        e.printStackTrace();  
		    }  
	}
}
