package jsonTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;

public class rwTest {
	
	
	public String ReadFile(String path){
		File file = new File(path);
		BufferedReader reader = null;
		String laststr = "";
		try {
		//System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			//一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
			//显示行号
				//System.out.println("line " + line + ": " + tempString);
				laststr = laststr + tempString;
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return laststr;
	}
	//把json格式的字符串写到文件
	public void writeFile(String filePath, String sets) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		PrintWriter out = new PrintWriter(fw);
		out.write(sets);
		out.println();
		fw.close();
		out.close();
	}
	public static void main(String argv[]){
		rwTest jsonRW = new rwTest();
		String jsonStr = jsonRW.ReadFile("data\\stock.json");
		try {
			JSONObject jsonObj = new JSONObject(jsonStr);
			System.out.println(jsonObj);
			System.out.println(jsonObj.getJSONObject("1000881").get("name"));
			jsonObj.getJSONObject("1000881").accumulate("苹果", "苹果");
			System.out.println(jsonObj);
			String str = jsonObj.toString();
			try {
				jsonRW.writeFile("data\\write.json", str);
				System.out.println("aaaaa");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(jsonObj);
		System.out.println("\u2193");
	}
}
