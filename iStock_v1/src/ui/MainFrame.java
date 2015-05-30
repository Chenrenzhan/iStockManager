package ui;

import java.io.File;
import java.util.Timer;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
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

import controller.IORW;
import controller.ImEx_port;
import controller.SettingControl;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.custom.CLabel;

public class MainFrame {
	private DataBindingContext m_bindingContext;
	public boolean refreshFlag = false;
	private Display display;
	protected Shell shell;

	private MyMenu menu;
	
	private ToolBar toolBar;
	private CoolBar coolBar;
	
	private DesktopForm desktopForm;
	private static int docNum = 0;

	private TabFolder tabFolder;
	private TabItem ownershipTabItem;
	private TabItem WealTabItem;
	private Composite statusbar;
	private Label statusbarLabel;

	private WealTabItemComposite wealTabItemComposite;
	private OwnershipTabItemComposite OwnershipTabItemComposite;
	private CoolItem coolItem;
	private CoolItem coolItem_1;

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
//		new SettingControl().setAutoHistory(false).saveToLocal();
		// che TODO addTimer to PreriodicRefresh;
		Realm.runWithDefault(SWTObservables.getRealm(Constant.homeDisplay),
				new Runnable() {
					public void run() {
						try {
							MainFrame window = new MainFrame();
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
		final String Path = "data/record.json";
		File file = new File(Path);
		String str = IORW.read(Path);
		if (str.equals("{}") | str.isEmpty())
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
		Timer timer = new RefreshTask(display).schedulePreriodicRf();// 建立周期任务
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
		timer.cancel();
	}

	private void clearTemp(){
		File file=new File("data/assets.json");
		file.delete();
		file=new File("data/holdrecord.json");
		file.delete();
		file=new File("data/stock.json");
		file.delete();
		file=new File("data/profit_linechart.json");
		file.delete();
		file=new File("data/profit.json");
		file.delete();
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		this.shell = new Shell(display, SWT.CLOSE | SWT.MIN | SWT.MAX | SWT.RESIZE);
		shell.setToolTipText("");
		shell.setText("股俱记");
		shell.setLayout(new FormLayout());

		menu = new MyMenu(shell);

		createToolbar();// 创建工具栏

//		createStatusbar(shell);// 创建状态栏
		
		createCoolBar();

		createMDI();
		
		Constant.PreriodicRefresh.addUI(wealTabItemComposite);
		Constant.PreriodicRefresh.addUI(OwnershipTabItemComposite);
		Constant.RecordChangeRefresh.addUI(wealTabItemComposite);
		Constant.RecordChangeRefresh.addUI(OwnershipTabItemComposite);

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
		fd_composite.bottom = new FormAttachment(coolBar,0);
		desktopForm.setLayoutData(fd_composite);
		
		createInternalShell(desktopForm, SWT.CLOSE , false, true);
		
		createInternalShell(desktopForm, SWT.CLOSE , false, true);
	}
	
	private  InternalShell createInternalShell(
			DesktopForm desktop, int style, boolean sizeGrip, boolean customMenu) {
		docNum++;
	    InternalShell ishell = new InternalShell(desktop, style);
	    ishell.setText("账号 " + docNum);

	    Composite ishellContent = ishell.getContentPane();

	    createTab(ishellContent);
	    
//	    setCompositeMove(ishell, bar);
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
		fd_toolBar.top = new FormAttachment(0,0);
		fd_toolBar.left = new FormAttachment(0,0);
		fd_toolBar.bottom = new FormAttachment(0,40);
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
				new RefreshTask(shell.getDisplay()).refreshAll();
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
				DlgAddNewStock ds = new DlgAddNewStock(shell, SWT.CLOSE
						| SWT.MIN);
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

	//状态栏
	public void createCoolBar() {
		coolBar = new CoolBar(shell, SWT.FLAT);
		FormData fd_coolBar = new FormData();
		fd_coolBar.bottom = new FormAttachment(100);
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
	
	private void createTab(Composite parent) {

		tabFolder = new TabFolder(parent, SWT.NONE);
		tabFolder.setBounds(0, 0, 974, 480);

		// 构成图和个人资产
		WealTabItem = new TabItem(tabFolder, SWT.NONE);
		WealTabItem.setText("资产");

		// 持股构成
		ownershipTabItem = new TabItem(tabFolder, SWT.NONE);
		ownershipTabItem.setText("持仓情况");

		OwnershipTabItemComposite = new OwnershipTabItemComposite(tabFolder,
				SWT.NONE);
		ownershipTabItem.setControl(OwnershipTabItemComposite);

		wealTabItemComposite = new WealTabItemComposite(tabFolder, SWT.NONE);
		WealTabItem.setControl(wealTabItemComposite);

	}

	public Shell getShell() {
		return shell;
	}
}
