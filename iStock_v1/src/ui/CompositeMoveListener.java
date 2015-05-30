package ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class CompositeMoveListener implements Listener{

	private int startX;
	private int startY;
	
	private Composite composite;
//	private Shell shell;
	
	public CompositeMoveListener(Composite composite){
		this.composite = composite;
//		this.shell = shell;
	}
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		
//		Composite composite = e.getSource();
		if (e.type == SWT.MouseDown && e.button == 1) {
			System.out.println("down   " + composite.getLocation());
			System.out.println("down   xy  (" + e.x + "," + e.y + ")");
            startX = e.x;
            startY = e.y;
        }
        if (e.type == SWT.MouseMove && (e.stateMask & SWT.BUTTON1) != 0) {
            Point p = composite.getLocation();
            System.out.println("before  p    " + p);
            p.x += e.x - startX;
            p.y += e.y - startY;
            System.out.println("after  p    " + p);
            System.out.println("before    " + composite.getBounds());
            composite.setLocation(p);
            System.out.println("after    " + composite.getBounds());
        }
    }
	
}
