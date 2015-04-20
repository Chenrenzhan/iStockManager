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

	private Label label;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	private Button button;
	private Button button_1;
	
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public RecordDetails(Composite parent, int style) {
		super(parent, style);
		final Shell shell = getShell();
		
		label = new Label(this, SWT.NONE);
		label.setText("日期");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label.setBounds(0, 10, 100, 19);
		
		label_1 = new Label(this, SWT.NONE);
		label_1.setText("类型");
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_1.setBounds(100, 10, 30, 19);
		
		label_2 = new Label(this, SWT.NONE);
		label_2.setText("价格");
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_2.setBounds(160, 10, 50, 19);
		
		label_3 = new Label(this, SWT.NONE);
		label_3.setText("数量");
		label_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_3.setBounds(210, 10, 60, 19);
		
		label_4 = new Label(this, SWT.NONE);
		label_4.setText("操作");
		label_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_4.setBounds(300, 10, 30, 19);
		
		button = new Button(this, SWT.NONE);
		button.setText("修改");
		button.setBounds(300, 10, 36, 17);
		button.setVisible(false);
		button_1 = new Button(this, SWT.NONE);
		button_1.setText("删除");
		button_1.setBounds(344, 10, 36, 17);
		button_1.setVisible(false);
		
		
		
//		Label label_5 = new Label(this, SWT.NONE);
//		label_5.setText("操作");
//		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
//		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
//		label_5.setBounds(405, 10, 26, 19);

	}

	public Label getLabel(int index){
		switch(index){
		case 0:
			return label;
		case 1:
			return label_1;
		case 2:
			return label_2;
		case 3:
			return label_3;
		case 4:
			return label_4;
		default:
			return null;
		}
	}
	
	public Button getButton(int index){
		switch(index){
		case 0:
			return button;
		case 1:
			return button_1;
		default:
			return null;
		}
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
