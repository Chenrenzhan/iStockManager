package ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

public class RecordDetails extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RecordDetails(Composite parent, int style) {
		super(parent, style);
		
		Label label = new Label(this, SWT.NONE);
		label.setText("日期");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label.setBounds(0, 10, 26, 19);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("类型");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_1.setBounds(105, 10, 26, 19);
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("价格");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_2.setBounds(194, 10, 26, 19);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("数量");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_3.setBounds(254, 10, 26, 19);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("操作");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_4.setBounds(316, 10, 26, 19);
		
//		Label label_5 = new Label(this, SWT.NONE);
//		label_5.setText("操作");
//		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
//		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
//		label_5.setBounds(405, 10, 26, 19);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
