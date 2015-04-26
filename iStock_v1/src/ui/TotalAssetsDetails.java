package ui;


import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class TotalAssetsDetails extends Composite {

	private Label lblMarket; //市场
	private Label lblBreakEventSum;//日盈亏额
	private Label lblFloatBreakEvent;//浮动盈亏
	private Label lblBreakEvent;//盈亏
	private Label lbltotalAssets;//账户总值
	private Label lblMarketValue;//市值
	private Label lblCash;//现金
	private Label lblCapital;//本金
	private Label lblChange;//股票详情
    private Shell _shell;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TotalAssetsDetails(Composite parent, int style) {
		super(parent, SWT.NONE);
		setShell(parent.getShell());
		lblMarket = new Label(this, SWT.NONE);
		lblMarket.setBounds(10, 7, 60, 17);
		lblMarket.setText("市场");
		
		lblBreakEventSum = new Label(this, SWT.NONE);
		lblBreakEventSum.setBounds(100, 7, 100, 17);
		lblBreakEventSum.setText("日盈亏额");
		
		lblFloatBreakEvent = new Label(this, SWT.WRAP);
		lblFloatBreakEvent.setBounds(200, 7, 120, 17);
		lblFloatBreakEvent.setText("浮动盈亏");
		
		lblBreakEvent = new Label(this, SWT.NONE);
		lblBreakEvent.setBounds(320, 7, 120, 17);
		lblBreakEvent.setText("盈亏");
		
		lbltotalAssets = new Label(this, SWT.NONE);
		lbltotalAssets.setBounds(440, 7, 100, 17);
		lbltotalAssets.setText("账户总资产");
		
		lblMarketValue = new Label(this, SWT.NONE);
		lblMarketValue.setBounds(540, 7, 100, 17);
		lblMarketValue.setText("市值");
		
		lblCash = new Label(this, SWT.NONE);
		lblCash.setBounds(640, 7, 100, 17);
		lblCash.setText("现金");
		
		lblCapital = new Label(this, SWT.NONE);
		lblCapital.setBounds(740, 7, 100, 17);
		lblCapital.setText("本金");
		
		lblChange = new Label(this, SWT.NONE);
		lblChange.setBounds(840, 7, 70, 17);
		lblChange.setText("修改本金");
//		lblMore.setVisible(false);
//		lblCapital.setFont( new Font(getDisplay(), "Arial",8 , SWT.BOLD)) ;
//		lblCapital.setForeground(getDisplay().getSystemColor(SWT.COLOR_BLUE));
//		lblCapital.addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseUp(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				try {
//					Dlg_ChangeMoney dlg=new Dlg_ChangeMoney(_shell);
//					dlg.open();
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//			}
//			
//			@Override
//			public void mouseDown(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseDoubleClick(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});

	}

	private void setShell(Shell shell) {
		// TODO Auto-generated method stub
         _shell=shell;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public Label getLblMarket() {
		return lblMarket;
	}

	public Label getLblBreakEventSum() {
		return lblBreakEventSum;
	}

	public Label getLblFloatBreakEvent() {
		return lblFloatBreakEvent;
	}

	public Label getLblBreakEvent() {
		return lblBreakEvent;
	}

	public Label getLbltotalAssets() {
		return lbltotalAssets;
	}

	public Label getLblMarketValue() {
		return lblMarketValue;
	}

	public Label getLblCash() {
		return lblCash;
	}

	public Label getLblCapital() {
		return lblCapital;
	}

	//根据0...7分别从左到右返回label
	public Label getLbl(int index) {
		switch(index){
		case 0:
			return lblMarket;
		case 1:
			return lblBreakEventSum;
		case 2:
			return lblFloatBreakEvent;
		case 3:
			return lblBreakEvent;
		case 4:
			return lbltotalAssets;
		case 5:
			return lblMarketValue;
		case 6:
			return lblCash;
		case 7:
			return lblCapital;
		case 8:
			return lblChange;
		default:
			return null;					
		}
	}
	
//	//返回label的位置和大小
//	public Rectangle getLblBounds(int index){
//		switch(index){
//		case 0:
//			return lblMarket.getBounds();
//		case 1:
//			return lblBreakEventSum.getBounds();
//		case 2:
//			return lblFloatBreakEvent.getBounds();
//		case 3:
//			return lblBreakEvent.getBounds();
//		case 4:
//			return lbltotalAssets.getBounds();
//		case 5:
//			return lblMarketValue.getBounds();
//		case 6:
//			return lblCash.getBounds();
//		case 7:
//			return lblCapital.getBounds();
//		default:
//			return null;					
//		}
//	}
}
