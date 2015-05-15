package ui;

/*
 * 构成图Tab的Composite
 */

import interfac.MyRefreshable;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

//import swing2swt.layout.FlowLayout;

import org.eclipse.swt.widgets.Group;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.json.JSONException;

import util.Constant;
import controller.MouseListenerAdapt;
import controller.StockMath;
import controller.TotalAssets;

public class WealTabItemComposite extends Composite implements MyRefreshable {

	private static final int ONEMONTH = 1;
	private static final int THREEMONTH = 2;
	private static final int SIXMONTH = 3;
	
	// 个人总值
	private Group totalAssetsGroup;
	// 收益率
	private Group yieldGroup;
	// 构成堆积图
	private Group stackGroup;
	// 个人总值表头
	private TotalAssetsDetails assetsHead;
	private TotalAssetsDetails assetsDetails;

	// 折线图LC(LineChart)
	private Composite lineChartComposite;
	private Composite lcTimeCompostie;
	private Composite lineComposite;
	private LineChart lineChart;
	private ChartComposite lineChartFrame;
	private Label oneMonthLC;
	private Label threeMonthLC;
	private Label sixMonthLC;
	private Label oneYearLC;
	private Label threeYearLC;
	private Label fiveYearLC;
	private Label allLC;

	// 堆积图SC(StackChart)
	private Composite stackChartComposite;
	private Composite scTimeComposite;
	private Composite stackComposite;
	private StackedChart stackChart;
	private ChartComposite stackChartFrame;
	private Label oneMonthSC;
	private Label threeMonthSC;
	private Label sixMonthSC;
	private Label oneYearSC;
	private Label threeYearSC;
	private Label fiveYearSC;
	private Label allSC;
	
	private final Shell shell;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public WealTabItemComposite(Composite parent, int style) {
		super(parent, SWT.NONE);
		shell = parent.getShell();
		setLayout(new FormLayout());

		// 个人总值
		totalAssetsGroup = new Group(this, SWT.NONE);
		FormData fd_totalAssetsGroup = new FormData();
		fd_totalAssetsGroup.bottom = new FormAttachment(0, 111);
		fd_totalAssetsGroup.right = new FormAttachment(0, 952);
		fd_totalAssetsGroup.top = new FormAttachment(0, 10);
		fd_totalAssetsGroup.left = new FormAttachment(0, 10);
		totalAssetsGroup.setLayoutData(fd_totalAssetsGroup);
		totalAssetsGroup.setText("个人总值");
		totalAssetsGroup.setLayout(null);
		createAssetsHead(totalAssetsGroup);
		createAssetsDetails(totalAssetsGroup);

		Label separator = new Label(totalAssetsGroup, SWT.SEPARATOR
				| SWT.HORIZONTAL);
		separator.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		separator.setBounds(1, 47, 940, 3);

		// 收益率
		yieldGroup = new Group(this, SWT.NONE);
		FormData fd_yieldGroup = new FormData();
		fd_yieldGroup.bottom = new FormAttachment(0, 445);
		fd_yieldGroup.right = new FormAttachment(0, 467);
		fd_yieldGroup.top = new FormAttachment(0, 126);
		fd_yieldGroup.left = new FormAttachment(0, 10);
		yieldGroup.setLayoutData(fd_yieldGroup);
		yieldGroup.setText("收益率");
		// 画收益率折线图
		drawLineChart(yieldGroup);

		// 构成堆积图
		stackGroup = new Group(this, SWT.NONE);
		stackGroup.setLayout(new FormLayout());
		FormData fd_stackGroup = new FormData();
		fd_stackGroup.bottom = new FormAttachment(0, 445);
		fd_stackGroup.right = new FormAttachment(0, 952);
		fd_stackGroup.top = new FormAttachment(0, 126);
		fd_stackGroup.left = new FormAttachment(0, 492);
		stackGroup.setLayoutData(fd_stackGroup);
		stackGroup.setText("持股构成");
		// 创建构成股堆积图
		createStackChart(stackGroup);

	}

	// 个人总值表头
	public void createAssetsHead(Composite parent) {
		assetsHead = new TotalAssetsDetails(parent, SWT.NONE);
		assetsHead.setBounds(3, 17, 935, 30);
	}

