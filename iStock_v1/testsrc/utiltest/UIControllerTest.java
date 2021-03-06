package utiltest;

import java.lang.reflect.Method;
import java.text.Format;
import java.util.ArrayList;

import interfac.MyRefreshable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.UIController;

public class UIControllerTest {

	ArrayList<MyRefreshable> uiList;

	@Before
	public void init() {
		UIController controller = new UIController(UIController.PreriodicMethod);
		uiList = controller.getUIList();
	}

	// 测试存在正常情况
	@Test
	public void removeShellNormal() {
		MyRefreshable target = new MyRefreshable() {

			@Override
			public void redrawui() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void redrawOnAdd() {
				// TODO Auto-generated method stub
				
			}


		};
		uiList.add(target);
		MyRefreshable notthis = new MyRefreshable() {

	
			@Override
			public void redrawui() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void redrawOnAdd() {
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
		MyRefreshable target = new MyRefreshable() {

			@Override
			public void redrawui() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void redrawOnAdd() {
				// TODO Auto-generated method stub
				
			}
		};
		uiList.remove(target);
		int result = uiList.size();
		Assert.assertEquals(0, result);
	}

	// 测试反复不存在的处理情况
	@Test
	public void removeShellNotFoundST() {
		MyRefreshable target = new MyRefreshable() {

			@Override
			public void redrawui() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void redrawOnAdd() {
				// TODO Auto-generated method stub
				
			}
		
		};
		for (int i = 0; i < 50; i++) {
			uiList.remove(target);
			int result = uiList.size();
			Assert.assertEquals(0, result);
		}
	}
}
