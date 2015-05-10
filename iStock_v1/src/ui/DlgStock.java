package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.json.JSONException;
import org.json.JSONObject;

import controller.StockMath;

public class DlgStock extends Dialog {
	private static final SimpleDateFormat NDF = 
			new SimpleDateFormat("yyyy/MM/dd");
	private static final SimpleDateFormat DF = 
			new SimpleDateFormat("yyyy-MM-dd");
	protected Object result;
	
	private Composite composite;
	private Label operate;
	
	
	protected Shell shell;
	
	private Label lblCurPrice; //当前价
	private DateTime date; //交易日期
	private Combo cbType; //交易类型，买入，卖出等
	private Text state; //说明
	private Text volumes; //交易数量
	private Text taxes; //税率
	private Text price; //交易价格
	private Text commission; //佣金
	private Text remark; //备注
	
	private Button btnOk; //确定按钮
	private Button btnCancel; //取消按钮
	
//	private String[] stockInfo;
	
	private JSONObject joStockInfo; 
	
	private String stockName; //股票名字
	private String code; //股票代码
	private String curPrice; //股票当前价格
	private String operateStr;//操作类型，添加或者修改

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DlgStock(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		this.operateStr = "操作";
	}
	
	//修改调用的构造函数
	public DlgStock(Shell parent, int style, JSONObject jo, String curPrice){
		super(parent, style);
		
		this.joStockInfo = jo;
		try {
			this.stockName = jo.getString("name");
			this.code = jo.getString("code");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.curPrice = curPrice;
		setText(this.stockName);
		this.operateStr = "修改股票交易记录信息";
	}
	
	//添加交易记录调用的构造函数
	public DlgStock(Shell parent, int style, 
			String stockName, String code, String curPrice){
		super(parent, style);
		
		joStockInfo = new JSONObject();
		this.stockName = stockName;
		this.code = code;
		this.curPrice = curPrice;
		setText(this.stockName);
		this.operateStr = "添加股票交易记录";
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
//		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(561, 573);
		shell.setText(getText());
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		composite = new Composite(shell, SWT.NONE);
		
		operate = new Label(composite, SWT.NONE);
		operate.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 15, SWT.BOLD));
		operate.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		operate.setBounds(37, 10, 236, 32);
		operate.setText(operateStr);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setBounds(37, 54, 74, 20);
		label_1.setText("当 前 价：");
		
		lblCurPrice = new Label(composite, SWT.NONE);
		lblCurPrice.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblCurPrice.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblCurPrice.setBounds(129, 54, 61, 20);
		lblCurPrice.setText("27.89");
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("日       期：");
		label_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_2.setBounds(37, 97, 74, 24);
		
		date = new DateTime(composite, SWT.BORDER);
		date.setBounds(129, 97, 99, 24);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setText("说      明：");
		label_6.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_6.setBounds(37, 297, 74, 24);
		
		state = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		state.setBounds(128, 293, 370, 68);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setText("类      型：");
		label_7.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_7.setBounds(307, 99, 74, 24);
		
		cbType = new Combo(composite, SWT.READ_ONLY);
		cbType.setVisible(true);
		cbType.setItems(new String[] {"买入", "卖出", "补仓", "卖空"});
		cbType.setBounds(398, 98, 100, 25);
		
		volumes = new Text(composite, SWT.BORDER);
		volumes.setBounds(398, 160, 100, 24);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setText("数      量：");
		label_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_8.setBounds(307, 165, 74, 24);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("说率(‰)：");
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_4.setBounds(307, 230, 74, 24);
		
		taxes = new Text(composite, SWT.BORDER);
		taxes.setBounds(398, 226, 100, 24);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("价 格(元)：");
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_3.setBounds(37, 165, 74, 24);
		
		price = new Text(composite, SWT.BORDER);
		price.setBounds(129, 160, 100, 24);
		
		commission = new Text(composite, SWT.BORDER);
		commission.setBounds(129, 226, 100, 24);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setText("佣金(‰)：");
		label_5.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_5.setBounds(36, 230, 74, 24);
		
		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setText("备      注：");
		label_9.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_9.setBounds(37, 385, 74, 24);
		
		remark = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		remark.setBounds(128, 381, 370, 68);
		
		btnOk = new Button(composite, SWT.NONE);
		btnOk.setBounds(161, 485, 80, 27);
		btnOk.setText("确定");
		btnOk.addSelectionListener(new OkListener());
		
		btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(330, 485, 80, 27);
		btnCancel.setText("取消");

	}
	
	public void change() throws JSONException{
//		open();
		createContents();
//		btnOk.setText("确认修改");
		String dstr = joStockInfo.getString("date");
		dstr = "20" + dstr;
		Date d = new Date();
		try {
			d = DF.parse(dstr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lblCurPrice.setText(curPrice);
		String[] s = dstr.split("-");
		date.setDate(Integer.valueOf(s[0]), 
				Integer.valueOf(s[1])-1, Integer.valueOf(s[2]));
		price.setText(joStockInfo.getString("price"));
		volumes.setText(joStockInfo.getString("volumes"));
		taxes.setText(StockMath.doubleToMilli(
				Double.valueOf(joStockInfo.getString("taxes"))));
		commission.setText(StockMath.doubleToMilli(
				Double.valueOf(joStockInfo.getString("commission"))));
		state.setText(joStockInfo.getString("state"));
		remark.setText(joStockInfo.getString("remark"));
		cbType.select(3);//typeIndex(joStockInfo.getString("type")));
		System.out.println(typeIndex(joStockInfo.getString("type")));
		
		open();
	}
	
	public void add(){
//		open();
		createContents();
		btnOk.setText("确认添加");
	}
	
	public int typeIndex(String type){System.out.println(type);
		if(type.equals("买入"))
			return 0;
		else if(type.equals("卖出"))
			return 1;
		else if(type.equals("补仓"))
			return 2;
		else if(type.equals("卖空"))
			return 3;
		else
			return -1;
	}
	
	//确定按钮监听事件
	class OkListener implements SelectionListener{
		private String[] stockSA;//保存交易记录信息
		
		public OkListener(){
			stockSA = new String[11];
		}
		String[] getStockStringArray(){
			return stockSA;
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			stockSA = getData();
			
			shell.close();
			shell.dispose();
		}
	}
	
	public String[] getData(){
		String[] sa = new String[11];
		
		String s = date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
		s = s.substring(2);
		sa[0] = stockName;
		sa[1] = code;
		sa[2] = s;
		sa[3] = cbType.getText();
		sa[4] = price.getText();
		sa[5] = volumes.getText();
		sa[6] = taxes.getText();
		sa[7] = commission.getText();
		sa[8] = state.getText();
		sa[9] = remark.getText();
		sa[10] = "删除 修改";
		for(String s1 : sa){
			System.out.println(" ssss    " + s1);
		}
		return sa;
	}
	
	public static void main(String[] argv){
		
	}
}
