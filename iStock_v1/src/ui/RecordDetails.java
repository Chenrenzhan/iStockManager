package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class RecordDetails extends Composite {

	private Shell shell;
	
	private Label date;
	private Label type;
	private Label price;
	private Label volumes;
	private Label handle;
	private Label change;
	private Label delete;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public RecordDetails(Composite parent, int style) {
		super(parent, style);
		shell = getShell();
		
		date = new Label(this, SWT.NONE);
		date.setText("日期");
		date.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		date.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		date.setBounds(0, 10, 84, 19);
		
		type = new Label(this, SWT.NONE);
		type.setText("类型");
		type.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		type.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		type.setBounds(89, 10, 30, 19);
		
		price = new Label(this, SWT.NONE);
		price.setText("价格");
		price.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		price.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		price.setBounds(147, 10, 50, 19);
		
		volumes = new Label(this, SWT.NONE);
		volumes.setText("数量");
		volumes.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		volumes.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		volumes.setBounds(222, 10, 60, 19);
		
		handle = new Label(this, SWT.NONE);
		handle.setText("操作");
		handle.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		handle.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		handle.setBounds(288, 10, 30, 19);
		
		change = new Label(this, SWT.NONE);
		change.setBounds(288, 12, 20, 17);
		change.setText("修改");
		change.setVisible(false);
		
		delete = new Label(this, SWT.NONE);
		delete.setBounds(314, 12, 20, 17);
		delete.setText("删除");
		delete.setVisible(false);
	}

	public Label getLabel(int index) {
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

	public Label getChange(){
		return change;
	}
	
	public Label getDelete(){
		return delete;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
