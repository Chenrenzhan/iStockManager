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

import javax.imageio.ImageIO;

import org.json.JSONException;

import models.SinaStockData;

public class GetInfoFromSina implements Runnable{

	private static final String FILEPATH = "data/temp";
	
	private String code;
	
	private String fileName;
	
	public GetInfoFromSina(String code, String fileName) {
		 this.code = code;
		 this.fileName = fileName;
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
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String str = getData(code);
		try {
			try {
				new SinaStockData(str, fileName);//保存数据
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		getKChart(code);
	}

	public static void main(String argv[]){
		GetInfoFromSina gifs = new GetInfoFromSina("sh600784,sh600496,sh600569", "stocksSet.json");
		Thread td = new Thread(gifs);
		td.start();
	}
}
