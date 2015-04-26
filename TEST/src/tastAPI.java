import org.apache.log4j.Logger;  
import org.apache.log4j.PropertyConfigurator; 
  
public class tastAPI {
	  /** 
     * @param args 
     */  
    static Logger logger = Logger.getLogger(tastAPI.class.getName());  
  
    public static void main(String[] args) {  
  
        PropertyConfigurator.configure ("src//log4j.properties");  
  
        logger.debug("Debug ≤‚ ‘");  
  
        logger.info("Info ≤‚ ‘");  
  
        logger.warn("Warn ≤‚ ‘");  
  
        logger.error("Error ≤‚ ‘");    }  


  
}
