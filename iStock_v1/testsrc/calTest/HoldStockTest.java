//package calTest;
//
//import java.lang.*;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import controller.HoldStock;
//
//
//public class HoldStockTest {
//	private static HoldStock holdstock=new HoldStock();
//
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		
//	}
//
//	@Before
//	public void setUp() throws Exception {
//	}
//	//测试计算摊薄成本
//	@Test
//	public void testCountDilutedCost() {
//		double dilution[]={0,0,0};
//
//		String[] str={"买入","##200*##","200","2","1"};
//		double t[]=holdstock.countDilutedCost(dilution,str);
//		System.out.println(t[0]);
//		System.out.println(holdstock.countDilutedCost(dilution,str));
//		double s[]={40000.0,120000.0,200.0};
//		assertEquals(t,holdstock.countDilutedCost(dilution,str));
//
//		
//		
//	}
//
//	
//
//}
