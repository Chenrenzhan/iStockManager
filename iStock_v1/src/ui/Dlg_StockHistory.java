
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
		GridLayout mainGridLayout = new GridLayout(2, false);
		shell.setLayout(mainGridLayout);
		
        Label stockRec = new Label(shell,SWT.NONE);
        stockRec.setText("��Ʊ��¼");
	    
		Button setRecord = new Button(shell, SWT.PUSH);
		setRecord.setLayoutData(new GridData(SWT.BEGINNING, SWT.NONE, false,
				false));
		setRecord.setText("�޸�");
		setRecord.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					StockSituation stockDetails=new StockSituation(shell);
					stockDetails.open("�޸ļ�¼",stockName);
				} catch (Exception e2) {
					// TODO: handle exception
					   e2.printStackTrace();
				}
			}
		});
		
	     
		 shell.layout();
		 shell.open();
		 //��ȡ������shell
		 Shell parentShell = (Shell) shell.getParent();
		 while (!shell.isDisposed())
		 {
			 //�жϸ������Ƿ�رգ��ر�����Ӵ���Ҳ�ر�
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
//			   window.open("�޸Ľ���");
//			  } catch (Exception e) {
//			   e.printStackTrace();
//			  }
//	}
		
}
