package controller;

import org.apache.log4j.Logger;  
import org.apache.log4j.PropertyConfigurator; 
public class log {
	 static Logger logger = Logger.getLogger(log.class.getName());
	 
	 public  void  getDebug(String str)
	 {
		 PropertyConfigurator.configure ("src//log4j.properties");
		 logger.debug(str);
	 }
	 public  void  getInfo(String str)
	 {
		 PropertyConfigurator.configure ("src//log4j.properties");
		 logger.info(str);
	 }
	 public  void getWarn(String str)
	 {
		 PropertyConfigurator.configure ("src//log4j.properties");
		 logger.warn(str);
	 }
	 public  void getError(String str)
	 {
		 PropertyConfigurator.configure ("src//log4j.properties");
		 logger.error(str);
	 }
	 
}
