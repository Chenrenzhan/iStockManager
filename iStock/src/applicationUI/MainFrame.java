package applicationUI;

import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class MainFrame {
	private Shell _shell;
	private TabFolder tFolder;
	private TabItem ownershipTabItem;
	private TabItem graphTabItem;
	private TabItem personalWealTab;
	private Menu menu;
	private MenuBar menuBar;
	private MenuItem menuItem_file;
	private Menu fileMenu;
	private MenuItem menuItem_set;
	private MenuItem menuItem_about;
	private Label historylabel;
	private GridData data;
	private Button[] goDetailsbButton;
	private Button[] rHistoryButton;
	
	private int i;
	String[] stocks = new String[]{"�й�����","��������","����ҽҩ"};

	// private Shell _shell;
	public MainFrame() {
		final Display display = new Display();
		// this shell is the main shell
		final Shell shell = new Shell(display, SWT.SHELL_TRIM);
		setShell(shell);
		GridLayout mainGridLayout = new GridLayout(2, false);
		_shell.setLayout(mainGridLayout);
		// the title of the shell
		_shell.setText("Storck Manager v1");

		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;
		data.horizontalSpan = 3;
		data.verticalSpan = 2;
		data.heightHint = 50;
		Label decorate = new Label(shell, SWT.PUSH);
		decorate.setText("decorate");
		decorate.setVisible(false);
		decorate.setLayoutData(data);

		Label decorate2 = new Label(_shell, SWT.FILL);
		data = new GridData();
		data.widthHint = 100;
		decorate2.setVisible(false);
		decorate2.setLayoutData(data);
		decorate2.setText("decorate");

		data = new GridData();
		tFolder = new TabFolder(_shell, SWT.None);
		tFolder.setLayoutData(data);
		tFolder.setLayout(new GridLayout());

		// �ֹ����
		ownershipTabItem = new TabItem(tFolder, SWT.NONE);
		ownershipTabItem.setText("�ֹɹ���");
		Composite ownershipTabComposite = new Composite(tFolder, SWT.NONE);

		//�ֹ�������ݲ���
		data = new GridData();
		data.heightHint = 400;
		data.widthHint = 800;
		ownershipTabComposite.setLayoutData(data);
		ownershipTabComposite.setLayout(new GridLayout(3, true));

		Composite mStockListComposite = new Composite(ownershipTabComposite,
				SWT.BORDER);
		data = new GridData();
		data.heightHint = 300;
		data.widthHint = 350;
        data.horizontalIndent=40;
        data.verticalIndent=40;
		mStockListComposite.setLayoutData(data);
		mStockListComposite.setLayout(new GridLayout(2, true));

		goDetailsbButton=new Button[stocks.length];
		rHistoryButton=new Button[stocks.length];
		for (int i = 0; i < 3; i++) {
			goDetailsbButton[i]=new Button(mStockListComposite, SWT.NONE);
			goDetailsbButton[i].setText(stocks[i]);
			String string= stocks[i];
		    goDetailsbButton[i].addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					StockDetails situation=new StockDetails();
					situation.open("�й�����");
				}
			});
		    
		    rHistoryButton[i]=new Button(mStockListComposite, SWT.NONE);
		    rHistoryButton[i].setText("��ʷ");
		    rHistoryButton[i].addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					historylabel.setText("�й�����");
				}
			});
		}

		Composite buyHistorycomposite = new Composite(ownershipTabComposite,
				SWT.BORDER|SWT.V_SCROLL);
		data = new GridData();
		data.heightHint = 300;
		data.widthHint = 350;
		data.horizontalSpan = 2; // Composite��ռ����Cell
        data.horizontalIndent=40;
        data.verticalIndent=40;
		buyHistorycomposite.setLayoutData(data);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 15;
		layout.marginRight = 150;
		buyHistorycomposite.setLayout(layout);

		data = new GridData(GridData.FILL_BOTH);
		data.minimumHeight = 100;
		data.minimumWidth = 200;
		historylabel = new Label(buyHistorycomposite, SWT.NONE);
		historylabel.setLayoutData(data);
		historylabel.setText("");

		ownershipTabItem.setControl(ownershipTabComposite);

		// ����ͼ
		graphTabItem = new TabItem(tFolder, SWT.NONE);
		graphTabItem.setText("����ͼ");
		Composite graphTabComposite = new Composite(tFolder, SWT.NONE);
		graphTabComposite.setLayout(new GridLayout(1, true));

		//  ����ͼ����
		// TODO

		graphTabItem.setControl(graphTabComposite);

		// �����ʲ�
		personalWealTab = new TabItem(tFolder, SWT.NONE);
		personalWealTab.setText("�����ʲ�");
		Composite personalcTabComposite = new Composite(tFolder, SWT.NONE);
		personalcTabComposite.setLayout(new GridLayout(2, true));
		
		// �����ʲ�����
		// TODO
        Label aMarket = new Label(personalcTabComposite, SWT.NONE);
		aMarket.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
				false));
		aMarket.setText("A���ʲ����");
		
		Button setCapital=new Button(personalcTabComposite, SWT.NONE);
		setCapital.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				super.widgetSelected(e);
				Dialog dialog=new Dialog(shell) {
				};
				
			}
		});
		setCapital.setText("�޸�");
		
		
		personalWealTab.setControl(personalcTabComposite);

		// menu
		menu = new Menu(_shell, SWT.BAR);

		menuItem_file = new MenuItem(menu, SWT.CASCADE);
		menuItem_file.setText("&�ļ�");
		fileMenu = new Menu(_shell, SWT.DROP_DOWN);
		MenuItem menuItem_Import = new MenuItem(fileMenu, SWT.PUSH);
		menuItem_Import.setText("&����");
		MenuItem menuItem_Export = new MenuItem(fileMenu, SWT.PUSH);
		menuItem_Export.setText("&����");
		MenuItem menuItem_Separator = new MenuItem(fileMenu, SWT.SEPARATOR);
		MenuItem menuItem_clearHistory = new MenuItem(fileMenu, SWT.PUSH);
		menuItem_clearHistory.setText("&�����ʷ");
		MenuItem menuItem_Separator2 = new MenuItem(fileMenu, SWT.SEPARATOR);
		MenuItem menuItem_exit = new MenuItem(fileMenu, SWT.PUSH);
		menuItem_exit.setText("&�˳�");
		menuItem_file.setMenu(fileMenu);
		
		//��ӡ��˳����¼�
		menuItem_exit.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});

		menuItem_set = new MenuItem(menu, SWT.PUSH);
		menuItem_set.setText("&����");
		menuItem_set.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Set setDlg = new Set();
				setDlg.open();
			};
		});

		menuItem_about = new MenuItem(menu, SWT.PUSH);
		menuItem_about.setText("&����");

		_shell.setMenuBar(menu);
		_shell.setBounds(80, 35, 1100, 700);
		_shell.open();

		while (!_shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	private void setShell(Shell shell) {
		// TODO Auto-generated method stub
		this._shell = shell;
	}

	public static void main(String[] args) {

		MainFrame frame = new MainFrame();

	}

	// private void setShell(Shell shell) {
	// // TODO Auto-generated method stub
	// this._shell=shell;
	// }
}