	// 个人总值详细信息
	public void createAssetsDetails(Composite parent) {
		assetsDetails = new TotalAssetsDetails(parent, SWT.NONE);
		assetsDetails.setBounds(3, 50, 935, 50);
        setAssetsLableData(assetsDetails);
//		Point size = assetsDetails.getSize();
//		int width = size.x;
//		int height = size.y*2;
//		assetsDetails.setSize(width, height);
		
//		String[] assets = new String[]{"A股(¥)","0.00","-23.33\r\n-0.02%","17632.28\r\n+3.53%","517632.19","-101121.31","618814.43","500000.00"};
		
		//修改浮动盈亏数据为绿色
		Label lblFloatBreakEvent = assetsDetails.getLbl(2);
		lblFloatBreakEvent.setForeground(getDisplay().getSystemColor(SWT.COLOR_GREEN));
		//修改盈亏数据为红色
		Label lblBreakEvent = assetsDetails.getLbl(3);
		lblBreakEvent.setForeground(getDisplay().getSystemColor(SWT.COLOR_RED));
		
		//本金
		final Label lblCapital = assetsDetails.getLbl(7);
		
		//修改“修改本金Label”样式
		final Label lblChange = assetsDetails.getLbl(8);
		lblChange.setFont( new Font(getDisplay(), "Arial",8 , SWT.BOLD)) ;
		lblChange.setForeground(getDisplay().getSystemColor(SWT.COLOR_BLUE));
		Image changeIcon = new Image(Display.getDefault(), "icon/change.png");
		lblChange.setImage(changeIcon);
		lblChange.setToolTipText("修改本金");
		
		lblChange.addMouseListener(new MouseListenerAdapt(){

			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				try{
					Constant.PreriodicRefresh.refreshAndSave();
					DlgChangeCapital dlg = new DlgChangeCapital(shell);
					dlg.setCapital(Double.valueOf(lblCapital.getText()));
					Text money = dlg.getMoney();
					if(money != null){
						lblCapital.setText(money.getText());
					}
				dlg.open();
				double d = dlg.getCapital();
				System.out.println(dlg.getCapital());
				lblCapital.setText(StockMath.valueOf(dlg.getCapital()));
				new TotalAssets().setCapital(d);//保存修改后的本金
				}
				catch (Exception e) {
					   e.printStackTrace();
				}  
			}

		});
	}

	// 创建收益率折线图
	public void drawLineChart(Composite parent) {
		yieldGroup.setLayout(new FormLayout());
		lineComposite = new Composite(parent, SWT.NONE);
		lineComposite.setLayout(new FormLayout());
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 292);
		fd_composite.right = new FormAttachment(0, 444);
		fd_composite.top = new FormAttachment(0, 5);
		fd_composite.left = new FormAttachment(0, 7);
		lineComposite.setLayoutData(fd_composite);

		lineChartComposite = new Composite(lineComposite, SWT.NONE);
		lineChartComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(0, 262);
		fd_composite_1.right = new FormAttachment(0, 437);
		fd_composite_1.top = new FormAttachment(0);
		fd_composite_1.left = new FormAttachment(0);
		lineChartComposite.setLayoutData(fd_composite_1);

		lcTimeCompostie = new Composite(lineComposite, SWT.NONE);
		FormData fd_composite_2 = new FormData();
		fd_composite_2.bottom = new FormAttachment(0, 287);
		fd_composite_2.right = new FormAttachment(0, 437);
		fd_composite_2.top = new FormAttachment(0, 263);
		fd_composite_2.left = new FormAttachment(0);
		lcTimeCompostie.setLayoutData(fd_composite_2);

		createLineChart(ONEMONTH);
		
		oneMonthLC = new Label(lcTimeCompostie, SWT.CENTER);
		oneMonthLC.setBounds(10, 10, 30, 17);
		oneMonthLC.setText("1月");
		oneMonthLC.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		oneMonthLC.addMouseListener(new LineListener(ONEMONTH));

		threeMonthLC = new Label(lcTimeCompostie, SWT.CENTER);
		threeMonthLC.setBounds(46, 10, 30, 17);
		threeMonthLC.setText("3月");
		threeMonthLC.addMouseListener(new LineListener(THREEMONTH));

		sixMonthLC = new Label(lcTimeCompostie, SWT.CENTER);
		sixMonthLC.setBounds(82, 10, 30, 17);
		sixMonthLC.setText("6月");
		sixMonthLC.addMouseListener(new LineListener(SIXMONTH));

