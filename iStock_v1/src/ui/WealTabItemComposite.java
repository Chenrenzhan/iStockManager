package ui;

/*
 * 构成图Tab的Composite
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

//import swing2swt.layout.FlowLayout;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.wb.swt.SWTResourceManager;
import org.jfree.experimental.chart.swt.ChartComposite;

public class WealTabItemComposite extends Composite {

	//个人总值
	private Group totalAssetsGroup;
	//收益率
	private Group yieldGroup;
	//构成堆积图
	private Group stackGroup;
	//个人总值表头
	private TotalAssetsDetails assetsHead;
	private TotalAssetsDetails assetsDetails;
//	private Label label;
//	private Label lblNewLabel;
	private Composite lineChartComposite;
	private Composite timeCompostie;
	
	private Label threeMonth;
	private Label sixMonth;
	private Label oneYear;
	private Label threeYear;
	private Label fiveYear;
	private Label all;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public WealTabItemComposite(Composite parent, int style) {
		super(parent, SWT.NONE);
		setLayout(new FormLayout());
		
		//个人总值
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
		
		Label separator = new Label(totalAssetsGroup, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		separator.setBounds(1, 47, 940, 3);
		
		
		//收益率
		yieldGroup = new Group(this, SWT.NONE);
		FormData fd_yieldGroup = new FormData();
		fd_yieldGroup.bottom = new FormAttachment(0, 445);
		fd_yieldGroup.right = new FormAttachment(0, 467);
		fd_yieldGroup.top = new FormAttachment(0, 126);
		fd_yieldGroup.left = new FormAttachment(0, 10);
		yieldGroup.setLayoutData(fd_yieldGroup);
		yieldGroup.setText("收益率");
		//画收益率折线图
		drawLineChart(yieldGroup);
		
		
		
		//构成堆积图
		stackGroup = new Group(this, SWT.NONE);
		FormData fd_stackGroup = new FormData();
		fd_stackGroup.bottom = new FormAttachment(0, 445);
		fd_stackGroup.right = new FormAttachment(0, 952);
		fd_stackGroup.top = new FormAttachment(0, 126);
		fd_stackGroup.left = new FormAttachment(0, 492);
		stackGroup.setLayoutData(fd_stackGroup);
		stackGroup.setText("持股构成");
		
		Label lblNewLabel_2 = new Label(stackGroup, SWT.NONE);
		lblNewLabel_2.setBounds(174, 186, 61, 17);
		lblNewLabel_2.setText("New Label");
		
	}
	//个人总值表头
	public void createAssetsHead(Composite parent){
		assetsHead = new TotalAssetsDetails(parent, SWT.NONE);
		assetsHead.setBounds(3, 17, 935, 30);		
	}
	
	//个人总值详细信息
	public void createAssetsDetails(Composite parent){
		assetsDetails = new TotalAssetsDetails(parent, SWT.NONE);
		assetsDetails.setBounds(3, 50, 935, 45);
		
	}
	
	public void drawLineChart(Composite parent){
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
		
		timeCompostie = new Composite(composite, SWT.NONE);
		FormData fd_composite_2 = new FormData();
		fd_composite_2.bottom = new FormAttachment(0, 287);
		fd_composite_2.right = new FormAttachment(0, 437);
		fd_composite_2.top = new FormAttachment(0, 263);
		fd_composite_2.left = new FormAttachment(0);
		timeCompostie.setLayoutData(fd_composite_2);
		
		Label oneMonth = new Label(timeCompostie, SWT.NONE);
		oneMonth.setBounds(10, 10, 30, 17);
		oneMonth.setText("1月");
		
		threeMonth = new Label(timeCompostie, SWT.NONE);
		threeMonth.setBounds(46, 10, 30, 17);
		threeMonth.setText("3月");
		
		sixMonth = new Label(timeCompostie, SWT.NONE);
		sixMonth.setBounds(82, 10, 30, 17);
		sixMonth.setText("6月");
		
		oneYear = new Label(timeCompostie, SWT.NONE);
		oneYear.setBounds(118, 10, 30, 17);
		oneYear.setText("1年");
		
		threeYear = new Label(timeCompostie, SWT.NONE);
		threeYear.setBounds(154, 10, 30, 17);
		threeYear.setText("3年");
		
		fiveYear = new Label(timeCompostie, SWT.NONE);
		fiveYear.setBounds(190, 10, 30, 17);
		fiveYear.setText("5年");
		
		all = new Label(timeCompostie, SWT.NONE);
		all.setBounds(226, 10, 36, 17);
		all.setText("全部");
		
		DrawLineChart chart = new DrawLineChart("Line Chart Demo");
        lineChartComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
        final ChartComposite lineChartFrame = new ChartComposite(
        		lineChartComposite, SWT.NONE, chart.getChart(),true);
        lineChartFrame.pack();
        
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
