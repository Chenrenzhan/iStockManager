package applicationUI;
//单股详情
import java.awt.Font;

import org.eclipse.swt.widgets.Control;
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
		 final Shell shell=new Shell(display,SWT.CLOSE);
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
		 
//		 //重写shell的关闭窗口按钮事件
//		 shell.addListener(SWT.Close, new Listener() {
//			public void handleEvent(Event event) {shell.getShells();
//				Control[] childrenShells = shell.getChildren();
//				System.out.println(childrenShells.length);
//				if(childrenShells.length != 0){
//					int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
//					MessageBox messageBox = new MessageBox(shell, style);
//					messageBox.setText("Information");
//					messageBox.setMessage("Close the shell?");
//					event.doit = messageBox.open() == SWT.YES;
//				}
//				
//			}
//			  });
		 
		 //“添加记录”按钮的点击事件
		 gainBtn.addSelectionListener(new SelectionAdapter(){ 
			 public void widgetSelected(SelectionEvent e) {   
				 try {System.out.println("sssss");
					   StockSituation window = new StockSituation(shell);
					   window.open("添加记录");
					  } catch (Exception er) {
					   er.printStackTrace();
					  }  
	            } 
		 });
		 
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
