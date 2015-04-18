package ui;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class TotalAssetsDetails extends Composite {

	private Label lblMarket; //�г�
	private Label lblBreakEventSum;//��ӯ����
	private Label lblFloatBreakEvent;//����ӯ��
	private Label lblBreakEvent;//ӯ��
	private Label lbltotalAssets;//�˻���ֵ
	private Label lblMarketValue;//��ֵ
	private Label lblCash;//�ֽ�
	private Label lblCapital;//����
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TotalAssetsDetails(Composite parent, int style) {
		super(parent, SWT.NONE);
		
		lblMarket = new Label(this, SWT.NONE);
		lblMarket.setBounds(10, 7, 61, 17);
		lblMarket.setText("�г�");
		
		lblBreakEventSum = new Label(this, SWT.NONE);
		lblBreakEventSum.setBounds(100, 7, 61, 17);
		lblBreakEventSum.setText("��ӯ����");
		
		lblFloatBreakEvent = new Label(this, SWT.NONE);
		lblFloatBreakEvent.setBounds(200, 7, 61, 17);
		lblFloatBreakEvent.setText("����ӯ��");
		
		lblBreakEvent = new Label(this, SWT.NONE);
		lblBreakEvent.setBounds(300, 7, 61, 17);
		lblBreakEvent.setText("ӯ��");
		
		lbltotalAssets = new Label(this, SWT.NONE);
		lbltotalAssets.setBounds(420, 7, 61, 17);
		lbltotalAssets.setText("�˻����ʲ�");
		
		lblMarketValue = new Label(this, SWT.NONE);
		lblMarketValue.setBounds(550, 7, 61, 17);
		lblMarketValue.setText("��ֵ");
		
		lblCash = new Label(this, SWT.NONE);
		lblCash.setBounds(680, 7, 61, 17);
		lblCash.setText("�ֽ�");
		
		lblCapital = new Label(this, SWT.NONE);
		lblCapital.setBounds(820, 7, 61, 17);
		lblCapital.setText("����");

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

	//����0...7�ֱ�����ҷ���label
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
		default:
			return null;					
		}
	}
	
	//����label��λ�úʹ�С
	public Rectangle getLblBounds(int index){
		switch(index){
		case 0:
			return lblMarket.getBounds();
		case 1:
			return lblBreakEventSum.getBounds();
		case 2:
			return lblFloatBreakEvent.getBounds();
		case 3:
			return lblBreakEvent.getBounds();
		case 4:
			return lbltotalAssets.getBounds();
		case 5:
			return lblMarketValue.getBounds();
		case 6:
			return lblCash.getBounds();
		case 7:
			return lblCapital.getBounds();
		default:
			return null;					
		}
	}
}
