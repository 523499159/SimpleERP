package com.second;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.pojo.PortInfo;
/**
		 * 
		 * @author SiVan
		 * @time 2017��4��27�� ����9:02:39
		 * TODO	���������ӿ��
		 */
public class AddInventoryDialog extends JDialog {

	
	private JLabel port = new JLabel("������");
	private JLabel portNumber = new JLabel("��������");

	private JComboBox<String> port_Field = new JComboBox<String>();
	private JTextField number = new JTextField();
	
	private JButton button = new JButton("���");
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "yourpassword";
	int number1 = 0;
	public AddInventoryDialog() {
		// TODO Auto-generated constructor stub
		this.setTitle("��ӿ��");
		this.setSize(350, 230);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		
		port.setBounds(30, 30, 80, 30);
		port_Field.setBounds(140, 30, 180, 30);
		portNumber.setBounds(30, 90, 80, 30);
		number.setBounds(140, 90, 180, 30);
		button.setBounds(210, 150, 80, 30);
		
		
		try{
			try(
				Connection conn = DriverManager.getConnection(url, user, password);
					){
				String sql = "select piId,piNumber from  portinfo where piStatus = \"1\" ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					port_Field.addItem(rs.getString(1));
				}
			}
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ���");
			e1.printStackTrace();
		}
		
		System.out.println(number1);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String number2 = number.getText();
				String portid = (String) port_Field.getSelectedItem();
				
				
				if("".equals(number2)){
					JOptionPane.showMessageDialog(null, "�����Ҫ���������ӵ���Ʒ����");
					return;
				}
				
				try{
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sq = "select piNumber from  portinfo where piStatus = \"1\" and piId = ?";
						PreparedStatement ps1 = conn.prepareStatement(sq);
						ps1.setObject(1, portid);
						ResultSet rs = ps1.executeQuery();
						while(rs.next()){
							number1 = Integer.parseInt(rs.getString(1));
						}
						String sql = "update portinfo  set piNumber = ? where piId = ? ";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setObject(1, number1 + Integer.parseInt(number2));
						ps.setObject(2, portid);
						int n = ps.executeUpdate();
						if(n <= 0 ){
							JOptionPane.showMessageDialog(null, "���ʧ�ܣ����Ժ�����");
							return;
						}else{
							JOptionPane.showMessageDialog(null, "��ӳɹ�");
							AddInventoryDialog.this.dispose();
						}
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ���");
					e1.printStackTrace();
				}
			}
		});
		this.add(port);
		this.add(port_Field);
		this.add(portNumber);
		this.add(number);
		this.add(button);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new AddInventoryDialog();
	}
}
