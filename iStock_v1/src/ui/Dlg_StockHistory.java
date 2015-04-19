
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
import org.eclipse.wb.swt.SWTResourceManager;

public class Dlg_StockHistory extends Dialog {
	private GridData data;
	private Shell shell;
	private String stockName;
	private String operation;
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
		GridLayout mainGridLayout = new GridLayout(15, false);
		shell.setLayout(mainGridLayout);
		
        Label stockRec = new Label(shell,SWT.NONE);
        stockRec.setText("¹ÉÆ±¼ÇÂ¼");
	    
		Button setRecord = new Button(shell, SWT.PUSH);
		setRecord.setLayoutData(new GridData(SWT.BEGINNING, SWT.NONE, false,
				false));
		setRecord.setText("ÐÞ¸Ä");
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
		new Label(shell, SWT.NONE);
		
		Label dateLabel = new Label(shell, SWT.NONE);
		dateLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		dateLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		dateLabel.setText("\u65E5\u671F");
		new Label(shell, SWT.NONE);
		
		Label typeLabel = new Label(shell, SWT.NONE);
		typeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		typeLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		typeLabel.setText("\u7C7B\u578B");
		new Label(shell, SWT.NONE);
		
		Label priceLabel = new Label(shell, SWT.NONE);
		priceLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		priceLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		priceLabel.setText("\u4EF7\u683C");
		new Label(shell, SWT.NONE);
		
		Label countLabel = new Label(shell, SWT.NONE);
		countLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		countLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		countLabel.setText("\u6570\u91CF");
		new Label(shell, SWT.NONE);
		
		Label texLabel = new Label(shell, SWT.NONE);
		texLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		texLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		texLabel.setText("\u7A0E\u7387");
		new Label(shell, SWT.NONE);
		
		Label mLabel = new Label(shell, SWT.NONE);
		mLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		mLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		mLabel.setText("\u4F63\u91D1");
		new Label(shell, SWT.NONE);
		
		Label remarkLabel = new Label(shell, SWT.NONE);
		remarkLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		remarkLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		remarkLabel.setText("\u5907\u6CE8");
		new Label(shell, SWT.NONE);
		
		Label optionLabel = new Label(shell, SWT.NONE);
		optionLabel.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 10, SWT.NORMAL));
		optionLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		optionLabel.setText("\u64CD\u4F5C");
		
		//¼ÙÊý¾Ý£¬ºóÆÚÒªÉ¾³ý
		Label label_6 = new Label(shell, SWT.NONE);
		label_6.setText("2015-3-11");
		new Label(shell, SWT.NONE);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setText("\u5356\u51FA");
		new Label(shell, SWT.NONE);
		
		Label label_8 = new Label(shell, SWT.NONE);
		label_8.setText("4.5");
		new Label(shell, SWT.NONE);
		
		Label label_12 = new Label(shell, SWT.NONE);
		label_12.setText("9900");
		new Label(shell, SWT.NONE);
		
		Label label_10 = new Label(shell, SWT.NONE);
		label_10.setText("1%");
		new Label(shell, SWT.NONE);
		
		Label label_17 = new Label(shell, SWT.NONE);
		label_17.setText("0.3\u2030");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button setBtn = new Button(shell, SWT.NONE);
		setBtn.setText("\u4FEE\u6539");
		
		Button deleteBtn = new Button(shell, SWT.NONE);
		deleteBtn.setText("\u5220\u9664");
		
		Label label_15 = new Label(shell, SWT.NONE);
		label_15.setText("2015-3-11");
		new Label(shell, SWT.NONE);
		
		Label label_14 = new Label(shell, SWT.NONE);
		label_14.setText("\u5356\u51FA");
		new Label(shell, SWT.NONE);
		
		Label label_13 = new Label(shell, SWT.NONE);
		label_13.setText("4.5");
		new Label(shell, SWT.NONE);
		
		Label label_9 = new Label(shell, SWT.NONE);
		label_9.setText("9900");
		new Label(shell, SWT.NONE);
		
		Label label_11 = new Label(shell, SWT.NONE);
		label_11.setText("1%");
		new Label(shell, SWT.NONE);
		
		Label label_16 = new Label(shell, SWT.NONE);
		label_16.setText("0.3\u2030");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button button_2 = new Button(shell, SWT.NONE);
		button_2.setText("\u4FEE\u6539");
		
		Button button_4 = new Button(shell, SWT.NONE);
		button_4.setText("\u5220\u9664");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("2015-3-11");
		new Label(shell, SWT.NONE);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u5356\u51FA");
		new Label(shell, SWT.NONE);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("4.5");
		new Label(shell, SWT.NONE);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("9900");
		new Label(shell, SWT.NONE);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("1%");
		new Label(shell, SWT.NONE);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("0.3\u2030");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button button = new Button(shell, SWT.NONE);
		button.setText("\u4FEE\u6539");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("\u5220\u9664");
		setRecord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Dlg_StockSituation stockDetails=new Dlg_StockSituation(shell);
					stockDetails.open("ÐÞ¸Ä¼ÇÂ¼",stockName);
				} catch (Exception e2) {
					// TODO: handle exception
					   e2.printStackTrace();
				}
			}
		});
		
	     
		 shell.layout();
		 shell.open();
		 //»ñÈ¡¸¸´°¿Úshell
		 Shell parentShell = (Shell) shell.getParent();
		 while (!shell.isDisposed())
		 {
			 //ÅÐ¶Ï¸¸´°¿ÚÊÇ·ñ¹Ø±Õ£¬¹Ø±ÕÔò°Ñ×Ó´°¿ÚÒ²¹Ø±Õ
			if(parentShell.isDisposed()){
				display.sleep();
			}
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		 }
		 shell.dispose();
	}



	private void setOperation(String str) {
		// TODO Auto-generated method stub
		operation=str;
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
//			   window.open("ÐÞ¸Ä½»Ò×");
//			  } catch (Exception e) {
//			   e.printStackTrace();
//			  }
//	}
		
}
