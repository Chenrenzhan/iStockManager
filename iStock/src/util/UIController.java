package util;
import interfac.Refreshable;

import java.util.ArrayList;
/**
 * ����:һ�㽫������Ϊȫ��(��̬)�����Է������κ����е��ã�ʵ�廯����������������������ڲ�
 * ͬ����ʱ�̵�ui��ͳһ����
 * 
 * ʾ��:һ��controller���ƶ�ʵʱ��Ʊ�������е�ui����,������һ����ͬ��controller�Կ���
 * ���û���¼���е�ui���ϣ���ô�ڹ�Ʊ���ݱ仯ʱ���ú�һcontroller���и��£������û�������
 * ����Ӽ�¼��ʱ����ú�һcontroller���п���
 * 
 * ���ԣ�uiList�д洢���ɸ�ʵ����������Ƶ�����UIģ��
 * ���ķ�����refreshAndSave-���������б��е�ui���ø��Ե�save,load,redraw����
 * ��������������getUIList,addUI,removeUI,isControlling,UIListEmpty
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
		return uiList.findShell(ui)!=-1? true:false;
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
		 * ���ڱ�����Ҫά����UI����ʵ��
		 */
		public UIList() {
			// TODO Auto-generated constructor stub
			super();
		}

		public void removeShell(Refreshable uiShell) {
			remove(findShell(uiShell));

		}

		private int findShell(Refreshable uisShell) {
			for (int i = 0; i < size(); i++) {
				if (uisShell == get(i))
					return i;
			}
			return -1;
		}

	}
}
