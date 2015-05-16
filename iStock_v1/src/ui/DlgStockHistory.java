package ui;

import interfac.MyRefreshable;

import java.io.IOException;
import java.util.ArrayList;

import models.RecordsSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.FillLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import util.Constant;
import util.RefreshTask;
import controller.MouseListenerAdapt;
import controller.StockMath;

public class DlgStockHistory extends Dialog implements MyRefreshable{

	private static final String[] KEYS = 
			new String[]{"name", "code", "date", "type", 
		"price", "volumes", "taxes", "commission", 
		"state", "remark"};
	
	private Object result;
	
	private Shell parentShell;
	private Shell shell;
	private String name;
	
	private String code;//股票代码
	private RecordsSet recordSet; //所有股票集合
	private JSONArray recordJA;//历史记录数组
	
	private Composite composite;
	
	private Button prebtn;//上一页按钮
	private Button nextbtn;//下一页按钮
	
	private int last;//一页的最后一个记录的序号
	private int len;//股票交易记录的总数
	private int page;//第几页
	
	private ArrayList<RecordFullDetails> rdList; //保存当前页的股票记录
	

	public DlgStockHistory(Shell parent, String code) {
		// TODO Auto-generated constructor stub
		super(parent, SWT.NONE);
		
		this.parentShell = getParent();
		this.shell = new Shell(parentShell, SWT.CLOSE | SWT.MIN);
		this.code = code;
		
		try {
			this.recordSet = new RecordsSet();
			JSONObject jo = this.recordSet.getRecordsSet();
			this.recordJA = jo.getJSONArray(code);
			this.name = this.recordJA.getJSONObject(0).getString("name");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.len = this.recordJA.length();
		this.last = this.len > 15 ? 15 : this.len;
		this.rdList = null;
		this.page = 0;
	}

	public Object open(String stockN) {

		Display display = shell.getDisplay();
		// Display display=Display.getDefault();
		// shell=new Shell(display,SWT.CLOSE|SWT.V_SCROLL);
		shell.setSize(993, 580);
		shell.setText(stockN);

		create();
		
		shell.layout();
		shell.open();
		// 获取父窗口shell
//		Shell parentShell = (Shell) shell.getParent();
		Constant.PreriodicRefresh.addUI(this);
		Constant.RecordChangeRefresh.addUI(this);
		while (!shell.isDisposed()) {
			// 判断父窗口是否关闭，关闭则把子窗口也关闭
			
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		Constant.PreriodicRefresh.removeUI(this);
		Constant.RecordChangeRefresh.removeUI(this);
//		shell.dispose();
		return result;
	}

	public void create() {
		shell.setLayout(null);
		
		composite = new Composite(shell, SWT.BORDER);
		composite.setBounds(10, 10, 967, 531);
		
		RecordFullDetails head = new RecordFullDetails(composite, SWT.NONE);
		head.getlblDelete().setLocation(912, 10);
		head.getlblChange().setLocation(882, 10);
		head.setBounds(0, 10, 967, 30);
		
		Label separator1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator1.setBounds(0, 40, 967, 3);
		
		try {
			record(last, page);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Label separator2 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator2.setBounds(0, 490, 967, 3);
		
		prebtn = new Button(composite, SWT.ARROW | SWT.LEFT);
		prebtn.setToolTipText("上一页");
		prebtn.setBounds(404, 500, 65, 27);
		prebtn.setText("上一页");
		prebtn.addSelectionListener(new PreListener());
		
		nextbtn = new Button(composite, SWT.ARROW | SWT.RIGHT);
		nextbtn.setToolTipText("下一页");
		nextbtn.setBounds(512, 500, 65, 27);
		nextbtn.setText("下一页");
		nextbtn.addSelectionListener(new NextListener());
	}
	
	public void record(int last, int page) throws JSONException{
		int index = page * 15;
		int l = last % 15 == 0 ? 15 : last % 15;
		if(last == 0){
			l = 0;
		}
//		System.out.println("l:" + l);
//		System.out.print("rdList     ");
//		System.out.println(rdList != null);
		if(rdList != null){
			for(RecordFullDetails rfd : rdList){
				rfd.dispose();
			}
		}
		rdList = null;
		rdList = new ArrayList<RecordFullDetails>();
		
		for(int i = 0; i < l; ++i){
			RecordFullDetails rfd = new RecordFullDetails(composite, SWT.NONE);
			rdList.add(rfd);
			rfd.setBounds(0, 40 + 30 * i, 967, 30);
			JSONObject jo = recordJA.getJSONObject(index++);
//			System.out.println("index:" + index + "     page:" + page);
			for(int j = 0; j < 10; ++j){
				Label lbl = rfd.getLabel(j);
				lbl.setFont(SWTResourceManager.getFont(
						"Microsoft YaHei UI", 10, SWT.NORMAL));
				lbl.setForeground(SWTResourceManager
						.getColor(SWT.COLOR_BLACK));
				if(j == 6){
					double d = jo.getDouble(KEYS[j]);
					lbl.setText(StockMath.doubleToMilli(d));
					continue;
				}
				if(j == 7){
					double d = jo.getDouble(KEYS[j]);
					lbl.setText(StockMath.doubleToMilli(d));
					continue;
				}
				
				lbl.setText(jo.getString(KEYS[j]));
			}
			
			Label lbl = rfd.getLabel(10);
			lbl.setFont(SWTResourceManager.getFont(
					"Microsoft YaHei UI", 10, SWT.NORMAL));
			lbl.setForeground(SWTResourceManager
					.getColor(SWT.COLOR_BLACK));
			lbl.setVisible(false);
			
			Label lblChange = rfd.getlblChange();
			Image iconChange = new Image(Display.getDefault(),
					"icon/change.png");
			lblChange.setImage(iconChange);
			lblChange.setToolTipText("修改交易记录信息");
			lblChange.setVisible(true);
			lblChange.addMouseListener(new ChangeListener(shell, jo, rfd,
					code));
			
			Label lblDelete = rfd.getlblDelete();
			Image iconDelete = new Image(Display.getDefault(),
					"icon/delete.png");
			lblDelete.setImage(iconDelete);
			lblDelete.setToolTipText("删除交易记录");
			lblDelete.setVisible(true);
			lblDelete.addMouseListener(new DeleteListener(jo));
		}
	}
	
	//上一页按钮监听事件
	public class PreListener implements SelectionListener{

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			prePage();
		}
	}
	
	//下一页监听按钮事件
	public class NextListener implements SelectionListener{
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			nextPage();
		}
	}
	
	// 修改交易记录监听
		public class ChangeListener extends MouseListenerAdapt {

			private JSONObject jo;
			private Shell shell;
			private String code;
			private RecordFullDetails rfd;

			public ChangeListener(Shell shell, JSONObject jo,
					RecordFullDetails rfd, String code) {
				this.jo = jo;
				this.shell = shell;
				this.code = code;
				this.rfd = rfd;
				
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				DlgStock ds = new DlgStock(shell, jo, code);
				try {
					ds.change();
					removeRecord(jo);
					JSONObject j = ds.getJoStockInfo();
					add(j);
					updateChange(jo, rfd);
					new RefreshTask(shell.getDisplay()).scheduleRecordChangeRf();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	
	//删除监听事件
	public class  DeleteListener extends MouseListenerAdapt{

		private JSONObject jo;
		public DeleteListener(JSONObject jo){
			this.jo = jo;
		}
		
		@Override
		public void mouseDown(MouseEvent arg0) {
			// TODO Auto-generated method stub
//			System.out.println("delete");
			try {
				deleteRecord(jo);
				new RefreshTask(shell.getDisplay()).scheduleRecordChangeRf();
			} catch (IOException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			fresh();
		}
		
	}
	
	public void prePage() {
		if(last > 15){
			int i = last % 15 == 0 ? 15 : last % 15;
			last -= i;
			--page;
//			System.out.println("Pre   last:" + last + ";   len:" + len + ";   page:"+page);
			try {
				record(last, page);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void nextPage() {
		if(len > last){
			if(len - last > 15){
				last += 15;
			}
			else{
				last = len;
			}
//			System.out.println("sddsdsds    " + last + "    fff:" + len);
			++page;
//			System.out.println("Next   last:" + last + ";   len:" + len + ";   page:"+page);
			try {
				record(last, page);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Boolean add(JSONObject jo) {
//		System.out.println(jo.toString());
		try {
			Boolean b = recordSet.addRecord(jo);
			recordSet.save();
			return b;
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	// 修改后更新
		public void updateChange(JSONObject jo, RecordFullDetails rfd) {
			for (int j = 0; j < KEYS.length; ++j) {
				Label lbl = rfd.getLabel(j);
				try {
					String text = "";
					System.out.println(jo.getString(KEYS[j]));
					if(j == 6 || j ==7){
						text = StockMath.doubleToMilli(
								Double.valueOf(jo.getString(KEYS[j])));
					}
					else {
						text = jo.getString(KEYS[j]);
					}
				
					lbl.setText(text);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	public void deleteRecord(JSONObject jo) 
			throws IOException, JSONException{
		
			recordSet.removeRecord(jo);
			
			recordSet.save();
			
			removeRecord(jo);
			
			if(last == len){
				last--;
				len--;
				if(last % 15 == 0){
					last += 15;
					prePage();
					return ;
				}
			}
			else{
				len--;
			}
			record(last, page);
			
	}
	
//	//更改后刷新
//	public void fresh(){
//		System.out.println("fresh");
//		this.recordSet = null;
//		this.recordJA = null;
//		try {
//			this.recordSet = new RecordsSet();
//			JSONObject jo = this.recordSet.getRecordsSet();
//			this.recordJA = jo.getJSONArray(code);
//			this.name = this.recordJA.getJSONObject(0).getString("name");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		this.len = this.recordJA.length();
//		this.last = this.len > 15 ? 15 : this.len;
//		System.out.println("len:" + len + "    last:" + last);
////		this.rdList = null;
//		this.page = 0;
//		
//		try {
//			record(last, page);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
	public Boolean removeRecord(JSONObject jo) 
			throws JSONException{
		JSONArray Njarray = new JSONArray();
		Boolean flag = true;
		for (int i = 0; i < recordJA.length(); i++) {
//			System.out.println("delete:   " + recordJA.get(i).equals(jo));
			if (!recordJA.get(i).equals(jo))
				Njarray.put(recordJA.get(i));
			else
				flag = false;
		}
//		System.out.println("old:    " + recordJA.toString());
		recordJA = Njarray;
//		System.out.println("new:    " + recordJA.toString());

		if(flag)
			return true;
		else
			return false;
	}

@Override
public void redrawui() {
	// TODO Auto-generated method stub
	try {
		record(last, page);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Override
public void redrawOnAdd() {
	// TODO Auto-generated method stub
	try {
		record(last, page);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
	
}
