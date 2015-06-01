package ui;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
//import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.*;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Composite;

public class DlgAddAccount extends Dialog {

	private Shell parentShell;
	private Shell shell;
	private Text value;
	private String result;
	private Button btnOK;
	private Button btnCancel;
	public final String TEXT_DEFAULTVALUE = "格式为:比较符加数值";

	public DlgAddAccount(Shell parent) {
		// TODO Auto-generated constructor stub
		super(parent, SWT.NONE);
		parentShell = getParent();
		shell = new Shell(parentShell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL
				| SWT.APPLICATION_MODAL);
		this.setText("命名账户");
		create();
	}

	public void open() {
		// TODO Auto-generated method stub

		shell.setSize(407, 261);
		shell.setText(getText());
		final Display display = shell.getDisplay();

		// create();

		// Your code goes here (widget creation, set result, etc).
		shell.open();
		// Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

	}

	public void create() {
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(10, 10, 381, 212);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(60, 70, 61, 17);
		lblNewLabel.setText("命名为：");

		value = new Text(composite, SWT.BORDER);
		value.setBounds(127, 64, 122, 23);
		value.setToolTipText("TEXT_DEFAULTVALUE");
		value.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				value.clearSelection();
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				// if(value.getText()==TEXT_DEFAULTVALUE){
				// value.setText("");
				// }
				value.selectAll();
			}
		});
		btnOK = new Button(composite, SWT.NONE);
		btnOK.setBounds(60, 129, 72, 27);
		btnOK.setText("确定");
		btnOK.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(value.getText());
				if (!value.getText().isEmpty()) {
					result = value.getText();
					// RefreshTask.addRefreshTask(shell.getDisplay(),
					// _refreshMethod);
					shell.close();
					shell.dispose();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(176, 129, 72, 27);
		btnCancel.setText("取消");
		btnCancel.addSelectionListener(new SelectionListener() {

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

		});
	}

	public String getValue() {
		return result;
	}

	// public Filter split(String str) throws IOException {
	// String string = new String();
	// char[] c = str.toCharArray();
	// for (int i = 1; i < c.length; i++) {
	// string = string + c[i];
	// }
	// if (c[0] == '<' || c[0] == '>') {
	// if (isNum(string)) {
	// Filter filter = new Filter(_addWhat,String.valueOf(c[0]),
	// Float.parseFloat(string));
	// // String string = filter.get_sign();
	// // float num = filter.get_Value();
	// // System.out.print(string);
	// // System.out.print(num);
	// return filter;
	// }
	// }
	// else throw new IOException("illegal input");
	// return null;
	//
	// }

	public static boolean isNum(String str) {
		for (int i = 0; i < str.length(); i++) { // 循环遍历字符串

			if (Character.isLetter(str.charAt(i))) { // 用char包装类中的判断字母的方法判断每一个字符
				return false;
			}
		}
		return true;
	}
}
