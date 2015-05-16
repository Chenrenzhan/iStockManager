package ui;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import util.Constant;
import util.RefreshTask;
import util.UIController;

<<<<<<< HEAD
=======


>>>>>>> origin/master
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.json.JSONException;

import com.ibm.icu.util.Calendar;

import controller.SettingControl;

public class MainFrame {
	private DataBindingContext m_bindingContext;
	public boolean refreshFlag = false;
	private Display display;
	protected Shell shell;

	private MyMenu menu;

	private TabFolder tabFolder;
	private TabItem ownershipTabItem;
	private TabItem WealTabItem;
	private Composite statusbar;
	private Label statusbarLabel;

	private WealTabItemComposite wealTabItemComposite;
	private OwnershipTabItemComposite OwnershipTabItemComposite;

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
		return file.exists();
	}

	/**
	 * Open the window.
	 */
	public void open() {

		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		if (!checkRecordExist()) {
			DlgImport dlg = new DlgImport(getShell());
			dlg.open();
		}
		Timer timer = new RefreshTask(display).schedulePreriodicRf();// 建立周期任务
		// while(true){
		while (!shell.isDisposed()) {

			if (!display.readAndDispatch()) {
				// System.out.println("MainFrame sleep");;
				// display.sleep();
				// System.out.println("Mainframe");

			}
		}
		new SettingControl().autoClearHistoryIfSetted();
		timer.cancel();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		this.shell = new Shell(display, SWT.CLOSE | SWT.MIN);
		shell.setToolTipText("");
		shell.setSize(1000, 620);
		shell.setText("SWT Application");

		menu = new MyMenu(shell);

		createToolbar();// 创建工具栏

		createStatusbar(shell);// 创建状态栏

		createTab(shell);

		Constant.PreriodicRefresh.addUI(wealTabItemComposite);
		Constant.PreriodicRefresh.addUI(OwnershipTabItemComposite);
		Constant.RecordChangeRefresh.addUI(wealTabItemComposite);
		Constant.RecordChangeRefresh.addUI(OwnershipTabItemComposite);

	}

	// 创建工具栏
	protected void createToolbar() {
		final ToolBar bar = new ToolBar(shell, SWT.FLAT);
		bar.setSize(984, 45);
<<<<<<< HEAD
		bar.setLocation(0, 0);

		// bar.setForeground(Color.BLACK);

		ToolItem importToolItem = new ToolItem(bar, SWT.PUSH);
		importToolItem.setWidth(50);
		importToolItem.setToolTipText("导入");
		// openToolItem.setText("导入");
=======
		bar.setLocation(0,0);
		
		ToolItem importToolItem = new ToolItem(bar, SWT.PUSH);
		importToolItem.setWidth(50);
		importToolItem.setToolTipText("导入");
>>>>>>> origin/master

		Image importIcon = new Image(display, "icon/import.png");
		importToolItem.setImage(importIcon);
		Image exportIcon = new Image(display, "icon/export.png");
<<<<<<< HEAD
		// openToolItem.setHotImage(importIcon);

		ToolItem exportToolItem = new ToolItem(bar, SWT.PUSH);
		exportToolItem.setWidth(50);
		// saveToolItem.setText("Tool");
		exportToolItem.setToolTipText("导出");
		exportToolItem.setImage(exportIcon);

		// ToolItem space = new ToolItem(bar, SWT.);

		ToolItem setToolItem = new ToolItem(bar, SWT.PUSH);
		setToolItem.setWidth(50);
		// saveToolItem.setText("Tool");
		setToolItem.setToolTipText("设置");
		Image setIcon = new Image(display, "icon/set.png");
		setToolItem.setImage(setIcon);
		setToolItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				Dlg_Set dlg=new Dlg_Set(shell);
				dlg.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		ToolItem addToolItem = new ToolItem(bar, SWT.PUSH);
		addToolItem.setWidth(50);
		// saveToolItem.setText("Tool");
=======

		ToolItem exportToolItem = new ToolItem(bar, SWT.PUSH);
		exportToolItem.setWidth(50);
		exportToolItem.setToolTipText("导出");
		exportToolItem.setImage(exportIcon);

		ToolItem setToolItem = new ToolItem(bar, SWT.PUSH);
		setToolItem.setWidth(50);
		setToolItem.setToolTipText("设置");
		Image setIcon = new Image(display, "icon/set.png");
		setToolItem.setImage(setIcon);

		ToolItem addToolItem = new ToolItem(bar, SWT.PUSH);
		addToolItem.setWidth(50);
>>>>>>> origin/master
		addToolItem.setToolTipText("添加新股");
		Image addIcon = new Image(display, "icon/add.png");
		addToolItem.setImage(addIcon);
		Image exitIcon = new Image(display, "icon/exit.png");
<<<<<<< HEAD

		bar.setVisible(true);

		// ToolItem cleanToolItem = new ToolItem(bar, SWT.PUSH);
		// cleanToolItem.setText("清除历史");
		// cleanToolItem.setToolTipText("清除历史");
		// Image cleanIcon = new Image(display, "icon/export.png");
		// cleanToolItem.setImage(cleanIcon);

		ToolItem exitToolItem = new ToolItem(bar, SWT.PUSH);
		exitToolItem.setWidth(50);
		// saveToolItem.setText("Tool");
		exitToolItem.setToolTipText("退出");
		exitToolItem.setImage(exitIcon);
=======
		addToolItem.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				DlgAddNewStock ds = new DlgAddNewStock(shell, SWT.CLOSE | SWT.MIN);
				ds.open();
			}
			
		});

		ToolItem exitToolItem = new ToolItem(bar, SWT.PUSH);
		exitToolItem.setWidth(50);
		exitToolItem.setToolTipText("退出");
		exitToolItem.setImage(exitIcon);
		
		bar.setVisible(true);
