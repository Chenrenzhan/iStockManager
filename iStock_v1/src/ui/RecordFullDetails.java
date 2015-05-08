package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

/*
 * 完整的股票交易记录信息，即产看全部记录对话框对应的信息
 */

public class RecordFullDetails extends Composite {

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
	private Label lblNewLabel_10;
	
	private Label lblChange;
	private Label lblDelete;
	
	private String code;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RecordFullDetails(Composite parent, int style) {
		super(parent, SWT.NONE);
		shell=getShell();

		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblNewLabel.setBounds(10, 10, 80, 20);
		lblNewLabel.setText("股票名称");
		lblNewLabel.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		
		lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblNewLabel_1.setBounds(100, 10, 80, 20);
		lblNewLabel_1.setText("股票编号");
		lblNewLabel_1.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		
		lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblNewLabel_2.setBounds(197, 10, 80, 20);
		lblNewLabel_2.setText("日期");
		lblNewLabel_2.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		
		lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblNewLabel_3.setBounds(286, 10, 38, 20);
		lblNewLabel_3.setText("类型");
		lblNewLabel_3.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		
		lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblNewLabel_4.setBounds(346, 10, 60, 20);
		lblNewLabel_4.setText("价格");
		lblNewLabel_4.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		
		lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblNewLabel_5.setBounds(412, 10, 60, 20);
		lblNewLabel_5.setText("数量");
		lblNewLabel_5.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		
		lblNewLabel_6 = new Label(this, SWT.NONE);
		lblNewLabel_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblNewLabel_6.setBounds(488, 10, 65, 20);
		lblNewLabel_6.setText("税率");
		lblNewLabel_6.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		
		lblNewLabel_7 = new Label(this, SWT.NONE);
		lblNewLabel_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblNewLabel_7.setBounds(563, 10, 74, 20);
		lblNewLabel_7.setText("佣金");
		lblNewLabel_7.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		
		lblNewLabel_8 = new Label(this, SWT.WRAP);
		lblNewLabel_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblNewLabel_8.setBounds(645, 10, 132, 20);
		lblNewLabel_8.setText("说明");
		lblNewLabel_8.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		
		lblNewLabel_9 = new Label(this, SWT.WRAP);
		lblNewLabel_9.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblNewLabel_9.setBounds(790, 10, 100, 20);
		lblNewLabel_9.setText("备注");
		lblNewLabel_9.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		
		lblNewLabel_10 = new Label(this, SWT.NONE);
		lblNewLabel_10.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.BOLD));
		lblNewLabel_10.setBounds(904, 10, 53, 20);
		lblNewLabel_10.setText("操作");
		lblNewLabel_10.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_SELECTION));
		
		lblChange = new Label(this, SWT.NONE);
		lblChange.setBounds(896, 12, 24, 17);
		lblChange.setText("修改");
		lblChange.setVisible(false);
		
		lblDelete = new Label(this, SWT.NONE);
		lblDelete.setBounds(926, 12, 24, 17);
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
		case 10: 
			return lblNewLabel_10;
		default:
			return null;
		}
	}
	
	public Label getlblChange(){
		return lblChange;
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
