
package applicationUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class TestGridLayout
{
 public static void main(String[] args)
 {
	 Display display = new Display();	
     Shell shell = new Shell(display);
shell.setText("GridLayout��ʾ");
GridLayout layout = new GridLayout();
layout.numColumns = 3; 
layout.makeColumnsEqualWidth = true;
shell.setLayout(layout);

// �������Ͻǵİ�ť
GridData data = new GridData(GridData.FILL_BOTH); 
data.widthHint = 200; //��ť�ĳ�ʼ���Ϊ200
Button one = new Button(shell, SWT.PUSH);

one.setText("��ť1");
one.setLayoutData(data);

 // ����һ��Composite���󣬲��������������ť
 Composite composite = new Composite(shell, SWT.NONE);
 data = new GridData(GridData.FILL_BOTH); 
 data.horizontalSpan = 2; // Composite��ռ����Cell

 composite.setLayoutData(data);
 layout = new GridLayout();
 layout.numColumns = 1;
 layout.marginHeight = 15;
 layout.marginRight = 150;
 composite.setLayout(layout);
 
 // �����ڶ�����ť
 data = new GridData(GridData.FILL_BOTH);
 Button two = new Button(composite, SWT.PUSH);
 two.setText("��ť2");
 two.setLayoutData(data);

 // ������������ť
 data = new GridData(GridData.HORIZONTAL_ALIGN_END);
 Button three = new Button(composite, SWT.PUSH);
 three.setText("��ť3");
 three.setLayoutData(data);

 // �������ĸ���ť
 data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
 Button four = new Button(composite, SWT.PUSH);
 four.setText("��ť4");
 four.setLayoutData(data);

 // ���������һ������ť
 data = new GridData();
 data.horizontalAlignment = GridData.FILL;
 data.grabExcessHorizontalSpace = true;
 data.horizontalSpan = 3;
 data.verticalSpan = 2;
 data.heightHint = 150;
 Button five = new Button(shell, SWT.PUSH);
 five.setText("��ť5");
 five.setLayoutData(data);
 
 shell.pack();
 shell.open();
 while (!shell.isDisposed())
 {
 if (!display.readAndDispatch())
 {
 display.sleep();
 }
 }
 display.dispose();
 }
}