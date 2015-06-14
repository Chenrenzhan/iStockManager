package controller;

/*
 * 从新浪获取单只股票指定时间的历史记录
 * 
 * symbol=sh601006&date=15-05-05
 * symbol=股票代码
 * date=股票历史信息的日期
 */

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

public class GetSingleStockHistory implements Runnable {

	private static final String CHARSET = "GBK";

	private String code;
	private String date;
	private double closePrice; // 收盘价

	public GetSingleStockHistory(String code, String date) {
		this.code = code;
		this.date = date;
	}

	public String structUrl(String code, String date) {
		String url_base = "http://vip.stock.finance.sina.com.cn/quotes_service/view/vMS_tradehistory.php?";
		String url = url_base + "symbol=" + code + "&date=" + date;
		return url;

	}

	public double connet() {
		String closePrice = "0.00";

		String url_str = structUrl(code, date);
		URL url = null;
		try {
			url = new URL(url_str);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		int sec_cont = 1000;
		try {
			URLConnection url_con = url.openConnection();
			url_con.setDoOutput(true);
			url_con.setReadTimeout(10 * sec_cont);
			url_con.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
			InputStream html_in = url_con.getInputStream();

			String html_str = InputStream2String(html_in, CHARSET);
			String re = ".*?收盘价:.*?>(\\d+\\.?\\d*)<.*";// .*?(\\d+\\.?\\d*).*
			if (html_str.contains("收盘价")) {
				Pattern pattern = Pattern.compile(re);
				Matcher matcher = pattern.matcher(html_str);
				if (matcher.find()) {
					closePrice = matcher.group(1);
				}
			}
		} catch (UnknownHostException e) {
			return Double.valueOf(closePrice);
		} catch (IOException e) {
			System.out.println("Connect Timeout in Linechart update");
		}

		return Double.valueOf(closePrice);
	}

	/*
	 * 返回查询股票信息的历史记录的收盘价
	 */
	public GetSingleStockHistory getData() throws InterruptedException {

		if (code.contains("sh") || code.contains("sz")) {
			Thread td = new Thread(this);
			td.start();
			td.join();
			closePrice = this.getClosePrice();
		} else {
			GetSingleStockHistory gssh1 = new GetSingleStockHistory(
					"sz" + code, date);
			Thread td1 = new Thread(gssh1);
			td1.start();

			GetSingleStockHistory gssh2 = new GetSingleStockHistory(
					"sh" + code, date);
			Thread td2 = new Thread(gssh2);
			td2.start();
			try {
				td1.join();
				td2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double c1 = gssh1.getClosePrice();
			double c2 = gssh2.getClosePrice();

			if (c1 != 0)
				closePrice = c1;
			else
				closePrice = c2;
		}
		return this;
	}

	public static String InputStream2String(InputStream in_st, String charset)
			throws IOException {
		BufferedReader buff = new BufferedReader(new InputStreamReader(in_st,
				charset));
		StringBuffer res = new StringBuffer();
		String line = "";
		while ((line = buff.readLine()) != null) {
			res.append(line);
		}
		return res.toString();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		closePrice = connet();
	}

	public double getClosePrice() {
		return closePrice;
	}

//	public static void main(String[] args) {
//		int i = 20;
//		while ((i--) != 0) {
//			GetSingleStockHistory gssh = new GetSingleStockHistory("601006",
//					"15-05-05");
//			try {
//				gssh.getData();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////			System.out.println(gssh.getClosePrice());
//		}
//	}
}