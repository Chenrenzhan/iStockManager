package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  

public class IORW {
	
	private final static String ENCODING = "utf-8";

	public static String read(String path){
		File file = new File(path);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		BufferedReader reader = null;
		String laststr = "";
		try {
		//System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new InputStreamReader(  
	                new FileInputStream(path), ENCODING)); 
			int line = 1;
			String tempString = "";
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
	public static void write(String filePath, String content)
			throws IOException {
		File file = new File(filePath);  
        file.delete();  
        file.createNewFile();  
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(  
                new FileOutputStream(file), ENCODING));  
        writer.write(content);  
        writer.close();  
	}
	
}
