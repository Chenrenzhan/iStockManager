package ui;

/*
 * ����ͼTab��Composite
 */

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.widgets.Display;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.wb.swt.SWTResourceManager;

public class WealTabItemComposite extends Composite {

	//������ֵ
	private Group totalAssetsGroup;
	//������
	private Group yieldGroup;
	//���ɶѻ�ͼ
	private Group stackGroup;
	//������ֵ��ͷ
	private TotalAssetsDetails assetsHead;
	private TotalAssetsDetails assetsDetails;
	private Label label;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public WealTabItemComposite(Composite parent, int style) {
		super(parent, SWT.NONE);
		setLayout(null);
		
		//������ֵ
		totalAssetsGroup = new Group(this, SWT.NONE);
		totalAssetsGroup.setBounds(10, 10, 942, 101);
		totalAssetsGroup.setText("������ֵ");
		totalAssetsGroup.setLayout(null);
		createAssetsHead(totalAssetsGroup);
		createAssetsDetails(totalAssetsGroup);
		
		Label separator = new Label(totalAssetsGroup, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		separator.setBounds(1, 47, 940, 3);
		
		
		//������
		yieldGroup = new Group(this, SWT.NONE);
		yieldGroup.setBounds(10, 126, 457, 319);
		yieldGroup.setText("������");
		
		//���ɶѻ�ͼ
		stackGroup = new Group(this, SWT.NONE);
		stackGroup.setBounds(492, 126, 460, 319);
		stackGroup.setText("�ֹɹ���");
		
	}
	//������ֵ��ͷ
	public void createAssetsHead(Composite parent){
		assetsHead = new TotalAssetsDetails(parent, SWT.NONE);
		assetsHead.setBounds(3, 17, 935, 30);		
	}
	
	//������ֵ��ϸ��Ϣ
	public void createAssetsDetails(Composite parent){
		assetsDetails = new TotalAssetsDetails(parent, SWT.NONE);
		assetsDetails.setBounds(3, 50, 935, 45);
		
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