>>>>>>> origin/master
	}

	// 创建状态栏
	private void createStatusbar(Composite parent) {
		statusbar = new Composite(parent, SWT.BORDER);
		// 设置工具栏在Shell中的形状为水平抢占充满，并高19像素
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 19;
		statusbar.setLayoutData(gridData);
		// 设置为用行列式布局管理状态栏里的组件
		RowLayout layout = new RowLayout();
		layout.marginLeft = layout.marginTop = 0; // 无边距
		statusbar.setLayout(layout);
		// 创建一个用于显示文字的标签
		statusbarLabel = new Label(statusbar, SWT.BORDER);
		statusbarLabel.setLayoutData(new RowData(70, -1));
		statusbarLabel.setText("状态栏");
		statusbar.setVisible(true);
		createProgressBar(statusbar);

		statusbar.setSize(994, 30);
		statusbar.setLocation(0, 541);
	}

	// 创建进度条
	private ProgressBar createProgressBar(Composite parent) {
		ProgressBar progressBar = new ProgressBar(parent, SWT.SMOOTH);
		progressBar.setMinimum(0); // 最小值
		progressBar.setMaximum(100);// 最大值
		return progressBar;
	}

	private void createTab(Shell parent) {

		tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 45, 974, 480);

		// 构成图和个人资产
		WealTabItem = new TabItem(tabFolder, SWT.NONE);
		WealTabItem.setText("资产");
		wealTabItemComposite = new WealTabItemComposite(tabFolder, SWT.NONE);
		WealTabItem.setControl(wealTabItemComposite);

		// 持股构成
		ownershipTabItem = new TabItem(tabFolder, SWT.NONE);
		ownershipTabItem.setText("持仓情况");

		// Label label = new Label(tabFolder, SWT.CENTER);
		// GridData gridData = new
		// GridData(GridData.FILL_HORIZONTAL|GridData.FILL_VERTICAL);
		// label.setData(gridData);
		// label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14,
		// SWT.NORMAL));
		// label.setAlignment(SWT.CENTER);
		// ownershipTabItem.setControl(label);
		// label.setText("网络连接失败");

		// try {
		OwnershipTabItemComposite = new OwnershipTabItemComposite(tabFolder,
				SWT.NONE);
		ownershipTabItem.setControl(OwnershipTabItemComposite);

		// } catch (UnknownHostException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		//
		// Label label = new Label(tabFolder, SWT.CENTER);
		// GridData gridData = new
		// GridData(GridData.FILL_HORIZONTAL|GridData.FILL_VERTICAL);
		// label.setData(gridData);
		// label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14,
		// SWT.NORMAL));
		// label.setAlignment(SWT.CENTER);
		// ownershipTabItem.setControl(label);
		// label.setText("网络连接失败");
		// }catch(IOException e){
		// e.printStackTrace();
		// }

	}

	public Shell getShell() {
		return shell;
	}

}
