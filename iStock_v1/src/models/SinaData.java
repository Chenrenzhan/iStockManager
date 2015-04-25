package models;

import java.util.Arrays;

public class SinaData {
	private String jsonStr;//json��ʽ���ַ�
	
/*
	private String stockExchange; //��Ʊ������sh����ʾ���Ϻ�֤ȯ�����������еĹ�Ʊ,sz����ʾ������֤ȯ�����������еĹ�Ʊ
	private String code;//���
	private String name;//��Ʊ����
	private double todayOpenPrice;//���տ��̼�
	private double yesterdayOpenPrice;//���տ��̼�
	private double currentPrice;//��ǰ�۸�
	private double todayHightestPrice;//������߼�
	private double todayLowestPrice;//������ͼ�
	private double bidsPrice;//����ۣ�������һ������
	private double auctionPrice;//�����ۣ�������һ������
	private int stockAllDealVolumes;//�ɽ��Ĺ�Ʊ��
	private double stockAllDealMoney;//�ɽ�����λΪ��Ԫ��
	private int buyFirstVolumes;//��һ��Ʊ��
	private int buySecondVolumes;//�����Ʊ��
	private int buyThirdVolumes;//�����Ʊ��
	private int buyFourthVolumes;//���Ĺ�Ʊ��
	private int buyFifthVolumes;//�����Ʊ��
	private double buyFirstPrice;//��һ����
	private double buySecondPrice;//�������
	private double buyThirdPrice;//�����
	private double buyFourthPrice;//���ı���
	private double buyFifthPrice;//���屨��
	private int saleFirstVolumes;//��һ��Ʊ��
	private int saleSecondVolumes;//������Ʊ��
	private int saleThirdVolumes;//�����Ʊ��
	private int saleFourthVolumes;//���Ĺ�Ʊ��
	private int saleFifthVolumes;//�����Ʊ��
	private double saleFirstPrice;//��һ����
	private double saleSecondPrice;//��������
	private double saleThirdPrice;//�����
	private double saleFourthPrice;//���ı���
	private double saleFifthPrice;//���屨��
	private String date;//����
	private String time;//ʱ��
*/

	
	SinaData(String dataString){
		String[][] str = parseString(dataString);
		jsonStr = structJsonString(str);
		System.out.println(jsonStr);
	}
	
	String getJsonString(){
		return jsonStr;
	}
	
/*����������API��ȡ���ַ����Ϊ����
 * @param sina API ���ص��ַ�
 * @return �����󷵻��ַ�����
 */
	private String[][] parseString(String str){
//		str="var hq_str_sh601003=\"��ֹɷ�,4.80,4.81,4.84,4.85,4.68,4.83,4.84,13312943,63256888,42065,4.83,122420,4.82,101000,4.81,189099,4.80,134600,4.79,1700,4.84,150860,4.85,104120,4.86,31900,4.87,54640,4.88,2015-04-16,11:35:54,00\";";
		String[][] returnStr;
		String[] stocks = str.split(";");
		int length = stocks.length;
		returnStr = new String[length][];
		for(int i = 0; i < length; ++i){
			String te = stocks[i].substring(stocks[i].lastIndexOf('_')+1, 
					stocks[i].indexOf('='));
			String imformation = stocks[i].substring(stocks[i].indexOf('\"')+1, 
					stocks[i].lastIndexOf('\"'));
			imformation = te.substring(0, 2) + "," + te.substring(2, te.length()) + "," + imformation;
			String[] stock = imformation.split(",");
			returnStr[i] = stock;
		}
		System.out.println(Arrays.toString(returnStr[0]));
		System.out.println(returnStr[0].length);
		for(int i = 0; i < returnStr[0].length; ++i){
			System.out.println(i + "     " + returnStr[0][i]);
		}
		return returnStr;
		
	}
	
