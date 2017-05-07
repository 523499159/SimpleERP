package com.second;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import com.pojo.EmpInfo;
		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��27�� ����10:18:14
		 * TODO	ʵ�����۵Ǽ�
		 */
public class QueryVendition extends JDialog {
//	private String url = "jdbc:mysql://localhost:3306/erp";
//	private String user = "root";
//	private String password = "yourpassword";
//	
//	
//	private JLabel customerId = new JLabel("�ͻ����");
//	private JLabel portInfoId = new JLabel("������");
//	private JLabel employeeId = new JLabel("Ա�����");
//	private JLabel countLabel = new JLabel("����");
//	private JLabel priceLabel = new JLabel("�����ۼ�");
//	private JLabel date = new JLabel("�۳�����");
//	
//	private JTextField customerId_Field = new JTextField();
//	private JTextField portInfoId_Field= new JTextField();
//	private JComboBox<EmpInfo> employeeId_Field = new JComboBox<EmpInfo>();
//	private JTextField countLabel_Field = new JTextField();
//	private JTextField priceLabel_Field = new JTextField();
//	private JTextField date_Field = new JTextField();
//	
//	
//	
//	private JButton button  = new JButton("�Ǽ�");
//	
//	
//	
//	public AddVenditionPanel() {
//		// TODO Auto-generated constructor stub
//		this.setTitle("���۵Ǽ�");
//		this.setSize(500, 400);
//		this.setLayout(null);
//		this.setLocationRelativeTo(null);
//		try{
//			try(
//				Connection conn = DriverManager.getConnection(url, user, password);
//					){
//				String sql = "select eId, eName from employee where eStatus = \"1\"";
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();
//				while(rs.next()){
//					EmpInfo emp = new EmpInfo();
//					emp.setId(rs.getString(1));
//					emp.setName(rs.getString(2));
//					employeeId_Field.addItem(emp);
//				}
//			}
//			
//		}catch(Exception e1){
//			JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ���");
//			e1.printStackTrace();
//		}
//		
//		button.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					String name = name_Field.getText();
//					String card = card_Field.getText();
//					String tel = tel_Field.getText();
//					String date = hiredate_Field.getText();
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//					Date empHireDate = null;
//					try {
//						empHireDate = sdf.parse(date);
//					} catch (ParseException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//					
//					//���ȼ���û����������Ƿ�Ϊ��
//					if(name.equals("")){
//						JOptionPane.showConfirmDialog(null, "��������Ϊ��", "����", 
//								JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
//						return;
//					}
//					if(card.equals("")){
//						JOptionPane.showConfirmDialog(null, "���֤���벻��Ϊ��", "����", 
//								JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
//						return;
//					}
//					if(tel.equals("")){
//						JOptionPane.showConfirmDialog(null, "��ϵ��ʽ����Ϊ��", "����", 
//								JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
//						return;
//					}
//					if(date.equals("")){
//						JOptionPane.showConfirmDialog(null, "��ְ���ڲ���Ϊ��", "����", 
//								JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
//						return;
//					}
//					
//					//ִ��JDBC
//					try{
//						Class.forName("com.mysql.jdbc.Driver");
//						try(
//							Connection conn = DriverManager.getConnection(Url, User, Password);
//								){
//							String sql = "INSERT INTO employee(eName, eCard, ePhone, eHiredate, eStatus) VALUES(?, ?, ?, ?, ?)";
//							PreparedStatement ps = conn.prepareStatement(sql);
//							ps.setObject(1, name);
//							ps.setObject(2, card);
//							ps.setObject(3, tel);
//							ps.setObject(4, empHireDate);
//							ps.setObject(5, "1");
//							int n = ps.executeUpdate();
//							if(n <= 0){
//								JOptionPane.showMessageDialog(null, "���ʧ�ܣ����Ժ�����");
//								return;
//							}else{
//								JOptionPane.showMessageDialog(null, "��ӳɹ�");
//								name_Field.setText("");
//								card_Field.setText("");
//								tel_Field.setText("");
//								hiredate_Field.setText("");
//							}
//						}
//					}catch(Exception e1){
//						e1.printStackTrace();
//						JOptionPane.showConfirmDialog(null, "�������֤�����Ƿ���ȷ�����֤���벻�����ظ�", "����", 
//								JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
//									}
//					}
//		});
//		
//		this.setVisible(true);
//	}
//	
//	public static void main(String[] args) {
//		
//	}
	
	
//	//����scrollpane ˳�����ò��ַ�ʽ�������������ô�ֱ���֣�Ȼ������ˮƽ����
//			private JScrollPane scrollpane = new JScrollPane(
//					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//
//			//��ӱ��ͬʱ���ñ�����ݲ��ɱ༭
//			private JTable table = new JTable(){
//				public boolean isCellEditable(int row, int column) {	
//					return false;
//				};
//			};
//			
//			private JButton update_Button = new JButton("�޸�");
//			private JButton del_Button = new JButton("ɾ��");
//			
//			//���ݿ�
//			private String url = "jdbc:mysql://localhost:3306/erp";
//			private String user = "root";
//			private String password = "yourpassword";
//			
//			Vector<String> header;
//			final Vector<Vector<String>> dataVector;
//			
//			public AddVenditionPanel() {
//				// TODO Auto-generated constructor stub
//				this.setTitle("���۲�ѯ");
//				this.setSize(700, 700);
//				this.setResizable(false);
//				this.setModal(false);
//				this.setLayout(null);
//				this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				this.setLocationRelativeTo(null);
//				scrollpane.setBounds(40, 20, 600, 600);
//				update_Button.setBounds(200, 630, 80, 20);
//				del_Button.setBounds(400, 630, 80, 20);
//				//��ͷ
//				header = new Vector<String>();
//				header.add("���۱���");
//				header.add("�ͻ�����");
//				header.add("�۳�������");
//				header.add("������");
//				header.add("�۳���������");
//				header.add("������");
//				header.add("�۳����赥��");
//				header.add("�۳������ܼ�");
//				header.add("����ʱ��");
//				
//				//������ݾ���
//		        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
//		        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
//		        table.setDefaultRenderer(Object.class, renderer);
//				
//				//�洢һ��һ���ı�����
//				dataVector = new Vector<Vector<String>>();
//				
//				//�����ݿ⣬�����ݿ��е������ϴ���Vector������
//				try{
//					Class.forName("com.mysql.jdbc.Driver");
//					try(
//						Connection conn = DriverManager.getConnection(url, user, password);
//							){
//						String sql = "select v.vId, c.cName, pi.piId, e.eId, v.vCount, pi.piPrice, v.vPrice, (v.vCount * v.vPrice) as price,  "
//								+ "vDate from vendition v, customer c, portInfo pi, employee e where c.cId = v.cId and pi.piId = v.piId and e.eId = v.eId and v.vStatus = \"1\"";
//						PreparedStatement ps = conn.prepareStatement(sql);
//						ResultSet rs = ps.executeQuery();
//						String vId = null;
//						String cId = null;
//						String piId = null;
//						String eId = null;
//						String vCount = null;
//						String vPrice = null;
//						String price = null;
//						String date = null;
//						String allPrice = null;
//						while(rs.next())
//						{
//							vId = rs.getString(1);
//							cId = rs.getString(2);
//							piId = rs.getString(3);
//							eId = rs.getString(4);
//							vCount = rs.getString(5);
//							vPrice = rs.getString(6);
//							price = rs.getString(7);
//							allPrice = rs.getString(8);
//							date = rs.getString(9);
//							Vector<String> data = new Vector<String>();
//							data.add(vId);
//							data.add(cId);
//							data.add(piId);
//							data.add(eId);
//							data.add(vCount);
//							data.add(vPrice);
//							data.add(price);
//							data.add(allPrice);
//							data.add(date);
//							dataVector.add(data);
//						}
//					}
//				}catch(Exception e1){
//					e1.printStackTrace();
//				}
//				
//				
//				
//				
//				
//				final DefaultTableModel df = new DefaultTableModel(dataVector, header);
//				table.setModel(df);
//				scrollpane.getViewport().add(table);
//				//���ñ�ͷ������������
//				table.getTableHeader().setReorderingAllowed(false);
//				FitTableColumns(table);
//				
//				//����¼���������
//				del_Button.addActionListener(new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						// TODO Auto-generated method stub
//						int row = table.getSelectedRow();	//ѡ�е�һ����0��ûѡ����-1
//						if(row == -1)
//						{
//							JOptionPane.showMessageDialog(null, "��ѡ����");
//							return;
//						}
//						String vId =(String) table.getValueAt(row, 0);
//						try{
//							Class.forName("com.mysql.jdbc.Driver");
//							try(
//								Connection conn = DriverManager.getConnection(url, user, password);
//									){
//								String sql = "update  vendition set vStatus = '0' where vId = ?";
//								PreparedStatement ps = conn.prepareStatement(sql);
//								ps.setObject(1, vId);
//								int n = ps.executeUpdate();
//								if(n > 0)
//									JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
//								else{
//									JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
//									return;
//								}
//								df.removeRow(row);
//								table.setModel(df);
//							}
//						}catch(Exception e1){
//							e1.printStackTrace();
//						}
//						
//					}
//				});
//					
//				/**
//				 * �޸ĵĲ�������Ҫ����һ���Ի�������޸�
//				 */
//				update_Button.addActionListener(new ActionListener() {
//					
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						// TODO Auto-generated method stub
//						int row = table.getSelectedRow();
//						if(row == -1)
//						{
//							JOptionPane.showMessageDialog(null, "��ѡ����");
//							return;
//						}
//						String empId = (String) table.getValueAt(row, 0);
//						String empName = (String) table.getValueAt(row, 1);
//						
////						new VenditionUpdate(table, empId, row, header);
//						
//					}
//				});
//				
//				this.add(scrollpane);
//				this.add(update_Button);
//				this.add(del_Button);
//				this.setVisible(true);
//			}
//			
//			public static void FitTableColumns(JTable myTable){
//				  JTableHeader header = myTable.getTableHeader();
//				     int rowCount = myTable.getRowCount();
//				     Enumeration columns = myTable.getColumnModel().getColumns();
//				     while(columns.hasMoreElements()){
//				         TableColumn column = (TableColumn)columns.nextElement();
//				         int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
//				         int width = (int)myTable.getTableHeader().getDefaultRenderer()
//				                 .getTableCellRendererComponent(myTable, column.getIdentifier()
//				                         , false, false, -1, col).getPreferredSize().getWidth();
//				         for(int row = 0; row<rowCount; row++){
//				             int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
//				               myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
//				             width = Math.max(width, preferedWidth);
//				         }
//				         header.setResizingColumn(column); // ���к���Ҫ
//				         column.setWidth(width+myTable.getIntercellSpacing().width);
//				     }
//			}
//			
//			public static void main(String[] args) {
//				new AddVenditionPanel();
//			}
	
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
		                	QueryVendition frame = new QueryVendition();
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
		    public QueryVendition() {
		       
		    	header = new Vector<String>(); 
				header.add("���۱���");
				header.add("�ͻ�����");
				header.add("������");
				header.add("��������");
				header.add("������");
				header.add("�۳���������");
				header.add("������");
				header.add("�۳����赥��");
				header.add("�۳������ܼ�");
				header.add("����ʱ��");
				
				
				
				data = new Vector<Vector<String>>();
				dataVector = new Vector<Vector<String>>();
				text = new JTextField();					//��ʾ��ǰҳ��
				try{
					text.setText("1");
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sql = "select v.vId, c.cName, pi.piId,pi.piName, e.eName, v.vCount, pi.piPrice, v.vPrice, (v.vCount * v.vPrice) as price,  "
								+ "vDate from vendition v, customer c, portInfo pi, employee e where c.cId = v.cId and pi.piId = v.piId and e.eId = v.eId and v.vStatus = \"1\" order by vDate desc";
						PreparedStatement ps = conn.prepareStatement(sql);
						ResultSet rs = ps.executeQuery();
						String vId = null;
						String cId = null;
						String piId = null;
						String eName = null;
						String vCount = null;
						String vPrice = null;
						String price = null;
						String date = null;
						String allPrice = null;
						String name = null;
						while(rs.next())
						{
							n++;
							vId = rs.getString(1);
							cId = rs.getString(2);
							piId = rs.getString(3);
							name = rs.getString(4);
							eName = rs.getString(5);
							vCount = rs.getString(6);
							vPrice = rs.getString(7);
							price = rs.getString(8);
							allPrice = rs.getString(9);
							date = rs.getString(10);
							Vector<String> data1 = new Vector<String>();
							data1.add(vId);
							data1.add(cId);
							data1.add(piId);
							data1.add(name);
							data1.add(eName);
							data1.add(vCount);
							data1.add(vPrice);
							data1.add(price);
							data1.add(allPrice);
							data1.add(date);
							dataVector.add(data1);
						}
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ���");
					e1.printStackTrace();
				}
				
				if(n / 15 >= 1){
					for (int i = 0; i < 15; i++) {
						data.add(dataVector.get(i));
					}
				}else{
					for (int i = 0; i < n % 15; i++) {
						data.add(dataVector.get(i));
					}
					
				}
				
				final DefaultTableModel model = new DefaultTableModel(data,header);
				table.setModel(model);
				sorter.setModel(model);
		        table.setRowSorter(sorter);
		        
		        
				textField = new JTextField();
		        textField.setFont(new Font("΢���ź�", Font.PLAIN, 16));
				textField.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						sorter.setRowFilter(RowFilter.regexFilter(textField.getText()));
					}
				});
				
				
				
		       
		        setTitle("���۲�ѯ");
		        this.setSize(1100, 650);
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
		        	}
				});
		        first.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		        previous.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		        next.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		        last.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		        page.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		        text.setColumns(5);
		        
		        
		        JButton add = new JButton("���");
		        JButton delete = new JButton("ɾ��");
		        JButton update = new JButton("�޸�");
		        buttonPanel.add(first);
		        buttonPanel.add(previous);
		        buttonPanel.add(page);
		        buttonPanel.add(text);
		        buttonPanel.add(next);
		        buttonPanel.add(last);
		        buttonPanel.add(add);
		        buttonPanel.add(update);
		        buttonPanel.add(delete);
		        JScrollPane scrollPane = new JScrollPane();
		        contentPane.add(scrollPane, BorderLayout.CENTER);
		        
		        add.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						new AddVenditionDialog(QueryVendition.this);
						
					}
				});
		    	
		        
		        update.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int raw = table.getSelectedRow();
						String portId = (String) table.getValueAt(raw, 2);
						new UpdateVendition(portId, table, data, header, raw, QueryVendition.this);
					}
				});
		        
		        delete.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int row = table.getSelectedRow();	//ѡ�е�һ����0��ûѡ����-1
						if(row == -1)
						{
							JOptionPane.showMessageDialog(null, "��ѡ����");
							return;
						}
						String vId =(String) table.getValueAt(row, 0);
						try{
							Class.forName("com.mysql.jdbc.Driver");
							try(
								Connection conn = DriverManager.getConnection(url, user, password);
									){
								String sql = "update  vendition set vStatus = '0' where vId = ?";
								PreparedStatement ps = conn.prepareStatement(sql);
								ps.setObject(1, vId);
								int n = ps.executeUpdate();
								if(n > 0){
									JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
									QueryVendition.this.dispose();
									new QueryVendition();
								}else{
									JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
									return;
								}
								model.removeRow(row);
								table.setModel(model);
							}
						}catch(Exception e1){
							e1.printStackTrace();
						}
					}
				});
		    	table.getTableHeader().setReorderingAllowed(false);
		        table.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		        table.setRowHeight(30);
		        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
		        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		        table.setDefaultRenderer(Object.class, renderer);
		        
		        JTableHeader header1 = table.getTableHeader();
		        header1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		        header1.setPreferredSize(new Dimension(header1.getWidth(), 35));
		        scrollPane.setViewportView(table);
		        this.setVisible(true);
		    }
		    
		   
}