package com.first;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pojo.Suppplier;
import com.pojo.Zone;

		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��27�� ����8:03:16
		 * TODO	��ӿͻ�
		 */
public class AddCustomerPanel extends JPanel{

	private JLabel customer_Name = new JLabel("����");
	private JLabel customer_Zone = new JLabel("�ͻ���������");
	private JLabel customer_Phone = new JLabel("��ϵ��ʽ");
	private JLabel customer_WeChat = new JLabel("΢�ź�");
	private JLabel customer_Address = new JLabel("��ַ");
	private JTextField name_Field = new JTextField();
	private JComboBox<Zone> zone_Field = new JComboBox<Zone>();
	private JTextField address_Field = new JTextField();
	private JTextField phone_Field = new JTextField();
	private JTextField wechat_Field = new JTextField();
	
	private JButton button = new JButton("���");
	
	
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "yourpassword";
	public AddCustomerPanel() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		customer_Name.setBounds(30, 30, 80, 30);
		name_Field.setBounds(130, 30, 250, 30);
		customer_Zone.setBounds(30, 90, 80, 30);
		zone_Field.setBounds(130, 90, 250, 30);
		customer_Phone.setBounds(30, 150, 80, 30);
		phone_Field.setBounds(130, 150, 250, 30);
		customer_WeChat.setBounds(30, 210, 80, 30);
		wechat_Field.setBounds(130, 210, 250, 30);
		customer_Address.setBounds(30, 270, 80, 30);
		address_Field.setBounds(130, 270, 250, 30);
		button.setBounds(200, 330, 80, 30);
		
		
		try{
			try(
				Connection conn = DriverManager.getConnection(url, user, password);
					){
				String sql = "select zId,zName from zone where zStatus = \"1\"";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Zone zone = new Zone();
					zone.setZone(rs.getString(2));
					zone.setZoneId(rs.getString(1));
					zone_Field.addItem(zone);
				}
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = name_Field.getText();
				Zone zone = (Zone) zone_Field.getSelectedItem();
				String address = address_Field.getText();
				String phone = phone_Field.getText();
				String wechat = wechat_Field.getText().trim();
				
				if("".equals(name)){
					JOptionPane.showMessageDialog(null, "��������Ϊ�գ�����������");
					return;
				}
				try{
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sql = "insert into customer(zId, cName, cPhone, cWeChat, cAddress, cStatus) values (?,?,?,?,?,\"1\")";
		 				PreparedStatement ps  = conn.prepareStatement(sql);
		 				ps.setObject(1, zone.getZoneId());
		 				ps.setObject(2, name);
		 				ps.setObject(3, phone);
		 				ps.setObject(4, wechat);
		 				ps.setObject(5, address);
		 				int n = ps.executeUpdate();
		 				if(n <= 0 ){
		 					JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ�������ת��");
		 					return;
		 				}else{
		 					JOptionPane.showMessageDialog(null, "��ӳɹ���");
		 					name_Field.setText("");
		 					address_Field.setText("");
		 					phone_Field.setText("");
		 					wechat_Field.setText("");
		 				}
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		this.add(customer_Name);
		this.add(customer_Zone);
		this.add(customer_Phone);
		this.add(customer_WeChat);
		this.add(customer_Address);
		this.add(name_Field);
		this.add(zone_Field);
		this.add(address_Field);
		this.add(button);
		this.add(wechat_Field);
		this.add(phone_Field);
		this.setVisible(true);
	}
}

