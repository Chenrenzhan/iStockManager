package calTest;



import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.HoldStock;

//
public class HoldStockTest {
	//private static HoldStock holdstock=new HoldStock() throws IOException{};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
	}
	
	//测试计算摊薄成本
	@Test
	public void testCountDilutedCost() throws IOException {
		HoldStock holdstock=new HoldStock();
		double dilution[]={0,0,0};

		String[] str={"买入","##200*##","200","2","1"};
		double t[]=holdstock.countDilutedCost(dilution,str);
		System.out.println(t[0]);
		System.out.println(holdstock.countDilutedCost(dilution,str));
		//double s[]={40000.0,120000.0,200.0};
		assertEquals(t,holdstock.countDilutedCost(dilution,str));		
	}
	
    //测试计算浮动盈亏
	@Test
	public void testCalBE() throws IOException
    {
    	HoldStock holdstock=new HoldStock();
    	double curPrice=4.0;
    	double holdCost=3.0;
    	int holdSum=100;
    	double realSum=30.0;
    	double testSum=holdstock.CalBE(curPrice, holdCost, holdSum);
    	System.out.println(testSum);
    	assertEquals(realSum,testSum,0.1);
    }
	
	//测试计算涨跌率
	@Test
	public void testCalrisefallRatio() throws IOException
	{
		HoldStock holdstock=new HoldStock();
		double curPrice=4.0;
		double yPrice=3.8;
		double realNum=0.05;
		double testNum=holdstock.CalrisefallRatio(curPrice, yPrice);
		System.out.println(testNum);
		assertEquals(realNum,testNum,0.1);
	}
	


    


	

}