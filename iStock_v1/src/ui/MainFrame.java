package ui;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
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

import util.Constant;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

public class MainFrame {
	private DataBindingContext m_bindingContext;

	private Display display;
	protected Shell shell;
	
	private MyMenu menu;
	
	private TabFolder tabFolder;
	private TabItem ownershipTabItem;
	private TabItem WealTabItem;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Constant.homeDisplay=Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(Constant.homeDisplay), new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.open();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(display,SWT.CLOSE | SWT.MIN);
		shell.setToolTipText("");
		shell.setSize(1000, 620);
		shell.setText("SWT Application");
		
		menu = new MyMenu(shell);
		
		createToolbar();//创建工具栏
		
		createStatusbar(shell);//创建状态栏
	
		createTab(shell);
		
		

	}
	
	//创建工具栏
	protected void createToolbar(){
		final ToolBar bar = new ToolBar(shell, SWT.FLAT);
		bar.setSize(984, 45);
		bar.setLocation(0,0);

		
		
		//bar.setForeground(Color.BLACK);
		
		ToolItem importToolItem = new ToolItem(bar, SWT.PUSH);
		importToolItem.setWidth(40);
		importToolItem.setToolTipText("导入");
//		openToolItem.setText("导入");
		
		Image importIcon = new Image(display, "icon/import.png");
		importToolItem.setImage(importIcon);
//		openToolItem.setHotImage(importIcon);
		
		
		ToolItem exportToolItem = new ToolItem(bar, SWT.PUSH);
//		saveToolItem.setText("Tool");
		exportToolItem.setToolTipText("导出");
		Image exportIcon = new Image(display, "icon/export.png");
		exportToolItem.setImage(exportIcon);

//		ToolItem space = new ToolItem(bar, SWT.);
		
		ToolItem setToolItem = new ToolItem(bar, SWT.PUSH);
//		saveToolItem.setText("Tool");
		setToolItem.setToolTipText("设置");
		Image setIcon = new Image(display, "icon/set.png");
		setToolItem.setImage(setIcon);
		
		ToolItem addToolItem = new ToolItem(bar, SWT.PUSH);
//		saveToolItem.setText("Tool");
		addToolItem.setToolTipText("添加新股");
		Image addIcon = new Image(display, "icon/add.png");
		addToolItem.setImage(addIcon);
		
		ToolItem cleanToolItem = new ToolItem(bar, SWT.PUSH);
		cleanToolItem.setText("清除历史");
		cleanToolItem.setToolTipText("清除历史");
//		Image cleanIcon = new Image(display, "icon/export.png");
//		cleanToolItem.setImage(cleanIcon);
		
		ToolItem exitToolItem = new ToolItem(bar, SWT.PUSH);
//		saveToolItem.setText("Tool");
		exitToolItem.setToolTipText("退出");
		Image exitIcon = new Image(display, "icon/exit.png");
		exitToolItem.setImage(exitIcon);
		
		bar.setVisible(true);
	}

	//创建状态栏
	private void createStatusbar(Composite parent) {
		Composite statusbar = new Composite(parent, SWT.BORDER);
		// 设置工具栏在Shell中的形状为水平抢占充满，并高19像素
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 19;
		statusbar.setLayoutData(gridData);
		// 设置为用行列式布局管理状态栏里的组件
		RowLayout layout = new RowLayout();
		layout.marginLeft = layout.marginTop = 0; // 无边距
		statusbar.setLayout(layout);
		// 创建一个用于显示文字的标签
		Label statusbarLabel = new Label(statusbar, SWT.BORDER);
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

	private void createTab(Shell parent){
		
		tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 45, 974, 480);
		
				
		//构成图和个人资产
		WealTabItem = new TabItem(tabFolder, SWT.NONE);
		WealTabItem.setText("资产");
		WealTabItemComposite graphTabItemComposite = 
				new WealTabItemComposite(
				tabFolder, SWT.NONE);
		WealTabItem.setControl(graphTabItemComposite);

		// 持股构成
		ownershipTabItem = new TabItem(tabFolder, SWT.NONE);
		ownershipTabItem.setText("持仓情况");
		
		OwnershipTabItemComposite OwnershipTabItemComposite = new OwnershipTabItemComposite(
				tabFolder, SWT.NONE);
		ownershipTabItem.setControl(OwnershipTabItemComposite);
		
	}
}

