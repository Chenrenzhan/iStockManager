package controller;

/*
 * 导入导出
 */

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import models.RecordsSet;

public class ImEx_port {
	private final static String[] HEAD = { "股票名称", "股票编号", "日期", "类型", "价格",
			"数量", "税率", "佣金", "说明", "备注", "操作" };

	// 导入
	public static void Import(String path) {
		String[][] strArray = Excel.read(path);
		try {
			new TotalAssets().setCapital(Double.valueOf(strArray[strArray.length-1][0]));
		} catch (NumberFormatException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[][] rsStrArray=new String[strArray.length-1][];
		for(int i=0;i<rsStrArray.length;i++){
			for(int j=0;j<strArray[0].length;j++){
				strArray[i][j]=rsStrArray[i][j];
			}
		}
		RecordsSet rs;
		try {
			rs = new RecordsSet(rsStrArray);
			rs.save();

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// 写入日志
			log logger = new log();
			logger.getError("ImEx中的import方法有问题，异常E1");
		}
	}

	// 导出
	public static void Export(String path) {
		try {
			RecordsSet rs = new RecordsSet();
			// JSONObject rsJsonObj = rs.getRecordsSet();
			String[][] strArray = rs.jsonToStringArray();
			String[][] str = new String[strArray.length + 2][];
			str[0] = HEAD;
			for (int i = 1; i < str.length-1; ++i) {
				str[i] = strArray[i - 1];
			}

			str[str.length-1][0]=
			String.valueOf(TotalAssets.getCapital());
//			new TotalAssets().setCapital(d);//保存修改后的本金
			Excel.write(path, str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 写入日志
			log logger = new log();
			logger.getError("ImEx中的Export方法有问题,异常1");
		} catch (FileNotFoundException e) {
			// 文件被占用或创建失败，应该生成一个提示窗口
			// TODO
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 写入日志
			log logger = new log();
			logger.getError("ImEx中的import方法有问题,异常2");
		}

	}
}
