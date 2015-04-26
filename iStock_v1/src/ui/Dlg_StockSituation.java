package ui;

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

public class Dlg_StockSituation extends Dialog {
	public Dlg_StockSituation(Shell parent) {
		super(parent, SWT.NONE);
		// TODO Auto-generated constructor stub
	}

	private GridData data;
	private String []array1={"买入","卖出"}; 
	private String []array2={"%","‰"}; 
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void open(String operation,String str)
	{
	    Display display=Display.getDefault();
		Shell shell=new Shell(display,SWT.CLOSE);
		shell.setSize(550,580);
		shell.setText(str);
		shell.setLayout(null);
		
		data=new GridData();
		/*data.horizontalAlignment= GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 3;
		data.verticalSpan = 4;
		data.heightHint = 50;*/
		//新增交易标题
	     Label gain =new Label(shell, SWT.FILL);
	     gain.setText(operation);
	     gain.setBounds(20,20,60,30);
	     gain.setVisible(true);
	     gain.setLayoutData(data);
	     //当前价格	     
	     Label stock =new Label(shell, SWT.FILL);
	     //data =new GridData();
	    // data.widthHint=300;
	    // data.heightHint = 30;
	     stock.setLayoutData(data);
	     stock.setText(str+",当前价：¥42.66");
	     stock.setBounds(20,50,200,30);
	     stock.setVisible(true);
	     //类型，买入或者卖出
	     Label type1 =new Label(shell, SWT.FILL);
	    // data =new GridData();
	    // data.widthHint=100;
	     type1.setLayoutData(data);
	     type1.setText("类型");
	     type1.setBounds(350,50,60,30);
	     type1.setVisible(true);
	     
	     Combo typeCombo = new Combo(shell,SWT.DROP_DOWN);
	     typeCombo.setBounds(410,50,100,20);
	     typeCombo.setItems(array1);
	     typeCombo.setVisible(true);
	     
	     //日期栏
	     Label datelabel =new Label(shell, SWT.FILL);
	    // data =new GridData();
	     datelabel.setLayoutData(data);
	     datelabel.setText("日期");
	     datelabel.setBounds(20, 100, 60, 30);
	     datelabel.setVisible(true);
	     
	     Text dateText= new Text(shell, SWT.BORDER);
	    // data =new GridData();
	     dateText.setBounds(80,100,200,25);
	     
	     //价格栏
	     Label priceLabel =new Label(shell,SWT.FILL);
	     priceLabel.setText("价格");
	     priceLabel.setBounds(20, 140, 60, 30);
	     
	     Text priceText =new Text(shell,SWT.BORDER);
	     priceText.setBounds(80,140,200,25);
	     
	     //数量栏
	     Label countLabel =new Label(shell,SWT.FILL);
	     countLabel.setText("数量");
	     countLabel.setBounds(20, 180, 60, 30);
	     
	     Text countText =new Text(shell,SWT.BORDER);
	     countText.setBounds(80,180,200,25);
	     
	     //佣金栏
	     Label moneyLabel =new Label(shell,SWT.FILL);
	     moneyLabel.setText("佣金");
	     moneyLabel.setBounds(20, 220, 60, 30);
	     
	     Text moneyText =new Text(shell,SWT.BORDER);
	     moneyText.setBounds(80,220,200,25);
	     
	     Combo moneyCombo = new Combo(shell,SWT.DROP_DOWN);
	     moneyCombo.setBounds(300,220,100,20);
	     moneyCombo.setItems(array2);
	     moneyCombo.setVisible(true);
	     
	     //税率
	     Label taxLabel =new Label(shell,SWT.FILL);
	     taxLabel.setText("税率");
	     taxLabel.setBounds(20, 260, 60, 30);
	     
	     Text taxText =new Text(shell,SWT.BORDER);
	     taxText.setBounds(80,260,200,25);
	     
	     Combo taxCombo = new Combo(shell,SWT.DROP_DOWN);
	     taxCombo.setBounds(300,260,100,20);
	     taxCombo.setItems(array2);
	     taxCombo.setVisible(true);
	     
	     //备注栏
	     Label otherLabel =new Label(shell,SWT.FILL);
	     otherLabel.setText("备注");
	     otherLabel.setBounds(20,300,60,30);
	     
	     Text otherText =new Text(shell,SWT.V_SCROLL);
	     otherText.setBounds(80,300,320,80);
	     
	     //保存按钮
	     Button saveButton= new Button(shell,SWT.PUSH);
	     saveButton.setText("保存");
	     saveButton.setBounds(350,450,50,30);
	     //取消按钮
	     Button cancelButton =new Button(shell,SWT.PUSH);
	     cancelButton.setText("取消");
	     cancelButton.setBounds(420,450,50,30);
	     
	     
	     
		 shell.layout();
		 shell.open();
		 //获取父窗口shell
		 Shell parentShell = (Shell) shell.getParent();
		 while (!shell.isDisposed())
		 {
			 //判断父窗口是否关闭，关闭则把子窗口也关闭
			if(parentShell.isDisposed()){
				display.sleep();
			}
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		 }
		 shell.dispose();
	} 
	
	

	/*public static void main(String[] args)
	{
	
	  try {
		   StockSituation window = new StockSituation(this);
			   window.open("修改交易");
			  } catch (Exception e) {
			   e.printStackTrace();
			  }
	}*/

//	public static void main(String[] args)
//	{
//	
//	  try {
//		   StockSituation window = new StockSituation(this);
//			   window.open("修改交易");
//			  } catch (Exception e) {
//			   e.printStackTrace();
//			  }
//	}

		
}