//		oneYearLC = new Label(lcTimeCompostie, SWT.NONE);
//		oneYearLC.setBounds(118, 10, 30, 17);
//		oneYearLC.setText("1年");
//
//		threeYearLC = new Label(lcTimeCompostie, SWT.NONE);
//		threeYearLC.setBounds(154, 10, 30, 17);
//		threeYearLC.setText("3年");
//
//		fiveYearLC = new Label(lcTimeCompostie, SWT.NONE);
//		fiveYearLC.setBounds(190, 10, 30, 17);
//		fiveYearLC.setText("5年");
//
//		allLC = new Label(lcTimeCompostie, SWT.NONE);
//		allLC.setBounds(226, 10, 36, 17);
//		allLC.setText("全部");

//		lineChart = new LineChart();
//		lineChartComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
//		final ChartComposite lineChartFrame = new ChartComposite(
//				lineChartComposite, SWT.NONE, lineChart.getChart(), true);
//		lineChartFrame.pack();

	}

	// 创建构成股堆积图
	public void createStackChart(Composite parent) {
		stackComposite = new Composite(stackGroup, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 7);
		fd_composite.left = new FormAttachment(0, 7);
		stackComposite.setLayoutData(fd_composite);
		stackComposite.setLayout(new FormLayout());

		stackChartComposite = new Composite(stackComposite, SWT.NONE);
		stackChartComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		FormData fd_stackChartComposite = new FormData();
		fd_stackChartComposite.bottom = new FormAttachment(0, 259);
		fd_stackChartComposite.right = new FormAttachment(0, 440);
		fd_stackChartComposite.top = new FormAttachment(0);
		fd_stackChartComposite.left = new FormAttachment(0);
		stackChartComposite.setLayoutData(fd_stackChartComposite);
		
		createStackeChart(ONEMONTH);
//		stackChartComposite = new Composite(composite, SWT.NONE);
//		stackChartComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
//		FormData fd_stackChartComposite = new FormData();
//		fd_stackChartComposite.bottom = new FormAttachment(0, 259);
//		fd_stackChartComposite.right = new FormAttachment(0, 440);
//		fd_stackChartComposite.top = new FormAttachment(0);
//		fd_stackChartComposite.left = new FormAttachment(0);
//		stackChartComposite.setLayoutData(fd_stackChartComposite);
//
//		stackChart = new StackedChart(THREEMONTH);
//		ChartComposite stackChartFrame = new ChartComposite(
//				stackChartComposite, SWT.NONE, stackChart.getChart(), true);
//		stackChartFrame.pack();

		scTimeComposite = new Composite(stackComposite, SWT.NONE);
		FormData fd_scTimeComposite = new FormData();
		fd_scTimeComposite.bottom = new FormAttachment(0, 285);
		fd_scTimeComposite.right = new FormAttachment(0, 440);
		fd_scTimeComposite.top = new FormAttachment(0, 260);
		fd_scTimeComposite.left = new FormAttachment(0);
		scTimeComposite.setLayoutData(fd_scTimeComposite);

		oneMonthSC = new Label(scTimeComposite, SWT.CENTER);
		oneMonthSC.setAlignment(SWT.CENTER);
		oneMonthSC.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		oneMonthSC.setText("1月");
		oneMonthSC.setBounds(10, 10, 30, 17);
		oneMonthSC.addMouseListener(new StackedListener(ONEMONTH));
		
		threeMonthSC = new Label(scTimeComposite, SWT.CENTER);
		threeMonthSC.setAlignment(SWT.CENTER);
		threeMonthSC.setText("3月");
		threeMonthSC.setBounds(46, 10, 30, 17);
		threeMonthSC.addMouseListener(new StackedListener(THREEMONTH));
		
		sixMonthSC = new Label(scTimeComposite, SWT.CENTER);
		sixMonthSC.setAlignment(SWT.CENTER);
		sixMonthSC.setText("6月");
		sixMonthSC.setBounds(82, 10, 30, 17);
		sixMonthSC.addMouseListener(new StackedListener(SIXMONTH));
		
		

//		oneYearSC = new Label(scTimeComposite, SWT.NONE);
//		oneYearSC.setText("1年");
//		oneYearSC.setBounds(118, 10, 30, 17);
//
//		threeYearSC = new Label(scTimeComposite, SWT.NONE);
//		threeYearSC.setText("3年");
//		threeYearSC.setBounds(154, 10, 30, 17);
//
//		fiveYearSC = new Label(scTimeComposite, SWT.NONE);
//		fiveYearSC.setText("5年");
//		fiveYearSC.setBounds(190, 10, 30, 17);
//
//		allSC = new Label(scTimeComposite, SWT.NONE);
//		allSC.setText("全部");
//		allSC.setBounds(226, 10, 36, 17);

	}

	public void createStackeChart(int type){
		StackedChart stackChart = new StackedChart(type);
		stackChartFrame = new ChartComposite(
				stackChartComposite, SWT.NONE, stackChart.getChart(), true);
		stackChartFrame.pack();
	}
	
	public void createLineChart(int type){
		LineChart lineChart = new LineChart(type);
		lineChartFrame = new ChartComposite(
				lineChartComposite, SWT.NONE, lineChart.getChart(), true);
		lineChartFrame.pack();
	}
	public void setAssetsLableData(TotalAssetsDetails assetsDetails){String[] assets = null;
	try {
		assets = new TotalAssets().orgnizeAssets();
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	for(int i = 0; i < assets.length; ++i){
		
		Label label = assetsDetails.getLbl(i);
		label.setText(assets[i]);
		Point point = label.getSize();
		point = new Point(point.x, 50);
		label.setSize(point);
	}}
	class LineListener implements MouseListener{
		public int type;
		Composite composite;
		LineListener(int type){
			this.type = type;
		}
		@Override
		public void mouseDoubleClick(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDown(MouseEvent arg0) {
			// TODO Auto-generated method stub
			oneMonthLC.setBackground(null);
			threeMonthLC.setBackground(null);
			sixMonthLC.setBackground(null);
			
			switch(type){
			case 1:
				oneMonthLC.setBackground(
						SWTResourceManager.getColor(SWT.COLOR_GRAY));
				break;
			case 2:
				threeMonthLC.setBackground(
						SWTResourceManager.getColor(SWT.COLOR_GRAY));
				break;
			case 3:
				sixMonthLC.setBackground(
						SWTResourceManager.getColor(SWT.COLOR_GRAY));
				break;
			}
		}

		@Override
		public void mouseUp(MouseEvent arg0) {
			// TODO Auto-generated method stub
			lineChartFrame.dispose();
			createLineChart(type);
			lineChartComposite.layout(true);
//			System.out.println("sssssssssssssssss");
		}
		
	}
	
	
	class StackedListener implements MouseListener{
		public int type;
		Composite composite;
		StackedListener(int type){
			this.type = type;
		}
		@Override
		public void mouseDoubleClick(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDown(MouseEvent arg0) {
			// TODO Auto-generated method stub
			oneMonthSC.setBackground(null);
			threeMonthSC.setBackground(null);
			sixMonthSC.setBackground(null);
			
			switch(type){
			case 1:
				oneMonthSC.setBackground(
						SWTResourceManager.getColor(SWT.COLOR_GRAY));
				break;
			case 2:
				threeMonthSC.setBackground(
						SWTResourceManager.getColor(SWT.COLOR_GRAY));
				break;
			case 3:
				sixMonthSC.setBackground(
						SWTResourceManager.getColor(SWT.COLOR_GRAY));
				break;
			}
		}

		@Override
		public void mouseUp(MouseEvent arg0) {
			// TODO Auto-generated method stub
			stackChartFrame.dispose();
			createStackeChart(type);
			stackChartComposite.layout(true);
			System.out.println("sssssssssssssssss");
		}
		
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}



	@Override
	public void redrawui() {
		// TODO Auto-generated method stub

		setAssetsLableData(assetsDetails);
		System.out.println("WealTabRefreshed");
	}


}
