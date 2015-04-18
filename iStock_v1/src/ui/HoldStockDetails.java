package ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class HoldStockDetails extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public HoldStockDetails(Composite parent, int style) {
		super(parent, SWT.NONE);
//		setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
//		setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 8, SWT.NORMAL));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setTouchEnabled(true);
//		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblNewLabel.setBounds(10, 10, 100, 17);
		lblNewLabel.setText("股票");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
//		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBounds(110, 10, 60, 17);
		lblNewLabel_1.setText("当前价");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
//		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(190, 10, 100, 17);
		lblNewLabel_2.setText("涨跌");
		
		Label lblNewLabel_3 = new Label(this, SWT.NONE);
//		lblNewLabel_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_3.setBounds(300, 10, 100, 17);
		lblNewLabel_3.setText("摊薄/持仓成本");
		
		Label lblNewLabel_4 = new Label(this, SWT.NONE);
//		lblNewLabel_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_4.setBounds(400, 10, 60, 17);
		lblNewLabel_4.setText("持有量");
		
		Label lblNewLabel_5 = new Label(this, SWT.NONE);
//		lblNewLabel_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_5.setBounds(460, 10, 80, 17);
		lblNewLabel_5.setText("持有市值");
		
		Label lblNewLabel_6 = new Label(this, SWT.NONE);
//		lblNewLabel_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_6.setBounds(540, 10, 100, 17);
		lblNewLabel_6.setText("浮动盈亏");
		
		Label lblNewLabel_7 = new Label(this, SWT.NONE);
		lblNewLabel_7.setBackground(lblNewLabel_7.getParent().getBackground());
		lblNewLabel_7.setBounds(640, 10, 100, 17);
		lblNewLabel_7.setText("盈亏");
		
		Label lblNewLabel_8 = new Label(this, SWT.NONE);
//		lblNewLabel_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_8.setBounds(740, 10, 60, 17);
		lblNewLabel_8.setText("操作");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
