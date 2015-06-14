package calTest;



import static org.junit.Assert.*;

import java.io.IOException;

import models.Account;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.HoldStock;

//
public class HoldStockTest {
	//private static HoldStock holdstock=new HoldStock() throws IOException{};
 static String account;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		account="test";
	}

	@Before
	public void setUp() throws Exception {
	}
	
	//测试计算摊薄成本
	@Test
	public void testCountDilutedCost() throws IOException {
		HoldStock holdstock=new HoldStock(account);
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
    	HoldStock holdstock=new HoldStock(account);
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
		HoldStock holdstock=new HoldStock(account);
		double curPrice=4.0;
		double yPrice=3.8;
		double realNum=0.05;
		double testNum=holdstock.CalrisefallRatio(curPrice, yPrice);
		System.out.println(testNum);
		assertEquals(realNum,testNum,0.1);
	}
	
	//测试计算盈亏率
	@Test
	public void testCalbeRatio() throws IOException
	{
		// 盈亏（简化）= （现价*0.9955 - 成本*1.0035）*持有量
				// ，系数：0.0035和0.995分别是印花税。券商佣金、杂费的折合值
		HoldStock holdstock=new HoldStock(account);
		//传入数据
		double curPrice=4.54;
		double holdCost =3.85;
		double holdSum=200;
		double holdMoney=20000;
		
		//期待数据
		double realRatio=0.05;
		double testRatio=holdstock.CalbeRatio(curPrice, holdCost, holdSum, holdMoney);
		//测试
		System.out.println(testRatio);
		assertEquals(realRatio,testRatio,0.1);		
	}
	
	


    


	

}