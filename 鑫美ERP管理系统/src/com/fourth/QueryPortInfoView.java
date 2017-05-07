package com.fourth;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

			/**
			 * 
			 * @author SiVan
			 * @time 2017��4��17�� ����11:53:37
			 * TODO	���������ѯ
			 */
public class QueryPortInfoView extends JDialog{

	//����scrollpane ˳�����ò��ַ�ʽ�������������ô�ֱ���֣�Ȼ������ˮƽ����
//	private JScrollPane scrollpane = new JScrollPane(
//			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//
//	//��ӱ��ͬʱ���ñ�����ݲ��ɱ༭
//	private JTable table = new JTable(){
//		public boolean isCellEditable(int row, int column) {	
//			return false;
//		};
//	};
//	
//	private JButton update_Button = new JButton("�޸�");
//	private JButton del_Button = new JButton("ɾ��");
//	
//	//���ݿ�
//	private String url = "jdbc:mysql://localhost:3306/erp";
//	private String user = "root";
//	private String password = "yourpassword";
//	
//	Vector<String> header;
//	final Vector<Vector<String>> dataVector;
//	
//	public QueryPortInfoView() {
//		// TODO Auto-generated constructor stub
//		this.setTitle("���������ѯ");
//		this.setSize(800, 700);
//		this.setResizable(false);
//		this.setModal(false);
//		this.setLayout(null);
//		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		this.setLocationRelativeTo(null);
//		scrollpane.setBounds(50, 50, 700, 500);
//		update_Button.setBounds(200, 600, 80, 20);
//		del_Button.setBounds(400, 600, 80, 20);
//		//��ͷ
//		header = new Vector<String>();
//		header.add("������");
//		header.add("�������");
//		header.add("��Ӧ��");
//		header.add("��������");
//		header.add("����۸�");
//		header.add("�������");
//		header.add("����������");
//		header.add("����״̬");
//		
//		//������ݾ���
//        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
//        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//        table.setDefaultRenderer(Object.class, renderer);
//		
//        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
//		//�洢һ��һ���ı�����
//		dataVector = new Vector<Vector<String>>();
//		
//		//�����ݿ⣬�����ݿ��е������ϴ���Vector������
//		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			try(
//				Connection conn = DriverManager.getConnection(url, user, password);
//					){
//				String sql = "select p.piId, pc.pcClassify, s.sName, p.piName, p.piPrice, p.piMaking, p.piNumber, p.piStatus from "
//						+ "portinfo p, portClassify pc, supplier s where p.pcId = pc.pcId and p.sId = s.sId and  p.piStatus = \"1\"";
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				String piId = null;
//				String pcId = null;
//				String sId = null;
//				String piName = null;
//				String piPrice = null;
//				String piMaking = null;
//				String piNumber = null;
//				String piStatus = null;
//				while(rs.next())
//				{
//					piId = rs.getString(1);
//					pcId = rs.getString(2);
//					sId = rs.getString(3);
//					piName = rs.getString(4);
//					piPrice = rs.getString(5);
//					piMaking = rs.getString(6);
//					piNumber = rs.getString(7);
//					piStatus = rs.getString(8);
//					Vector<String> data = new Vector<String>();
//					data.add(piId);
//					data.add(pcId);
//					data.add(sId);
//					data.add(piName);
//					data.add(piPrice);
//					data.add(piMaking);
//					data.add(piNumber);
//					if("1".equals(piStatus))
//					{
//						data.add("����");
//					}else{
//						data.add("�¼�");
//					}
//					dataVector.add(data);
//				}
//			}
//		}catch(Exception e1){
//			e1.printStackTrace();
//		}
//		
//		
//		
//		
//		final DefaultTableModel df = new DefaultTableModel(dataVector, header);
//		table.setModel(df);
//		scrollpane.getViewport().add(table);
//		//���ñ�ͷ������������
//		table.getTableHeader().setReorderingAllowed(false);
//		FitTableColumns(table);
//		
//		//����¼���������
////		del_Button.addActionListener(new ActionListener() {
////			
////			@Override
////			public void actionPerformed(ActionEvent e) {
////				// TODO Auto-generated method stub
////				int row = table.getSelectedRow();	//ѡ�е�һ����0��ûѡ����-1
////				if(row == -1)
////				{
////					JOptionPane.showMessageDialog(null, "��ѡ����");
////					return;
////				}
////				String piId =(String) table.getValueAt(row, 0);
////				try{
////					Class.forName("com.mysql.jdbc.Driver");
////					try(
////						Connection conn = DriverManager.getConnection(url, user, password);
////							){
////						String sql = "update  portinfo set piStatus = \"0\" where piId = ?";
////						PreparedStatement ps = conn.prepareStatement(sql);
////						ps.setObject(1, piId);
////						int n = ps.executeUpdate();
////						if(n > 0)
////							JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
////						else{
////							JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
////							return;
////						}
////						df.removeRow(row);
////						table.setModel(df);
////					}
////				}catch(Exception e1){
////					e1.printStackTrace();
////				}
////				
////			}
////		});
//			
//		/**
//		 * �޸ĵĲ�������Ҫ����һ���Ի�������޸�
//		 */
////		update_Button.addActionListener(new ActionListener() {
////			
////			@Override
////			public void actionPerformed(ActionEvent e) {
////				// TODO Auto-generated method stub
////				int row = table.getSelectedRow();
////				if(row == -1)
////				{
////					JOptionPane.showMessageDialog(null, "��ѡ����");
////					return;
////				}
////				String piId = (String) table.getValueAt(row, 0);
////				
////				new PortInfoUpdate(table, piId, row, header);
////				
////			}
////		});
//		
//		this.add(scrollpane);
////		this.add(update_Button);
////		this.add(del_Button);
//		this.setVisible(true);
//	}
//	
//	public static void FitTableColumns(JTable myTable){
//		  JTableHeader header = myTable.getTableHeader();
//		     int rowCount = myTable.getRowCount();
//		     Enumeration columns = myTable.getColumnModel().getColumns();
//		     while(columns.hasMoreElements()){
//		         TableColumn column = (TableColumn)columns.nextElement();
//		         int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
//		         int width = (int)myTable.getTableHeader().getDefaultRenderer()
//		                 .getTableCellRendererComponent(myTable, column.getIdentifier()
//		                         , false, false, -1, col).getPreferredSize().getWidth();
//		         for(int row = 0; row<rowCount; row++){
//		             int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
//		               myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
//		             width = Math.max(width, preferedWidth);
//		         }
//		         header.setResizingColumn(column); // ���к���Ҫ
//		         column.setWidth(width+myTable.getIntercellSpacing().width);
//		     }
//	}
//	
//		public static void main(String[] args) {
//			new QueryPortInfoView();
//		}
//	
//}
	
