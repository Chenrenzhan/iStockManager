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
			download(stockCode, "weekly", "weekly.gif");
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
	   
//	   resizeImage(is, os, 300, "");
	} 
	
	
	/**
	 * 改变图片的大小到宽为size，然后高随着宽等比例变化
	 * @param is 上传的图片的输入流
	 * @param os 改变了图片的大小后，把图片的流输出到目标OutputStream
	 * @param size 新图片的宽
	 * @param format 新图片的格式
	 * @throws IOException
	 */
	public static void resizeImage(InputStream is, OutputStream os, int size, String format) throws IOException {
		BufferedImage prevImage = ImageIO.read(is);
		double width = prevImage.getWidth();
		double height = prevImage.getHeight();System.out.println(width);
		double percent = size/width;
		int newWidth = (int)(width * percent);
		int newHeight = (int)(height * percent);
		BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);
		Graphics graphics = image.createGraphics();
		graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
		ImageIO.write(image, format, os);
		os.flush();
		is.close();
		os.close();
	}
	
	@Override
	public void run() {System.out.println("run");
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
