package ui;


import org.eclipse.swt.SWT;
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

public class MainFrame {
	private DataBindingContext m_bindingContext;

	private Display display;
	protected Shell shell;
	
	private MyMenu menu;
	
	private TabFolder tabFolder;
	private TabItem ownershipTabItem;
	private TabItem WealTabItem;
	private Text search;
	private Button searchBtn;
	

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
		shell.setSize(1000, 600);
		shell.setText("SWT Application");
		
		menu = new MyMenu(shell);
		
		createToolbar();//创建工具栏
		
		createStatusbar(shell);//创建状态栏
	
		createTab(shell);
		
		search = new Text(shell, SWT.BORDER);
		search.setBounds(687, 7, 132, 23);
		
		searchBtn = new Button(shell, SWT.NONE);
		searchBtn.setBounds(842, 7, 58, 23);
		searchBtn.setText("搜索");
		
		

	}
	
	//创建工具栏
	protected void createToolbar(){
		final ToolBar bar = new ToolBar(shell, SWT.NONE);
		bar.setSize(984, 30);
		bar.setLocation(0,0);
		
		
		//bar.setForeground(Color.BLACK);
		
		ToolItem openToolItem = new ToolItem(bar, SWT.PUSH);
		openToolItem.setText("Tool");
		openToolItem.setToolTipText("Open File");
		ToolItem saveToolItem = new ToolItem(bar, SWT.PUSH);
		saveToolItem.setText("Tool");
		saveToolItem.setToolTipText("Save File");

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
		statusbar.setLocation(0, 521);
	}

	// 创建进度条
	private ProgressBar createProgressBar(Composite parent) {
		ProgressBar progressBar = new ProgressBar(parent, SWT.SMOOTH);
		progressBar.setMinimum(0); // 最小值
		progressBar.setMaximum(100);// 最大值
		System.out.println("aaaaaaaaaaaaaaaa");
		return progressBar;
	}

	private void createTab(Shell parent){
		
		tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 35, 974, 480);
		
				
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

