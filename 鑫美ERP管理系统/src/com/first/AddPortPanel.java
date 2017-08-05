package com.first;

import java.awt.Button;
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

import com.pojo.PortClassifyInfo;
import com.pojo.Suppplier;
		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��28�� ����10:16:07
		 * TODO	��ӻ��裬���ڻ�����Ϣ������Ļ������У�����ͨ����������������
		 */
public class AddPortPanel extends JPanel{

	
	private JLabel portId_Label = new JLabel("������");
	private JLabel portClassify_Label = new JLabel("������������");
	private JLabel supplierName_Label = new JLabel("������Ӧ��");
	private JLabel portName_Label = new JLabel("��������");
	private JLabel portPrice_Label = new JLabel("������");
	private JLabel portMaking_Label = new JLabel("����");
	private JLabel portNumber_Label = new JLabel("��������");
	
	private JTextField portId_Field = new JTextField();
	private JComboBox<PortClassifyInfo> portClassify_Field = new JComboBox<PortClassifyInfo>();
	private JComboBox<Suppplier> supplier = new JComboBox<Suppplier>();
	private JTextField portName_Field = new JTextField();
	private JTextField portPrice_Field = new JTextField();
	private JTextField portMaking_Field = new JTextField();
	private JTextField portNumber_Field = new JTextField();
	
	
	private JButton add = new JButton("���");
	
	
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "1018222wxw";
	public AddPortPanel() {
		// TODO Auto-generated constructor stub
		
		this.setLayout(null);
		portId_Label.setBounds(35, 35, 100, 35);
		portId_Field.setBounds(165, 35, 180, 35);
		portClassify_Label.setBounds(35, 105, 100, 35);
		portClassify_Field.setBounds(165, 105, 180, 35);
		supplierName_Label.setBounds(35, 175, 100, 35);
		supplier.setBounds(165, 175, 180, 35);
		portName_Label.setBounds(35, 245, 100, 35);
		portName_Field.setBounds(165, 245, 180, 35);
		portPrice_Label.setBounds(35, 315, 100, 35);
		portPrice_Field.setBounds(165, 315, 180, 35);
		portMaking_Label.setBounds(35, 385, 100, 35);
		portMaking_Field.setBounds(165, 385, 180, 35);
		portNumber_Label.setBounds(35, 455, 100, 35);
		portNumber_Field.setBounds(165, 455, 180, 35);
		add.setBounds(260, 525, 80, 35);
		
		
		
		try{
			try(
				Connection conn = DriverManager.getConnection(url, user, password);
					){
				String sql = "select sId,sName from supplier";
				String sql2 = "select pcId, pcClassify from portclassify";
				PreparedStatement ps = conn.prepareStatement(sql);
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ResultSet rs = ps.executeQuery();
				ResultSet rs2 = ps2.executeQuery();
				while(rs.next()){
					Suppplier supplier1 = new Suppplier();
					supplier1.setSupplierId(rs.getString(1));
					supplier1.setSupplierName(rs.getString(2));
					supplier.addItem(supplier1);
				}
				while(rs2.next()){
					PortClassifyInfo classify = new PortClassifyInfo();
					classify.setClassifyId(rs2.getString(1));
					classify.setClassifyName(rs2.getString(2));
					portClassify_Field.addItem(classify);
				}
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String id = portId_Field.getText();
				String classifyId = ((PortClassifyInfo)portClassify_Field.getSelectedItem()).getClassifyId();
				String sid = ((Suppplier)supplier.getSelectedItem()).getSupplierId();
				String name = portName_Field.getText().trim();;
				String making = portMaking_Field.getText().trim();
				String price = portPrice_Field.getText().trim();
				String number = portNumber_Field.getText().trim();

				if("".equals(id)){
					JOptionPane.showMessageDialog(null, "�����Ų�����Ϊ��");
					return;
				}
				if("".equals(classifyId)){
					JOptionPane.showMessageDialog(null, "�������಻����Ϊ��");
					return;
				}
				if("".equals(price)){
					JOptionPane.showMessageDialog(null, "�����۲�����Ϊ��");
					return;
				}
				if("".equals(sid)){
					JOptionPane.showMessageDialog(null, "����������Ӧ�̲�����Ϊ��");
					return;
				}
				
				double price1;
				int number1;
				try{
					 price1 = Double.parseDouble(price);
					 number1 = Integer.parseInt(number);
				}catch(Exception e2){
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "ȷ��������۸��ʽ��ȷ");
					return;
				}
				
				
				if(price1 <= 0 &&  number1 <= 0){
					JOptionPane.showMessageDialog(null, "����Ƿ�");
					return;
				}
				try{
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sql = "select piId from portinfo";
						PreparedStatement ps = conn.prepareStatement(sql);
						ResultSet rs = ps.executeQuery();
						while(rs.next()){
							if(id.equals(rs.getString(1))){
								JOptionPane.showMessageDialog(null, "�Ѵ�����ͬ�����ŵĻ���");
								return;
							}
						}
						String sql1 = "insert into portinfo(piId,pcId,sId,piName,piPrice,piMaking,piNumber,piStatus) values (?,?,?,?,?,?,?,\"1\")";
						PreparedStatement ps1 = conn.prepareStatement(sql1);
						ps1.setObject(1, id);
						ps1.setObject(2, classifyId);
						ps1.setObject(3, sid);
						ps1.setObject(4, name);
						ps1.setObject(5, price1);
						ps1.setObject(6, making);
						ps1.setObject(7, number1);
						int n = ps1.executeUpdate();
						if(n <= 0){
							JOptionPane.showMessageDialog(null, "���ʧ�ܣ��������ݿ�����Ƿ�����");
							return;
						}else{
							JOptionPane.showMessageDialog(null, "��ӳɹ���");
							  portId_Field.setText("");
							  portName_Field.setText("");
							  portPrice_Field.setText("");
							  portMaking_Field.setText("");
							  portNumber_Field.setText("");
						}
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		
		
		
		
		this.add(add);
		this.add(portNumber_Field);
		this.add(portNumber_Label);
		this.add(portMaking_Field);
		this.add(portMaking_Label);
		this.add(portPrice_Field);
		this.add(portPrice_Label);
		this.add(portName_Field);
		this.add(portName_Label);
		this.add(supplier);
		this.add(supplierName_Label);
		this.add(portClassify_Field);
		this.add(portClassify_Label);
		this.add(portId_Label);
		this.add(portId_Field);
		
		this.setVisible(true);
	}
	
	
}
