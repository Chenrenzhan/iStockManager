
package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.wb.swt.SWTResourceManager;

public class Dlg_StockHistory extends Dialog {
	private Shell shell;
	private String stockName;
	public Dlg_StockHistory(Shell parent) {
		super(parent, SWT.NONE);
		// TODO Auto-generated constructor stub
	}


	
	public void open(String stockN)
	{
		setStockName(stockN);

	    Display display=Display.getDefault();
		shell=new Shell(display,SWT.CLOSE|SWT.V_SCROLL);
		shell.setSize(550,580);
		shell.setText(stockN);
		GridLayout mainGridLayout = new GridLayout(14, false);
		shell.setLayout(mainGridLayout);
		
        Label stockRec = new Label(shell,SWT.NONE);
        stockRec.setText("股票记录");
	    stockRec.setVisible(false);
		Button setRecord = new Button(shell, SWT.PUSH);
		setRecord.setLayoutData(new GridData(SWT.BEGINNING, SWT.NONE, false,
				false));
		setRecord.setText("修改");
		setRecord.setVisible(false);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label dateLabel = new Label(shell, SWT.NONE);
		dateLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		dateLabel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		dateLabel.setText("\u65E5\u671F");
		new Label(shell, SWT.NONE);
		
		Label typeLabel = new Label(shell, SWT.NONE);
		typeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		typeLabel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		typeLabel.setText("\u7C7B\u578B");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label priceLabel = new Label(shell, SWT.NONE);
		priceLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		priceLabel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		priceLabel.setText("\u4EF7\u683C");
		new Label(shell, SWT.NONE);
		
		Label countLabel = new Label(shell, SWT.NONE);
		countLabel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		countLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		countLabel.setText("\u6570\u91CF");
		new Label(shell, SWT.NONE);
		
		Label remarkLabel = new Label(shell, SWT.NONE);
		remarkLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		remarkLabel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		remarkLabel.setText("\u5907\u6CE8");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label optionLabel = new Label(shell, SWT.NONE);
		optionLabel.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		optionLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		optionLabel.setText("\u64CD\u4F5C");
		new Label(shell, SWT.NONE);
		
		//假数据，后期要删除
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("2015-3-11");
		new Label(shell, SWT.NONE);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("\u5356\u51FA");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label_8 = new Label(shell, SWT.NONE);
		label_8.setText("4.5");
		new Label(shell, SWT.NONE);
		
		Label label_12 = new Label(shell, SWT.NONE);
		label_12.setText("9900");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button setBtn = new Button(shell, SWT.NONE);
		setBtn.setText("\u4FEE\u6539");
		setBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Dlg_StockSituation stockDetails=new Dlg_StockSituation(shell);
					stockDetails.open("修改记录",stockName);
				} catch (Exception e2) {
					// TODO: handle exception
					   e2.printStackTrace();
				}
			}
		});
		Button deleteBtn = new Button(shell, SWT.NONE);
		deleteBtn.setText("\u5220\u9664");
		
		Label label_15 = new Label(shell, SWT.NONE);
		label_15.setText("2015-3-11");
		new Label(shell, SWT.NONE);
		
		Label label_14 = new Label(shell, SWT.NONE);
		label_14.setText("\u5356\u51FA");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label_13 = new Label(shell, SWT.NONE);
		label_13.setText("4.5");
		new Label(shell, SWT.NONE);
		
		Label label_9 = new Label(shell, SWT.NONE);
		label_9.setText("9900");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.setText("\u4FEE\u6539");
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Dlg_StockSituation stockDetails=new Dlg_StockSituation(shell);
					stockDetails.open("修改记录",stockName);
				} catch (Exception e2) {
					// TODO: handle exception
					   e2.printStackTrace();
				}
			}
		});
		Button button_4 = new Button(shell, SWT.NONE);
		button_4.setText("\u5220\u9664");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("2015-3-11");
		new Label(shell, SWT.NONE);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5356\u51FA");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("4.5");
		new Label(shell, SWT.NONE);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("9900");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button button = new Button(shell, SWT.NONE);
		button.setText("\u4FEE\u6539");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("\u5220\u9664");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Dlg_StockSituation stockDetails=new Dlg_StockSituation(shell);
					stockDetails.open("修改记录",stockName);
				} catch (Exception e2) {
					// TODO: handle exception
					   e2.printStackTrace();
				}
			}
		});
		
	     
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




	private void setStockName(String stockN) {
		// TODO Auto-generated method stub
		stockName=stockN;
	} 
	
	
//	public static void main(String[] args)
//	{
//		
//		  try {
//			   StockSituation window = new StockSituation(this);
//			   window.open("修改交易");
//			  } catch (Exception e) {
//			   e.printStackTrace();
//			  }
//	}
		
}
