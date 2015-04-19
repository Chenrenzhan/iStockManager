package ui;
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
import org.omg.CORBA.PRIVATE_MEMBER;

import ui.StockSituation;
import ui.Dlg_StockHistory;
public class StockDetails extends Dialog{
	private String stockName;
	private String operation;
public StockDetails(Shell parent) {
		

		super(parent, SWT.NONE);
		// TODO Auto-generated constructor stub
	}
	public void open(String stockN)
	{
		setStockName(stockN);
		Display display =Display.getDefault();
		 final Shell shell=new Shell(display,SWT.CLOSE);
		 shell.setSize(750,650);
		 shell.setText("设置");
		 shell.setLayout(null);
		 shell.setVisible(true);
		 
		 //交易记录组合框
		 Group group =new Group(shell,SWT.NONE);
		 group.setText("交易记录");
		 group.setBounds(312,20,418,243);
		 group.setLayout(null);
		 
		 Button gainBtn=new Button(group,SWT.PUSH);
		 gainBtn.setText("添加交易");
		 gainBtn.setBounds(278,203,60,30);
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
					   window.open("添加记录",stockName);
					  } catch (Exception er) {
					   er.printStackTrace();
					  }  
	            } 
		 });
		 
		 Button allBtn=new Button(group,SWT.PUSH);
		 allBtn.setText("查看全部");
		 allBtn.setBounds(348, 203, 60, 30);
		 allBtn.setVisible(true);
		 
		 Label dateLabel = new Label(group, SWT.NONE);
		 dateLabel.setBounds(13, 34, 38, 17);
		 dateLabel.setText("\u65E5\u671F");
		 
		 Label leiLabel = new Label(group, SWT.NONE);
		 leiLabel.setBounds(83, 34, 38, 17);
		 leiLabel.setText("\u7C7B\u578B");
		 
		 Label priceLabel = new Label(group, SWT.NONE);
		 priceLabel.setBounds(144, 34, 46, 17);
		 priceLabel.setText("\u4EF7\u683C");
		 
		 Label countLabel = new Label(group, SWT.NONE);
		 countLabel.setBounds(196, 34, 46, 17);
		 countLabel.setText("\u6570\u91CF");
		 
		 Label texLabel = new Label(group, SWT.NONE);
		 texLabel.setBounds(248, 34, 38, 17);
		 texLabel.setText("\u7A0E\u7387");
		 
		 Label MoneyLabel = new Label(group, SWT.NONE);
		 MoneyLabel.setBounds(292, 34, 46, 17);
		 MoneyLabel.setText("\u4F63\u91D1");
		 
		 Label optionLabel = new Label(group, SWT.NONE);
		 optionLabel.setBounds(357, 34, 51, 17);
		 optionLabel.setText("\u64CD\u4F5C");
		 
		 Button changeBtn = new Button(group, SWT.NONE);
		 changeBtn.setBounds(330, 62, 36, 17);
		 changeBtn.setText("\u4FEE\u6539");
		 
		 Button deleteBtn = new Button(group, SWT.NONE);
		 deleteBtn.setBounds(372, 62, 36, 17);
		 deleteBtn.setText("\u5220\u9664");
		 
		 
		 //假数据，后期要删除
		 Label label = new Label(group, SWT.NONE);
		 label.setBounds(10, 62, 66, 17);
		 label.setText("2015-03-11");
		 
		 Label label_1 = new Label(group, SWT.NONE);
		 label_1.setBounds(83, 62, 38, 17);
		 label_1.setText("\u5356\u51FA");
		 
		 Label label_2 = new Label(group, SWT.NONE);
		 label_2.setBounds(144, 62, 38, 17);
		 label_2.setText("4.5");
		 
		 Label label_3 = new Label(group, SWT.NONE);
		 label_3.setBounds(196, 62, 46, 17);
		 label_3.setText("9900");
		 
		 Label label_4 = new Label(group, SWT.NONE);
		 label_4.setBounds(248, 62, 36, 17);
		 label_4.setText("1%");
		 
		 Label label_5 = new Label(group, SWT.NONE);
		 label_5.setBounds(286, 62, 38, 17);
		 label_5.setText("0.3\u2030");
		 
		 Label label_6 = new Label(group, SWT.NONE);
		 label_6.setText("2015-03-11");
		 label_6.setBounds(10, 85, 66, 17);
		 
		 Label label_7 = new Label(group, SWT.NONE);
		 label_7.setText("\u5356\u51FA");
		 label_7.setBounds(83, 85, 38, 17);
		 
		 Label label_8 = new Label(group, SWT.NONE);
		 label_8.setText("4.5");
		 label_8.setBounds(144, 85, 38, 17);
		 
		 Label label_9 = new Label(group, SWT.NONE);
		 label_9.setText("9900");
		 label_9.setBounds(196, 85, 46, 17);
		 
		 Label label_10 = new Label(group, SWT.NONE);
		 label_10.setText("1%");
		 label_10.setBounds(248, 85, 36, 17);
		 
		 Label label_11 = new Label(group, SWT.NONE);
		 label_11.setText("0.3\u2030");
		 label_11.setBounds(286, 85, 38, 17);
		 
		 Button button = new Button(group, SWT.NONE);
		 button.setText("\u4FEE\u6539");
		 button.setBounds(330, 87, 36, 17);
		 
		 Button button_1 = new Button(group, SWT.NONE);
		 button_1.setText("\u5220\u9664");
		 button_1.setBounds(372, 87, 36, 17);
		 allBtn.addSelectionListener(new SelectionAdapter(){ 
			 public void widgetSelected(SelectionEvent e) {   
				 try {System.out.println("sssss");
					   Dlg_StockHistory window = new Dlg_StockHistory(shell);
					   window.open(stockName);
					  } catch (Exception er) {
					   er.printStackTrace();
					  }  
	            } 
		 });
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
	     
	     Shell parentShell = (Shell) shell.getParent();
	     
		 while (!shell.isDisposed())
		 {
			 if(parentShell.isDisposed()){
					display.sleep();
				}
				if (!display.readAndDispatch()) {
					display.sleep();
				}
		 }
		 display.dispose();

	}
	private void setStockName(String stockN) {
		// TODO Auto-generated method stub
		stockName=stockN;
	}  
}
