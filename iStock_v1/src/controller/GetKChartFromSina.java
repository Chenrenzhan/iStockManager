package controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

import models.StocksSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.dnd.SwtUtil;

public class GetKChartFromSina implements Runnable {
	private  static final String ROOTPATH = "data/";
	private  final String ACCOUNTNAME;
	private static final String FILENAME = "/temp/";

	private String code;
	private String kType; // K线图类型，分时、日K、周K、月K
	private boolean dl_completed = true;

	public GetKChartFromSina(String account,String code, String kType) {
		ACCOUNTNAME=account;
		this.code = code;

		this.kType = kType;
	}

	public static String getKCPathInTemp(String account, String code,String type){
		return ROOTPATH+account+FILENAME+code+type+".gif";
	}
	
	public static void getAllKChart(String account,Shell shell, String stockCode)
			throws UnknownHostException {

		// 获取分时K线图线程
		GetKChartFromSina minK = new GetKChartFromSina(account,stockCode, "min");
		Thread minTd = new Thread(minK);
		minTd.start();

		// 获取日K线图线程
		GetKChartFromSina dailyK = new GetKChartFromSina(account,stockCode, "daily");
		Thread dailyTd = new Thread(dailyK);
		dailyTd.start();

		// 获取周K线图线程
		GetKChartFromSina weeklyK = new GetKChartFromSina(account,stockCode, "weekly");
		Thread weeklyTd = new Thread(weeklyK);
		weeklyTd.start();

		// 获取月K线图线程
		GetKChartFromSina monthlyK = new GetKChartFromSina(account,stockCode, "monthly");
		Thread monthlyTd = new Thread(monthlyK);
		monthlyTd.start();

//		// 存储老的光标
//		Cursor oldCursor = Display.getCurrent().getSystemCursor(SWT.CURSOR_ARROW);
//		// 设置等待光标
//		Cursor cursor = Display.getCurrent().getSystemCursor(SWT.CURSOR_WAIT);
//		shell.setCursor(cursor);

		try {
			// tdf.join();// 等待子线程结束
			minTd.join();// 等待子线程结束
			dailyTd.join();
			weeklyTd.join();
			monthlyTd.join();

		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		shell.setCursor(oldCursor);
		minK.checkDownload();
		dailyK.checkDownload();
		weeklyK.checkDownload();
		monthlyK.checkDownload();
		// 设置会原来的光标样式

	}

	private void checkDownload() throws UnknownHostException {
		if (!this.getDlCompleted()) {
			throw new UnknownHostException(this.getKType());
		}
	};

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
//			System.out.println("KChart test:" + code);
			getKChart(ACCOUNTNAME,code, kType);
			dl_completed = true;
//			System.out.println("k has downloaded");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dl_completed = false;
		}

	}

	public  void getKChart(String account,String stockCode, String kType)
			throws Exception {
//		System.out.println("getKChart   " + stockCode);
		download(account,stockCode, kType, getKChartName(stockCode, kType));
		// download(stockCode, "daily", "daily.gif");
		// download(stockCode, "weekly", "weekly.gif");
		// download(stockCode, "monthly", "monthly.gif");

	}

	public String getKChartName(String code, String kType) {
		return code + kType + ".gif";
	}

	public  void download(String account,String code, String type, String imageName)
			throws IOException {
		String urlStr = "http://image.sinajs.cn/newchart/" + type + "/n/"
				+ code + ".gif";
		// 构造URL
		URL url = new URL(urlStr);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		// final String path = FILEPATH + imageName;
		File sf = new File(ROOTPATH+account+FILENAME);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "/" + imageName);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();

		// resizeImage(is, os, 300, "");

	}

	/**
	 * 改变图片的大小到宽为size，然后高随着宽等比例变化
	 * 
	 * @param is
	 *            上传的图片的输入流
	 * @param os
	 *            改变了图片的大小后，把图片的流输出到目标OutputStream
	 * @param size
	 *            新图片的宽
	 * @param format
	 *            新图片的格式
	 * @throws IOException
	 */
	public static void resizeImage(InputStream is, OutputStream os, int size,
			String format) throws IOException {
		BufferedImage prevImage = ImageIO.read(is);
		double width = prevImage.getWidth();
		double height = prevImage.getHeight();
		System.out.println(width);
		double percent = size / width;
		int newWidth = (int) (width * percent);
		int newHeight = (int) (height * percent);
		BufferedImage image = new BufferedImage(newWidth, newHeight,
				BufferedImage.TYPE_INT_BGR);
		Graphics graphics = image.createGraphics();
		graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
		ImageIO.write(image, format, os);
		os.flush();
		is.close();
		os.close();
	}

	public boolean getDlCompleted() {
		// 获取run线程的执行情况
		return dl_completed;
	}

	public String getKType() {
		return kType;
	}
	// public static void main(String argv[]) {
	// GetKChartFromSina gifs = new GetKChartFromSina("sh600399", "min");
	// Thread td = new Thread(gifs);
	// td.start();
	// }
}
