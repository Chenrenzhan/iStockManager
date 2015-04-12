package applicationUI;

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
public class ChangeMoney extends Dialog
 {
		
		public ChangeMoney(Shell parent) {
			
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
			 label.setBounds(60,75,50,30);
			 
			 Text money=new Text(shell,SWT.BORDER);
			 money.setBounds(100,75,250,30);
			 
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
