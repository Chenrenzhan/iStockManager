package ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import controller.ImEx_port;
import controller.SettingControl;

import org.eclipse.wb.swt.SWTResourceManager;

public class Dlg_Set extends Dialog {
	private Shell _shell;
	private Shell parentShell;
	private Button select1;
	private Button checkboxAutoExport;
	private Button btnAdd;
	SettingControl settingObject;
	private Text txPath;

	public Dlg_Set(Shell parent) {

		super(parent, SWT.NONE);
		parentShell = getParent();
		settingObject = new SettingControl();
		// TODO Auto-generated constructor stub
	}

	public void open() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.CLOSE);
		setShell(shell);
		shell.setSize(340, 184);
		shell.setText("设置");
		shell.setLayout(null);
		int x = parentShell.getBounds().x + 350;
		int y = parentShell.getBounds().y + 210;
		shell.setBounds(x, y, 340, 250);
		// 两个提示语
		select1 = new Button(shell, SWT.CHECK);
		select1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		select1.setSelection(settingObject.getAutoHistory());
		select1.setText("自动清除记录");
		select1.setBounds(42, 25, 250, 30);

		// Button select2=new Button(shell,SWT.CHECK);
		// select2.setText("状态栏滚动显示“实时资产”");
		// select2.setBounds(100,75,250,30);
		// 保存按钮

		Button Savebtn = new Button(shell, SWT.PUSH);
		Savebtn.setText("保存");
		Savebtn.setBounds(86, 124, 50, 30);
		Savebtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					settingObject.setAutoHistory(select1.getSelection());
					System.out.println(select1.getSelection());
					settingObject.setAutoExport(checkboxAutoExport.getSelection(), txPath.getText());
					settingObject.saveToLocal();
					_shell.dispose();

				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});
		Button Cancelbtn = new Button(shell, SWT.NONE);
		Cancelbtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				_shell.close();
				_shell.dispose();
			}
		});
		Cancelbtn.setText("取消");
		Cancelbtn.setBounds(168, 124, 50, 30);

		checkboxAutoExport = new Button(shell, SWT.CHECK);
		checkboxAutoExport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (checkboxAutoExport.getSelection()) {
					txPath.setEnabled(true);
                    btnAdd.setEnabled(true);
				}
				else{
					txPath.setEnabled(false);
                    btnAdd.setEnabled(false);
				}
			}
		});
		checkboxAutoExport.setText("自动导出到：");
		checkboxAutoExport.setSelection(settingObject.getAutoHistoryStatu());
		checkboxAutoExport.setBounds(42, 53, 104, 30);
		
		txPath = new Text(shell, SWT.BORDER);
		txPath.setEnabled(checkboxAutoExport.getSelection());
		txPath.setEditable(false);
		txPath.setText(settingObject.getAutoHistoryPath());
		txPath.setBounds(52, 86, 195, 23);

	
		btnAdd = new Button(shell, SWT.NONE);
		btnAdd.setEnabled(checkboxAutoExport.getSelection());
		btnAdd.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 8,
				SWT.NORMAL));
		btnAdd.setGrayed(true);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileSelect = new FileDialog(_shell, SWT.SAVE);
				fileSelect
						.setFilterNames(new String[] { "Excel Files (*.xls)" });
				fileSelect.setFilterExtensions(new String[] { "*.xls" });
				String path = "";
				path = fileSelect.open();
				if (path == null) {
					return;
				}
				txPath.setText(path);
			}
		});
		btnAdd.setBounds(257, 91, 55, 17);
		btnAdd.setText("添加");

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
