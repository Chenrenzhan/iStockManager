package ui;

import java.text.DecimalFormat;

//import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.*;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
 public class DlgChangeCapital extends Dialog {
	 
	 private Shell parentShell;
	 private Shell shell;
        Object result;
        private Text money;
        private Button btnOK;
        private Button btnCancel;
        private double capital;
                
        public DlgChangeCapital (Shell parent, int style) {
                super (parent, style);
                parentShell = getParent();
                shell = new Shell(parentShell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
                create();
        }
        public DlgChangeCapital (Shell parent) {
                this (parent, 0); // your default style bits go here (not the Shell's style bits)
                parentShell = getParent();
                shell = new Shell(parentShell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
                create();
        }
        public void create(){
        	Composite composite = new Composite(shell, SWT.NONE);
            composite.setBounds(10, 10, 381, 212);
            
            Label lblNewLabel = new Label(composite, SWT.NONE);
            lblNewLabel.setBounds(60, 70, 61, 17);
            lblNewLabel.setText("修改本金：");
            
            money = new Text(composite, SWT.BORDER);
            money.setBounds(127, 64, 122, 23);
            
            btnOK = new Button(composite, SWT.NONE);
            btnOK.setBounds(60, 129, 72, 27);
            btnOK.setText("确定");
            btnOK.addSelectionListener(new SelectionListener(){

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					System.out.println(money.getText());
					if(!money.getText().isEmpty())
						capital = Double.valueOf(money.getText());
					shell.close();
					shell.dispose();
				}
            	
            });
            
            btnCancel = new Button(composite, SWT.NONE);
            btnCancel.setBounds(176, 129, 72, 27);
            btnCancel.setText("取消");
            btnCancel.addSelectionListener(new SelectionListener(){

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
        public Object open () {
                //Shell parent = getParent();
//                final Shell shell = new Shell(parentShell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
                shell.setSize(407, 261);
                shell.setText(getText());
                final Display display = shell.getDisplay();
                
                
                // Your code goes here (widget creation, set result, etc).
                shell.open();
//                Display display = parent.getDisplay();
                while (!shell.isDisposed()) {
                        if (!display.readAndDispatch()) 
                        	display.sleep();
                }
                return result;
        }
        
        public Text getMoney(){
        	return money;
        }
        

      
		public double getCapital() {
			// TODO Auto-generated method stub
			return capital;
		}
		
		public void setCapital(double cap){
			
			this.capital = cap;
			money.setText(String.valueOf(cap));
		}
		
 }