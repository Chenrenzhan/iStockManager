package util;
import interfac.Refreshable;

import java.util.ArrayList;
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
	private UIList uiList;

	public UIController() {
		// TODO Auto-generated constructor stub
		uiList = new UIList();
	}

	public void refreshAndSave() {
		if (!UIListEmpty()) {
			UIList uList = getUIList();

			for (int j = 0; j < uList.size(); j++) {
				Refreshable ui = uList.get(j);
				ui.save();
				ui.load();
				ui.redraw();
				System.out.println("refreshAndSave");
			}
		}
//		UIList mList = getShellGroup();
//		System.out.println(mList.size());
	}

	public UIList getUIList() {
		if (UIListEmpty())
			uiList = new UIList();
		return uiList;

	}
	
	public Refreshable addUI(Refreshable ui) {
		if (UIListEmpty())
			uiList = new UIList();
		uiList.add(ui);
		return ui;
	}
	
	public void removeUI(Refreshable ui) {
		if (UIListEmpty())
			uiList = new UIList();
		uiList.removeShell(ui);
	}
	
	public boolean isControlling(Refreshable ui) {
		if (UIListEmpty())
			uiList = new UIList();
		return uiList.indexOf(ui)!=-1? true:false;
	}

	public Boolean UIListEmpty() {
		if (uiList == null) {
			return true;
		} else {
			return false;
		}
	}

	final private class UIList extends ArrayList<Refreshable> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3034626814663722121L;

		/**
		 * 用于保存需要维护的UI界面实例
		 */
		public UIList() {
			// TODO Auto-generated constructor stub
			super();
		}

		public void removeShell(Refreshable uiShell) {
			int idx;
			if((idx=indexOf(uiShell))!=-1) 
				remove(idx);

		}

//		private int findShell(Refreshable uisShell) {
//			for (int i = 0; i < size(); i++) {
//				if (uisShell == get(i))
//					return i;
//			}
//			return -1;
//		}

	}
}
