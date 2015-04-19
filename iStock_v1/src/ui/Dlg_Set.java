package ui;

import org.eclipse.swt.widgets.Display;
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

import util.Constant;

public class Dlg_Set extends Dialog {
	private Shell shell;
	public Dlg_Set(Shell parent) {
		
		super(parent, SWT.NONE);
		// TODO Auto-generated constructor stub
	}


	 public void open()
	 {
		
		 shell=new Shell(Constant.homeDisplay,SWT.CLOSE);
		 shell.setSize(400,200);
		 shell.setText("����");
		 shell.setLayout(null);
		 //������ʾ��
		 Button select1=new Button(shell,SWT.CHECK);
		 select1.setText("�Զ������¼");
		 select1.setBounds(100,40,250,30);
		 
		 Button select2=new Button(shell,SWT.CHECK);
		 select2.setText("״̬��������ʾ��ʵʱ�ʲ���");
		 select2.setBounds(100,75,250,30);
		 //���水ť
		 
		 Button Savebtn=new Button(shell,SWT.PUSH);
		 Savebtn.setText("����");
		 Savebtn.setBounds(170,120,50,30);
		 Savebtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					try {
						shell.dispose();
					} catch (Exception e2) {
						// TODO: handle exception
						
					}
				};
			});		 
		 shell.layout();
		 shell.open();
		 
         Shell parentShell = (Shell) shell.getParent();
	     
		 while (!shell.isDisposed())
		 {
			 if(parentShell.isDisposed()){
					Constant.homeDisplay.sleep();
				}
				if (!Constant.homeDisplay.readAndDispatch()) {
					Constant.homeDisplay.sleep();
				}
		 }
	 }
	 
	/* public static void main(String[] args)
		{
			
			  try {
				   Set window = new Set(this);
				   window.open();
				  } catch (Exception e) {
				   e.printStackTrace();
				  }
		}*/
//	 public static void main(String[] args)
//		{
//			
//			  try {
//				   Set window = new Set(this);
//				   window.open();
//				  } catch (Exception e) {
//				   e.printStackTrace();
//				  }
//		}

}
