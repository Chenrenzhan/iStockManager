package ui;

import java.awt.Color;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

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
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
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
		
		createToolbar();//����������
		
		createStatusbar(shell);//����״̬��
	
		createTab(shell);
		
		

	}
	
	//����������
	protected void createToolbar(){
		final ToolBar bar = new ToolBar(shell, SWT.NONE);
		bar.setSize(507, 30);
		bar.setLocation(0,0);
		
		
		//bar.setForeground(Color.BLACK);
		
		ToolItem openToolItem = new ToolItem(bar, SWT.PUSH);
		openToolItem.setText("Open");
		openToolItem.setToolTipText("Open File");
		ToolItem saveToolItem = new ToolItem(bar, SWT.PUSH);
		saveToolItem.setText("Save");
		saveToolItem.setToolTipText("Save File");

		bar.setVisible(true);
	}

	//����״̬��
	private void createStatusbar(Composite parent) {
		Composite statusbar = new Composite(parent, SWT.BORDER);
		// ���ù�������Shell�е���״Ϊˮƽ��ռ����������19����
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 19;
		statusbar.setLayoutData(gridData);
		// ����Ϊ������ʽ���ֹ���״̬��������
		RowLayout layout = new RowLayout();
		layout.marginLeft = layout.marginTop = 0; // �ޱ߾�
		statusbar.setLayout(layout);
		// ����һ��������ʾ���ֵı�ǩ
		Label statusbarLabel = new Label(statusbar, SWT.BORDER);
		statusbarLabel.setLayoutData(new RowData(70, -1));
		statusbarLabel.setText("״̬��");
		statusbar.setVisible(true);
		createProgressBar(statusbar);
		
		statusbar.setSize(994, 30);
		statusbar.setLocation(0, 521);
	}

	// ����������
	private ProgressBar createProgressBar(Composite parent) {
		ProgressBar progressBar = new ProgressBar(parent, SWT.SMOOTH);
		progressBar.setMinimum(0); // ��Сֵ
		progressBar.setMaximum(100);// ���ֵ
		System.out.println("aaaaaaaaaaaaaaaa");
		return progressBar;
	}

	private void createTab(Shell parent){
		
		tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 35, 974, 480);
		
				
		//����ͼ�͸����ʲ�
		WealTabItem = new TabItem(tabFolder, SWT.NONE);
		WealTabItem.setText("�ʲ�");
		WealTabItemComposite graphTabItemComposite = 
				new WealTabItemComposite(
				tabFolder, SWT.NONE);
		WealTabItem.setControl(graphTabItemComposite);

		// �ֹɹ���
		ownershipTabItem = new TabItem(tabFolder, SWT.NONE);
		ownershipTabItem.setText("�ֹɹ���");
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("New Item");
		OwnershipTabItemComposite OwnershipTabItemComposite = new OwnershipTabItemComposite(
				tabFolder, SWT.NONE);
		tabItem.setControl(OwnershipTabItemComposite);
		
	}
}

