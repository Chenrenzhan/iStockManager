import java.util.logging.Logger;; 
public class testAPI2 {

	public static void main(String[] args)
	{
		Logger log=Logger.getLogger("test");
		Logger logtmp=Logger.getLogger("test");
		log.fine("fine日志测试");
		log.info("info日志测试");
		log.warning("warning日志测试");
		System.out.println(log==logtmp);
		System.out.println(System.getProperty("java.home"));
	}
}
