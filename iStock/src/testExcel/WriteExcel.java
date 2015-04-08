package testExcel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

//中文名
public class WriteExcel
{
    public static void main(String argus[]) throws Exception{
        //构造数据，有两行
         List<String> row1=new ArrayList<String>();    
         row1.add("最合适列宽");    
         row1.add("这个基本可以实现 chenrenzhan");
         List<String> row2=new ArrayList<String>();    
         row2.add("Best Column Width");    
         row2.add("Haha               ae       dw");
         List list=new ArrayList();    
         list.add(row1);    
         list.add(row2);

        //写数据到Excel中
         WritableWorkbook book= Workbook.createWorkbook(new File("data\\write.xls"));
         WritableSheet sheet=book.createSheet("测试",0);
         writeDataToSheet(sheet,list);
         book.write();
         book.close();
    }

    public static void writeDataToSheet(WritableSheet sheet,List<List<String>> list) throws Exception{
        int columnBestWidth[]=new  int[list.get(0).size()];    //保存最佳列宽数据的数组

        for(int i=0;i<list.size();i++){
            List<String> row=list.get(i);
            for(int j=0;j<row.size();j++){
                 sheet.addCell(new Label(j,i,row.get(j)));

                 int width=row.get(j).length()+getChineseNum(row.get(j));    ///汉字占2个单位长度
                 if(columnBestWidth[j]<width)    ///求取到目前为止的最佳列宽
                     columnBestWidth[j]=width;
            }
        }

        for(int i=0;i<columnBestWidth.length;i++){    ///设置每列宽
            sheet.setColumnView(i, columnBestWidth[i]);
        }
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



//3.只是实现而已
//(1)这个是一次项目中写的，自动导数Excel电子报表，可以很好实现。主要是：汉字，阿拉伯数字。
//(2)汉字是方块字，每个字的大小很统一，可以很惊奇的统计列宽。
//(3)英文小写字母共26个，每个字母的大小差异很小，也可以实现最佳列宽。
//(4)其他字符，每个字符的占位大小差异不定，比如大小字母序列“IIIIIII”和字母“DDDDDDD”。
//
//总之，jxl.jar没有自动实现最适合列宽的方法；如果要导出的电子报表中汉字占绝大多数，就能很好的用这个程序实现最适合列宽。