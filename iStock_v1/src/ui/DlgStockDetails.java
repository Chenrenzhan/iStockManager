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
import org.eclipse.wb.swt.SWTResourceManager;

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
		shell.setSize(610, 650);
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
		group.setBounds(206, 20, 383, 243);
		group.setLayout(null);

		Button gainBtn = new Button(group, SWT.PUSH);
		gainBtn.setText("添加交易");
		gainBtn.setBounds(235, 203, 60, 30);
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
		allBtn.setBounds(310, 203, 60, 30);
		allBtn.setVisible(true);

		Label dateLabel = new Label(group, SWT.NONE);
		dateLabel.setBounds(13, 34, 38, 17);
		dateLabel.setText("\u65E5\u671F");

		Label leiLabel = new Label(group, SWT.NONE);
		leiLabel.setBounds(103, 34, 38, 17);
		leiLabel.setText("\u7C7B\u578B");

		Label priceLabel = new Label(group, SWT.NONE);
		priceLabel.setBounds(161, 34, 46, 17);
		priceLabel.setText("\u4EF7\u683C");

		Label countLabel = new Label(group, SWT.NONE);
		countLabel.setBounds(235, 34, 46, 17);
		countLabel.setText("\u6570\u91CF");

		Label optionLabel = new Label(group, SWT.NONE);
		optionLabel.setBounds(310, 34, 51, 17);
		optionLabel.setText("\u64CD\u4F5C");

		// 假数据，后期要删除
		Label label = new Label(group, SWT.NONE);
		label.setBounds(10, 62, 66, 17);
		label.setText("2015-03-11");

		Label label_1 = new Label(group, SWT.NONE);
		label_1.setBounds(103, 62, 38, 17);
		label_1.setText("\u5356\u51FA");

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setBounds(161, 62, 38, 17);
		label_2.setText("4.5");

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setBounds(235, 62, 46, 17);
		label_3.setText("9900");

		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("2015-03-11");
		label_6.setBounds(10, 85, 66, 17);

		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("\u5356\u51FA");
		label_7.setBounds(103, 85, 38, 17);

		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("4.5");
		label_8.setBounds(161, 85, 38, 17);

		Label label_9 = new Label(group, SWT.NONE);
		label_9.setText("9900");
		label_9.setBounds(235, 85, 46, 17);
		
		Label lblNewLabel_4 = new Label(group, SWT.NONE);
		lblNewLabel_4.setBounds(310, 57, 17, 17);
//		lblNewLabel_4.setText("修改");
		Image changeIcon = new Image(Display.getDefault(), "icon/change.png");
		lblNewLabel_4.setImage(changeIcon);
		
		Label lblNewLabel_5 = new Label(group, SWT.NONE);
		lblNewLabel_5.setBounds(343, 57, 17, 17);
//		lblNewLabel_5.setText("删除");
		Image deleteIcon = new Image(Display.getDefault(), "icon/delete.png");
		lblNewLabel_5.setImage(deleteIcon);
		
		Label label_17 = new Label(group, SWT.NONE);
		label_17.setText("修改");
		label_17.setBounds(310, 85, 17, 17);
		
		Label label_18 = new Label(group, SWT.NONE);
		label_18.setText("删除");
		label_18.setBounds(343, 85, 17, 17);

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
		name.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		name.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.NORMAL));
		name.setText("工商银行(SH:601398)");
		name.setBounds(0, 20, 198, 34);
		name.setVisible(true);

		// 标签切换图表
		final TabFolder tab = new TabFolder(shell, SWT.NONE);
		tab.setBounds(20, 280, 569, 331);
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
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 18, SWT.NORMAL));
		lblNewLabel.setBounds(10, 60, 24, 34);
		lblNewLabel.setText("¥");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 18, SWT.NORMAL));
		lblNewLabel_1.setBounds(31, 60, 61, 34);
		lblNewLabel_1.setText("4.57");
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_4.setBounds(98, 67, 99, 27);
		label_4.setText("0.00(0.00%)");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel_2.setBounds(10, 100, 44, 27);
		lblNewLabel_2.setText("今开：");
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblNewLabel_3.setBounds(60, 100, 76, 27);
		lblNewLabel_3.setText("4.61");
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("4.66");
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_5.setBounds(60, 133, 76, 27);
		
		Label label_10 = new Label(shell, SWT.NONE);
		label_10.setText("最高：");
		label_10.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_10.setBounds(10, 133, 44, 27);
		
		Label label_11 = new Label(shell, SWT.NONE);
		label_11.setText("4.57");
		label_11.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_11.setBounds(60, 168, 76, 27);
		
		Label label_12 = new Label(shell, SWT.NONE);
		label_12.setText("昨收：");
		label_12.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_12.setBounds(10, 168, 44, 27);
		
		Label label_13 = new Label(shell, SWT.NONE);
		label_13.setText("4.53");
		label_13.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_13.setBounds(60, 201, 76, 27);
		
		Label label_14 = new Label(shell, SWT.NONE);
		label_14.setText("最低：");
		label_14.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_14.setBounds(10, 201, 44, 27);
		
		Label label_15 = new Label(shell, SWT.NONE);
		label_15.setText("0.63");
		label_15.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_15.setBounds(91, 234, 76, 27);
		
		Label label_16 = new Label(shell, SWT.NONE);
		label_16.setText("每股收益：");
		label_16.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_16.setBounds(10, 236, 75, 27);
	}

	private void setStockName(String stockN) {
		// TODO Auto-generated method stub
		stockName = stockN;
	}
}
