import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class testAPI {
	public static void main(String[] args){
				Logger log=Logger.getLogger("test");
					Handler[] handleList=log.getHandlers();
					System.out.println("test的handler数:"+handleList.length);
					handleList=log.getParent().getHandlers();
					System.out.println("test的父级handler数:"+handleList.length);
					for(Handler h:handleList){
						System.out.println(h);
					}
					log.addHandler(new ConsoleHandler());
					log.info("两个handler处理，一个自定义，另一个是祖先");
				}

}
