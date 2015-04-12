package applicationUI;
//��������
import java.awt.Font;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

public class StockDetails {
	
	public void open(String stockName)
	{
		Display display =Display.getDefault();
		 final Shell shell=new Shell(display,SWT.CLOSE);
		 shell.setSize(750,650);
		 shell.setText("����");
		 shell.setLayout(null);
		 shell.setVisible(true);
		 
		 //���׼�¼��Ͽ�
		 Group group =new Group(shell,SWT.NONE);
		 group.setText("���׼�¼");
		 group.setBounds(350,50,350,220);
		 group.setLayout(null);
		 
		 Button gainBtn=new Button(group,SWT.PUSH);
		 gainBtn.setText("��ӽ���");
		 gainBtn.setBounds(280,20,60,30);
		 gainBtn.setVisible(true);
		 
//		 //��дshell�Ĺرմ��ڰ�ť�¼�
//		 shell.addListener(SWT.Close, new Listener() {
//			public void handleEvent(Event event) {shell.getShells();
//				Control[] childrenShells = shell.getChildren();
//				System.out.println(childrenShells.length);
//				if(childrenShells.length != 0){
//					int style = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
//					MessageBox messageBox = new MessageBox(shell, style);
//					messageBox.setText("Information");
//					messageBox.setMessage("Close the shell?");
//					event.doit = messageBox.open() == SWT.YES;
//				}
//				
//			}
//			  });
		 
		 //����Ӽ�¼����ť�ĵ���¼�
		 gainBtn.addSelectionListener(new SelectionAdapter(){ 
			 public void widgetSelected(SelectionEvent e) {   
				 try {System.out.println("sssss");
					   StockSituation window = new StockSituation(shell);
					   window.open("��Ӽ�¼");
					  } catch (Exception er) {
					   er.printStackTrace();
					  }  
	            } 
		 });
		 
		 Button allBtn=new Button(group,SWT.PUSH);
		 allBtn.setText("�鿴ȫ��");
		 allBtn.setBounds(280, 180, 60, 30);
		 allBtn.setVisible(true);
		 
		 Label name =new Label(shell, SWT.FILL);
	     name.setText(stockName);
	     name.setBounds(20,20,60,50);
	     name.setVisible(true);
	     
	     //��ǩ�л�ͼ��
	     final TabFolder tab=new TabFolder(shell,SWT.NONE);
	     tab.setBounds(20,280,710,300);
	     //��ӱ�ǩ
	     final TabItem TabI1=new TabItem(tab,SWT.NONE);
	     TabI1.setText("��ʱ");
	     final TabItem TabI2=new TabItem(tab,SWT.NONE);
	     TabI2.setText("��K");
	     final TabItem TabI3=new TabItem(tab,SWT.NONE);
	     TabI3.setText("��K");
	     final TabItem TabI4=new TabItem(tab,SWT.NONE);
	     TabI4.setText("��K");
	     
	     //ʵʱ�ʲ�
	     Label moneyLabel=new Label(shell, SWT.FILL);
	     moneyLabel.setText("ʵʱ�ʲ�  :");
	     moneyLabel.setBounds(20, 590, 60, 30);
	     moneyLabel.setVisible(true);
	     
		 while (!shell.isDisposed())
		 {
		 if (!display.readAndDispatch())
		 {
		 display.sleep();
		 }
		 }
		 display.dispose();

	}  

	 public static void main(String[] args)
		{
			
			  try {
				   StockDetails window = new StockDetails();
				   window.open("����֤ȯ");
				  } catch (Exception e) {
				   e.printStackTrace();
				  }
		}
	 
}
