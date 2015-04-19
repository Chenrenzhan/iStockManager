package ui;

/*
 * 持股构成Tab的Composite
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

	//持仓情况
	private Group holdStockGroup;
	private HoldStockDetails holdStockHead;
	private HoldStockDetails holdStockDetails1;//第一条
	private HoldStockDetails holdStockDetails2;
	private HoldStockDetails holdStockDetails3;
	private HoldStockDetails holdStockDetails4;
	private HoldStockDetails holdStockDetails5;
	private HoldStockDetails holdStockDetails6;
	private HoldStockDetails holdStockDetails7;
	private HoldStockDetails holdStockDetails8;
	private HoldStockDetails holdStockDetails9;
	private HoldStockDetails holdStockDetails10;
	//历史记录
	private Group recordGroup;
	//搜索
	private Composite searchComposite;
	
	//搜索结果滚动
	private ScrolledComposite resultScrolledComposite;
	//搜索输入框
	private Combo searchCombo;
	//搜索按钮
	private Button btnSearch;
	
	private final Color BACK_GROUND = new Color(null, 246, 250, 254);
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Label btnPrevious;
	private Label btnNext;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public OwnershipTabItemComposite(Composite parent, int style) {
		super(parent, SWT.NONE);
		setLayout(null);
		//持仓情况
		createHoldStockGroup(this);
		//历史记录
		createRecordGroup(this);
		//搜索
		createSearchComposite(this);
	}
	
	//创建持仓情况
	public void createHoldStockGroup(Composite parent){
		holdStockGroup = new Group(parent, SWT.NONE | SWT.H_SCROLL);
		holdStockGroup.setText("持仓情况");
		holdStockGroup.setBounds(10, 10, 515, 430);
		
		createHoldStockDetails(holdStockGroup);
		
		
		
		
		
	}
	
	//创建历史记录
	public void createRecordGroup(Composite parent){
		recordGroup = new Group(parent, SWT.NONE);
		recordGroup.setText("历史记录");
		recordGroup.setBounds(545, 193, 409, 247);

	}

	// 创建搜索
	public void createSearchComposite(Composite parent) {
		searchComposite = new Composite(parent, SWT.BORDER);
		searchComposite.setBounds(545, 20, 409, 158);

		searchCombo = new Combo(searchComposite, SWT.NONE);
		searchCombo.setBounds(10, 10, 293, 25);

		btnSearch = new Button(searchComposite, SWT.FLAT);
		btnSearch.setBounds(315, 10, 80, 27);
		btnSearch.setText("搜索");

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
	
	//创建持股情况详细信息
	public void createHoldStockDetails(Composite parent)
	{
		holdStockHead = new HoldStockDetails(parent, SWT.NONE);
		holdStockHead.setBounds(1, 20, 513, 30);
		
		createSeparator(parent, 1, 50, 513, 5);
		
		holdStockDetails1 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails1.setBounds(1, 50, 513, 35);
		holdStockDetails2 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails2.setBounds(1, 85, 513, 35);
//		holdStockDetails2.setBackground(new Color(null, 246, 250, 254));
		holdStockDetails3 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails3.setBounds(1, 120, 513, 35);
		holdStockDetails4 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails4.setBounds(1, 155, 513, 35);
		holdStockDetails5 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails5.setBounds(1, 190, 513, 35);
		holdStockDetails6 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails6.setBounds(1, 225, 513, 35);
		holdStockDetails7 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails7.setBounds(1, 260, 513, 35);
		holdStockDetails8 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails8.setBounds(1, 295, 513, 35);
		holdStockDetails9 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails9.setBounds(1, 330, 513, 35);
		holdStockDetails10 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails10.setBounds(1, 365, 513, 35);
		
		createSeparator(parent, 1, 400, 513, 3);
		
		btnPrevious = new Label(holdStockGroup, SWT.BORDER | SWT.SHADOW_IN);
		btnPrevious.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		btnPrevious.setBounds(356, 410, 61, 17);
		formToolkit.adapt(btnPrevious, true, true);
		btnPrevious.setText("上一页");
		
		btnNext = new Label(holdStockGroup, SWT.BORDER);
		btnNext.setBounds(441, 410, 61, 17);
		formToolkit.adapt(btnNext, true, true);
		btnNext.setText("下一页");
	}
	
	//创建水平分割直线
	public void  createSeparator(Composite parent, 
			int x, int y, int width, int heigth){
		Label label = new Label(holdStockGroup, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(x,y,width, heigth);
		formToolkit.adapt(label, true, true);
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
