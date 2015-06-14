package ui;

import interfac.InternalShellControl;

import java.io.File;
import java.util.ArrayList;

import models.Account;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;

import util.Constant;
import util.RefreshTask;
import util.UIController;

import com.novocode.naf.swt.custom.ishell.DesktopForm;
import com.novocode.naf.swt.custom.ishell.InternalShell;
import com.novocode.naf.swt.custom.ishell.internal.TitleBarButton;

import controller.IORW;
import controller.ImEx_port;
import controller.SettingControl;

public class MainFrame_vMDI implements InternalShellControl {

	private DataBindingContext m_bindingContext;
	private ArrayList<InternalShell> ShellList;
	public boolean refreshFlag = false;
	private Display display;
	protected Shell shell;

	private MyMenu menu;

	private ArrayList<MenuItem> accountList;
	private Account account;
	ArrayList<String> accountNameList;

	private ToolBar toolBar;
	private CoolBar coolBar;

	private DesktopForm desktopForm;
	

	private static int docNum = 0;

	private TabFolder tabFolder;
	private TabItem ownershipTabItem;
	private TabItem WealTabItem;
	private Composite statusbar;
	private Label statusbarLabel;

	// private WealTabItemComposite wealTabItemComposite;
	// private OwnershipTabItemComposite OwnershipTabItemComposite;
	private CoolItem coolItem;
	private CoolItem coolItem_1;

	public MainFrame_vMDI() {
		account = new Account();
		accountNameList = account.getAccounts();
		ShellList = new ArrayList<InternalShell>();
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Constant.homeDisplay = Display.getDefault();
		Constant.PreriodicRefresh = new UIController(
				UIController.PreriodicMethod);
		Constant.RecordChangeRefresh = new UIController(
				UIController.RecordChangeMethod);
		// new SettingControl().setAutoHistory(false).saveToLocal();
		// che TODO addTimer to PreriodicRefresh;
		Realm.runWithDefault(SWTObservables.getRealm(Constant.homeDisplay),
				new Runnable() {
					public void run() {
						try {
							MainFrame_vMDI window = new MainFrame_vMDI();
							Constant.instance = window;
							window.open();
							// System.out.println("Mainframe");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}

	private static boolean checkRecordExist() {
		// TODO Auto-generated method stub
		// FIXME
		final String Path = "data/accounts.json";
		File file = new File(Path);
		String str = IORW.read(Path);
		if (str.equals("[]") | str.isEmpty())
			return false;
		else
			return file.exists();

	}

	/**
	 * Open the window.
	 */
	public void open() {

		display = Display.getDefault();

		System.out.println("in" + " Import dlg");
		createContents();

		shell.open();

		shell.layout();
		if (!checkRecordExist()) {

			DlgImport dlg = new DlgImport(getShell());
			dlg.open();
			

		}

		new RefreshTask(shell.getDisplay()).refreshAll();
		// Timer timer = new RefreshTask(display).schedulePreriodicRf();//
		// 建立周期任务
		// while(true){
		while (!shell.isDisposed()) {

			if (!display.readAndDispatch()) {
				// System.out.println("MainFrame sleep");;
				// display.sleep();
				// System.out.println("Mainframe");

			}
		}
		new SettingControl().autoExportIfSetted().autoClearHistoryIfSetted();
		clearTemp();
		// timer.cancel();
	}

	private void clearTemp() {
		File file = new File("data/holdrecord.json");
		file.delete();
		file = new File("data/stock.json");
		file.delete();
		file = new File("data/profit_linechart.json");
		file.delete();
		file = new File("data/profit.json");
		file.delete();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		this.shell = new Shell(display, SWT.CLOSE | SWT.MIN | SWT.MAX
				| SWT.RESIZE);
		shell.setToolTipText("");
		shell.setText("股俱记");
		shell.setLayout(new FormLayout());

		menu = new MyMenu(shell, this);

		createToolbar();// 创建工具栏

		// createStatusbar(shell);// 创建状态栏

		// createCoolBar();

		createMDI();

	}

	public void setCompositeMove(Composite moveComposite, Composite composite) {
		CompositeMoveListener listener = new CompositeMoveListener(
				moveComposite);
		composite.addListener(SWT.MouseDown, listener);
		composite.addListener(SWT.MouseMove, listener);
	}

	public void createMDI() {
		desktopForm = new DesktopForm(shell, SWT.NONE);
		desktopForm.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(toolBar, 0);
		fd_composite.left = new FormAttachment(toolBar, 0, SWT.LEFT);
		fd_composite.right = new FormAttachment(100, 0);
		fd_composite.bottom = new FormAttachment(100, 0);
		desktopForm.setLayoutData(fd_composite);
		// ShellList = new ArrayList<InternalShell>();

		ArrayList<String> accName = new Account().getAccounts();
		InternalShellFactory shellFactory = new InternalShellFactory(ShellList,
				desktopForm);
		for (int i = 0; i < accName.size(); i++) {
			shellFactory.createInternalShell(InternalShellFactory.MAINSHELL,
					accName.get(i));

		}
		createAccountMenuItem();
		setIshellListener();
	}

	// 账号菜单
	public void createAccountMenuItem() {
		menu.addAccountMenuItem(accountNameList);

		MenuItem addAccountItem = menu.getAddAccountMenuItem();
		addAccountItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				DlgAddAccount dlg = new DlgAddAccount(shell);
				dlg.open();
				String value = dlg.getValue();
				if (value != null) {
					accountNameList = account.addAccount(value);
					// accountNameList = account.getAccounts();
					InternalShell ishell = createInternalShell(desktopForm,
							SWT.ON_TOP | SWT.MIN | SWT.CLOSE, false, true,
							value);
					ShellList.add(ishell);

					accountList = menu.addAccountMenuItem(value);
					setIshellListener();
					setAccountItemListener();
				}
			}
		});

		// 删除账号
		MenuItem delAccountItem = menu.getDelAccountMenuItem();
		delAccountItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				InternalShell ishell = activeShell();
				if(ishell == null){
					return ;
				}
				String accountName = ishell.getAccount();

				MessageBox delWarmBox = new MessageBox(shell, SWT.OK
						| SWT.CANCEL);
				delWarmBox.setText("删除账号");
				String message = "确定删除当前账号：" + accountName + "?";
				delWarmBox.setMessage(message);
				int result = delWarmBox.open();
				if (result == SWT.CANCEL) {
					return;
				}
				ShellList.remove(ishell);
				accountList = menu.deleteAccountMenuItem(accountName);
				accountNameList = account.deleteAccount(accountName);
				ishell.dispose();
				
			}

		});

