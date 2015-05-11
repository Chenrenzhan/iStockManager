package controller;

/*
 * 从新浪获取单支股票数据并临时保存到本地
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import models.SinaStockData;
import controller.log;

public class GetInfoFromSina implements Runnable {

	// private static final String FILEPATH = "data/temp";

	private JSONObject jsonObj;
	private ArrayList<Callable<GetInfoFromSina>> list;
	private String code;

	// private String fileName;

	public GetInfoFromSina(String code) throws IOException {
		System.out.println("lllllllllll     " + code);
		this.code = code;
		this.jsonObj = new JSONObject();

		// this.fileName = fileName;

		// try {
		// structJsonObject(parseString(getData(code)));
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// // 写入日志
		// log logger = new log();
		// logger.getError("GetInfoFromSina发生问题");
		// }

	}

	public static String getData(String stockCode) throws IOException {
		URL url = null;
		System.out.println("saaaaa        " + stockCode);
		try {
			stockCode = structCode(stockCode);
		} catch (IOException e) {
			return "";
		}
		String urlStr = "http://hq.sinajs.cn/list=" + stockCode;
		String str = "";
		BufferedReader reader = null;
		try {
			// ur = new
			// URL("http://hq.sinajs.cn/list=sh601006,sh601003,sh601001");
			url = new URL(urlStr);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			reader = new BufferedReader(new InputStreamReader(url.openStream(),
					"GBK"));

			String line;
			while ((line = reader.readLine()) != null) {
				str += line;
			}
			System.out.println("getData:" + str);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 写入日志
			log logger = new log();
			logger.getError("getData发生问题（1）");
		} catch (UnknownHostException e) {
			throw new UnknownHostException("can't connect to internet");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 写入日志
			log logger = new log();
			logger.getError("getData发生问题（2）");
		}
		str = removeEmpty(str);
		System.out.println("constuctor:" + str);
		// 写入日志
		log logger = new log();
		logger.getInfo(str);
		return str;
	}

	// 构建请求数据的股票编码
	public static String structCode(String stockCode) throws IOException {
		String str = "";
		System.out.println("sssssssss        " + stockCode);
		String[] ca = stockCode.split(",");
		for (int i = 0; i < ca.length; ++i) {
			if (ca[i].contains("sh") || ca[i].contains("sz")) {
				if (i == 0)
					str += ca[i];
				else
					str += "," + ca[i];
				continue;
			} else if (isNum(ca[i])) {
				if (i == 0)
					str += "sh" + ca[i] + ",sz" + ca[i];
				else
					str += ",sh" + ca[i] + ",sz" + ca[i];
			} else
				throw new IOException("contain error charactor");
		}
		// 写入日志
		log logger = new log();
		logger.getInfo("股票编码：" + str);
		return str;
	}

	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) { // 循环遍历字符串

			if (Character.isLetter(str.charAt(i))) { // 用char包装类中的判断字母的方法判断每一个字符
				return false;
			}
		}
		return true;
	}

	// 去掉错误的股票代码返回的空股票信息
	public static String removeEmpty(String str) {

		String re = "var hq_str_.{0,15}=\"\";";
		String s = Pattern.compile(re).matcher(str).replaceAll("");
		// 写入日志
		log logger = new log();
		logger.getInfo("空股票信息：" + s);
		return s;
	}

	public String[][] parseString(String str) {

		System.out.println("parseString:"+str);
		if (str != "") {
			String[][] returnStr;

			String[] stocks = str.split(";");
			int length = stocks.length;
			returnStr = new String[length][];
			for (int i = 0; i < length; ++i) {
				String te = stocks[i].substring(stocks[i].lastIndexOf('_') + 1,
						stocks[i].indexOf('='));
				String imformation = stocks[i].substring(
						stocks[i].indexOf('\"') + 1,
						stocks[i].lastIndexOf('\"'));
				imformation = te.substring(0, 2) + ","
						+ te.substring(2, te.length()) + "," + imformation;
				String[] stock = imformation.split(",");
				returnStr[i] = stock;
			}
			for (int i = 0; i < returnStr[0].length; ++i) {
			}
			// 写入日志
			log logger = new log();
			logger.getInfo(returnStr);
			return returnStr;

		}
		return null;

	}

	public JSONObject structJsonObject(String[][] strs) throws JSONException {

		if (strs == null) {
			return null;
		} else {
			int stockSum = strs.length;

			String[] jsonKeys = new String[] { "stockExchange", "code", "name",
					"todayOpenPrice", "yesterdayClosePrice", "currentPrice",
					"todayHightestPrice", "todayLowestPrice", "bidsPrice",
					"auctionPrice", "stockAllDealVolumes", "stockAllDealMoney",
					"buyFirstVolumes", "buyFirstPrice", "buySecondVolumes",
					"buySecondPrice", "buyThirdVolumes", "buyThirdPrice",
					"buyFourthVolumes", "buyFourthPrice", "buyFifthVolumes",
					"buyFifthPrice", "saleFirstVolumes", "saleFirstPrice",
					"saleSecondVolumes", "saleSecondPrice", "saleThirdVolumes",
					"saleThirdPrice", "saleFourthVolumes", "saleFourthPrice",
					"saleFifthVolumes", "saleFifthPrice", "date", "time" };
			for (int i = 0; i < stockSum; ++i) {
				JSONObject jsonObjSub = new JSONObject();
				if (strs[i].length >= jsonKeys.length) {
					for (int j = 0; j < jsonKeys.length; ++j) {
						jsonObjSub.put(jsonKeys[j], strs[i][j]);
					}

					jsonObj.put(jsonObjSub.getString("code"), jsonObjSub);
				}

			}
			// 写入日志
			log logger = new log();
			logger.getInfo(jsonObj);

			return jsonObj;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String str = getData(code);
			String[][] strArr = parseString(str);

			structJsonObject(strArr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 写入日志
			log logger = new log();
			logger.getError("run发生问题");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public String getCode() {
		return code;
	}

	public static void main(String argv[]) throws IOException {
		GetInfoFromSina gifs = new GetInfoFromSina("sh600784,sh600496,sh600569");
		Thread td = new Thread(gifs);
		td.start();
		System.out.println(gifs.getJsonObj().toString());
	}
}
