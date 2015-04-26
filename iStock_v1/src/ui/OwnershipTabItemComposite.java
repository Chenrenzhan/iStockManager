package ui;

/*
 * 持股构成Tab的Composite
 */
//import org.eclipse.swt.events.MouseEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.Color;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

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
	private RecordDetails recordDetailsHead;
	private RecordDetails recordDetails1;
	
	private final Color BACK_GROUND = new Color(null, 246, 250, 254);
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Label btnPrevious;
	private Label btnNext;
	
	//K线图
	private TabFolder tfKChart;
	private TabItem tiMinChart;
	private TabItem tiDayChart;
	private TabItem tiWeekChart;
	private TabItem tiMonthChart;
	
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
		
		tfKChart = new TabFolder(this, SWT.NONE);
		tfKChart.setBounds(549, 205, 409, 235);
		formToolkit.adapt(tfKChart);
		formToolkit.paintBordersFor(tfKChart);
		
		tiMinChart = new TabItem(tfKChart, SWT.NONE);
		tiMinChart.setText("分时");
		
		tiDayChart = new TabItem(tfKChart, SWT.NONE);
		tiDayChart.setText("日K");
		
		tiWeekChart = new TabItem(tfKChart, SWT.NONE);
		tiWeekChart.setText("周K");
		
		tiMonthChart = new TabItem(tfKChart, SWT.NONE);
		tiMonthChart.setText("月K");
		
//		Label a = holdStockDetails1.getLabel(5);
//		System.out.println(a.getText());
		
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
		recordGroup.setBounds(549, 10, 409, 175);
		
		recordDetailsHead = 
				new RecordDetails(recordGroup, SWT.NONE);
		recordDetailsHead.setSize(389, 30);
		recordDetailsHead.setLocation(10, 20);
		recordDetailsHead.setVisible(true);
		recordDetails1 = 
				new RecordDetails(recordGroup, SWT.NONE);
		recordDetails1.setSize(389, 30);
		recordDetails1.setLocation(10, 50);
		recordDetails1.setVisible(false);

	}

	// 创建搜索
	public void createSearchComposite(Composite parent) {
	
	}
	
	//创建持股情况详细信息
	public void createHoldStockDetails(Composite parent)
	{
		holdStockHead = new HoldStockDetails(parent, SWT.NONE);
		holdStockHead.setBounds(1, 20, 513, 30);
		
		createSeparator(parent, 1, 50, 513, 5);
		
		holdStockDetails1 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails1.setBounds(1, 50, 513, 35);
		holdStockDetails1.getLabel(5).addMouseListener(new MouseListener(){

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
				recordDetails1.setVisible(true);
				changeRecord(recordDetails1);
			}
			
		});
		
		
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
	
	public void changeRecord(final RecordDetails record){
		record.getLabel(0).setText("2015-3-11");
		record.getLabel(0).setForeground(new Color(null,0,0,0));
		record.getLabel(1).setText("卖出");
		record.getLabel(1).setForeground(new Color(null,0,0,0));
		record.getLabel(2).setText("4.5");
		record.getLabel(2).setForeground(new Color(null,0,0,0));
		record.getLabel(3).setText("9900");
		record.getLabel(3).setForeground(new Color(null,0,0,0));
		record.getLabel(4).setVisible(false);
		record.getButton(0).setVisible(true);
		record.getButton(0).addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
//				new Dlg_StockSituation();
				
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				try{
				Dlg_StockSituation dlg=new Dlg_StockSituation(getShell());
				dlg.open("修改", holdStockDetails1.getLabel(0).getText());
				}
				catch(Exception e){}

			}
			
		});
		record.getButton(1).setVisible(true);
		record.getButton(1).addSelectionListener(new SelectionListener(){

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				record.setVisible(false);
			}
			
		});
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
