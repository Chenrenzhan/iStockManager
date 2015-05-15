package util;
import interfac.MyRefreshable;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.json.JSONException;

import com.ibm.icu.util.BytesTrie.Iterator;

import controller.HoldStock;
/**
 * 解释:一般将该类作为全局(静态)变量以方便在任何类中调用，实体化多个这样的类可以完成隶属于不
 * 同更新时刻的ui的统一控制
 * 
 * 示例:一个controller控制对实时股票数据敏感的ui集合,再生成一个不同的controller以控制
 * 对用户记录敏感的ui集合，那么在股票数据变化时调用后一controller进行更新，而在用户操作（
 * 如添加记录）时则调用后一controller进行控制
 * 
 * 属性：uiList中存储了由该实例化的类控制的所有UI模块
 * 核心方法：refreshAndSave-触发控制列表中的ui调用各自的save,load,redraw方法
 * 其他公开方法：getUIList,addUI,removeUI,isControlling,UIListEmpty
 *
 */
public class UIController {
	private ArrayList<MyRefreshable> uiList;

	public UIController() {
		// TODO Auto-generated constructor stub
		uiList = new ArrayList<MyRefreshable>();
	}

	public void refreshAndSave()  {
		//刷新stock.json获取现价
		try {
			new HoldStock().countStockFromRecord();
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("周期性刷新时stock.json刷新失败");
		}
		
		if (!UIListEmpty()) {
			ArrayList<MyRefreshable> uList = getUIList();

			for (int j = 0; j < uList.size(); j++) {
				MyRefreshable ui = uList.get(j);
				ui.redrawui();
				System.out.println("refreshAndSave");
			}
		}
//		UIList mList = getShellGroup();
//		System.out.println(mList.size());
	}

	public ArrayList<MyRefreshable> getUIList() {
		if (UIListEmpty())
			uiList = new ArrayList<MyRefreshable>();
		return uiList;

	}
//	private class UIPackList{
//		private MyRefreshable ui;
//		private RefreshSignal signal;
//		public UIPackList(MyRefreshable _ui,RefreshSignal _signal) {
//			// TODO Auto-generated constructor stub
//			ui=_ui;
//			signal=_signal;
//		}
//		public synchronized void setRefreshSignal(boolean value){
//			signal.setSignal(value);
//		}
//		public MyRefreshable getUIIntant(){
//			return ui;
//		}
//		public synchronized RefreshSignal getRefreshSignal(){
//			return signal;
//		}
//	}
	public MyRefreshable addUI(MyRefreshable ui,RefreshSignal refreshflag) {
		if (UIListEmpty())
			uiList = new ArrayList<MyRefreshable>();
		uiList.add(ui);
		return ui;
	}
	
	public void removeUI(MyRefreshable ui) {
		if (UIListEmpty())
			uiList = new ArrayList<MyRefreshable>();
		int idx;
		if((idx=uiList.indexOf(ui))!=-1) 
		uiList.remove(idx);
		
	}
	
	public boolean isControlling(MyRefreshable ui) {
		if (UIListEmpty())
			uiList = new ArrayList<MyRefreshable>();
		return uiList.indexOf(ui)!=-1? true:false;
	}

	public Boolean UIListEmpty() {
		if (uiList == null) {
			return true;
		} else {
			return false;
		}
	}

//	final public class UIList extends ArrayList<MyRefreshable> {
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 3034626814663722121L;
//
//		/**
//		 * 用于保存需要维护的UI界面实例
//		 */
//		public UIList() {
//			// TODO Auto-generated constructor stub
//			super();
//		}
//
//		public void removeShell(MyRefreshable uiShell) {
//			int idx;
//			if((idx=indexOf(uiShell))!=-1) 
//				remove(idx);
//
//		}

//		private int findShell(Refreshable uisShell) {
//			for (int i = 0; i < size(); i++) {
//				if (uisShell == get(i))
//					return i;
//			}
//			return -1;
//		}

	}

