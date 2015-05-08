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
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
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

import controller.GetInfoFromSina;
import controller.GetKChartFromSina;
import controller.GetSingleStock;
import controller.HoldStock;
import controller.MouseListenerAdapt;

import org.eclipse.swt.layout.GridLayout;
import org.json.JSONException;

public class OwnershipTabItemComposite extends Composite {

	//K线图
	private static final String MIN = "min";
	private static final String DAILY = "daily";
	private static final String WEEKLY = "weekly";
	private static final String MONTHLY = "monthly";
	
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
	
	private final Color BACK_GROUND = new Color(null, 246, 250, 254);
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Label btnPrevious;
	private Label btnNext;
	private Composite minKComp;
	
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
//		createRecordGroup(this);//有错
//		//K线图
//		createKChartComposite(this);
		
//		Label a = holdStockDetails1.getLabel(5);
//		System.out.println(a.getText());
		
	}
	
	//创建持仓情况
	public void createHoldStockGroup(Composite parent){
		holdStockGroup = new Group(parent, SWT.NONE | SWT.H_SCROLL);
		holdStockGroup.setText("持仓情况");
		holdStockGroup.setBounds(10, 10, 948, 430);
		
		createHoldStockDetails(holdStockGroup);
		
	}
	
//	
//	//创建历史记录
//	public void createRecordGroup(Composite parent){
////		recordDetails1.setSize(389, 30);
////		recordDetails1.setLocation(10, 50);
////		recordDetails1.setVisible(false);
//
//	}

