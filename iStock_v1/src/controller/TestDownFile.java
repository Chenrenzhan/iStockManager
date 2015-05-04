package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestDownFile {
	public static void main(String[] args) {
		String sURL = "http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/600000.phtml?year=2015&jidu=1";
		
		/*
		int nStartPos = 0;
		int nRead = 0;
		String sName = "book.csv";
		String sPath = "data/temp";
		try {
			URL url = new URL(sURL);
			// 打开连接
			HttpURLConnection httpConnection = (HttpURLConnection) url
					.openConnection();
			if(httpConnection == null){
				System.out.println("aaaaaaaaaaaaaaaa");
			}
			
			// 获得文件长度
			long nEndPos = getFileSize(sURL);
			RandomAccessFile oSavedFile = new RandomAccessFile(sPath + "\\"
					+ sName, "rw");
			httpConnection
					.setRequestProperty("User-Agent", "Internet Explorer");
			String sProperty = "bytes=" + nStartPos + "-";
			// 告诉服务器book.rar这个文件从nStartPos字节开始传
			httpConnection.setRequestProperty("RANGE", sProperty);
			System.out.println(sProperty);
			InputStream input = httpConnection.getInputStream();
			byte[] b = new byte[1024];
			// 读取网络文件,写入指定的文件中
			while ((nRead = input.read(b, 0, 1024)) > 0 && nStartPos < nEndPos) {
				oSavedFile.write(b, 0, nRead);
				nStartPos += nRead;
				System.out.println(b);
			}
			httpConnection.disconnect();
		} catch (Exception e) {
			System.out.println("dddddddddddddd");
			e.printStackTrace();
		}
	}

	// 获得文件长度
	public static long getFileSize(String sURL) {
		int nFileLength = -1;
		try {
			URL url = new URL(sURL);
			HttpURLConnection httpConnection = (HttpURLConnection) url
					.openConnection();
			httpConnection
					.setRequestProperty("User-Agent", "Internet Explorer");

			int responseCode = httpConnection.getResponseCode();
			if (responseCode >= 400) {
				System.err.println("Error Code : " + responseCode);
				return -2; // -2 represent access is error
			}
			String sHeader;
			for (int i = 1;; i++) {
				sHeader = httpConnection.getHeaderFieldKey(i);
				if (sHeader != null) {
					if (sHeader.equals("Content-Length")) {
						nFileLength = Integer.parseInt(httpConnection
								.getHeaderField(sHeader));
						break;
					}
				} else
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(nFileLength);
		return nFileLength;
	}
	*/
		
		
		connet();
	}
	
	public static void connet(){
		
		URL url;  
		String str = "";
	    try {  
	    	url = new URL("http://table.finance.yahoo.com/table.csv?s=000001.sz&d=3&e=22&f=2015&g=d&a=1&b=16&c=2015&ignore=.csv");
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),
					"GBK"));
			
			String line;
			while ((line = reader.readLine()) != null) {
				str += line;
				System.out.println(line);
			} 
//	         url = new URL("http://www.baidu.com");  
//	         InputStream in = url.openStream();  
	         System.out.println("连接可用");  
	    } catch (Exception e1) {  
	         System.out.println("连接打不开!");  
	         url = null;  
	    }  
	    
//	    String reg = "<table id=\"FundHoldSharesTable\">(.*)</table>";
//	    Pattern pattern = Pattern.compile(reg);
//		Matcher matcher = pattern.matcher(str);
//		if(matcher.find()){
//			System.out.println(matcher.group(1));
//		}
	}
}