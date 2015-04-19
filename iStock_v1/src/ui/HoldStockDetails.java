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
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 60, 17);
		lblNewLabel.setText("股票");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(70, 10, 60, 17);
		lblNewLabel_1.setText("当前价");
		
		Label lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBounds(130, 10, 100, 17);
		lblNewLabel_2.setText("涨跌");
		
		Label lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setBounds(230, 10, 60, 17);
		lblNewLabel_3.setText("成本");
		
		Label lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setBounds(290, 10, 60, 17);
		lblNewLabel_4.setText("持有量");
		
		Label lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setBounds(350, 10, 100, 17);
		lblNewLabel_5.setText("盈亏");
		
		Label lblNewLabel_6 = new Label(this, SWT.NONE);
		lblNewLabel_6.setBounds(450, 10, 60, 17);
		lblNewLabel_6.setText("市值");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
