package models;

import java.util.Arrays;

public class SinaData {
	private String jsonStr;//json格式的字符串
	
/*
	private String stockExchange; //股票交易所，sh即表示在上海证券交易所所上市的股票,sz即表示在深圳证券交易所所上市的股票
	private String code;//编号
	private String name;//股票名字
	private double todayOpenPrice;//今日开盘价
	private double yesterdayOpenPrice;//昨日开盘价
	private double currentPrice;//当前价格
	private double todayHightestPrice;//今日最高价
	private double todayLowestPrice;//今日最低价
	private double bidsPrice;//竞买价，即“买一”报价
	private double auctionPrice;//竞卖价，即“卖一”报价
	private int stockAllDealVolumes;//成交的股票数
	private double stockAllDealMoney;//成交金额，单位为“元”
	private int buyFirstVolumes;//买一股票数
	private int buySecondVolumes;//买二股票数
	private int buyThirdVolumes;//买三股票数
	private int buyFourthVolumes;//买四股票数
	private int buyFifthVolumes;//买五股票数
	private double buyFirstPrice;//买一报价
	private double buySecondPrice;//买二报价
	private double buyThirdPrice;//买三报价
	private double buyFourthPrice;//买四报价
	private double buyFifthPrice;//买五报价
	private int saleFirstVolumes;//卖一股票数
	private int saleSecondVolumes;//卖二股票数
	private int saleThirdVolumes;//卖三股票数
	private int saleFourthVolumes;//卖四股票数
	private int saleFifthVolumes;//卖五股票数
	private double saleFirstPrice;//卖一报价
	private double saleSecondPrice;//卖二报价
	private double saleThirdPrice;//卖三报价
	private double saleFourthPrice;//卖四报价
	private double saleFifthPrice;//卖五报价
	private String date;//日期
	private String time;//时间
*/

	
	SinaData(String dataString){
		String[][] str = parseString(dataString);
		jsonStr = structJsonString(str);
		System.out.println(jsonStr);
	}
	
	String getJsonString(){
		return jsonStr;
	}
	
/*解析从新浪API获取的字符串数据为数组
 * @parameter sina API 返回的字符串
 * @return 解析后返回字符串数组
 */
	private String[][] parseString(String str){
//		str="var hq_str_sh601003=\"柳钢股份,4.80,4.81,4.84,4.85,4.68,4.83,4.84,13312943,63256888,42065,4.83,122420,4.82,101000,4.81,189099,4.80,134600,4.79,1700,4.84,150860,4.85,104120,4.86,31900,4.87,54640,4.88,2015-04-16,11:35:54,00\";";
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
			jsonStr += "{\"stockExchange\":" + strs[i][0] + "," //交易所
					+ "\"code\":" + strs[i][1] + "," //编号
					+ "\"name\":" + strs[i][2] + "," //股票名字
					+ "\"todayOpenPrice\":" + strs[i][3] + ","//今日开盘价
					+ "\"yesterdayOpenPrice\":" + strs[i][4] + ","//昨日开盘价
					+ "\"currentPrice\":" + strs[i][5] + ","//当前价格
					+ "\"todayHightestPrice\":" + strs[i][6] + "," //今日最高价
					+ "\"todayLowestPrice\":" + strs[i][7] + ","//今日最低价
					+ "\"bidsPrice\":" + strs[i][8] + ","//竞买价，即“买一”报价
					+ "\"auctionPrice\":" + strs[i][9] + ","//竞卖价，即“卖一”报价；
					+ "\"stockAllDealVolumes\":" + strs[i][10] + ","//成交的股票数
					+ "\"stockAllDealMoney\":" + strs[i][11] + ","//成交金额，单位为“元”
					+ "\"lateBuy\":["
		                    + "{\"buyVolumes\":" + strs[i][12] + ","
		                    	+ "\"buyPrice\":" + strs[i][13] + "},"//{买一，买一报价}
		                    + "{\"buyVolumes\":" + strs[i][14] + ","
		                    	+ "\"buyPrice\":" + strs[i][15] + "},"//{买二，买二报价}
		                    + "{\"buyVolumes\":" + strs[i][16] + ","
		                    	+ "\"buyPrice\":" + strs[i][17] + "},"//{买三，买三报价}
		                    + "{\"buyVolumes\":" + strs[i][18] + ","
		                    	+ "\"buyPrice\":" + strs[i][19] + "},"//{买四，买四报价}
		                    + "{\"buyVolumes\":" + strs[i][20] + ","
		                    	+ "\"buyPrice\":" + strs[i][21] + "},"//{买五，买五报价}			
		                    + "],"
		              + "\"lataSale\":[" 
		              		+ "{\"saleVolumes\":" + strs[i][22] + ","
		              			+ "\"salePrice\":" + strs[i][23] + "},"//{买一，买一报价}
		              		+ "{\"saleVolumes\":" + strs[i][24] + ","
		              			+ "\"salePrice\":" + strs[i][25] + "},"//{买二，买二报价}
		              		+ "{\"saleVolumes\":" + strs[i][26] + ","
		              			+ "\"salePrice\":" + strs[i][27] + "},"//{买三，买三报价}
		              		+ "{\"saleVolumes\":" + strs[i][28] + ","
		              			+ "\"salePrice\":" + strs[i][29] + "},"//{买四，买四报价}
			                + "{\"saleVolumes\":" + strs[i][30] + ","
			                  	+ "\"salePrice\":" + strs[i][31] + "},"//{买五，买五报价}
			                + "],"
			          + "\"date\":\"" + strs[i][32] + "\","//日期
			          + "\"time\":\"" + strs[i][33] + "\","//时间
		    		  + "},";
		}		
		jsonStr += "],}";
		
		return jsonStr;
		
	}
	
	public static void main(String argv[]){
		String str="var hq_str_sh601003=\"柳钢股份,4.80,4.81,4.84,4.85,4.68,4.83,4.84,13312943,63256888,42065,4.83,122420,4.82,101000,4.81,189099,4.80,134600,4.79,1700,4.84,150860,4.85,104120,4.86,31900,4.87,54640,4.88,2015-04-16,11:35:54,00\";";
		new SinaData(str);
	}
	
}

//var hq_str_sh601003="柳钢股份,4.80,4.81,4.84,4.85,4.68,4.83,4.84,13312943,63256888,42065,4.83,122420,4.82,101000,4.81,189099,4.80,134600,4.79,1700,4.84,150860,4.85,104120,4.86,31900,4.87,54640,4.88,2015-04-16,11:35:54,00";
