package ui;


import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import util.Constant;
import util.RefreshTask;

import com.novocode.naf.swt.custom.ishell.DesktopForm;
import com.novocode.naf.swt.custom.ishell.InternalShell;

import controller.ImEx_port;

public class InternalShellFactory {
	public static final int MAINSHELL = 0;
	private DesktopForm _desktop;
	private ArrayList<InternalShell> _shellList;

	public InternalShellFactory(ArrayList<InternalShell> shellList,
			DesktopForm desktopForm) {
		_desktop = desktopForm;
		_shellList = shellList;
	}



	void createInternalShell(int type, String account) {
		switch (type) {
		case MAINSHELL:
			createMainShell(_desktop, SWT.RESIZE | SWT.ON_TOP | SWT.MIN
					| SWT.CLOSE, false, true, account);
			break;

		default:
			break;
		}
	}

	private InternalShell createMainShell(DesktopForm desktop, int style,
			boolean sizeGrip, boolean customMenu, String account) {
		InternalShell ishell = new InternalShell(desktop, style, account);
		ishell.setText("账号 " + account);

		Composite ishellContent = ishell.getContentPane();
		createToolbar(ishellContent);// 创建工具栏
		createTab(account, ishellContent);

		// setCompositeMove(ishell, bar);
		// setCompositeMove(ishell, tabFolder);

		ishell.pack();
		ishell.open();
		_shellList.add(ishell);
		return ishell;
	}

	private void createTab(String account, Composite parent) {

		TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		tabFolder.setBounds(0, 0, 974, 480);

		// 构成图和个人资产
		TabItem WealTabItem = new TabItem(tabFolder, SWT.NONE);
		WealTabItem.setText("资产");

		// 持股构成
		TabItem ownershipTabItem = new TabItem(tabFolder, SWT.NONE);
		ownershipTabItem.setText("持仓情况");

		OwnershipTabItemComposite OwnershipTabItemComposite = new OwnershipTabItemComposite(
				tabFolder, SWT.NONE, account);
		ownershipTabItem.setControl(OwnershipTabItemComposite);

		WealTabItemComposite wealTabItemComposite = new WealTabItemComposite(
				account, tabFolder, SWT.NONE);
		WealTabItem.setControl(wealTabItemComposite);
		Constant.PreriodicRefresh.addUI(wealTabItemComposite);
		Constant.PreriodicRefresh.addUI(OwnershipTabItemComposite);
		Constant.RecordChangeRefresh.addUI(wealTabItemComposite);
		Constant.RecordChangeRefresh.addUI(OwnershipTabItemComposite);

	}
	
	protected void createToolbar(Composite parent) {
		ToolBar toolBar = new ToolBar(parent, SWT.FLAT);
		FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(100, 0);
		fd_toolBar.top = new FormAttachment(0, 0);
		fd_toolBar.left = new FormAttachment(0, 0);
		fd_toolBar.bottom = new FormAttachment(0, 40);
		toolBar.setLayoutData(fd_toolBar);


		ToolItem addToolItem = new ToolItem(toolBar, SWT.PUSH);
		addToolItem.setWidth(50);

		addToolItem.setToolTipText("添加新股");
		Image addIcon = new Image(Display.getDefault(), "icon/add.png");
		addToolItem.setImage(addIcon);

		addToolItem.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				// FIXME
				// DlgAddNewStock ds = new DlgAddNewStock(shell, SWT.CLOSE
				// | SWT.MIN, _account);
				// ds.open();
			}

		});


		toolBar.setVisible(true);
		}
}