//	// 创建K线图
//	public void createKChartComposite(Composite parent) {
//		
//		setKChart();
//	}
//
//	public void setKChart(){
//		GetInfoFromSina gifs = new GetInfoFromSina("sh600126");
//		Thread td = new Thread(gifs);
//		td.start();
//		Image image = new Image(Display.getDefault(), "data/temp/weekly.gif");
////		drawImage(monthKComp, "data/temp/monthly.gif");
//	}
//	
//	public void drawImage(final Composite parent, String imagePath){
//		final Image img = new Image(Display.getDefault(), imagePath);
//	
////		parent.setBackgroundImage(img);
//		
//		parent.addPaintListener(new PaintListener() {
//            @Override
//            public void paintControl(PaintEvent e) {
//                Point size  = parent.getSize();System.out.println(size);
//                Point p  = parent.getLocation();System.out.println(p);
//                e.gc.drawImage(img, 0, 0, 1024, 768, 4, 26, 401, 205);
//            }
//        });System.out.println(imagePath);
//	}
	
	//创建持股情况详细信息
	public void createHoldStockDetails(Composite parent)
	{
		String[][] strStock = null;
		HoldStock hs = new HoldStock();
		try {
//			hs.countStockFromRecord();
			strStock = hs.organizeHoldStock();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		holdStockHead = new HoldStockDetails(parent, SWT.NONE);
		holdStockHead.setBounds(1, 20, 946, 30);
		
		createSeparator(parent, 1, 50, 946, 5);
//		String[] stockdetails = new String[]{"工商银行","4.57","0(0.00%)","-13.36/4.32","100","457.00","25.44(+5.89%)","1793.06(+4.15%)"};
		for(int i = 0; i < strStock.length; ++i){
			HoldStockDetails hsd = new HoldStockDetails(parent, SWT.NONE);
			hsd.setBounds(1, 50 + i * 35, 946, 35);
			
			for(int j = 0; j < strStock[i].length-1; ++j){
				hsd.getLabel(j).setText(strStock[i][j+1]);
			}
			//股票代码
			String code = strStock[i][0];
			
			//股票详情图标
			Label lblDetail = hsd.getLabel(8);
			lblDetail.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			lblDetail.addMouseListener(new DetailListener(code));
			Image detailIcon = new Image(Display.getDefault(), "icon/details.png");
			lblDetail.setImage(detailIcon);
			
			Label lblHandle = hsd.getLabel(9);
			lblHandle.setVisible(false);
			
			//添加图标
			Label lblAdd = hsd.getlblAdd();
			lblAdd.setVisible(true);
			lblAdd.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			lblAdd.addMouseListener(new AddListener());
			Image addIcon = new Image(Display.getDefault(), "icon/addLittle.png");
			lblAdd.setImage(addIcon);
			
			//删除图标
			Label lblDelete = hsd.getlblDelete();
			lblDelete.setVisible(true);
			lblDelete.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
			Image deleteIcon = new Image(Display.getDefault(), "icon/delete.png");
			lblDelete.setImage(deleteIcon);
		}
		
		/*
		holdStockDetails1 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails1.setBounds(1, 50, 946, 35);
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
//				recordDetails1.setVisible(true);
//				changeRecord(recordDetails1);
			}
			
		});
		
		
		holdStockDetails2 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails2.setBounds(1, 85, 946, 35);
//		holdStockDetails2.setBackground(new Color(null, 246, 250, 254));
		holdStockDetails3 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails3.setBounds(1, 120, 946, 35);
		holdStockDetails4 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails4.setBounds(1, 155, 946, 35);
		holdStockDetails5 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails5.setBounds(1, 190, 946, 35);
		holdStockDetails6 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails6.setBounds(1, 225, 946, 35);
		holdStockDetails7 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails7.setBounds(1, 260, 946, 35);
		holdStockDetails8 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails8.setBounds(1, 295, 946, 35);
		holdStockDetails9 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails9.setBounds(1, 330, 946, 35);
		holdStockDetails10 = new HoldStockDetails(parent, SWT.NONE);
		holdStockDetails10.setBounds(1, 365, 946, 35);
		*/
		
		createSeparator(parent, 1, 400, 946, 3);
		
		btnPrevious = new Label(holdStockGroup, SWT.BORDER | SWT.SHADOW_IN);
		btnPrevious.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		btnPrevious.setBounds(356, 410, 61, 17);
		formToolkit.adapt(btnPrevious, true, true);
		btnPrevious.setText("上一页");
//		Image preIcon = new Image(Display.getDefault(), "icon/pre.png");
//		btnPrevious.setImage(preIcon);
		
		btnNext = new Label(holdStockGroup, SWT.BORDER);
		btnNext.setBounds(441, 410, 61, 17);
		formToolkit.adapt(btnNext, true, true);
		btnNext.setText("下一页");
	}
	
	//创建水平分割直线
	public void  createSeparator(Composite parent, 
			int x, int y, int width, int height){
		Label label = new Label(holdStockGroup, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(x,y,width,height);
		formToolkit.adapt(label, true, true);
	}
	
//	public void changeRecord(final RecordDetails record){
//		record.getLabel(0).setText("2015-3-11");
//		record.getLabel(0).setForeground(new Color(null,0,0,0));
//		record.getLabel(1).setText("卖出");
//		record.getLabel(1).setForeground(new Color(null,0,0,0));
//		record.getLabel(2).setText("4.5");
//		record.getLabel(2).setForeground(new Color(null,0,0,0));
//		record.getLabel(3).setText("9900");
//		record.getLabel(3).setForeground(new Color(null,0,0,0));
//		record.getLabel(4).setVisible(false);
//		record.getButton(0).setVisible(true);
//		record.getButton(0).addSelectionListener(new SelectionListener(){
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
////				new Dlg_StockSituation();
//				
//				
//			}
//
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				try{
//				DlgStockSituation dlg=new DlgStockSituation(getShell());
//				dlg.open("修改", holdStockDetails1.getLabel(0).getText());
//				}
//				catch(Exception e){}
//
//			}
//			
//		});
//		record.getButton(1).setVisible(true);
//		record.getButton(1).addSelectionListener(new SelectionListener(){
//
//			@Override
//			public void widgetDefaultSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				record.setVisible(false);
//			}
//			
//		});
//	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	class DetailListener extends MouseListenerAdapt{

		private String code;
		
		public DetailListener(String code) {
			// TODO Auto-generated constructor stub
			this.code = code;
		}

		@Override
		public void mouseDown(MouseEvent arg0) {
			// TODO Auto-generated method stub
//			String code = "sh600532";
			//获取股票信息进程
			GetSingleStock gss = new GetSingleStock(code);
			Thread tdf = new Thread(gss);
			tdf.start();
			try {
				tdf.join();
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			String fullcode = gss.getCode();
			
			//获取分时K线图线程
			GetKChartFromSina minK = new GetKChartFromSina(fullcode, MIN);
			Thread	minTd = new Thread(minK);
			minTd.start();
			
			//获取日K线图线程
			GetKChartFromSina dailyK = new GetKChartFromSina(fullcode, DAILY);
			Thread	dailyTd = new Thread(dailyK);
			dailyTd.start();
			
			//获取周K线图线程
			GetKChartFromSina weeklyK = new GetKChartFromSina(fullcode, WEEKLY);
			Thread	weeklyTd = new Thread(weeklyK);
			weeklyTd.start();
			
			//获取月K线图线程
			GetKChartFromSina monthlyK = new GetKChartFromSina(fullcode, MONTHLY);
			Thread	monthlyTd = new Thread(monthlyK);
			monthlyTd.start();
			
			Shell shell = getShell();
			//存储老的光标
			Cursor oldCursor = Display.getCurrent().getCursorControl().getCursor();
			//设置等待光标
			Cursor cursor = Display.getCurrent().getSystemCursor(SWT.CURSOR_WAIT);
			shell.setCursor(cursor);
			
			try {
//				tdf.join();// 等待子线程结束
				minTd.join();// 等待子线程结束
				dailyTd.join();
				weeklyTd.join();
				monthlyTd.join();

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//设置会原来的光标样式
			shell.setCursor(oldCursor);
			try{
				DlgStockDetails dlg = new DlgStockDetails(getShell(), code);
				dlg.open();
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	class AddListener extends MouseListenerAdapt{

		@Override
		public void mouseDown(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
			try{
				DlgStockSituation dlg = new DlgStockSituation(getShell());
				dlg.open("", "");
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}
}