	private String structJsonString(String[][] strs){
		String jsonStr;
		jsonStr = "{\"stock\":[";
		
		int stockSum = strs.length;
		for(int i = 0; i < stockSum; ++i){
			String tempStr = "";
			jsonStr += "{\"stockExchange\":" + strs[i][0] + "," //������
					+ "\"code\":" + strs[i][1] + "," //���
					+ "\"name\":" + strs[i][2] + "," //��Ʊ����
					+ "\"todayOpenPrice\":" + strs[i][3] + ","//���տ��̼�
					+ "\"yesterdayOpenPrice\":" + strs[i][4] + ","//���տ��̼�
					+ "\"currentPrice\":" + strs[i][5] + ","//��ǰ�۸�
					+ "\"todayHightestPrice\":" + strs[i][6] + "," //������߼�
					+ "\"todayLowestPrice\":" + strs[i][7] + ","//������ͼ�
					+ "\"bidsPrice\":" + strs[i][8] + ","//����ۣ�������һ������
					+ "\"auctionPrice\":" + strs[i][9] + ","//�����ۣ�������һ�����ۣ�
					+ "\"stockAllDealVolumes\":" + strs[i][10] + ","//�ɽ��Ĺ�Ʊ��
					+ "\"stockAllDealMoney\":" + strs[i][11] + ","//�ɽ�����λΪ��Ԫ��
					+ "\"lateBuy\":["
		                    + "{\"buyVolumes\":" + strs[i][12] + ","
		                    	+ "\"buyPrice\":" + strs[i][13] + "},"//{��һ����һ����}
		                    + "{\"buyVolumes\":" + strs[i][14] + ","
		                    	+ "\"buyPrice\":" + strs[i][15] + "},"//{������������}
		                    + "{\"buyVolumes\":" + strs[i][16] + ","
		                    	+ "\"buyPrice\":" + strs[i][17] + "},"//{���������}
		                    + "{\"buyVolumes\":" + strs[i][18] + ","
		                    	+ "\"buyPrice\":" + strs[i][19] + "},"//{���ģ����ı���}
		                    + "{\"buyVolumes\":" + strs[i][20] + ","
		                    	+ "\"buyPrice\":" + strs[i][21] + "},"//{���壬���屨��}			
		                    + "],"
		              + "\"lataSale\":[" 
		              		+ "{\"saleVolumes\":" + strs[i][22] + ","
		              			+ "\"salePrice\":" + strs[i][23] + "},"//{��һ����һ����}
		              		+ "{\"saleVolumes\":" + strs[i][24] + ","
		              			+ "\"salePrice\":" + strs[i][25] + "},"//{������������}
		              		+ "{\"saleVolumes\":" + strs[i][26] + ","
		              			+ "\"salePrice\":" + strs[i][27] + "},"//{���������}
		              		+ "{\"saleVolumes\":" + strs[i][28] + ","
		              			+ "\"salePrice\":" + strs[i][29] + "},"//{���ģ����ı���}
			                + "{\"saleVolumes\":" + strs[i][30] + ","
			                  	+ "\"salePrice\":" + strs[i][31] + "},"//{���壬���屨��}
			                + "],"
			          + "\"date\":\"" + strs[i][32] + "\","//����
			          + "\"time\":\"" + strs[i][33] + "\","//ʱ��
		    		  + "},";
		}		
		jsonStr += "],}";
		
		return jsonStr;
		
	}
	
	public static void main(String argv[]){
		String str="var hq_str_sh601003=\"��ֹɷ�,4.80,4.81,4.84,4.85,4.68,4.83,4.84,13312943,63256888,42065,4.83,122420,4.82,101000,4.81,189099,4.80,134600,4.79,1700,4.84,150860,4.85,104120,4.86,31900,4.87,54640,4.88,2015-04-16,11:35:54,00\";";
		new SinaData(str);
	}
	
}

//var hq_str_sh601003="��ֹɷ�,4.80,4.81,4.84,4.85,4.68,4.83,4.84,13312943,63256888,42065,4.83,122420,4.82,101000,4.81,189099,4.80,134600,4.79,1700,4.84,150860,4.85,104120,4.86,31900,4.87,54640,4.88,2015-04-16,11:35:54,00";
