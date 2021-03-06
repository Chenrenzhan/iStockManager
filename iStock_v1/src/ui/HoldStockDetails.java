package ui;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

import controller.MouseListenerAdapt;

public class HoldStockDetails extends Composite {

	private Shell shell;
	private Label lblNewLabel;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private Label lblNewLabel_3;
	private Label lblNewLabel_4;
	private Label lblNewLabel_5;
	private Label lblNewLabel_6;
	private Label lblNewLabel_7;
	private Label lblNewLabel_8;
	private Label lblNewLabel_9;
	
	private Label lblAdd;
	private Label lblDelete;
	
	private String code;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public HoldStockDetails(Composite parent, int style) {
		super(parent, SWT.NONE);
		setToolTipText("股票名字");
		shell=getShell();

		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 90, 17);
		lblNewLabel.setText("股票");
		
		lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setToolTipText("股票当前价格");
		lblNewLabel_1.setBounds(100, 10, 80, 17);
		lblNewLabel_1.setText("当前价");
		
		lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setToolTipText("股票涨跌幅");
		lblNewLabel_2.setBounds(180, 10, 120, 17);
		lblNewLabel_2.setText("涨跌");
		
		lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setToolTipText("股票摊薄/持仓成本");
		lblNewLabel_3.setBounds(300, 10, 120, 17);
		lblNewLabel_3.setText("摊薄/持仓成本");
		
		lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setToolTipText("股票持有量");
		lblNewLabel_4.setBounds(420, 10, 60, 17);
		lblNewLabel_4.setText("持有量");
		
		lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setToolTipText("股票持有市值");
		lblNewLabel_5.setBounds(480, 10, 80, 17);
		lblNewLabel_5.setText("持有市值");
		
		lblNewLabel_6 = new Label(this, SWT.NONE);
		lblNewLabel_6.setToolTipText("股票浮动盈亏");
		lblNewLabel_6.setBounds(580, 10, 120, 17);
		lblNewLabel_6.setText("浮动盈亏");
		
		lblNewLabel_7 = new Label(this, SWT.NONE);
		lblNewLabel_7.setToolTipText("股票盈亏");
		lblNewLabel_7.setBounds(700, 10, 120, 17);
		lblNewLabel_7.setText("盈亏");
		
		lblNewLabel_8 = new Label(this, SWT.NONE);
		lblNewLabel_8.setToolTipText("点击可查看股票详细情况");
		lblNewLabel_8.setBounds(836, 10, 38, 17);
		lblNewLabel_8.setText("详情");
		
		lblNewLabel_9 = new Label(this, SWT.NONE);
		lblNewLabel_9.setBounds(880, 10, 24, 17);
		lblNewLabel_9.setText("操作");
		
		lblAdd = new Label(this, SWT.NONE);
		lblAdd.setToolTipText("添加股票交易记录");
		lblAdd.setBounds(880, 10, 24, 17);
		lblAdd.setText("添加");
		lblAdd.setVisible(false);
		
		lblDelete = new Label(this, SWT.NONE);
		lblDelete.setToolTipText("删除持有该股票");
		lblDelete.setBounds(910, 10, 24, 17);
		lblDelete.setText("删除");
		lblDelete.setVisible(false);

	}
	
	public Label getLabel(int index){
		switch(index){
		case 0: 
			return lblNewLabel;
		case 1: 
			return lblNewLabel_1;
		case 2: 
			return lblNewLabel_2;
		case 3: 
			return lblNewLabel_3;
		case 4: 
			return lblNewLabel_4;
		case 5: 
			return lblNewLabel_5;
		case 6: 
			return lblNewLabel_6;
		case 7: 
			return lblNewLabel_7;
		case 8: 
			return lblNewLabel_8;
		case 9: 
			return lblNewLabel_9;
		default:
			return null;
		}
	}
	
	public Label getlblAdd(){
		return lblAdd;
	}
	
	public Label getlblDelete(){
		return lblDelete;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
