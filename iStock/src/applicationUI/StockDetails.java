package applicationUI;
//单股详情
import java.awt.Font;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class StockDetails {
	
	public void open(String stockName)
	{
		Display display =Display.getDefault();
		 Shell shell=new Shell(display,SWT.CLOSE);
		 shell.setSize(750,650);
		 shell.setText("设置");
		 shell.setLayout(null);
		 shell.setVisible(true);
		 
		 //交易记录组合框
		 Group group =new Group(shell,SWT.NONE);
		 group.setText("交易记录");
		 group.setBounds(350,50,350,220);
		 group.setLayout(null);
		 
		 Button gainBtn=new Button(group,SWT.PUSH);
		 gainBtn.setText("添加交易");
		 gainBtn.setBounds(280,20,60,30);
		 gainBtn.setVisible(true);
		 
		 Button allBtn=new Button(group,SWT.PUSH);
		 allBtn.setText("查看全部");
		 allBtn.setBounds(280, 180, 60, 30);
		 allBtn.setVisible(true);
		 
		 Label name =new Label(shell, SWT.FILL);
	     name.setText(stockName);
	     name.setBounds(20,20,60,50);
	     name.setVisible(true);
	     
	     //标签切换图表
	     final TabFolder tab=new TabFolder(shell,SWT.NONE);
	     tab.setBounds(20,280,710,300);
	     //添加标签
	     final TabItem TabI1=new TabItem(tab,SWT.NONE);
	     TabI1.setText("分时");
	     final TabItem TabI2=new TabItem(tab,SWT.NONE);
	     TabI2.setText("日K");
	     final TabItem TabI3=new TabItem(tab,SWT.NONE);
	     TabI3.setText("周K");
	     final TabItem TabI4=new TabItem(tab,SWT.NONE);
	     TabI4.setText("月K");
	     
	     //实时资产
	     Label moneyLabel=new Label(shell, SWT.FILL);
	     moneyLabel.setText("实时资产  :");
	     moneyLabel.setBounds(20, 590, 60, 30);
	     moneyLabel.setVisible(true);
	     
		 while (!shell.isDisposed())
		 {
		 if (!display.readAndDispatch())
		 {
		 display.sleep();
		 }
		 }
		 display.dispose();
	}  

	 public static void main(String[] args)
		{
			
			  try {
				   StockDetails window = new StockDetails();
				   window.open("中信证券");
				  } catch (Exception e) {
				   e.printStackTrace();
				  }
		}

}
