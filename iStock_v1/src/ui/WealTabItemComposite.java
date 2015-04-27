package ui;

/*
 * 构成图Tab的Composite
 */

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Font;
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

import controller.MouseListenerAdapt;

public class WealTabItemComposite extends Composite {

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
	private DrawLineChart lineChart;
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
	private DrawStackedChart stackChart;
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

//		Point size = assetsDetails.getSize();
//		int width = size.x;
//		int height = size.y*2;
//		assetsDetails.setSize(width, height);
		
		String[] assets = new String[]{"A股(¥)","0.00","-23.33\r\n-0.02%","17632.28\r\n+3.53%","517632.19","-101121.31","618814.43","500000.00","修改"};
		for(int i = 0; i < assets.length; ++i){
			
			Label label = assetsDetails.getLbl(i);
			label.setText(assets[i]);
			Point point = label.getSize();
			point = new Point(point.x, 50);
			label.setSize(point);
		}
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
		
		lblChange.addMouseListener(new MouseListenerAdapt(){

			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				try{
					DlgChangeCapital dlg = new DlgChangeCapital(shell);
					dlg.setCapital(Double.valueOf(lblCapital.getText()));
					Text money = dlg.getMoney();
					if(money != null){
						lblCapital.setText(money.getText());
					}
				dlg.open();
				double d = dlg.getCapital();
				System.out.println(dlg.getCapital());
				lblCapital.setText(String.valueOf(dlg.getCapital()));
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
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 292);
		fd_composite.right = new FormAttachment(0, 444);
		fd_composite.top = new FormAttachment(0, 5);
		fd_composite.left = new FormAttachment(0, 7);
		composite.setLayoutData(fd_composite);

		lineChartComposite = new Composite(composite, SWT.NONE);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(0, 262);
		fd_composite_1.right = new FormAttachment(0, 437);
		fd_composite_1.top = new FormAttachment(0);
		fd_composite_1.left = new FormAttachment(0);
		lineChartComposite.setLayoutData(fd_composite_1);

		lcTimeCompostie = new Composite(composite, SWT.NONE);
		FormData fd_composite_2 = new FormData();
		fd_composite_2.bottom = new FormAttachment(0, 287);
		fd_composite_2.right = new FormAttachment(0, 437);
		fd_composite_2.top = new FormAttachment(0, 263);
		fd_composite_2.left = new FormAttachment(0);
		lcTimeCompostie.setLayoutData(fd_composite_2);

		oneMonthLC = new Label(lcTimeCompostie, SWT.NONE);
		oneMonthLC.setBounds(10, 10, 30, 17);
		oneMonthLC.setText("1月");

		threeMonthLC = new Label(lcTimeCompostie, SWT.NONE);
		threeMonthLC.setBounds(46, 10, 30, 17);
		threeMonthLC.setText("3月");

		sixMonthLC = new Label(lcTimeCompostie, SWT.NONE);
		sixMonthLC.setBounds(82, 10, 30, 17);
		sixMonthLC.setText("6月");

		oneYearLC = new Label(lcTimeCompostie, SWT.NONE);
		oneYearLC.setBounds(118, 10, 30, 17);
		oneYearLC.setText("1年");

		threeYearLC = new Label(lcTimeCompostie, SWT.NONE);
		threeYearLC.setBounds(154, 10, 30, 17);
		threeYearLC.setText("3年");

		fiveYearLC = new Label(lcTimeCompostie, SWT.NONE);
		fiveYearLC.setBounds(190, 10, 30, 17);
		fiveYearLC.setText("5年");

		allLC = new Label(lcTimeCompostie, SWT.NONE);
		allLC.setBounds(226, 10, 36, 17);
		allLC.setText("全部");

		lineChart = new DrawLineChart();
		lineChartComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		final ChartComposite lineChartFrame = new ChartComposite(
				lineChartComposite, SWT.NONE, lineChart.getChart(), true);
		lineChartFrame.pack();

	}

	// 创建构成股堆积图
	public void createStackChart(Composite parent) {
		Composite composite = new Composite(stackGroup, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(0, 7);
		fd_composite.left = new FormAttachment(0, 7);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new FormLayout());

		stackChartComposite = new Composite(composite, SWT.NONE);
		FormData fd_stackChartComposite = new FormData();
		fd_stackChartComposite.bottom = new FormAttachment(0, 259);
		fd_stackChartComposite.right = new FormAttachment(0, 440);
		fd_stackChartComposite.top = new FormAttachment(0);
		fd_stackChartComposite.left = new FormAttachment(0);
		stackChartComposite.setLayoutData(fd_stackChartComposite);

		stackChart = new DrawStackedChart();
		stackChartComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		ChartComposite stackChartFrame = new ChartComposite(
				stackChartComposite, SWT.NONE, stackChart.getChart(), true);
		stackChartFrame.pack();

		scTimeComposite = new Composite(composite, SWT.NONE);
		FormData fd_scTimeComposite = new FormData();
		fd_scTimeComposite.bottom = new FormAttachment(0, 285);
		fd_scTimeComposite.right = new FormAttachment(0, 440);
		fd_scTimeComposite.top = new FormAttachment(0, 260);
		fd_scTimeComposite.left = new FormAttachment(0);
		scTimeComposite.setLayoutData(fd_scTimeComposite);

		oneMonthSC = new Label(scTimeComposite, SWT.NONE);
		oneMonthSC.setText("1月");
		oneMonthSC.setBounds(10, 10, 30, 17);

		threeMonthSC = new Label(scTimeComposite, SWT.NONE);
		threeMonthSC.setText("3月");
		threeMonthSC.setBounds(46, 10, 30, 17);

		sixMonthSC = new Label(scTimeComposite, SWT.NONE);
		sixMonthSC.setText("6月");
		sixMonthSC.setBounds(82, 10, 30, 17);

		oneYearSC = new Label(scTimeComposite, SWT.NONE);
		oneYearSC.setText("1年");
		oneYearSC.setBounds(118, 10, 30, 17);

		threeYearSC = new Label(scTimeComposite, SWT.NONE);
		threeYearSC.setText("3年");
		threeYearSC.setBounds(154, 10, 30, 17);

		fiveYearSC = new Label(scTimeComposite, SWT.NONE);
		fiveYearSC.setText("5年");
		fiveYearSC.setBounds(190, 10, 30, 17);

		allSC = new Label(scTimeComposite, SWT.NONE);
		allSC.setText("全部");
		allSC.setBounds(226, 10, 36, 17);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
