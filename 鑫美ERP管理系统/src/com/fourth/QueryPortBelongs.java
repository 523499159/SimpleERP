package com.fourth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.plugin.Chooser;
		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��17�� ����8:07:35
		 * TODO	��ѯ������������
		 */
public class QueryPortBelongs extends JDialog{

	private JLabel port_Label = new JLabel("������");
	private JTextField port_Field = new JTextField();
	
	
	private JScrollPane scrollpane = new JScrollPane(
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	//��ӱ��ͬʱ���ñ�����ݲ��ɱ༭
	private JTable table = new JTable(){
		public boolean isCellEditable(int row, int column) {	
			return false;
		};
	};
	
	private JButton query_Button = new JButton("��ѯ");
	
	//���ݿ�
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "yourpassword";
	
	Vector<String> header;
	final Vector<Vector<String>> dataVector;
	
	public QueryPortBelongs() {
		// TODO Auto-generated constructor stub
		this.setTitle("����������ѯ");
		this.setSize(1000, 500);
		this.setResizable(false);
		this.setModal(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		scrollpane.setBounds(25, 50, 950, 400);
		port_Label.setBounds(60, 20, 80, 30);
		port_Field.setBounds(130, 20, 150, 30);
		query_Button.setBounds(310, 20, 80, 30);
		
		//��ͷ
		header = new Vector<String>();
		header.add("������");
		header.add("��������");
		header.add("����������Ӧ��");
		header.add("��Ӧ����ϵ��");
		header.add("��ַ");
		header.add("�绰");
		header.add("�����˺�");
		header.add("��Ӧ��΢�ź�");
		//������ݾ���
        DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
        ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
		//�洢һ��һ���ı�����
		dataVector = new Vector<Vector<String>>();
		
		query_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//�����ݿ⣬�����ݿ��е������ϴ���Vector������
				
				try{
					Class.forName("com.mysql.jdbc.Driver");
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						//where v.vDate = ? and pi.piId = v.piId and pi.pCID = pc.pcId
						String sql = "select p.piId,p.piName, s.sName, s.sLinkman, s.sAddress, s.sPhone, s.sBankAc,"
								+ "s.sWeChat from portInfo p, supplier s where p.sId = s.sId and p.piId = ?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setObject(1, port_Field.getText());
						ResultSet rs = ps.executeQuery();
						String piId = null;
						String name = null;
						String sName = null;
						String sLinkman = null;
						String sAddress = null;
						String sPhone = null;
						String sBankAc = null;
						String sWeChat = null;
						//����ʹ���˸�if�жϣ����ѡ������û�г��������ۼ�¼�����
						if(!rs.next()){
							dataVector.removeAllElements();
							DefaultTableModel df = new DefaultTableModel(dataVector, header);
							table.setModel(df);
							JOptionPane.showMessageDialog(null, "�ܱ�Ǹ��֪ͨ����û���ҵ��ñ�Ŷ�Ӧ���ҵļ�¼");
							return;
						}else{
							piId = rs.getString(1);
							name = rs.getString(2);
							sName = rs.getString(3);
							sLinkman = rs.getString(4);
							sAddress = rs.getString(5);
							sPhone = rs.getString(6);
							sBankAc = rs.getString(7);
							sWeChat = rs.getString(8);
							Vector<String> data = new Vector<String>();
							data.add(piId);
							data.add(name);
							data.add(sName);
							data.add(sLinkman);
							data.add(sAddress);
							data.add(sPhone);
							data.add(sBankAc);
							data.add(sWeChat);
							dataVector.add(data);
							DefaultTableModel df = new DefaultTableModel(dataVector, header);
							table.setModel(df);
						}
						while(rs.next()){
							piId = rs.getString(1);
							name = rs.getString(2);
							sName = rs.getString(3);
							sLinkman = rs.getString(4);
							sAddress = rs.getString(5);
							sPhone = rs.getString(6);
							sBankAc = rs.getString(7);
							sWeChat = rs.getString(8);
							Vector<String> data = new Vector<String>();
							data.add(piId);
							data.add(name);
							data.add(sName);
							data.add(sLinkman);
							data.add(sAddress);
							data.add(sPhone);
							data.add(sBankAc);
							data.add(sWeChat);
							dataVector.add(data);
							DefaultTableModel df = new DefaultTableModel(dataVector, header);
							table.setModel(df);
						}
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		table.setRowHeight(30);
		//���ñ�ͷ������������
		table.getTableHeader().setReorderingAllowed(false);
		FitTableColumns(table);
		scrollpane.getViewport().add(table);
		this.add(port_Field);
		this.add(port_Label);
		this.add(query_Button);
		this.add(scrollpane);
		this.setVisible(true);
	}
	
	public static void FitTableColumns(JTable myTable){
		  JTableHeader header = myTable.getTableHeader();
		     int rowCount = myTable.getRowCount();
		     Enumeration columns = myTable.getColumnModel().getColumns();
		     while(columns.hasMoreElements()){
		         TableColumn column = (TableColumn)columns.nextElement();
		         int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
		         int width = (int)myTable.getTableHeader().getDefaultRenderer()
		                 .getTableCellRendererComponent(myTable, column.getIdentifier()
		                         , false, false, -1, col).getPreferredSize().getWidth();
		         for(int row = 0; row<rowCount; row++){
		             int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
		               myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
		             width = Math.max(width, preferedWidth);
		         }
		         header.setResizingColumn(column); // ���к���Ҫ
		         column.setWidth(width+myTable.getIntercellSpacing().width);
		     }
	}
	
	public static void main(String[] args) {
		new QueryPortBelongs();
	}
}
