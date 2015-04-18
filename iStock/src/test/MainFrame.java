//package ui;
//
//import java.awt.EventQueue;
//import java.awt.Rectangle;
//
//import javax.swing.JFrame;
//
//import java.awt.Color;
//
//import javax.swing.JTabbedPane;
//
//
//
//import javax.swing.ImageIcon;
//import javax.swing.JPanel;
//import javax.swing.JLabel;
//import javax.swing.JTable;
//
//import java.awt.Font;
//import java.awt.event.MouseEvent;
//
//import javax.swing.border.LineBorder;
//import javax.swing.JTextField;
//import javax.swing.JButton;
//
//import java.awt.event.MouseAdapter;
//
//public class MainFrame {
//
//	private JFrame frame;
//	private ImageIcon icon = new ImageIcon("Image/icon.jpg");
//	private String market, day_breakeven, profit_loss, profit_loss_pre;
//	private String float_pro, float_pro_pre, account_sum, cash, market_cap,principal;
//	private JTextField searchField;
//	private JTabbedPane tabbedPane;
//	private MainChart mainchart = new MainChart();
//	private GSBank gsbank = new GSBank();
//	private FixCash fixCash = new FixCash();
//	private StockPre stockPre = new StockPre();
//	private int lx,ly,lw,lh;
//	
//	private int tabnum = 0,gsnum = -1,fixnum = -1, stocknum = -1;
//	private boolean gst = false,fixb = false, stockb = false;
//	
//	public JFrame getFrame_info(){
//		return frame;
//	}
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrame window = new MainFrame();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 
//	*/
//	public MainFrame() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		
//		market = "A鑲�;
//		day_breakeven = "楼0.00";
//		profit_loss = "楼-23.33";
//		profit_loss_pre = "-0.02%";
//	    float_pro = "楼17632.28";
//	    float_pro_pre = "+3.53%";
//	    account_sum = "楼517632.28";
//	    cash = "楼-101121.31";
//	    market_cap = "楼618814.43";
//	    principal = "楼500000.00";
//	    
//		frame = new JFrame();
//		frame.setTitle("浼樿偂");
//		frame.getContentPane().setBackground(Color.WHITE);
//		frame.getContentPane().setLayout(null);
//		
//		
//		
//		
//		JPanel panel = new JPanel();
//		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel.setBackground(Color.WHITE);
//		panel.setBounds(1, 1, 255, 551);
//		frame.getContentPane().add(panel);
//		panel.setLayout(null);
//		
//	
//		JLabel ImageIcon = new JLabel(icon);
//		ImageIcon.setBounds(10, 35, 97, 92);
//		panel.add(ImageIcon);
//		
//		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A\u8D85\u80FD\u5927\u767D");
//		label_1.setFont(new Font("瀹嬩綋", Font.PLAIN, 13));
//		label_1.setBounds(117, 87, 112, 15);
//		panel.add(label_1);
//		
//		JLabel logout = new JLabel("娉ㄩ攢");
//		logout.setForeground(Color.BLUE);
//		logout.setFont(new Font("瀹嬩綋", Font.PLAIN, 13));
//		logout.setBounds(193, 112, 31, 15);
//		logout.addMouseListener(new  MouseAdapter(){
//			public void mouseClicked(MouseEvent e) 
//            { //鍝嶅簲榧犳爣鐐瑰嚮浜嬩欢);
//				frame.setVisible(false);
//				Login login = new Login();
//				login.getFrame_info().setVisible(true);
//            }
//			});
//		panel.add(logout);
//		
//		JPanel panel_1 = new JPanel();
//		panel_1.setBackground(Color.WHITE);
//		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_1.setBounds(0, 0, 255, 25);
//		panel.add(panel_1);
//		panel_1.setLayout(null);
//		
//		JLabel label = new JLabel("\u7528\u6237\u4FE1\u606F");
//		label.setBounds(84, 10, 87, 15);
//		panel_1.add(label);
//		label.setFont(new Font("瀹嬩綋", Font.BOLD, 16));
//		
//		JPanel panel_2 = new JPanel();
//		panel_2.setBackground(Color.WHITE);
//		panel_2.setForeground(Color.BLACK);
//		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
//		panel_2.setBounds(0, 131, 255, 25);
//		panel.add(panel_2);
//		panel_2.setLayout(null);
//		
//		JLabel label_3 = new JLabel("\u8D26\u6237\u4FE1\u606F");
//		label_3.setFont(new Font("瀹嬩綋", Font.BOLD, 14));
//		label_3.setBounds(88, 10, 76, 15);
//		panel_2.add(label_3);
//		
//		JLabel label_4 = new JLabel("\u5E02\u573A\uFF1A");
//		label_4.setFont(new Font("瀹嬩綋", Font.BOLD, 12));
//		label_4.setBounds(10, 170, 54, 15);
//		panel.add(label_4);
//		
//		JLabel lblNewLabel_1 = new JLabel(market);
//		lblNewLabel_1.setFont(new Font("瀹嬩綋", Font.BOLD, 12));
//		lblNewLabel_1.setBounds(92, 170, 54, 15);
//		panel.add(lblNewLabel_1);
//		
//		JLabel hold_can = new JLabel("\u6301\u4ED3\u60C5\u51B5");
//		hold_can.setForeground(Color.BLUE);
//		hold_can.setBounds(175, 170, 54, 15);
//		hold_can.addMouseListener(new MouseAdapter(){
//			public void mouseClicked(MouseEvent e) 
//            { //鍝嶅簲榧犳爣鐐瑰嚮浜嬩欢
//               tabbedPane.setSelectedIndex(0);
//            }
//		});
//		panel.add(hold_can);
//		
//		JLabel lblNewLabel_2 = new JLabel("鏃ョ泩浜忓�锛�);
//		lblNewLabel_2.setBounds(10, 195, 72, 15);
//		panel.add(lblNewLabel_2);
//		
//		JLabel lblNewLabel_3 = new JLabel(day_breakeven);
//		lblNewLabel_3.setBounds(92, 195, 97, 15);
//		panel.add(lblNewLabel_3);
//		
//		JLabel lblNewLabel_4 = new JLabel("鐩堜簭鍊硷細");
//		lblNewLabel_4.setBounds(10, 230, 54, 15);
//		panel.add(lblNewLabel_4);
//		
//		JLabel lblNewLabel_5 = new JLabel(profit_loss);
//		lblNewLabel_5.setForeground(new Color(0, 128, 0));
//		lblNewLabel_5.setBounds(92, 230, 72, 15);
//		panel.add(lblNewLabel_5);
//		
//		JLabel lblNewLabel_6 = new JLabel(profit_loss_pre);
//		lblNewLabel_6.setForeground(new Color(0, 128, 0));
//		lblNewLabel_6.setBounds(175, 230, 77, 15);
//		panel.add(lblNewLabel_6);
//		
//		JLabel label_6 = new JLabel("\u6D6E\u52A8\u76C8\u4E8F\uFF1A");
//		label_6.setBounds(10, 262, 79, 15);
//		panel.add(label_6);
//		
//		JLabel label_7 = new JLabel(float_pro);
//		label_7.setForeground(Color.RED);
//		label_7.setBounds(92, 262, 77, 15);
//		panel.add(label_7);
//
//		JLabel lblNewLabel_11 = new JLabel(float_pro_pre);
//		lblNewLabel_11.setForeground(Color.RED);
//		lblNewLabel_11.setBounds(177, 261, 70, 15);
//		panel.add(lblNewLabel_11);
//		frame.setBounds(100, 100, 900, 600);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		JLabel label_8 = new JLabel("\u8D26\u6237\u603B\u8D44\u4EA7\uFF1A");
//		label_8.setBounds(10, 292, 72, 15);
//		panel.add(label_8);
//		
//		JLabel lblNewLabel_7 = new JLabel(account_sum);
//		lblNewLabel_7.setBounds(92, 292, 97, 15);
//		panel.add(lblNewLabel_7);
//		
//		JLabel label_9 = new JLabel("\u73B0\u91D1\uFF1A");
//		label_9.setBounds(10, 327, 54, 15);
//		panel.add(label_9);
//		
//		JLabel lblNewLabel_8 = new JLabel(cash);
//		lblNewLabel_8.setBounds(92, 326, 112, 15);
//		panel.add(lblNewLabel_8);
//		
//		JLabel label_10 = new JLabel("\u5E02\u503C\uFF1A");
//		label_10.setBounds(8, 360, 54, 15);
//		panel.add(label_10);
//		
//		JLabel lblNewLabel_9 = new JLabel(market_cap);
//		lblNewLabel_9.setBounds(92, 360, 97, 15);
//		panel.add(lblNewLabel_9);
//		
//		JLabel label_11 = new JLabel("\u672C\u91D1\uFF1A");
//		label_11.setBounds(10, 396, 54, 15);
//		panel.add(label_11);
//		
//		JLabel lblNewLabel_10 = new JLabel(principal);
//		lblNewLabel_10.setBounds(92, 396, 85, 15);
//		panel.add(lblNewLabel_10);
//		
//		JLabel fixc = new JLabel("\u4FEE\u6539");
//		fixc.setForeground(Color.BLUE);
//		fixc.setBounds(175, 396, 54, 15);
//		fixc.addMouseListener(new MouseAdapter(){
//			public void mouseClicked(MouseEvent e) 
//            { //鍝嶅簲榧犳爣鐐瑰嚮浜嬩欢
//                fp(e);
//            }
//		});
//		panel.add(fixc);
//		
//		JLabel stockbase = new JLabel("\u80A1\u7968\u6536\u76CA\u7387/\u6301\u80A1\u6784\u6210");
//		stockbase.setForeground(Color.BLUE);
//		stockbase.setBounds(10, 432, 154, 15);
//		stockbase.addMouseListener(new MouseAdapter(){
//			public void mouseClicked(MouseEvent e) 
//            { //鍝嶅簲榧犳爣鐐瑰嚮浜嬩欢
//                sp(e);
//            }
//		});
//		panel.add(stockbase);
//		
//		searchField = new JTextField();
//		searchField.setText("\u8F93\u5165\u80A1\u7968\u540D\u5B57/\u7F16\u53F7");
//		searchField.setBounds(10, 468, 168, 21);
//		panel.add(searchField);
//		searchField.setColumns(10);
//		
//		JButton button = new JButton("\u641C\u7D22");
//		button.setBounds(183, 467, 62, 23);
//		button.addMouseListener(new MouseAdapter(){
//			public void mouseClicked(MouseEvent e) 
//            { //鍝嶅簲榧犳爣鐐瑰嚮浜嬩欢
//			   String s = searchField.getText();
//			   if(s.equals("宸ュ晢閾惰") || s.equals("6101398"))
//			   {
//				  if(!gst)
//				  {
//					  tabnum++;
//					  gsnum = tabnum;
//					  tabbedPane.addTab("宸ュ晢閾惰", gsbank);
//					  tabbedPane.setSelectedIndex(gsnum);
//					  gst = true;
//				  }
//				  else
//				  {
//				       tabbedPane.setSelectedIndex(gsnum);
//				  }
//			   }
//            }
//		});
//		panel.add(button);
//		
//		
//		
//	    lx = 260;ly=29;lw=620;lh = 526;
//		
//		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane.setBounds(254, 0, 626, 552);
//		mainchart.setBounds(lx, ly,lw, lh);
//		tabbedPane.add(mainchart,"鎸佷粨鎯呭喌");
//		frame.getContentPane().add(tabbedPane);
//		
//		
//		mainchart.table.addMouseListener(new MouseAdapter(){
//			public void mouseClicked(MouseEvent e) 
//            { //鍝嶅簲榧犳爣鐐瑰嚮浜嬩欢
//                tp(e);
//            }
//			});
//		tabbedPane.addMouseListener(new MouseAdapter(){
//			public void mouseClicked(MouseEvent e) 
//            { //鍝嶅簲榧犳爣鐐瑰嚮浜嬩欢
//                p(e);
//            }
//		});
//		 
//		
//	}
//	private void tp(MouseEvent e)
//	{
//		 int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //鑾峰緱琛屼綅缃�
//         int col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); //鑾峰緱鍒椾綅缃�
//         String cellVal=(String)(mainchart.table.getValueAt(row,col)); //鑾峰緱鐐瑰嚮鍗曞厓鏍兼暟鎹�
//         if(row == 1 && col== 0)
//         {
//        	 if(!gst)
//        	 {
//        		 tabbedPane.addTab("宸ュ晢閾惰", gsbank);
//        		 tabnum++;
//        		 gsnum = tabnum;
//            	 tabbedPane.setSelectedIndex(gsnum);
//            	 gst = true;
//        	 }
//        	 else
//        	 {
//        		 tabbedPane.setSelectedIndex(gsnum);
//        	 }
//        	 
//         }
//	}
//	private void p(MouseEvent e) {
//	    for (int i = 0; i < tabbedPane.getTabCount(); i++) {
//	    	
//	    		Rectangle rect = tabbedPane.getBoundsAt(i); //鎷垮埌鏍囩鐨勮竟鐣�
//	    		if (rect.contains(e.getX(), e.getY()) && e.getClickCount() == 2) { //鍒ゆ柇鏄惁鐐瑰湪杈圭晫鍐�
//	    			if(i != 0)
//	    	    	{
//	    				tabbedPane.remove(i);
//	    				tabnum--;
//	    				if(i == gsnum)
//	    				{
//	    					if(fixnum > gsnum)
//	    						fixnum--;
//	    					if(stocknum > gsnum)
//	    						stocknum--;
//	    		
//	    					gst = false;
//	    					gsnum = -1;
//	    				}
//	    				else if(i == fixnum)
//	    				{
//	    					if(gsnum > fixnum)
//	    						gsnum--;
//	    					if(stocknum > fixnum)
//	    						stocknum--;
//	    					fixnum = -1;
//	    					fixb = false;
//	    				}
//	    				else if(i == stocknum)
//	    				{
//	    					if(gsnum > stocknum)
//	    						gsnum--;
//	    					if(fixnum > stocknum)
//	    						fixnum--;
//	    					stocknum = -1;
//	    					stockb = false;
//	    				}
//	    				
//	    			    //璺宠浆鍒颁笂涓�釜椤甸潰
//	    	    	}
//	    		}
//	    		else if(rect.contains(e.getX(), e.getY()) && e.getClickCount() == 1)
//	    		{
//	    			//璺宠浆鍒版煇涓〉闈�
//	    			System.out.print("鎸佷粨鎯呭喌");
//	    		}
//	    }
//	}
//	private void fp(MouseEvent e) {
//		 if(!fixb)
//    	 {
//    		 tabbedPane.addTab("閲戦淇敼", fixCash);
//    		 tabnum++;
//    		 fixnum = tabnum;
//        	 tabbedPane.setSelectedIndex(fixnum);
//        	 fixb = true;
//    	 }
//    	 else
//    	 {
//    		 tabbedPane.setSelectedIndex(fixnum);
//    	 }
//	}
//	private void sp(MouseEvent e){
//		 if(!stockb)
//    	 {
//    		 tabbedPane.addTab("鎸佽偂鏋勬垚/鏀剁泭鐜�, stockPre);
//    		 tabnum++;
//    		 stocknum = tabnum;
//        	 tabbedPane.setSelectedIndex(stocknum);
//        	 stockb = true;
//    	 }
//    	 else
//    	 {
//    		 tabbedPane.setSelectedIndex(stocknum);
//    	 }
//		
//	};
//}
//
//
