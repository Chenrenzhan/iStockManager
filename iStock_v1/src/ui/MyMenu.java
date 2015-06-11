package ui;

import java.util.ArrayList;

import interfac.InternalShellControl;
import models.Account;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
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
	
	private Menu menu_account;
	private MenuItem menuItem_account;
	private MenuItem menuItem_addAccount;
	private MenuItem menuItem_delAccount;
	private ArrayList<MenuItem> accountList;

	// private Menu fileMenu;
	// private MenuItem menuItem_set;
	private MenuItem menuItem_about;
	private Menu menu_about;
	private InternalShellControl targetShell;
	
	//右键弹出菜单
	private Menu POP_UP_Menu;
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	MyMenu(Shell parentShell,InternalShellControl targetShell) {
		this.shell = parentShell;
		this.targetShell=targetShell;
		menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		POP_UP_Menu = new Menu(shell, SWT.POP_UP);
		shell.setMenu(POP_UP_Menu);
		
		accountList = new ArrayList<MenuItem>();
		
		addMenu();
	}

	private void addMenu() {
		fileMenu(); //文件菜单

		operateMenu(); //操作菜单
		
		accountMenu(); //账户菜单
		
		aboutMenu(); //关于菜单

	}

	public void fileMenu() {
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
	}

	public void operateMenu() {
		menuItem_operate = new MenuItem(menu, SWT.CASCADE);
		menuItem_operate.setText("&操作");
		menu_operate = new Menu(menuItem_operate);
		menuItem_operate.setMenu(menu_operate);

		menuItem_set = new MenuItem(menu_operate, SWT.NONE);
		menuItem_set.setText("&设置");

		menuItem_searchStock = new MenuItem(menu_operate, SWT.NONE);
		menuItem_searchStock.setText("&搜索");

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
	}
	
	public void accountMenu(){
		menuItem_account = new MenuItem(menu, SWT.CASCADE);
		menuItem_account.setText("&账号");
		menu_account = new Menu(menuItem_account);
		menuItem_account.setMenu(menu_account);
		
		
		menuItem_addAccount = new MenuItem(menu_account, SWT.NONE);
		menuItem_addAccount.setText("添加新账号");
		Image addAccountIcon = new Image(Display.getDefault(), "icon/addLittle.png");
		menuItem_addAccount.setImage(addAccountIcon);
		
		menuItem_delAccount = new MenuItem(menu_account, SWT.NONE);
		menuItem_delAccount.setText("删除当前账号");
		Image delAccountIcon = new Image(Display.getDefault(), "icon/delete.png");
		menuItem_delAccount.setImage(delAccountIcon);
		
		new MenuItem(menu_account, SWT.SEPARATOR);
		
	}
	
	public ArrayList<MenuItem> addAccountMenuItem(String name){
		MenuItem account = new MenuItem(menu_account, SWT.CHECK);
		account.setText(name);
		account.setSelection(true);
		accountList.add(account);
		return accountList;
	}
	public ArrayList<MenuItem> addAccountMenuItem(ArrayList<String> nameList){
		for(String name : nameList){
			MenuItem account = new MenuItem(menu_account, SWT.CHECK);
			account.setText(name);
			account.setSelection(true);
			accountList.add(account);
		}
		return accountList;
	}
	
	public ArrayList<MenuItem> deleteAccountMenuItem(String name){
		for(MenuItem account : accountList){
			if(account.getText().equals(name)){
				accountList.remove(account);
				account.dispose();
				break;
			}
		}
		return accountList;
	}
	
	//获取第几个账户MuneItem
	public int getIndex(String text){
		int index = -1;
		for(int i = 0; i < accountList.size(); ++i){
			if(accountList.get(i).getText().equals(text)){
				index = i;
				return index;
			}
		}
		return index;
	}
	
	public MenuItem getAccountItemOfIndex(int index){
		try{
			return accountList.get(index);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	public void aboutMenu() {
		menuItem_about = new MenuItem(menu, SWT.CASCADE);
		menuItem_about.setText("关于");

		menuItem_about.setMenu(menu_about);
		menuItem_about.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("about");
				MessageBox aboutBox = new MessageBox(shell, SWT.OK|SWT.CANCEL); 
				aboutBox.setText("关于股俱记");
				aboutBox.setMessage("版本 v1");
				aboutBox.open();
			};
		});
	}
	
	// Menu menu = new Menu(shell, SWT.BAR);
	
	public MenuItem getAddAccountMenuItem(){
		return menuItem_addAccount;
	}
	
	public MenuItem getDelAccountMenuItem(){
		return menuItem_delAccount;
	}
	
	public ArrayList<MenuItem> getAccountList(){
		return accountList;
	}

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
