package ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.widgets.*;

public class Dlg_Set extends Dialog {
	private Shell _shell;
	public Dlg_Set(Shell parent) {
		
		super(parent, SWT.NONE);
		// TODO Auto-generated constructor stub
	}


	 public void open()
	 {
		 Display display =Display.getDefault();
		 Shell shell=new Shell(display,SWT.CLOSE);
		 setShell(shell);
		 shell.setSize(400,200);
		 shell.setText("设置");
		 shell.setLayout(null);
		 //两个提示语
		 Button select1=new Button(shell,SWT.CHECK);
		 select1.setText("自动清除记录");
		 select1.setBounds(100,40,250,30);
		 
		 Button select2=new Button(shell,SWT.CHECK);
		 select2.setText("状态栏滚动显示“实时资产”");
		 select2.setBounds(100,75,250,30);
		 //保存按钮
		 
		 Button Savebtn=new Button(shell,SWT.PUSH);
		 Savebtn.setText("保存");
		 Savebtn.setBounds(170,120,50,30);
		 Savebtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					try {
						System.out.println("sssss");
						_shell.dispose();
					} catch (Exception er) {
						er.printStackTrace();
					}
				}
			});	 
		 shell.layout();
		 shell.open();
		 
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
	 }


	private void setShell(Shell shell) {
		// TODO Auto-generated method stub
		_shell=shell;
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
