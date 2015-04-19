package ui;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class HoldStockDetails extends Composite {

	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private Label lblNewLabel_3;
	private Label lblNewLabel_4;
	private Label lblNewLabel_5;
	private Label lblNewLabel_6;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	private Shell _shell;
	private Label lblNewLabel;
	public HoldStockDetails(Composite parent, int style) {
		super(parent, SWT.NONE);
		_shell=getShell();
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				try {
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 60, 17);
		lblNewLabel.setText("股票");
		lblNewLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Dlg_StockDetails dlg=new Dlg_StockDetails(_shell);
					dlg.open(lblNewLabel.getText());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(70, 10, 60, 17);
		lblNewLabel_1.setText("当前价");
		
		lblNewLabel_2 = new Label(this, SWT.NONE);
		lblNewLabel_2.setBounds(130, 10, 100, 17);
		lblNewLabel_2.setText("涨跌");
		
		lblNewLabel_3 = new Label(this, SWT.NONE);
		lblNewLabel_3.setBounds(230, 10, 60, 17);
		lblNewLabel_3.setText("成本");
		
		lblNewLabel_4 = new Label(this, SWT.NONE);
		lblNewLabel_4.setBounds(290, 10, 60, 17);
		lblNewLabel_4.setText("持有量");
		
		lblNewLabel_5 = new Label(this, SWT.NONE);
		lblNewLabel_5.setBounds(350, 10, 100, 17);
		lblNewLabel_5.setText("盈亏");
		
		lblNewLabel_6 = new Label(this, SWT.NONE);
		lblNewLabel_6.setBounds(450, 10, 60, 17);
		lblNewLabel_6.setText("市值");

	}
	
	public Label getLabel(int index){
		switch(index){
		case 0: 
			return lblNewLabel_1;
		case 1: 
			return lblNewLabel_2;
		case 2: 
			return lblNewLabel_3;
		case 3: 
			return lblNewLabel_4;
		case 4: 
			return lblNewLabel_5;
		case 5: 
			return lblNewLabel_6;
		default:
			return null;
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void event(){
		lblNewLabel_6.addMouseListener(new MouseListener(){

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

}
