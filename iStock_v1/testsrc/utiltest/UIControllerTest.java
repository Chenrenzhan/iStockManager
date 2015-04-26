package utiltest;

import java.lang.reflect.Method;
import java.text.Format;

import interfac.Refreshable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.UIController;
import util.UIController.UIList;

public class UIControllerTest {

	UIList uiList;

	@Before
	public void init() {
		UIController controller = new UIController();
		uiList = controller.getUIList();
	}

	// 测试存在正常情况
	@Test
	public void removeShellNormal() {
		Refreshable target = new Refreshable() {

			@Override
			public void save() {
				// TODO Auto-generated method stub

			}

			@Override
			public void redraw() {
				// TODO Auto-generated method stub

			}

			@Override
			public void load() {
				// TODO Auto-generated method stub

			}
		};
		uiList.add(target);
		Refreshable notthis = new Refreshable() {

			@Override
			public void save() {
				// TODO Auto-generated method stub

			}

			@Override
			public void redraw() {
				// TODO Auto-generated method stub

			}

			@Override
			public void load() {
				// TODO Auto-generated method stub

			}
		};
		uiList.add(notthis);
		uiList.remove(target);
		Assert.assertArrayEquals(new int[] { 1, -1 }, new int[] {
				uiList.size(), uiList.indexOf(target) });
	}

	// 测试不存在时的处理情况
	@Test
	public void removeShellNotFound() {
		Refreshable target = new Refreshable() {

			@Override
			public void save() {
				// TODO Auto-generated method stub

			}

			@Override
			public void redraw() {
				// TODO Auto-generated method stub

			}

			@Override
			public void load() {
				// TODO Auto-generated method stub

			}
		};
		uiList.removeShell(target);
		int result = uiList.size();
		Assert.assertEquals(0, result);
	}

	// 测试反复不存在的处理情况
	@Test
	public void removeShellNotFoundST() {
		Refreshable target = new Refreshable() {
			@Override
			public void save() {
				// TODO Auto-generated method stub

			}

			@Override
			public void redraw() {
				// TODO Auto-generated method stub

			}

			@Override
			public void load() {
				// TODO Auto-generated method stub
			}
		};
		for (int i = 0; i < 50; i++) {
			uiList.removeShell(target);
			int result = uiList.size();
			Assert.assertEquals(0, result);
		}
	}
}
