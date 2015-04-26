package controller;

/*
 * 从新浪获取单支股票数据并临时保存到本地
 */

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

import org.json.JSONException;

import models.SinaStockData;

public class GetInfoFromSina implements Runnable{

	private static final String FILEPATH = "data/temp";
	
	private String code;
	
	public GetInfoFromSina(String code) {
		 this.code = code;
    }
	
	public static String getData(String stockCode){
		URL url = null;
		String urlStr = "http://hq.sinajs.cn/list=" + stockCode;
		String str = "";
		BufferedReader reader = null;
		try {
//			ur = new URL("http://hq.sinajs.cn/list=sh601006,sh601003,sh601001");
			url = new URL(urlStr);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			reader = new BufferedReader(new InputStreamReader(url.openStream(),
					"GBK"));
			
			String line;
			while ((line = reader.readLine()) != null) {
				str += line;
			}                                                       
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static void getKChart(String stockCode){
		try {
			download(stockCode, "min", "min.gif");
			download(stockCode, "daily", "daily.gif");
			download(stockCode, "weekly", "weekl.gif");
			download(stockCode, "monthly", "monthly.gif");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void download(String code, String type, String imageName) 
			throws Exception {
	    String urlStr = "http://image.sinajs.cn/newchart/"
	    		+ type + "/n/" + code+ ".gif";
		// 构造URL
	    URL url = new URL(urlStr);
	    // 打开连接
	    URLConnection con = url.openConnection();
	    //设置请求超时为5s
	    con.setConnectTimeout(5*1000);
	    // 输入流
	    InputStream is = con.getInputStream();
	
	    // 1K的数据缓冲
	    byte[] bs = new byte[1024];
	    // 读取到的数据长度
	    int len;
	    // 输出的文件流
//	    final String path = FILEPATH + imageName;
	   File sf=new File(FILEPATH);
	   if(!sf.exists()){
		   sf.mkdirs();
	   }
	   OutputStream os = new FileOutputStream(sf.getPath()+"/"+imageName);
	    // 开始读取
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    // 完毕，关闭所有链接
	    os.close();
	    is.close();
	} 
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String str = getData(code);
		try {
			try {
				new SinaStockData(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getKChart(code);
	}

	public static void main(String argv[]){
		GetInfoFromSina gifs = new GetInfoFromSina("sh601006");
		Thread td = new Thread(gifs);
		td.start();
	}
}
