package ui;
import java.text.DecimalFormat;



//import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.*;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;

import controller.ImEx_port;
import util.RefreshTask;
public class DlgImport extends Dialog{



		private Shell parentShell;
		private Shell shell;
		private Button btnOK;
		private Button btnCancel;


		public DlgImport(Shell parent, int style) {
			super(parent, style);
			parentShell = getParent();
			shell = new Shell(parentShell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
			create();
		}

		public DlgImport(Shell parent) {
			this(parent, 0); // your default style bits go here (not the Shell's
								// style bits)
			parentShell = getParent();
			shell = new Shell(parentShell, 
					SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
			int x=parentShell.getBounds().x+350;
			int y=parentShell.getBounds().y+210;
			shell.setBounds(x,y,261,118);
			create();
		}

		public void create() {
			
			Composite composite = new Composite(shell, SWT.NONE);
			composite.setBounds(22, 10, 261, 118);

			Label lblNewLabel = new Label(composite, SWT.NONE);
			lblNewLabel.setFont(SWTResourceManager.getFont("Microsoft MHei", 13, SWT.NORMAL));
			lblNewLabel.setBounds(31, 31, 197, 39);
			lblNewLabel.setText("无数据，是否现在初始化");

	

			btnOK = new Button(composite, SWT.NONE);
			btnOK.setBounds(41, 70, 72, 27);
			btnOK.setText("确定");
			btnOK.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
                    //che TODO import dlg
					FileDialog fileSelect = new FileDialog(shell, SWT.OPEN);
					fileSelect.setText("导入");
					fileSelect
							.setFilterNames(new String[] { "Excel Files (*.xls)" });
					fileSelect.setFilterExtensions(new String[] { "*.xls" });
					String path = "";
					// if(path=fileSelect.open() == null)
					path = fileSelect.open();
					if (path == null) {
						return;
					}
					shell.close();
					shell.dispose();
					ImEx_port.Import(path);
					new RefreshTask(parentShell.getDisplay()).refreshAll();;
				}

			});

			btnCancel = new Button(composite, SWT.NONE);
			btnCancel.setBounds(132, 70, 72, 27);
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

		public void open() {
			// Shell parent = getParent();
			// final Shell shell = new Shell(parentShell, SWT.DIALOG_TRIM |
			// SWT.APPLICATION_MODAL);
			shell.setSize(311, 152);
			shell.setText(getText());
			final Display display = shell.getDisplay();
			
			// Your code goes here (widget creation, set result, etc).
			shell.open();
			// Display display = parent.getDisplay();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		}


	
}
