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

//������
public class WriteExcel
{
    public static void main(String argus[]) throws Exception{
        //�������ݣ�������
         List<String> row1=new ArrayList<String>();    
         row1.add("������п�");    
         row1.add("�����������ʵ�� chenrenzhan");
         List<String> row2=new ArrayList<String>();    
         row2.add("Best Column Width");    
         row2.add("Haha               ae       dw");
         List list=new ArrayList();    
         list.add(row1);    
         list.add(row2);

        //д���ݵ�Excel��
         WritableWorkbook book= Workbook.createWorkbook(new File("data\\write.xls"));
         WritableSheet sheet=book.createSheet("����",0);
         writeDataToSheet(sheet,list);
         book.write();
         book.close();
    }

    public static void writeDataToSheet(WritableSheet sheet,List<List<String>> list) throws Exception{
        int columnBestWidth[]=new  int[list.get(0).size()];    //��������п����ݵ�����

        for(int i=0;i<list.size();i++){
            List<String> row=list.get(i);
            for(int j=0;j<row.size();j++){
                 sheet.addCell(new Label(j,i,row.get(j)));

                 int width=row.get(j).length()+getChineseNum(row.get(j));    ///����ռ2����λ����
                 if(columnBestWidth[j]<width)    ///��ȡ��ĿǰΪֹ������п�
                     columnBestWidth[j]=width;
            }
        }

        for(int i=0;i<columnBestWidth.length;i++){    ///����ÿ�п�
            sheet.setColumnView(i, columnBestWidth[i]);
        }
    }

    
 
    public static int getChineseNum(String context){    ///ͳ��context���Ǻ��ֵĸ���
        int lenOfChinese=0;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");    //���ֵ�Unicode���뷶Χ
        Matcher m = p.matcher(context);
        while(m.find()){
            lenOfChinese++;
        }
        return lenOfChinese;
    }
}



//3.ֻ��ʵ�ֶ���
//(1)�����һ����Ŀ��д�ģ��Զ�����Excel���ӱ������Ժܺ�ʵ�֡���Ҫ�ǣ����֣����������֡�
//(2)�����Ƿ����֣�ÿ���ֵĴ�С��ͳһ�����Ժܾ����ͳ���п�
//(3)Ӣ��Сд��ĸ��26����ÿ����ĸ�Ĵ�С�����С��Ҳ����ʵ������п�
//(4)�����ַ���ÿ���ַ���ռλ��С���첻���������С��ĸ���С�IIIIIII������ĸ��DDDDDDD����
//
//��֮��jxl.jarû���Զ�ʵ�����ʺ��п�ķ��������Ҫ�����ĵ��ӱ����к���ռ������������ܺܺõ����������ʵ�����ʺ��п