import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class testAPI {
	public static void main(String[] args){
				Logger log=Logger.getLogger("test");
					Handler[] handleList=log.getHandlers();
					System.out.println("test��handler��:"+handleList.length);
					handleList=log.getParent().getHandlers();
					System.out.println("test�ĸ���handler��:"+handleList.length);
					for(Handler h:handleList){
						System.out.println(h);
					}
					log.addHandler(new ConsoleHandler());
					log.info("����handler����һ���Զ��壬��һ��������");
				}

}