	private static final long serialVersionUID = -3619887890741475524L;
    private JPanel contentPane;
    private JTable table  = new JTable(){
		public boolean isCellEditable(int row, int column) {	
			return false;
		};
	};
    private JTextField textField;
    private TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>();;
    
    Vector<String> header ;
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "yourpassword";
	Vector<Vector<String>> dataVector;		//�����������
	JTextField text;						//��ʾ��ǰҳ��
	Vector<Vector<String>> data;			//�����Ҫ��ʾ��ÿһҳ������
	int n = 0;									//�õ���������
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	QueryPortInfoView frame = new QueryPortInfoView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public QueryPortInfoView() {
       
    	header = new Vector<String>(); 
    	header.add("������");
		header.add("�������");
		header.add("��Ӧ��");
		header.add("��������");
		header.add("����۸�");
		header.add("�������");
		header.add("����������");
		header.add("����״̬");
		data = new Vector<Vector<String>>();
		dataVector = new Vector<Vector<String>>();
		text = new JTextField();					//��ʾ��ǰҳ��
		try{
			text.setText("1");
			try(
				Connection conn = DriverManager.getConnection(url, user, password);
					){
				String sql = "select p.piId, pc.pcClassify, s.sName, p.piName, p.piPrice, p.piMaking, p.piNumber, p.piStatus from "
						+ "portinfo p, portClassify pc, supplier s where p.pcId = pc.pcId and p.sId = s.sId and  p.piStatus = \"1\"";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				String piId = null;
				String pcId = null;
				String sId = null;
				String piName = null;
				String piPrice = null;
				String piMaking = null;
				String piNumber = null;
				String piStatus = null;
				while(rs.next())
				{
					n++;
					piId = rs.getString(1);
					pcId = rs.getString(2);
					sId = rs.getString(3);
					piName = rs.getString(4);
					piPrice = rs.getString(5);
					piMaking = rs.getString(6);
					piNumber = rs.getString(7);
					piStatus = rs.getString(8);
					Vector<String> data1 = new Vector<String>();
					data1.add(piId);
					data1.add(pcId);
					data1.add(sId);
					data1.add(piName);
					data1.add(piPrice);
					data1.add(piMaking);
					data1.add(piNumber);
					if("1".equals(piStatus))
					{
						data1.add("����");
					}else{
						data1.add("�¼�");
					}
					dataVector.add(data1);
				}
			}
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ���");
			e1.printStackTrace();
		}
		
		System.out.println(n);
		if(n / 15 >= 1){
			for (int i = 0; i < 15; i++) {
				data.add(dataVector.get(i));
			}
			System.out.println("q");
		}else{
			for (int i = 0; i < n % 15; i++) {
				data.add(dataVector.get(i));
			}
			System.out.println("s");
		}
		
		System.out.println(data.isEmpty());
		DefaultTableModel model = new DefaultTableModel(data,header);
		table.setModel(model);
		
		textField = new JTextField();
        textField.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				sorter.setRowFilter(RowFilter.regexFilter(textField.getText()));
			}
		});
		
		
		sorter.setModel(model);
        table.setRowSorter(sorter);
        
        
       
        setTitle("��Ʒ��Ϣ");
        this.setSize(920, 650);
        this.setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        
        JLabel label = new JLabel("\u5173\u952E\u5B57\uFF1A");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(label);
        
        
        panel.add(textField);
        textField.setColumns(20);
        
        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton button = new JButton("�޸�");
        JButton first = new JButton("��ҳ");
        JButton previous = new JButton("��һҳ");
        JButton next = new JButton("��һҳ");
        JButton last = new JButton("βҳ");
        JLabel page = new JLabel("��ǰҳ��:");
        
        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        first.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				text.setText("1");
				data.removeAllElements();
				if(n / 15 >= 1){
					for (int i = 0; i < 15; i++) {
						data.add(dataVector.get(i));
					}
				}else{
					for (int i = 0; i < n%15; i++) {
						data.add(dataVector.get(i));
					}
				}
        		DefaultTableModel model = new DefaultTableModel(data,header);
        		table.setModel(model);
        	       table.getColumnModel().getColumn(0).setPreferredWidth(90);
        	        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        	        table.getColumnModel().getColumn(2).setPreferredWidth(170);
        	        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(5).setPreferredWidth(110);
        	        table.getColumnModel().getColumn(6).setPreferredWidth(110);
        	        table.getColumnModel().getColumn(7).setPreferredWidth(110);
			}
		});
        
        previous.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String page = text.getText();
				if("1".equals(page)){
					JOptionPane.showMessageDialog(null, "�Բ��������Ѿ�����ҳ��");
					return;
				}
				int k = Integer.parseInt(page);
				text.setText(k - 1 + "");
				data.removeAllElements();
				for (int i = (k - 2) * 15; i < (k - 2) * 5 + 15; i++) {
					data.add(dataVector.get(i));
				}
        		DefaultTableModel model = new DefaultTableModel(data,header);
        		table.setModel(model);
        	       table.getColumnModel().getColumn(0).setPreferredWidth(90);
        	        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        	        table.getColumnModel().getColumn(2).setPreferredWidth(170);
        	        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(5).setPreferredWidth(110);
        	        table.getColumnModel().getColumn(6).setPreferredWidth(110);
        	        table.getColumnModel().getColumn(7).setPreferredWidth(110);
			}
		});
        
        next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int page = Integer.parseInt(text.getText());
				int k = n % 15;
				if(k == 0){
					if(page == n / 15){
						JOptionPane.showMessageDialog(null, "�Ѿ������һҳ��");
						return;
					}
				}else{
					if(page == n / 15 + 1)
					{
						JOptionPane.showMessageDialog(null, "�Ѿ������һҳ��");
						return;
					}
					if(!(page == n / 15 )){
						k = 15;
					}
					text.setText(page + 1 + "");
				}
				data.removeAllElements();
				for (int i = page * 15; i < page * 15 +  k; i++) {
					data.add(dataVector.get(i));
				}
				DefaultTableModel model = new DefaultTableModel(data,header);
        		table.setModel(model);
        	       table.getColumnModel().getColumn(0).setPreferredWidth(90);
        	        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        	        table.getColumnModel().getColumn(2).setPreferredWidth(170);
        	        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(5).setPreferredWidth(110);
        	        table.getColumnModel().getColumn(6).setPreferredWidth(110);
        	        table.getColumnModel().getColumn(7).setPreferredWidth(110);
			}
		});
        
        last.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int k = n % 15;
				data.removeAllElements();
				for(int i = n - k ; i <= n - 1; i++){
					data.add(dataVector.get(i));
				}
				DefaultTableModel model = new DefaultTableModel(data,header);
        		table.setModel(model);
        	       table.getColumnModel().getColumn(0).setPreferredWidth(90);
        	        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        	        table.getColumnModel().getColumn(2).setPreferredWidth(170);
        	        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(5).setPreferredWidth(110);
        	        table.getColumnModel().getColumn(6).setPreferredWidth(110);
        	        table.getColumnModel().getColumn(7).setPreferredWidth(110);
			}
		});
        
        text.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyReleased(KeyEvent e) {
        		// TODO Auto-generated method stub
        		int k = n % 15;
        		int page = 0;
        		try{
        			 page = Integer.parseInt(text.getText());
        		}catch(Exception e1){
        			JOptionPane.showMessageDialog(null, "��ע��ҳ����ʽ");
        			e1.printStackTrace();
        			return;
        		}
        		if(page < 1){
        			JOptionPane.showMessageDialog(null, "ҳ���Ƿ�");
        			return;
        		}
        		if(k == 0){
        			if( page > n/15){
        				JOptionPane.showMessageDialog(null, "ҳ���Ƿ�");
        				return;
        			}
        			k = 15;
        		}else{
        			if( page > n/15 + 1){
        				JOptionPane.showMessageDialog(null, "ҳ���Ƿ�");
        				return;
        			}
        			if( page < n/15 + 1){
        				k = 15;
        			}
        			
        		}
				data.removeAllElements();
				for(int i = (page - 1) * 15 ; i < (page - 1) * 15 + k; i++){
					data.add(dataVector.get(i));
				}
				DefaultTableModel model = new DefaultTableModel(data,header);
        		table.setModel(model);
        	       table.getColumnModel().getColumn(0).setPreferredWidth(90);
        	        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        	        table.getColumnModel().getColumn(2).setPreferredWidth(170);
        	        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        	        table.getColumnModel().getColumn(5).setPreferredWidth(110);
        	        table.getColumnModel().getColumn(6).setPreferredWidth(110);
        	        table.getColumnModel().getColumn(7).setPreferredWidth(110);
        	}
		});
        first.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        previous.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        next.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        last.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        page.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        text.setColumns(5);
        buttonPanel.add(first);
        buttonPanel.add(previous);
        buttonPanel.add(page);
        buttonPanel.add(text);
        buttonPanel.add(next);
        buttonPanel.add(last);
        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setPreferredWidth(90);
        table.getColumnModel().getColumn(2).setPreferredWidth(170);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(110);
        table.getColumnModel().getColumn(6).setPreferredWidth(110);
        table.getColumnModel().getColumn(7).setPreferredWidth(110);
    	
    	table.getTableHeader().setReorderingAllowed(false);
        table.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        table.setRowHeight(30);
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        scrollPane.setViewportView(table);
        this.setVisible(true);
    }
    
   
    
}

