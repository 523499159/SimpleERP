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

public class AddCustomerZonePanel extends JPanel{

	private JLabel name_Label = new JLabel("�ͻ���������");
	private JTextField name_Field = new JTextField();
	private JButton addButton = new JButton("���");
	
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "yourpassword";
	public AddCustomerZonePanel() {
		// TODO Auto-generated constructor stub
		this.setLayout(null);
		name_Label.setBounds(50, 150, 100, 35);
		name_Field.setBounds(170, 150, 220, 35);
		name_Field.setHorizontalAlignment(JTextField.CENTER);
		addButton.setBounds(170, 255, 100, 35);
		
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String classifyName = name_Field.getText().trim();
				if("".equals(classifyName)){
					JOptionPane.showMessageDialog(null, "�ͻ��������Ʋ���Ϊ��");
					return;
				}
				try{
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sql = "select zName from zone";
						String sql1 = "insert into zone(zName,zStatus) values (?,\"1\")";
						PreparedStatement ps = conn.prepareStatement(sql);
						ResultSet rs = ps.executeQuery();
						while(rs.next()){
							if(classifyName.equals(rs.getString(1))){
								JOptionPane.showMessageDialog(null, "���ʧ�ܣ��ÿͻ������Ѿ����ڣ�");
								return;
							}
						}
						PreparedStatement ps1 = conn.prepareStatement(sql1);
						ps1.setObject(1, classifyName);
						int n = ps1.executeUpdate();
						if(n <= 0){
							JOptionPane.showMessageDialog(null, "���ʧ�ܣ��������ݿ�����Ƿ�����");
							return;
						}else{
							JOptionPane.showMessageDialog(null, "��ӳɹ���");
							name_Field.setText("");
						}
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		this.add(name_Label);
		this.add(name_Field);
		this.add(addButton);
		this.setVisible(true);
	}
}

