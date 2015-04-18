package ui;

/*
 * �ֹɹ���Tab��Composite
 */

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class OwnershipTabItemComposite extends Composite {

	//�ֲ����
	private Group holdStockGroup;
	
	private ScrolledComposite holdStocScrolledComposite;
	//�ֲ����һ����ϸ��Ϣ
	private HoldStockDetails holdStockHead;//��ͷ
	private HoldStockDetails holdStockDetails1;//��һ��
	private HoldStockDetails holdStockDetails2;
	private HoldStockDetails holdStockDetails3;
	private HoldStockDetails holdStockDetails4;
	private HoldStockDetails holdStockDetails5;
	private HoldStockDetails holdStockDetails6;
	private HoldStockDetails holdStockDetails7;
	private HoldStockDetails holdStockDetails8;
	private HoldStockDetails holdStockDetails9;
	private HoldStockDetails holdStockDetails10;
	//��ʷ��¼
	private Group recordGroup;
	//����
	private Composite searchComposite;
	
	//�����������
	private ScrolledComposite resultScrolledComposite;
	//���������
	private Combo searchCombo;
	//������ť
	private Button btnSearch;
	
	private final Color BACK_GROUND = new Color(null, 246, 250, 254);
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public OwnershipTabItemComposite(Composite parent, int style) {
		super(parent, SWT.NONE);
		setLayout(null);
		//�ֲ����
		createHoldStockGroup(this);
		//��ʷ��¼
		createRecordGroup(this);
		//����
		createSearchComposite(this);
	}
	
	//�����ֲ����
	public void createHoldStockGroup(Composite parent){
		holdStockGroup = new Group(parent, SWT.NONE | SWT.H_SCROLL);
		holdStockGroup.setText("�ֲ����");
		holdStockGroup.setBounds(10, 10, 515, 430);
		
		holdStocScrolledComposite = 
				new ScrolledComposite(holdStockGroup, 
						SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		holdStocScrolledComposite.setTouchEnabled(true);
		holdStocScrolledComposite.setAlwaysShowScrollBars(true);
		holdStocScrolledComposite.setBounds(10, 22, 495, 398);
		formToolkit.adapt(holdStocScrolledComposite);
		formToolkit.paintBordersFor(holdStocScrolledComposite);
		holdStocScrolledComposite.setExpandHorizontal(true);
		holdStocScrolledComposite.setExpandVertical(true);
		holdStocScrolledComposite.setMinWidth(1000);
		holdStocScrolledComposite.setMinHeight(400);
		
		Composite composite = new Composite(holdStocScrolledComposite, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		holdStocScrolledComposite.setContent(composite);
		holdStocScrolledComposite.setMinSize(composite.computeSize(800, 20*30));
//		composite.setSize(1000, 400);
		
//		holdStockGroup.setExpandHorizontal(true);
		
		//����һ����ϸ��Ϣ
		createHoldStockDetails(composite);
	}
	
	//������ʷ��¼
	public void createRecordGroup(Composite parent){
		recordGroup = new Group(parent, SWT.NONE);
		recordGroup.setText("��ʷ��¼");
		recordGroup.setBounds(545, 193, 409, 247);

	}

	// ��������
	public void createSearchComposite(Composite parent) {
		searchComposite = new Composite(parent, SWT.BORDER);
		searchComposite.setBounds(545, 20, 409, 158);

		searchCombo = new Combo(searchComposite, SWT.NONE);
		searchCombo.setBounds(10, 10, 293, 25);

		btnSearch = new Button(searchComposite, SWT.FLAT);
		btnSearch.setBounds(315, 10, 80, 27);
		btnSearch.setText("����");

		resultScrolledComposite = new ScrolledComposite(searchComposite,
				SWT.BORDER | SWT.V_SCROLL);
		resultScrolledComposite.setBounds(10, 31, 293, 113);
		formToolkit.adapt(resultScrolledComposite);
		formToolkit.paintBordersFor(resultScrolledComposite);
		resultScrolledComposite.setExpandHorizontal(true);
		resultScrolledComposite.setExpandVertical(true);
//		resultScrolledComposite.setMinWidth(800);
//		resultScrolledComposite.setMinHeight(400);

		Label lblNewLabel = new Label(resultScrolledComposite, SWT.NONE);
		formToolkit.adapt(lblNewLabel, true, true);
		lblNewLabel.setText("New Label");
		resultScrolledComposite.setContent(lblNewLabel);
		resultScrolledComposite.setMinSize(lblNewLabel.computeSize(SWT.DEFAULT,
				SWT.DEFAULT));
	}
	
	//�����ֹ������ϸ��Ϣ
	public void createHoldStockDetails(Composite parent)
	{
		holdStockHead = new HoldStockDetails(parent, SWT.NONE);
		holdStockHead.setBounds(1, 0, 998, 30);
		
		createSeparator(parent, 1, 30, 998, 3);
		
//		holdStockDetails1 = new HoldStockDetails(parent, SWT.NONE);
//		holdStockDetails1.setBounds(1, 30, 998, 30);
////		holdStockDetails1.setBackground(BACK_GROUND);
//		
////		createSeparator(parent, 1, 80, 998, 3);
//		
//		holdStockDetails2 = new HoldStockDetails(parent, SWT.NONE);
//		holdStockDetails2.setBounds(1, 60, 998, 30);
//		
////		createSeparator(parent, 1, 110, 998, 3);
//		
//		holdStockDetails3 = new HoldStockDetails(parent, SWT.NONE);
//		holdStockDetails3.setBounds(1, 90, 998, 30);
////		holdStockDetails3.setBackground(BACK_GROUND);
//		
////		createSeparator(parent, 1, 140, 998, 3);
//		
//		holdStockDetails4 = new HoldStockDetails(parent, SWT.NONE);
//		holdStockDetails4.setBounds(1, 120, 998, 30);
//		
////		createSeparator(parent, 1, 170, 998, 3);
//		
//		holdStockDetails5 = new HoldStockDetails(parent, SWT.NONE);
//		holdStockDetails5.setBounds(1, 150, 998, 30);
////		holdStockDetails5.setBackground(BACK_GROUND);
//		
////		createSeparator(parent, 1, 200, 998, 3);
//		
//		holdStockDetails6 = new HoldStockDetails(parent, SWT.NONE);
//		holdStockDetails6.setBounds(1, 180, 998, 30);
//		
////		createSeparator(parent, 1, 230, 998, 3);
//		
//		holdStockDetails7 = new HoldStockDetails(parent, SWT.NONE);
//		holdStockDetails7.setBounds(1, 210, 998, 30);
////		holdStockDetails7.setBackground(BACK_GROUND);
//		
////		createSeparator(parent, 1, 260, 998, 3);
//		
//		holdStockDetails8 = new HoldStockDetails(parent, SWT.NONE);
//		holdStockDetails8.setBounds(1, 240, 998, 30);
//		
////		createSeparator(parent, 1, 290, 998, 3);
//		
//		holdStockDetails9 = new HoldStockDetails(parent, SWT.NONE);
//		holdStockDetails9.setBounds(1, 270, 998, 30);
////		holdStockDetails9.setBackground(BACK_GROUND);
//		
////		createSeparator(parent, 1, 320, 998, 3);
//		
//		holdStockDetails10 = new HoldStockDetails(parent, SWT.NONE);
//		holdStockDetails10.setBounds(1, 300, 998, 30);
		
		for(int i = 0; i < 20; ++i){
			new HoldStockDetails(parent, SWT.NONE).setBounds(1, 30*(i+1), 998, 30);
		}
		
		createSeparator(parent, 1, 320, 998, 3);
	}
	
	//����ˮƽ�ָ�ֱ��
	public void  createSeparator(Composite parent, 
			int x, int y, int width, int heigth){
		Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator.setBounds(x, y, width, heigth);
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
