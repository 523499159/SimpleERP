package com.second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.plugin.Chooser;
import com.pojo.Customer;
import com.pojo.EmpInfo;
import com.pojo.PortInfo;
/**
 * 
 * @author SiVan
 * @time 2017��4��27�� ����6:25:14
 * TODO	�޸����۵Ǽǣ������漰������޸�
 */
public class UpdateVendition extends JDialog{

	private JLabel count = new JLabel("����");
	private JLabel price = new JLabel("�۳�����");
	private JLabel date = new JLabel("����ʱ��");
	
	
	private JTextField count_Field = new JTextField();
	private JTextField singlePrice_Field = new JTextField();
	private JTextField date_Field = new JTextField();
	
	
	private JButton button =  new JButton("�޸�");
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "yourpassword";
	int number1 = 0;
	public UpdateVendition(final String portId, final JTable table, final Vector<Vector<String>> data, 
			final Vector<String> header, final int raw, final QueryVendition query) {
		// TODO Auto-generated constructor stub
		this.setTitle("�޸����ۼ�¼");
		this.setLayout(null);
		this.setSize(400, 280);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		Chooser choose = Chooser.getInstance();
		choose.register(date_Field);
		
		count.setBounds(30, 30, 80, 30);
		count_Field.setBounds(130, 30, 180, 30);
		price.setBounds(30, 90, 80, 30);
		singlePrice_Field.setBounds(130, 90, 180, 30);
		date.setBounds(30, 150, 80, 30);
		date_Field.setBounds(130, 150, 180, 30);
		button.setBounds(210, 210, 80, 30);
		this.add(count);
		this.add(count_Field);
		this.add(price);
		this.add(singlePrice_Field);
		this.add(date);
		this.add(date_Field);
		this.add(button);
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//�ȼӸ���棬Ȼ������޸ĵļ�ȥ  �������Ļ�����Ϣ��
				//�������۱���ֱ���޸�
				String vId = (String) table.getValueAt(raw, 0);
				String count = count_Field.getText();
				String price = singlePrice_Field.getText();
				Date date = null;
				SimpleDateFormat date1 = new SimpleDateFormat("yyy-MM-dd");
				try {
					date = date1.parse(date_Field.getText());
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "������Ϸ������ڸ�ʽ");
				}
				
				Connection conn = null;
				try{
					conn = DriverManager.getConnection(url, user, password);
					conn.setAutoCommit(false);
					String sql = "update vendition set vCount = ?, vPrice = ?, vDate = ? where vId = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					if("".equals(price)){
						JOptionPane.showMessageDialog(null, "�۸񲻿���Ϊ��");
						return;
					}
					if("".equals(count)){
						JOptionPane.showMessageDialog(null, "����������Ϊ��");
						return;
					}
					if("".equals(date)){
						JOptionPane.showMessageDialog(null, "���ڲ�����Ϊ��");
						return;
					}
					ps.setObject(1, count);
					ps.setObject(2, price);
					ps.setObject(3, date);
					ps.setObject(4, vId);
					ps.executeUpdate();
					
					String sq = "select piNumber from portinfo where piId = ?";
					PreparedStatement ps1 = conn.prepareStatement(sq);
					ps1.setObject(1, portId);
					System.out.println(portId);
					ResultSet rs = ps1.executeQuery();
					int number = 0;
					while(rs.next()){
						number = Integer.parseInt(rs.getString(1));
						System.out.println(number + "s");
					}
					String sql1 = "update portinfo set piNumber = ? where piId = ?";
					ps = conn.prepareStatement(sql1);
					int num = number - Integer.parseInt(count) + Integer.parseInt((String) table.getValueAt(raw, 5));
					System.out.println(Integer.parseInt((String) table.getValueAt(raw, 5)));
					if(num < 0){
						JOptionPane.showMessageDialog(null, "��治��");
						return;
					}
					ps.setObject(1, number - Integer.parseInt(count) + Integer.parseInt((String) table.getValueAt(raw, 5)));
					ps.setObject(2, portId);
					ps.executeUpdate();
					conn.commit();
					conn.setAutoCommit(true);
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
					UpdateVendition.this.dispose();
					query.dispose();
					new QueryVendition();
				}catch(Exception e1){
					try {
						conn.rollback();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ�������ת");
					e1.printStackTrace();
				}finally{
					try {
						conn.setAutoCommit(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		this.setVisible(true);
	}
	
	
}

