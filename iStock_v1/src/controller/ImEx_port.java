package controller;

/*
 * 导入导出
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.json.JSONException;
import org.json.JSONObject;

import util.Constant;

import com.novocode.naf.swt.custom.ishell.InternalShell;

import models.Account;
import models.RecordsSet;

public class ImEx_port {
	private final static String[] HEAD = { "股票名称", "股票编号", "日期", "类型", "价格",
			"数量", "税率", "佣金", "说明", "备注", "操作" };

	// 导入
	public static void Import(String path) {
		HashMap<String, String[][]> strHashMap = Excel.read(path);
		System.out.println("导入"+strHashMap.get("浦发证券")[1][1]);
		Account account=new Account();
		account.deleteAllAccountHistory();
		Iterator iterator = strHashMap.keySet().iterator(); 
		while (iterator.hasNext()) { 
		Object key=iterator.next();
		String[][] strArray = strHashMap.get(key); 
		System.out.println("key"+(String)key);
		RecordsSet rs;
		try {
			account.addAccount((String)key);
			rs = new RecordsSet((String)key,strArray);
			rs.save();

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// 写入日志
			log logger = new log();
			logger.getError("ImEx中的import方法有问题，异常E1");
		}
		} 
		redrawAllUI();
		
	}

	// 导出
	public static void Export(String path) {
		try {
			Account acc = new Account();
			ArrayList<String[][]> dataList = new ArrayList<String[][]>();
			ArrayList<String> accounts = acc.getAccounts();
			for (int i = 0; i < accounts.size(); i++) {
				RecordsSet rs = new RecordsSet(accounts.get(i));
				// JSONObject rsJsonObj = rs.getRecordsSet();
				String[][] strArray = rs.jsonToStringArray();
				String[][] str = new String[strArray.length + 1][];
				str[0] = HEAD;
				for (int j = 1; j < str.length; ++j) {
					str[j] = strArray[j - 1];
				}
				dataList.add(str);
			}

			Excel.write(path, accounts, dataList);
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
	private static void redrawAllUI() {
		depostAllShells();
		recreateShells();
		updateMenu();
	}
	
	private static void depostAllShells(){
		Constant.instance.depostAllShells();
	}
	private static void recreateShells(){
		
		Constant.instance.recreateShells();
		
	}
	
	private static void updateMenu(){
		Constant.instance.updateAccountMenu();
	}
}
