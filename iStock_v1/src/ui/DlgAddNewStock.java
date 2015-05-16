package ui;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import models.RecordsSet;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.json.JSONException;
import org.json.JSONObject;

import controller.GetSingleStock;
import controller.StockMath;

public class DlgAddNewStock extends Dialog {
	private static final SimpleDateFormat NDF = 
			new SimpleDateFormat("yyyy/MM/dd");
	private static final SimpleDateFormat DF = 
			new SimpleDateFormat("yyyy-MM-dd");
	
	public static final String[] KEYS = new String[]{
		"name","code","date","type", "price", 
		"volumes", "taxes", "commission", 
		"state", "remark", "handle", };
	
	protected Object result;
	
	private Composite composite;
	
	private Shell parentShell;
	protected Shell shell;
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
	
	private JSONObject joStockInfo;  //修改或添加的股票
	
	private JSONObject intimeStockInfo; //修改或者添加的股票的即时信息
	
	private String stockName; //股票名字
	private String code; //股票代码
	private String curPrice; //股票当前价格
	private Label label_10;
	private Text tName;
	private Label label_11;
	private Text tCode;
	private Label label;
	private Label lblCurPrice;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DlgAddNewStock(Shell parent, int style) {
		super(parent, SWT.CLOSE | SWT.MIN);
		setText("添加新股交易记录");
//		this.operateStr = "";
//		this.stockName = "股票名字";
		
		this.parentShell = parent;
		shell = new Shell(parentShell, SWT.CLOSE | SWT.MIN);
	
		createContents();
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
		volumes.setText("0");
		volumes.setBounds(398, 160, 100, 24);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setText("数      量：");
		label_8.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_8.setBounds(307, 165, 74, 24);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setText("税率(‰)：");
		label_4.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_4.setBounds(307, 230, 74, 24);
		
		taxes = new Text(composite, SWT.BORDER);
		taxes.setText("0.00");
		taxes.setBounds(398, 226, 100, 24);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setText("价 格(元)：");
		label_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_3.setBounds(37, 165, 74, 24);
		
		price = new Text(composite, SWT.BORDER);
		price.setText("0.00");
		price.setBounds(129, 160, 100, 24);
		
		commission = new Text(composite, SWT.BORDER);
		commission.setText("0.00");
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
		
		
		btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(330, 485, 80, 27);
		btnCancel.setText("取消");
		
		label_10 = new Label(composite, SWT.NONE);
		label_10.setText("股票名字:");
		label_10.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		label_10.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_10.setBounds(37, 54, 74, 24);
		
		tName = new Text(composite, SWT.BORDER);
		tName.setBounds(128, 54, 100, 24);
		
		label_11 = new Label(composite, SWT.NONE);
		label_11.setText("股票代码:");
		label_11.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		label_11.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_11.setBounds(307, 54, 74, 24);
		
		tCode = new Text(composite, SWT.BORDER);
		tCode.setBounds(398, 54, 100, 24);
		tCode.addListener(SWT.FocusOut, new Listener(){

			@Override
			public void handleEvent(Event e) {
				// TODO Auto-generated method stub
				String c = tCode.getText();
				GetSingleStock gifs = new GetSingleStock(c);
				Thread td = new Thread(gifs);
				td.start();
				try {
					td.join();
				} catch (InterruptedException ie) {
					// TODO Auto-generated catch block
					ie.printStackTrace();
				}
				if(gifs.getIsError()){
					int style = SWT.APPLICATION_MODAL | SWT.YES;
					MessageBox messageBox = new MessageBox(shell, style);
					messageBox.setText("提示");
					messageBox.setMessage("股票代码有误，请确认再输入！");
					e.doit = messageBox.open() == SWT.YES;
//					messageBox(e, "提示", "股票代码有误，请确认再输入！");
				}
				else{
					intimeStockInfo = gifs.getJsonObj();
					try {
						stockName = intimeStockInfo.getString("name");
						curPrice = intimeStockInfo.getString("currentPrice");
						tName.setText(stockName);
						lblCurPrice.setText(curPrice);
						if(c.contains("sh")){
							c = c.replace("sh", "");
						}
						if(c.contains("sz")){
							c = c.replace("sz", "");
						}
						code = c;
						System.out.println(code);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		label = new Label(composite, SWT.NONE);
		label.setText("当 前 价：");
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setBounds(37, 15, 74, 24);
		
		lblCurPrice = new Label(composite, SWT.NONE);
		lblCurPrice.setText("0.00");
		lblCurPrice.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblCurPrice.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		lblCurPrice.setBounds(129, 15, 61, 20);
		
		btnOk.addSelectionListener(new OkListener());
		btnCancel.addSelectionListener(new CancelListener());

	}

	
	public JSONObject getIntimeStockInfo(String code){
		GetSingleStock gifs = new GetSingleStock(code);
		Thread td = new Thread(gifs);
		td.start();
		try {
			td.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(gifs.getIsError()){
			return null; //股票代码错误，返回null
		}
		return gifs.getJsonObj();
	}

	public void addStock() {
		RecordsSet rd;
		try {
			rd = new RecordsSet();
			rd.addRecord(joStockInfo);
			rd.save();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int typeIndex(String type){
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
	
	public void messageBox(SelectionEvent e, String title, String message) {
		int style = SWT.APPLICATION_MODAL | SWT.YES;
		MessageBox messageBox = new MessageBox(shell, style);
		messageBox.setText(title);
		messageBox.setMessage(message);
		e.doit = messageBox.open() == SWT.YES;
		e.doit = messageBox.open() == SWT.NO;
	}
	
	//确定按钮监听事件
	class OkListener implements SelectionListener{
		private ArrayList<Object > list;//保存交易记录信息
		
		public OkListener(){
			list = new ArrayList<Object>();
		}
		ArrayList<Object> getStockStringArray(){
			return list;
		}
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
			String p = price.getText();
			String v = volumes.getText();
			String t = taxes.getText();
			String c = commission.getText();
			
			if(p.isEmpty()){
				messageBox(e, "提示", "价格不能为空，请重新填写！");
				return ;
			}
			else if(v.isEmpty()){
				messageBox(e, "提示", "数量不能为空，请重新填写！");
				return ;
			}
			else if(t.isEmpty()){
				messageBox(e, "提示", "税率不能为空，请重新填写！");
				return ;
			}
			else if(c.isEmpty()){
				messageBox(e, "提示", "佣金不能为空，请重新填写！");
				return ;
			}
			
			
			
			list = getData();
			
			shell.close();
			shell.dispose();
			
			str2json(list);
			addStock();
			System.out.println(joStockInfo.toString());
		}
	}
	
	//取消按钮监听
	class CancelListener implements SelectionListener{

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			shell.close();
			shell.dispose();
		}
		
	}
	
	public ArrayList<Object > getData(){
		String[] sa = new String[11];
		
		ArrayList<Object > list = new ArrayList<Object>();
		
		String s = date.getYear() + "-" + date.getMonth()+1 + "-" + date.getDay();
		s = s.substring(2);
		list.add(0, stockName);
		list.add(1, code);
		list.add(2, s);
		list.add(3, cbType.getText());
		list.add(4, StockMath.valueOf(price.getText()));
		list.add(5, Integer.valueOf(volumes.getText()));
		list.add(6, StockMath.milliToDouble(taxes.getText()));
		list.add(7, StockMath.milliToDouble(commission.getText()));
		list.add(8, state.getText());
		list.add(9, remark.getText());
		list.add(10, "删除 修改");

//		for(Object l : list){
//			System.out.println(l.getClass() + " ssss    " + l);
//		}
		return list;
	}
	
	public JSONObject str2json(ArrayList<Object> list){
		if(joStockInfo == null){
			joStockInfo = new JSONObject();
		}
		for(int i = 0; i < list.size(); ++i){
			try {
				joStockInfo.put(KEYS[i], list.get(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return joStockInfo;
	}
	
	public JSONObject getJoStockInfo() {
		return joStockInfo;
	}

	public static void main(String[] argv){
		DlgAddNewStock ds = new DlgAddNewStock(new Shell(Display.getDefault()), SWT.CLOSE | SWT.MIN);
		ds.open();
	}
}
