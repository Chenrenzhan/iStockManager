package ui;

/*
 * 持股构成Tab的Composite
 */
//import org.eclipse.swt.events.MouseEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.Color;

import interfac.MyRefreshable;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.security.auth.Refreshable;

import models.RecordsSet;

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

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.json.JSONException;
import org.json.JSONObject;

import util.Constant;
import util.RefreshTask;

public class OwnershipTabItemComposite extends Composite implements
		MyRefreshable {

	private Shell shell;
	private String _account;
	// K线图
	private static final String MIN = "min";
	private static final String DAILY = "daily";
	private static final String WEEKLY = "weekly";
	private static final String MONTHLY = "monthly";

	// 持仓情况
	private Group holdStockGroup;
	private HoldStockDetails holdStockHead;
	private HoldStockDetails holdStockDetails1;// 第一条
	private HoldStockDetails holdStockDetails2;
	private HoldStockDetails holdStockDetails3;
	private HoldStockDetails holdStockDetails4;
	private HoldStockDetails holdStockDetails5;
	private HoldStockDetails holdStockDetails6;
	private HoldStockDetails holdStockDetails7;
	private HoldStockDetails holdStockDetails8;
	private HoldStockDetails holdStockDetails9;
	private HoldStockDetails holdStockDetails10;
	private ArrayList<HoldStockDetails> hsdList;
	private final Color BACK_GROUND = new Color(null, 246, 250, 254);

	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Button btnPrevious;
	private Button btnNext;
	private Label lb_noNet;
	private Composite minKComp;
	private int last;// 一页的最后一个记录的序号
	private int len;// 股票交易记录的总数
	private int page;// 第几页
	private ArrayList<DlgStockDetails> DetailsDlgList;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 * @throws IOException
	 */
	public OwnershipTabItemComposite(Composite parent, int style,String account) {
		super(parent, SWT.NONE);
		shell = getShell();
		setLayout(null);
		_account=account;
		page = 0;
		hsdList = new ArrayList<HoldStockDetails>();
		DetailsDlgList = new ArrayList<DlgStockDetails>();
		// 持仓情况框架
		createHoldStockGroup(this);		
		//无网络标签
		createNoNetLabel();
		//持仓记录
		createHSDThread();
		// 历史记录
		// createRecordGroup(this);//有错
		// //K线图
		// createKChartComposite(this);

		// Label a = holdStockDetails1.getLabel(5);
		// System.out.println(a.getText());

	}

	private void createNoNetLabel() {
		// TODO Auto-generated method stub
		lb_noNet = new Label(holdStockGroup, SWT.CENTER);
		lb_noNet.setVisible(false);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL
				| GridData.FILL_VERTICAL);
		lb_noNet.setData(gridData);
		lb_noNet.setBounds(356, 210, 150, 30);
		lb_noNet.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 14,
				SWT.NORMAL));
		lb_noNet.setAlignment(SWT.CENTER);
		lb_noNet.setText("网络连接失败");
	}

	// 创建持仓情况
	public void createHoldStockGroup(Composite parent) {
		holdStockGroup = new Group(parent, SWT.NONE | SWT.H_SCROLL);
		holdStockGroup.setText("持仓情况");
		holdStockGroup.setBounds(10, 10, 948, 430);

		holdStockHead = new HoldStockDetails(holdStockGroup, SWT.NONE);
		holdStockHead.setBounds(1, 20, 946, 30);
		createSeparator(holdStockGroup, 1, 50, 946, 5);


	}

	//
	// //创建历史记录
	// public void createRecordGroup(Composite parent){
	// // recordDetails1.setSize(389, 30);
	// // recordDetails1.setLocation(10, 50);
	// // recordDetails1.setVisible(false);
	//
	// }

	// // 创建K线图
	// public void createKChartComposite(Composite parent) {
	//
	// setKChart();
	// }
	//
	// public void setKChart(){
	// GetInfoFromSina gifs = new GetInfoFromSina("sh600126");
	// Thread td = new Thread(gifs);
	// td.start();
	// Image image = new Image(Display.getDefault(), "data/temp/weekly.gif");
	// // drawImage(monthKComp, "data/temp/monthly.gif");
	// }
	//
	// public void drawImage(final Composite parent, String imagePath){
	// final Image img = new Image(Display.getDefault(), imagePath);
	//
	// // parent.setBackgroundImage(img);
	//
	// parent.addPaintListener(new PaintListener() {
	// @Override
	// public void paintControl(PaintEvent e) {
	// Point size = parent.getSize();System.out.println(size);
	// Point p = parent.getLocation();System.out.println(p);
	// e.gc.drawImage(img, 0, 0, 1024, 768, 4, 26, 401, 205);
	// }
	// });System.out.println(imagePath);
	// }

	// 创建持股情况详细信息
	public void createHSDThread() {
		HoldStockThread toupdate = new HoldStockThread(holdStockGroup);
		Thread th = new Thread(toupdate);
		th.start();
	}

	class HoldStockThread implements Runnable {

		String[][] strStock;
		HoldStock hs;
		Composite parent;
		boolean hasnet;

		public HoldStockThread(Composite parent) {
			// TODO Auto-generated constructor stub
			this.parent = parent;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {
				hs = new HoldStock(_account);
				strStock = hs.organizeHoldStock();
				hasnet = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				hasnet = false;
			}
			shell.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					// OwnershipTabItemComposite.redrawui();
					// ownershiptabOnPrerio.setSignal(false);
					// wealTabItemComposite.redrawui();
					// wealtabOnPrerio.setSignal(false);
					createHoldStockDetails(parent, strStock, hasnet);
				}
			});

		}
	}

	// 创建持股情况详细信息
	public void createHoldStockDetails(Composite parent, String[][] strStock,
			boolean hasnet) {

		if (!hasnet) {
			for (int i = 0; i < hsdList.size(); i++) {
				hsdList.get(i).dispose();
			}

			lb_noNet.setVisible(true);
		} else {
			lb_noNet.setVisible(false);

			// hs.countStockFromRecord();

			len = strStock.length;
			if (page * 10 > len)
				page = len / 10;
			last = len - page * 10 > 10 ? 10 : len - page * 10;
			// System.out.println("len:" + len + "    last:" + last);

			int index = page * 10;

			// if(strStock == null){
			// Label error = new Label(parent, SWT.NONE);
			// error.setBounds(200, 200, 200, 100);
			// error.setText("**网络连接出错,加载数据失败！");
			// error.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
			// error.setFont(SWTResourceManager.getFont("MingLiU", 20,
			// SWT.BOLD));
			// return ;
			// }

			// String[] stockdetails = new
			// String[]{"工商银行","4.57","0(0.00%)","-13.36/4.32","100","457.00","25.44(+5.89%)","1793.06(+4.15%)"};
			if (hsdList != null) {
				for (int i = 0; i < hsdList.size(); i++) {
					hsdList.get(i).dispose();
				}
			}
			this.hsdList = new ArrayList<HoldStockDetails>();
			for (int i = 0; i < last; ++i) {
				HoldStockDetails hsd = new HoldStockDetails(parent, SWT.NONE);
				hsd.setBounds(1, 50 + i * 35, 946, 35);
				hsdList.add(hsd);
				for (int j = 0; j < strStock[index + i].length - 1; ++j) {
					hsd.getLabel(j).setText(strStock[index + i][j + 1]);
				}
				// 股票代码
				final String code = strStock[index + i][0];

				// 股票详情图标
				Label lblDetail = hsd.getLabel(8);
				lblDetail.setForeground(SWTResourceManager
						.getColor(SWT.COLOR_BLUE));
				Image detailIcon = new Image(Display.getDefault(),
						"icon/details.png");
				lblDetail.setImage(detailIcon);
				lblDetail.addMouseListener(new DetailListener(code));

				Label lblHandle = hsd.getLabel(9);
				lblHandle.setVisible(false);

				// 添加图标
				Label lblAdd = hsd.getlblAdd();
				lblAdd.setVisible(true);
				lblAdd.setForeground(SWTResourceManager
						.getColor(SWT.COLOR_BLUE));
				Image addIcon = new Image(Display.getDefault(),
						"icon/addLittle.png");
				lblAdd.setImage(addIcon);
				lblAdd.addMouseListener(new AddListener(code));

				// 删除图标
				Label lblDelete = hsd.getlblDelete();
				lblDelete.setVisible(true);
				lblDelete.setForeground(SWTResourceManager
						.getColor(SWT.COLOR_BLUE));
				Image deleteIcon = new Image(Display.getDefault(),
						"icon/delete.png");
				lblDelete.setImage(deleteIcon);
				lblDelete.addMouseListener(new DeleteListener(code));
			}

			createSeparator(parent, 1, 400, 946, 3);
		}
		btnPrevious = new Button(holdStockGroup, SWT.BORDER);
		// btnPrevious.setBackground(SWTResourceManager
		// .getColor(SWT.COLOR_LIST_SELECTION));
		btnPrevious.setBounds(356, 410, 61, 17);
		formToolkit.adapt(btnPrevious, true, true);
		btnPrevious.setText("上一页");
		btnPrevious.addSelectionListener(new PreListener());
		// Image preIcon = new Image(Display.getDefault(), "icon/pre.png");
		// btnPrevious.setImage(preIcon);

		btnNext = new Button(holdStockGroup, SWT.BORDER);
		btnNext.setBounds(441, 410, 61, 17);
		formToolkit.adapt(btnNext, true, true);
		btnNext.setText("下一页");
		btnNext.addSelectionListener(new NextListener());
	}


	// 创建水平分割直线
	public void createSeparator(Composite parent, int x, int y, int width,
			int height) {
		Label label = new Label(holdStockGroup, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(x, y, width, height);
		formToolkit.adapt(label, true, true);
	}

	// public void changeRecord(final RecordDetails record){
	// record.getLabel(0).setText("2015-3-11");
	// record.getLabel(0).setForeground(new Color(null,0,0,0));
	// record.getLabel(1).setText("卖出");
	// record.getLabel(1).setForeground(new Color(null,0,0,0));
	// record.getLabel(2).setText("4.5");
	// record.getLabel(2).setForeground(new Color(null,0,0,0));
	// record.getLabel(3).setText("9900");
	// record.getLabel(3).setForeground(new Color(null,0,0,0));
	// record.getLabel(4).setVisible(false);
	// record.getButton(0).setVisible(true);
	// record.getButton(0).addSelectionListener(new SelectionListener(){
	//
	// @Override
	// public void widgetDefaultSelected(SelectionEvent arg0) {
	// // TODO Auto-generated method stub
	// // new Dlg_StockSituation();
	//
	//
	// }
	//
	// @Override
	// public void widgetSelected(SelectionEvent arg0) {
	// // TODO Auto-generated method stub
	// try{
	// DlgStockSituation dlg=new DlgStockSituation(getShell());
	// dlg.open("修改", holdStockDetails1.getLabel(0).getText());
	// }
	// catch(Exception e){}
	//
	// }
	//
	// });
	// record.getButton(1).setVisible(true);
	// record.getButton(1).addSelectionListener(new SelectionListener(){
	//
	// @Override
	// public void widgetDefaultSelected(SelectionEvent arg0) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void widgetSelected(SelectionEvent arg0) {
	// // TODO Auto-generated method stub
	// record.setVisible(false);
	// }
	//
	// });
	// }

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	class DetailListener extends MouseListenerAdapt {

		private String code;

		public DetailListener(String code) {
			// TODO Auto-generated constructor stub
			this.code = code;
		}

		@Override
		public void mouseDown(MouseEvent arg0) {
			// TODO Auto-generated method stub
			// String code = "sh600532";
			// 获取股票信息进程
			GetSingleStock gss = new GetSingleStock(code);
			Thread tdf = new Thread(gss);
			tdf.start();
			try {
				tdf.join();
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			// String fullcode = gss.getCode();

			try {

				if (!hasDlg(code)) {
					DlgStockDetails dlg = new DlgStockDetails(getShell(), _account, code);
					System.out.println("Detial:" + code);
					DetailsDlgList.add(dlg);
					dlg.open();
					DetailsDlgList.remove(dlg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private boolean hasDlg(String code2) {
			// TODO Auto-generated method stub
			System.out.println("Detialhasdlg:" + DetailsDlgList.size()
					+ "code:" + code2);
			for (int i = 0; i < DetailsDlgList.size(); i++) {
				if (DetailsDlgList.get(i).getCode() == code2) {
					System.out.println("return true");
					return true;
				}

			}
			return false;
		}
	}

	class AddListener extends MouseListenerAdapt {

		private String code;

		public AddListener(String code) {
			this.code = code;
		}

		@Override
		public void mouseDown(MouseEvent arg0) {
			// TODO Auto-generated method stub

			DlgStock ds = new DlgStock(shell, code);
			ds.add();
			JSONObject newJo = ds.getJoStockInfo();
			if (newJo != null) {
//				System.out.println("qqqqqqqqqqqq");

				try {
					RecordsSet rs = new RecordsSet(_account);
					rs.addRecord(newJo);
					rs.save();
					new RefreshTask(shell.getDisplay()).scheduleRecordChangeRf();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// add(newJo);

			}

		}

		// lblAdd.addMouseListener(new MouseListenerAdapt() {
		//
		// @Override
		// public void mouseDown(MouseEvent arg0) {
		// // TODO Auto-generated method stub
		// DlgStock ds = new DlgStock(shell, code);
		// ds.add();
		// JSONObject newJo = ds.getJoStockInfo();
		// if(newJo != null){
		// System.out.println("qqqqqqqqqqqq");
		//
		// try {
		// RecordsSet rs = new RecordsSet();
		// rs.addRecord(newJo);
		// rs.save();
		// } catch (JSONException | IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// // add(newJo);
		// }
		//
		// }
		//
		// });
	}

	class DeleteListener extends MouseListenerAdapt {

		private String code;

		public DeleteListener(String code) {
			this.code = code;
		}

		@Override
		public void mouseDown(MouseEvent arg0) {
			// removeOneStock(code);
		    try {
				new RecordsSet(_account).removeOneStock(code);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new RefreshTask(shell.getDisplay()).scheduleRecordChangeRf();
		}
	}

	@Override
	public void redrawui() {
		// TODO Auto-generated method stub
		// System.out.println("OwnerTabRefreshed");
		createHSDThread();
	}

	@Override
	public void redrawOnAdd() {
		// TODO Auto-generated method stub
		createHSDThread();
	}

	// 下一页监听按钮事件
	public class NextListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			page++;
			createHSDThread();

		}

	}

	// 上一页按钮监听事件
	public class PreListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			if (page > 0) {
				--page;
				createHSDThread();
			}
		}
	}
}
