import java.util.logging.Logger;; 
public class testAPI2 {

	public static void main(String[] args)
	{
		Logger log=Logger.getLogger("test");
		Logger logtmp=Logger.getLogger("test");
		log.fine("fine��־����");
		log.info("info��־����");
		log.warning("warning��־����");
		System.out.println(log==logtmp);
		System.out.println(System.getProperty("java.home"));
	}
}
