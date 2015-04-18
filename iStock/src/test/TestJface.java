package test;


import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

	public class TestJface extends Window {
		 
		      public TestJface(Shell arg0) {
		          super(arg0);
		      }
		        @Override
		      protected Control createContents(Composite parent) {
		          Text text = new Text(parent, SWT.NONE);
		         text.setText("Hello,world!");
		         return parent;
		 
		     }
		     /**
	     * @param args
		    */
		     public static void main(String[] args) {
		 
	       TestJface demo = new TestJface(null);
		         demo.setBlockOnOpen(true);
		         demo.open();
		         Display.getCurrent().dispose();
		 
		     }
		 }