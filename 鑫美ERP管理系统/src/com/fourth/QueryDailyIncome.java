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
		 * @time 2017��4��16�� ����10:37:27
		 * TODO	ÿ�������ѯ
		 */
public class QueryDailyIncome extends JDialog{

	private JLabel date_Label = new JLabel("����");
	private JTextField date_Field = new JTextField();
	
	
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
	private String password = "1018222wxw";
	
	Vector<String> header;
	final Vector<Vector<String>> dataVector;
	
	public QueryDailyIncome() {
		// TODO Auto-generated constructor stub
		this.setTitle("ÿ�������ѯ");
		this.setSize(800, 700);
		this.setResizable(false);
		this.setModal(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		scrollpane.setBounds(65, 100, 700, 500);
		date_Label.setBounds(100, 50, 80, 30);
		date_Field.setBounds(150, 50, 150, 30);
		query_Button.setBounds(310, 50, 80, 30);
		
		Chooser chooser  = Chooser.getInstance();
		chooser.register(date_Field);
		
		
		//��ͷ
		header = new Vector<String>();
		header.add("��������");
		header.add("������");
		header.add("������");
		header.add("�����ۼ�");
		header.add("�۳�����");
		header.add("���ۼ�");
		header.add("������");
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
				Date date1 = null;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = date_Field.getText();
				
				
				if ("".equals(date)) {
					JOptionPane.showMessageDialog(null, "��ȷ�����ڵ�������");
					return;
				}
				
				
				DefaultTableModel df = null;
				try {
					date1 = sdf.parse(date);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try{
					Class.forName("com.mysql.jdbc.Driver");
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						//where v.vDate = ? and pi.piId = v.piId and pi.pCID = pc.pcId
						String sql = "select pc.pcClassify, pi.piId, pi.piPrice, v.vPrice, v.vCount,(v.vPrice * v.vCount) as price, (v.vPrice * v.vCount - pi.piPrice * v.vCount) as profit from portclassify pc, vendition v, portinfo pi where v.vDate = ? and pi.piId = v.piId and pi.pCID = pc.pcId";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setObject(1, date1);
						ResultSet rs = ps.executeQuery();
						String pcClassify = null;
						String piId = null;
						String piPrice = null;
						String vPrice = null;
						String vCount = null;
						String price = null;
						String profit = null;
						
						//����ʹ���˸�if�жϣ����ѡ������û�г��������ۼ�¼�����
						if(!rs.next()){
							dataVector.removeAllElements();
							DefaultTableModel df1 = new DefaultTableModel(dataVector, header);
							table.setModel(df1);
							JOptionPane.showMessageDialog(null, "�ܱ�Ǹ��֪ͨ��������û���κ����ۼ�¼");
							return;
						}else{
							dataVector.removeAllElements();
							pcClassify = rs.getString(1);
							piId = rs.getString(2);
							piPrice = rs.getString(3);
							vPrice = rs.getString(4);
							vCount = rs.getString(5);
							price = rs.getString(6);
							profit = rs.getString(7);
							Vector<String> data = new Vector<String>();
							data.add(pcClassify);
							data.add(piId);
							data.add(piPrice);
							data.add(vPrice);
							data.add(vCount);
							data.add(price);
							data.add(profit);
							dataVector.add(data);
							df = new DefaultTableModel(dataVector, header);
							table.setModel(df);
						}
						while(rs.next()){
							pcClassify = rs.getString(1);
							piId = rs.getString(2);
							piPrice = rs.getString(3);
							vPrice = rs.getString(4);
							vCount = rs.getString(5);
							price = rs.getString(6);
							profit = rs.getString(7);
							Vector<String> data = new Vector<String>();
							data.add(pcClassify);
							data.add(piId);
							data.add(piPrice);
							data.add(vPrice);
							data.add(vCount);
							data.add(price);
							data.add(profit);
							dataVector.add(data);
							df = new DefaultTableModel(dataVector, header);
							table.setModel(df);
						}
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		scrollpane.getViewport().add(table);
		//���ñ�ͷ������������
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(30);
		FitTableColumns(table);
		
		this.add(date_Label);
		this.add(date_Field);
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
		new QueryDailyIncome();
	}
}
