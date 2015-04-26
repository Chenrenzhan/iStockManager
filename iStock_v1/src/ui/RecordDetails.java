package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class RecordDetails extends Composite {

	private Label date;
	private Label type;
	private Label price;
	private Label volumes;
	private Label handle;
	private Button btnDetail;
	private Button btnCancel;
	
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RecordDetails(Composite parent, int style) {
		super(parent, style);
		final Shell shell = getShell();
		
		date = new Label(this, SWT.NONE);
		date.setText("日期");
		date.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		date.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		date.setBounds(0, 10, 100, 19);
		
		type = new Label(this, SWT.NONE);
		type.setText("类型");
		type.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		type.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		type.setBounds(100, 10, 30, 19);
		
		price = new Label(this, SWT.NONE);
		price.setText("价格");
		price.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		price.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		price.setBounds(160, 10, 50, 19);
		
		volumes = new Label(this, SWT.NONE);
		volumes.setText("数量");
		volumes.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		volumes.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		volumes.setBounds(210, 10, 60, 19);
		
		handle = new Label(this, SWT.NONE);
		handle.setText("操作");
		handle.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		handle.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		handle.setBounds(300, 10, 30, 19);
		
		btnDetail = new Button(this, SWT.NONE);
		btnDetail.setText("详情");
		btnDetail.setBounds(300, 10, 36, 17);
		btnDetail.setVisible(false);
		btnCancel = new Button(this, SWT.NONE);
		btnCancel.setText("删除");
		btnCancel.setBounds(344, 10, 36, 17);
		btnCancel.setVisible(false);

	}

	public Label getLabel(int index){
		switch(index){
		case 0:
			return date;
		case 1:
			return type;
		case 2:
			return price;
		case 3:
			return volumes;
		case 4:
			return handle;
		default:
			return null;
		}
	}
	
	public Button getButton(int index){
		switch(index){
		case 0:
			return btnDetail;
		case 1:
			return btnCancel;
		default:
			return null;
		}
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
