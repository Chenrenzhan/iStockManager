package ui;

//单股详情

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

import ui.DlgStockSituation;
import ui.DlgStockHistory;

import org.eclipse.swt.layout.FillLayout;

public class DlgStockDetails extends Dialog {
	private Shell parentShell;
	private final Shell shell;
	
	Object result;
	
	private String stockName;

	public DlgStockDetails(Shell parent) {
		// TODO Auto-generated constructor stub

		super(parent, SWT.NONE);
		parentShell = getParent();
		shell = new Shell(parentShell, SWT.CLOSE);
	}



	public Object open(String stockN) {
		setStockName(stockN);
		Display display = shell.getDisplay();
		shell.setSize(750, 650);
		shell.setText("设置");
		shell.setLayout(null);
		shell.setVisible(true);

		create();


		while (!shell.isDisposed()) {
			
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	public void create() {
		// 交易记录组合框
		Group group = new Group(shell, SWT.NONE);
		group.setText("交易记录");
		group.setBounds(312, 20, 418, 243);
		group.setLayout(null);

		Button gainBtn = new Button(group, SWT.PUSH);
		gainBtn.setText("添加交易");
		gainBtn.setBounds(278, 203, 60, 30);
		gainBtn.setVisible(true);

		// //重写shell的关闭窗口按钮事件
		// shell.addListener(SWT.Close, new Listener() {
		// public void handleEvent(Event event) {shell.getShells();
		// Control[] childrenShells = shell.getChildren();
		// System.out.println(childrenShells.length);
		// if(childrenShells.length != 0){
		// int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
		// MessageBox messageBox = new MessageBox(shell, style);
		// messageBox.setText("Information");
		// messageBox.setMessage("Close the shell?");
		// event.doit = messageBox.open() == SWT.YES;
		// }
		//
		// }
		// });

		// “添加记录”按钮的点击事件
		gainBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					System.out.println("sssss");
					DlgStockSituation window = new DlgStockSituation(shell);
					window.open("添加记录", stockName);
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});

		Button allBtn = new Button(group, SWT.PUSH);
		allBtn.setText("查看全部");
		allBtn.setBounds(348, 203, 60, 30);
		allBtn.setVisible(true);

		Label dateLabel = new Label(group, SWT.NONE);
		dateLabel.setBounds(13, 34, 38, 17);
		dateLabel.setText("\u65E5\u671F");

		Label leiLabel = new Label(group, SWT.NONE);
		leiLabel.setBounds(103, 34, 38, 17);
		leiLabel.setText("\u7C7B\u578B");

		Label priceLabel = new Label(group, SWT.NONE);
		priceLabel.setBounds(181, 34, 46, 17);
		priceLabel.setText("\u4EF7\u683C");

		Label countLabel = new Label(group, SWT.NONE);
		countLabel.setBounds(264, 34, 46, 17);
		countLabel.setText("\u6570\u91CF");

		Label optionLabel = new Label(group, SWT.NONE);
		optionLabel.setBounds(357, 34, 51, 17);
		optionLabel.setText("\u64CD\u4F5C");

		Button changeBtn = new Button(group, SWT.NONE);
		changeBtn.setBounds(330, 62, 36, 17);
		changeBtn.setText("\u4FEE\u6539");
		changeBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					System.out.println("sssss");
					DlgStockSituation dlg = new DlgStockSituation(shell);
					dlg.open("修改记录", "股票名称");
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});
		Button deleteBtn = new Button(group, SWT.NONE);
		deleteBtn.setBounds(372, 62, 36, 17);
		deleteBtn.setText("\u5220\u9664");

		// 假数据，后期要删除
		Label label = new Label(group, SWT.NONE);
		label.setBounds(10, 62, 66, 17);
		label.setText("2015-03-11");

		Label label_1 = new Label(group, SWT.NONE);
		label_1.setBounds(103, 62, 38, 17);
		label_1.setText("\u5356\u51FA");

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setBounds(181, 62, 38, 17);
		label_2.setText("4.5");

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setBounds(264, 57, 46, 17);
		label_3.setText("9900");

		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("2015-03-11");
		label_6.setBounds(10, 85, 66, 17);

		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("\u5356\u51FA");
		label_7.setBounds(103, 85, 38, 17);

		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("4.5");
		label_8.setBounds(181, 85, 38, 17);

		Label label_9 = new Label(group, SWT.NONE);
		label_9.setText("9900");
		label_9.setBounds(264, 85, 46, 17);

		Button button = new Button(group, SWT.NONE);
		button.setText("\u4FEE\u6539");
		button.setBounds(330, 87, 36, 17);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					DlgStockSituation dlg = new DlgStockSituation(shell);
					dlg.open("修改记录", "股票名称");
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});

		Button button_1 = new Button(group, SWT.NONE);
		button_1.setText("\u5220\u9664");
		button_1.setBounds(372, 87, 36, 17);

		allBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					DlgStockHistory window = new DlgStockHistory(shell);
					window.open(stockName);
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});
		Label name = new Label(shell, SWT.FILL);
		name.setText(stockName);
		name.setBounds(20, 20, 60, 50);
		name.setVisible(true);

		// 标签切换图表
		final TabFolder tab = new TabFolder(shell, SWT.NONE);
		tab.setBounds(161, 280, 569, 331);
		// 添加标签
		final TabItem TabI1 = new TabItem(tab, SWT.NONE);
		TabI1.setText("分时");

		Composite composite = new Composite(tab, SWT.NONE);
		TabI1.setControl(composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		// new ImageComposite(composite, SWT.NONE, "data/temp/min.gif",
		// ImageComposite.SCALED);
		Image image = new Image(Display.getDefault(), "data/temp/min.gif");
		composite.setBackgroundImage(image);

		final TabItem TabI2 = new TabItem(tab, SWT.NONE);
		TabI2.setText("日K");

		Composite composite_1 = new Composite(tab, SWT.NONE);
		TabI2.setControl(composite_1);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		new ImageComposite(composite_1, SWT.NONE, "data/temp/daily.gif",
				ImageComposite.SCALED);

		final TabItem TabI3 = new TabItem(tab, SWT.NONE);
		TabI3.setText("周K");

		Composite composite_2 = new Composite(tab, SWT.NONE);
		TabI3.setControl(composite_2);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		new ImageComposite(composite_2, SWT.NONE, "data/temp/weekly.gif",
				ImageComposite.SCALED);

		final TabItem TabI4 = new TabItem(tab, SWT.NONE);
		TabI4.setText("月K");

		Composite composite_3 = new Composite(tab, SWT.NONE);
		TabI4.setControl(composite_3);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		new ImageComposite(composite_3, SWT.NONE, "data/temp/monthly.gif",
				ImageComposite.SCALED);
	}

	private void setStockName(String stockN) {
		// TODO Auto-generated method stub
		stockName = stockN;
	}
}