		setAccountItemListener();

	}

	public void depostAllShells(){
		System.out.println("delete all");
//		ArrayList<InternalShell> shelllist=Constant.instance.getShellList();
		for (int i = 0; i < ShellList.size(); i++) {
			ShellList.get(i).dispose();
			System.out.println("         ssss         " + ShellList.get(i).isDisposed());
		}
		account.deleteAllAccount();
		ShellList = null;
	}
	public void recreateShells(){
		System.out.println("recreate");
		if(ShellList == null ){
			ShellList = new ArrayList<InternalShell>();
		}
		
		accountNameList = new Account().getAccounts();
		for (int i = 0; i < accountNameList.size(); i++) {
			InternalShell ishell = 
					createInternalShell(desktopForm, 
							SWT.ON_TOP | SWT.MIN| SWT.CLOSE, false, true, 
							accountNameList.get(i));
			ShellList.add(ishell);
		}
//		return shellList;
	}
	
	public void updateAccountMenu() {
		menu.deleteAllAccountMenu();
//		createAccountMenuItem();
		setIshellListener();
		setAccountItemListener();

	}
	public void setAccountItemListener() {
		accountList = menu.getAccountList();
		for (int i = 0; i < accountList.size(); ++i) {
			MenuItem accountItem = accountList.get(i);
			accountItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					MenuItem account = (MenuItem) e.getSource();
					int index = menu.getIndex(account.getText());
					if (index == -1) {
						return;
					}
					InternalShell ishell = ShellList.get(index);
					if (account.getSelection()) {
						ishell.setActive();
					} else {
						ishell.setMinimized(true);
					}
				}
			});
		}
	}

	private void setIshellListener() {
		for (int i = 0; i < ShellList.size(); ++i) {
			final InternalShell ishell = ShellList.get(i);

			TitleBarButton closeButton = ishell.getCloseButton();
			TitleBarButton minButton = ishell.getMinButton();
			closeButton.addListener(SWT.Selection, new Listener() {
				// @Override
				public void handleEvent(Event event) {
					ishell.setMinimized(true);
					int index = ShellList.indexOf(ishell);
					MenuItem accountItem = menu.getAccountItemOfIndex(index);
					accountItem.setSelection(false);
				}
			});
			minButton.addListener(SWT.Selection, new Listener() {
				// @Override
				public void handleEvent(Event event) {
					ishell.setMinimized(true);
					int index = ShellList.indexOf(ishell);
					MenuItem accountItem = menu.getAccountItemOfIndex(index);
					accountItem.setSelection(false);
				}
			});
		}
	}

	public InternalShell createInternalShell(DesktopForm desktop, int style,
			boolean sizeGrip, boolean customMenu, String account) {
		InternalShell ishell = new InternalShell(desktop, style, account);
		ishell.setText("账号 " + account);

		Composite ishellContent = ishell.getContentPane();

		createTab(account, ishellContent);

		// setCompositeMove(ishell, bar);
		setCompositeMove(ishell, tabFolder);

		ishell.pack();
		ishell.open();
		return ishell;
	}

	// 创建工具栏
	protected void createToolbar() {
		toolBar = new ToolBar(shell, SWT.FLAT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(100, 0);
		fd_toolBar.top = new FormAttachment(0, 0);
		fd_toolBar.left = new FormAttachment(0, 0);
		fd_toolBar.bottom = new FormAttachment(0, 40);
		toolBar.setLayoutData(fd_toolBar);

		ToolItem importToolItem = new ToolItem(toolBar, SWT.PUSH);
		importToolItem.setWidth(50);
		importToolItem.setToolTipText("导入");
		importToolItem.addSelectionListener(new SelectionAdapter() {
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
			}
		});

		Image importIcon = new Image(display, "icon/import.png");
		importToolItem.setImage(importIcon);
		Image exportIcon = new Image(display, "icon/export.png");

		ToolItem exportToolItem = new ToolItem(toolBar, SWT.PUSH);
		exportToolItem.setWidth(50);
		exportToolItem.setToolTipText("导出");
		exportToolItem.setImage(exportIcon);
		exportToolItem.addSelectionListener(new SelectionAdapter() {
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

		ToolItem setToolItem = new ToolItem(toolBar, SWT.PUSH);
		setToolItem.setWidth(50);
		setToolItem.setToolTipText("设置");
		Image setIcon = new Image(display, "icon/set.png");
		setToolItem.setImage(setIcon);
		setToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				Dlg_Set dlg = new Dlg_Set(shell);
				dlg.open();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		ToolItem addToolItem = new ToolItem(toolBar, SWT.PUSH);
		addToolItem.setWidth(50);

		addToolItem.setToolTipText("添加新股");
		Image addIcon = new Image(display, "icon/add.png");
		addToolItem.setImage(addIcon);
		Image exitIcon = new Image(display, "icon/exit.png");

		addToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				InternalShell ishell = activeShell();
				if (ishell == null) {
					return;
				}
				String account = ishell.getAccount();
				if (account == null)
					return;
				DlgAddNewStock ds = new DlgAddNewStock(shell, SWT.CLOSE
						| SWT.MIN, account);
				ds.open();
			}

		});

		ToolItem exitToolItem = new ToolItem(toolBar, SWT.PUSH);
		exitToolItem.setWidth(50);
		exitToolItem.setToolTipText("退出");
		exitToolItem.setImage(exitIcon);
		exitToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});

		toolBar.setVisible(true);

	}

	// public String activeShell(){
	// for(InternalShell ishell : ShellList){
	// if(ishell.isActiveShell()){
	// return ishell.getAccount();
	// }
	// }
	// return null;
	// }

	public InternalShell activeShell() {
		for (InternalShell ishell : ShellList) {
			if (ishell.isActiveShell()) {
				return ishell;
			}
		}
		return null;
	}

	// 状态栏
	public void createCoolBar() {
		coolBar = new CoolBar(shell, SWT.FLAT);
		coolBar.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		FormData fd_coolBar = new FormData();
		fd_coolBar.top = new FormAttachment(toolBar, 0);
		// fd_coolBar.bottom = new FormAttachment(100);
		fd_coolBar.left = new FormAttachment(toolBar, 0, SWT.LEFT);
		fd_coolBar.right = new FormAttachment(100, 0);
		coolBar.setLayoutData(fd_coolBar);

		coolItem_1 = new CoolItem(coolBar, SWT.NONE);
		coolItem_1.setText("weew");
		coolItem_1.setSize(11, 20);

		coolItem = new CoolItem(coolBar, SWT.NONE);
		coolItem.setText("coolBar");

		CLabel lblNewLabel = new CLabel(coolBar, SWT.NONE);
		coolItem.setControl(lblNewLabel);
		lblNewLabel.setText("New Label");
	}

	private void createTab(String account, Composite parent) {

		tabFolder = new TabFolder(parent, SWT.NONE);
		tabFolder.setBounds(0, 0, 974, 480);

		// 构成图和个人资产
		WealTabItem = new TabItem(tabFolder, SWT.NONE);
		WealTabItem.setText("资产");

		// 持股构成
		ownershipTabItem = new TabItem(tabFolder, SWT.NONE);
		ownershipTabItem.setText("持仓情况");

		OwnershipTabItemComposite OwnershipTabItemComposite = new OwnershipTabItemComposite(
				tabFolder, SWT.NONE, account);
		ownershipTabItem.setControl(OwnershipTabItemComposite);

		WealTabItemComposite wealTabItemComposite = new WealTabItemComposite(
				account, tabFolder, SWT.NONE);
		WealTabItem.setControl(wealTabItemComposite);
		Constant.PreriodicRefresh.addUI(wealTabItemComposite);
		Constant.PreriodicRefresh.addUI(OwnershipTabItemComposite);
		Constant.RecordChangeRefresh.addUI(wealTabItemComposite);
		Constant.RecordChangeRefresh.addUI(OwnershipTabItemComposite);

	}

	public Shell getShell() {
		return shell;
	}

	

	public ArrayList<InternalShell> getShellList(){
		return ShellList;
	}
	
	public DesktopForm getDesktopForm() {
		return desktopForm;
	}
	
	public MyMenu getMenu() {
		return menu;
	}

	
	@Override
	public void commandCreateMyInternalShell(String account) {
		// TODO Auto-generated method stub
		new InternalShellFactory(ShellList, desktopForm).createInternalShell(
				InternalShellFactory.MAINSHELL, account);
	}

	@Override
	public void commandRemoveMyInternalShell(String account) {
		// TODO Auto-generated method stub

	}

}
