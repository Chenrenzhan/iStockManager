package ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;
import org.json.JSONException;

import controller.GetSingleStock;
import controller.SettingControl;
public class Dlg_Search extends Dialog {
	private Shell _shell;
	private Shell parentShell;
	private String _account;
	private Text text;
	private String code;

	public Dlg_Search(Shell parent,String account) {

		super(parent, SWT.NONE);
		parentShell=getParent();
		_account=account;
		// TODO Auto-generated constructor stub
	}

	public void open() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.CLOSE);
		setShell(shell);
		shell.setSize(308, 113);
		shell.setText("搜索");
		shell.setLayout(null);
		int x=parentShell.getBounds().x+350;
		int y=parentShell.getBounds().y+210;
		shell.setBounds(x,y,308,152);

		// Button select2=new Button(shell,SWT.CHECK);
		// select2.setText("状态栏滚动显示“实时资产”");
		// select2.setBounds(100,75,250,30);
		// 保存按钮

		Button Savebtn = new Button(shell, SWT.PUSH);
		Savebtn.setText("搜索");
		Savebtn.setBounds(128, 68, 50, 30);
		Savebtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {

						// TODO Auto-generated method stub
						String c = text.getText();
						GetSingleStock gifs = new GetSingleStock(c);
						Thread td = new Thread(gifs);
						td.start();
						try {
							td.join();
						} catch (InterruptedException ie) {
							// TODO Auto-generated catch block
							ie.printStackTrace();
						}
						if(gifs.getIsError()){
							int style = SWT.APPLICATION_MODAL | SWT.YES;
							MessageBox messageBox = new MessageBox(_shell, style);
							messageBox.setText("提示");
							messageBox.setMessage("股票代码有误，请确认再输入！");
							e.doit = messageBox.open() == SWT.YES;
//							messageBox(e, "提示", "股票代码有误，请确认再输入！");
						}
						else{
							if(c.contains("sh")){
								c = c.replace("sh", "");
							}
							if(c.contains("sz")){
								c = c.replace("sz", "");
							}
							code = c;
							_shell.close();
							_shell.dispose();
							DlgStockDetails dlg=new DlgStockDetails(parentShell, code,_account);
							dlg.open();
						}
					
			
			}
		});
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(87, 39, 159, 23);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(20, 42, 61, 17);
		lblNewLabel.setText("股票代码");

		shell.layout();
		shell.open();

		Shell parentShell = getParent();

		while (!shell.isDisposed()) {
			if (parentShell.isDisposed()) {
				display.sleep();
			}
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	private void setShell(Shell shell) {
		// TODO Auto-generated method stub
		_shell = shell;
	}
}
