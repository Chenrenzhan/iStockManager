package ui;

import interfac.InternalShellControl;
import models.Account;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import util.RefreshTask;
import controller.ImEx_port;
import controller.SettingControl;

public class MyMenu {

	private Shell shell;
	private Menu menu;
	// private MenuBar menuBar;
	private MenuItem menuItem_file;
	private Menu menu_file;
	private MenuItem menuItem_file_import;
	private MenuItem menuItem_file_outport;
	private MenuItem menuItem_file_clean;
	private MenuItem menuItem_file_exit;

	private Menu menu_operate;
	private MenuItem menuItem_operate;
	private MenuItem menuItem_set;
	private MenuItem menuItem_addNewStock;
	private MenuItem menuItem_searchStock;

	// private Menu fileMenu;
	// private MenuItem menuItem_set;
	private MenuItem menuItem_about;
	private Menu menu_about;
	private InternalShellControl targetShell;
	/**
	 * @wbp.parser.entryPoint
	 */
	MyMenu(Shell parentShell,InternalShellControl targetShell) {
		this.shell = parentShell;
		this.targetShell=targetShell;
		menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		addMenu();
	}

	private void addMenu() {
		menuItem_file = new MenuItem(menu, SWT.CASCADE);
		menuItem_file.setText("文件");

		menu_file = new Menu(menuItem_file);
		menuItem_file.setMenu(menu_file);

		menuItem_file_import = new MenuItem(menu_file, SWT.NONE);
		menuItem_file_import.setText("导入");
		menuItem_file_import.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				FileDialog fileSelect = new FileDialog(shell, SWT.OPEN);
				fileSelect.setText("导入");
				fileSelect
						.setFilterNames(new String[] { "Excel Files (*.xls)" });
				fileSelect.setFilterExtensions(new String[] { "*.xls" });
				String path = "";
				// if(path=fileSelect.open() == null)
				path = fileSelect.open();
				if (path == null) {
					return;
				}
				ImEx_port.Import(path);
				new RefreshTask(shell.getDisplay()).scheduleRecordChangeRf();
			}
		});

		menuItem_file_outport = new MenuItem(menu_file, SWT.NONE);
		menuItem_file_outport.setText("导出");
		menuItem_file_outport.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileSelect = new FileDialog(shell, SWT.SAVE);
				fileSelect
						.setFilterNames(new String[] { "Excel Files (*.xls)" });
				fileSelect.setFilterExtensions(new String[] { "*.xls" });
				String path = "";
				path = fileSelect.open();
				if (path == null) {
					return;
				}
				ImEx_port.Export(path);
			}
		});

		menuItem_file_clean = new MenuItem(menu_file, SWT.NONE);
		menuItem_file_clean.setText("清除历史");
		menuItem_file_clean.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
//				new SettingControl().ClearHistory();
				DlgClearHistory dlg=new DlgClearHistory(shell);
				dlg.open();
			}
		});
		
		menuItem_file_exit = new MenuItem(menu_file, SWT.NONE);
		menuItem_file_exit.setText("退出");
		menuItem_file_exit.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});

		menuItem_operate = new MenuItem(menu, SWT.CASCADE);
		menuItem_operate.setText("&操作");
		menu_operate = new Menu(menuItem_operate);
		menuItem_operate.setMenu(menu_operate);

		menuItem_set = new MenuItem(menu_operate, SWT.NONE);
		menuItem_set.setText("&设置");

		menuItem_addNewStock = new MenuItem(menu_operate, SWT.NONE);
		menuItem_addNewStock.setText("&添加账户");
		//FIXME
		menuItem_addNewStock.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DlgAddAccount dlg = new DlgAddAccount(shell);
				dlg.open();
				String value = dlg.getValue();
				if (value != null) {
					new Account().addAccount(value);
					targetShell.commandCreateMyInternalShell(value);

				}
			}
		});

		menuItem_searchStock = new MenuItem(menu_operate, SWT.NONE);
		menuItem_searchStock.setText("&搜索");
		//FIXME
		/*menuItem_searchStock.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Dlg_Search dlg=new Dlg_Search(shell);
				dlg.open();
			}
		});*/

		// menuItem_set.setMenu(menu_set);

		menuItem_set.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					Dlg_Set setDlg = new Dlg_Set(shell);
					setDlg.open();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			};
		});

		menuItem_about = new MenuItem(menu, SWT.CASCADE);
		menuItem_about.setText("关于");

		menuItem_about.setMenu(menu_about);

	}

	// Menu menu = new Menu(shell, SWT.BAR);

	public MenuItem getMenuItem_file_import() {
		return menuItem_file_import;
	}

	public MenuItem getMenuItem_file_outport() {
		return menuItem_file_outport;
	}

	public MenuItem getMenuItem_file_clean() {
		return menuItem_file_clean;
	}

	public MenuItem getMenuItem_file_exit() {
		return menuItem_file_exit;
	}

	public MenuItem getMenuItem_set() {
		return menuItem_set;
	}

	public MenuItem getMenuItem_addNewStock() {
		return menuItem_addNewStock;
	}

	public MenuItem getMenuItem_about() {
		return menuItem_about;
	}

	public void setMenuItem_file_exit(MenuItem menuItem_file_exit) {
		this.menuItem_file_exit = menuItem_file_exit;
	}

}
