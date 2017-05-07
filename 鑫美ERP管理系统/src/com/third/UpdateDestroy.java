package com.third;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.plugin.Chooser;
		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��19�� ����5:32:31
		 * TODO	������״̬�������𻵱�Id�Լ��𻵱�����д��״̬��������ֻ���ڴ������״̬�²ſ����޸�
		 */
public class UpdateDestroy extends JDialog{

	private JLabel 	status_Label = new JLabel("����״̬");
	private JLabel 	time_Label = new JLabel("��ʱ��");
	private JLabel 	date_Label = new JLabel("��ʱ��");
	private JComboBox<String> status_Field = new JComboBox<String>();
	private JTextField date_Field = new JTextField();
	private JButton update_Button = new JButton("�޸�");
	private JComboBox<String> time_List = new JComboBox<String>();
	
	//��װ���ݿ�ʹ�õ���������
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "yourpassword";
	
	public UpdateDestroy(final JTable table, final int row, final Vector<String> header, 
			final String classify, final String name, final String portId){
		// TODO Auto-generated constructor stub
		this.setSize(400, 260);
		this.setLayout(null);
		this.setTitle("���»���");
		this.setModal(true);
		this.setLocationRelativeTo(null);
		status_Label.setBounds(40, 40, 80, 30);
		time_Label.setBounds(40, 90, 80, 30);
		date_Label.setBounds(40, 140, 80, 30);
		update_Button.setBounds(213, 185, 93, 30);
		status_Field.setBounds(158, 40, 148, 30);
		time_List.setBounds(158, 90, 148, 30);
		date_Field.setBounds(158, 140, 148, 30);
		//status_Field.setEditable(false);  					//��Ϊ�����ݿ������������������Բ����޸ģ��Ӷ����ɱ༭
		Chooser chooser = Chooser.getInstance();
		chooser.register(date_Field);
		
		time_List.addItem("����");
		time_List.addItem("����");
		status_Field.addItem("������");
		status_Field.addItem("���޸�");
		status_Field.addItem("����");
		
		this.add(status_Label);
		this.add(time_Label);
		this.add(date_Label);
		this.add(update_Button);
		this.add(status_Field);
		this.add(time_List);
		this.add(date_Field);
		
		
		update_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String status = (String) status_Field.getSelectedItem();
				String time = (String) time_List.getSelectedItem();
				String date = date_Field.getText();
				
				if("".equals(status)){
					JOptionPane.showMessageDialog(null, "����״̬����Ϊ��");
					return;
				}
				if("".equals(time)){
					JOptionPane.showMessageDialog(null, "��ʱ�ڲ���Ϊ��");
					return;
				}
				if("".equals(date)){
					JOptionPane.showMessageDialog(null, "�����ڲ���Ϊ��");
					return;
				}
				
				int status1;
				if("������".equals(status)){
					 status1 = 0;
				}else if("���޸�".equals(status)){
					 status1 = 1;
				}else{
					 status1 = -1;
				}
				
				int time1;
				if("����".equals(time)){
					time1 = 0;
				}else{
					time1 = 1;
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dDate = null;
				try {
					dDate = sdf.parse(date);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
				
//				System.out.println(dDate + " " + status1 + " " + " " + time1);
				try{
					Class.forName("com.mysql.jdbc.Driver");
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sql = "update  destroy set dStatus = ?, dTime = ?, dWay = ? where dId = ? and dStatus = \"0\"";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setObject(1, status1);
						ps.setObject(2, dDate);
						ps.setObject(3, time1);
						ps.setObject(4, table.getValueAt(row, 0));
						int n = ps.executeUpdate();
						String sql3 = "SELECT pc.pcid, p.piId, p.piNumber from portClassify pc, portinfo p where pc.pcClassify = ? and p.piId = ? and p.piName = ? ";
						PreparedStatement ps3 = conn.prepareStatement(sql3);
						ps3.setObject(1, classify);
						ps3.setObject(2, portId);
						ps3.setObject(3, name);
						ResultSet rs = ps3.executeQuery();
						String pcId = null;
						String piid = null;
						String piNumber = null;
						while(rs.next()){
							pcId = rs.getString(1);
							piid = rs.getString(2);
							piNumber = rs.getString(3);
						}
						String sql2 = "Update portinfo set piNumber = ? where piId = ? and piName = ? and pcId = ?";
						if(n <= 0)
						{
							JOptionPane.showMessageDialog(null, "�޸�ʧ��");
							return;
						}else{
							PreparedStatement ps4 = conn.prepareStatement(sql2);
							ps4.setObject(1, Integer.parseInt(piNumber) + 1);
							ps4.setObject(2, piid);
							ps4.setObject(3, name);
							ps4.setObject(4, pcId);
							int n1 = ps4.executeUpdate();
							if(n1 <= 0){
								JOptionPane.showMessageDialog(null, "����ʧ��");
								return;
							}else{
								JOptionPane.showMessageDialog(null, "���³ɹ�");
								String sql1 = "select d.dId, p.piId, p.piName, d.dWay, d.dTime, d.dStatus "
											+ "from destroy d, portinfo p where p.piId = d.piId";
									PreparedStatement ps1 = conn.prepareStatement(sql1);
									ResultSet rs2 = ps1.executeQuery();
									String dId = null;
									String piId = null;
									String piName = null;
									String dWay = null;
									String dTime = null;
									String dStatus = null;
									while(rs2.next())
									{
										dId = rs2.getString(1);
										piId = rs2.getString(2);
										piName = rs2.getString(3);
										dWay = rs2.getString(4);
										dTime = rs2.getString(5);
										dStatus = rs2.getString(6);
										Vector<String> data = new Vector<String>();
										if("0".equals(dStatus)){
											dStatus = "������";
										}else if("1".equals(dStatus)){
											dStatus = "���޸�";
										}else{
											dStatus = "����";
										}
										
										if("0".equals(dWay)){
											dWay = "����";
										}else{
											dWay = "����";
										}
										data.add(dId);
										data.add(piId);
										data.add(piName);
										data.add(dWay);
										data.add(dTime);
										data.add(dStatus);
										dataVector.add(data);
										DefaultTableModel df = new DefaultTableModel(dataVector, header);
										table.setModel(df);
										FitTableColumns(table);
									}
							}
						}
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "������Ϣ�Ƿ�����");
					e1.printStackTrace();
				}
			}
		});
		
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
	

}
