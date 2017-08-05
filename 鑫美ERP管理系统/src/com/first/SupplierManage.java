package com.first;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
		 * 
		 * @author SiVan
		 * @time 2017��4��26�� ����10:11:17
		 * TODO	��Ӧ�����
		 */
public class SupplierManage extends JPanel{

	private JLabel supplier_Name = new JLabel("����");
	private JLabel supplier_LinkMan = new JLabel("��ϵ��");
	private JLabel supplier_Address = new JLabel("��ַ");
	private JLabel supplier_Phone = new JLabel("��ϵ��ʽ");
	private JLabel supplier_BankAc = new JLabel("�����˺�");
	private JLabel supplier_WeChat = new JLabel("΢�ź�");
	private JTextField name_Field = new JTextField();
	private JTextField linkman_Field = new JTextField();
	private JTextField address_Field = new JTextField();
	private JTextField phone_Field = new JTextField();
	private JTextField bankAc_Field = new JTextField();
	private JTextField wechat_Field = new JTextField();
	
	private JButton button = new JButton("���");
	
	
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "1018222wxw";
	public SupplierManage() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		supplier_Name.setBounds(30, 30, 80, 30);
		name_Field.setBounds(130, 30, 250, 30);
		supplier_LinkMan.setBounds(30, 90, 80, 30);
		linkman_Field.setBounds(130, 90, 250, 30);
		supplier_Address.setBounds(30, 150, 80, 30);
		address_Field.setBounds(130, 150, 250, 30);
		supplier_Phone.setBounds(30, 210, 80, 30);
		phone_Field.setBounds(130, 210, 250, 30);
		supplier_BankAc.setBounds(30, 270, 80, 30);
		bankAc_Field.setBounds(130, 270, 250, 30);
		button.setBounds(200, 390, 80, 30);
		wechat_Field.setBounds(130, 330, 250, 30);
		supplier_WeChat.setBounds(30, 330, 80, 30);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = name_Field.getText();
				String linkman = linkman_Field.getText();
				String address = address_Field.getText();
				String phone = phone_Field.getText();
				String bank = bankAc_Field.getText();
				String wechat = wechat_Field.getText().trim();
				
				if("".equals(name)){
					JOptionPane.showMessageDialog(null, "��������Ϊ�գ�����������");
					return;
				}
				if("".equals(address)){
					JOptionPane.showMessageDialog(null, "��Ӧ�̵�ַ����Ϊ�գ�����������");
					return;
				}
				if("".equals(linkman)){
					JOptionPane.showMessageDialog(null, "��ϵ�˲���Ϊ�գ�����������");
					return;
				}
				if("".equals(phone)){
					JOptionPane.showMessageDialog(null, "��ϵ��ʽ����Ϊ�գ�����������");
					return;
				}
				try{
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sql = "insert into supplier(sName, sLinkman, sAddress, sPhone, sBankAc, sWeChat, sStatus) values (?,?,?,?,?,?,\"1\")";
		 				PreparedStatement ps  = conn.prepareStatement(sql);
		 				ps.setObject(1, name);
		 				ps.setObject(2, linkman);
		 				ps.setObject(3, address);
		 				ps.setObject(4, phone);
		 				ps.setObject(5, bank);
		 				ps.setObject(6, wechat);
		 				int n = ps.executeUpdate();
		 				if(n <= 0 ){
		 					JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ�������ת��");
		 					return;
		 				}else{
		 					JOptionPane.showMessageDialog(null, "��ӳɹ���");
		 					linkman_Field.setText("");
		 					name_Field.setText("");
		 					phone_Field.setText("");
		 					bankAc_Field.setText("");
		 					address_Field.setText("");
		 					wechat_Field.setText("");
		 				}
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		this.add(name_Field);
		this.add(supplier_Name);
		this.add(supplier_LinkMan);
		this.add(linkman_Field);
		this.add(supplier_Address);
		this.add(address_Field);
		this.add(supplier_Phone);
		this.add(phone_Field);
		this.add(supplier_BankAc);
		this.add(bankAc_Field);
		this.add(button);
		this.add(wechat_Field);
		this.add(supplier_WeChat);
		this.setVisible(true);
	}
}
