package ui;

	import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.*;
public class Dlg_ChangeMoney extends Dialog
 {
		
		public Dlg_ChangeMoney(Shell parent) {
			
			super(parent, SWT.NONE);
			// TODO Auto-generated constructor stub
		}


		 public void open()
		 {
			 Display display =Display.getDefault();
			 Shell shell=new Shell(display,SWT.CLOSE);
			 shell.setSize(400,200);
			 shell.setText("修改本金");
			 shell.setLayout(null);
			 //修改本金
			 Label label=new Label(shell,SWT.FILL);
			 label.setText("修改本金");
			 label.setBounds(96,61,50,27);
			 
			 Text money=new Text(shell,SWT.BORDER);
			 money.setBounds(170,58,128,30);
			 
			 //确定按钮
			 
			 Button btn=new Button(shell,SWT.PUSH);
			 btn.setText("确定");
			 btn.setBounds(170,120,50,30);
			 		 
			 shell.layout();
			 shell.open();
			 
	        Shell parentShell = (Shell) shell.getParent();
		     
		 while (!shell.isDisposed())
			 {
				if(parentShell.isDisposed()){
						display.sleep();
					}
				 if(!display.readAndDispatch()) 
					{
						display.sleep();
					}
				 shell.dispose();
			 }
		 } 
			 
		/*public static void main(String[] args)
				{
					
					  try {
						   ChangeMoney window = new ChangeMoney();
						   window.open();
						  } catch (Exception e) {
						   e.printStackTrace();
						  }
				}*/
	}
