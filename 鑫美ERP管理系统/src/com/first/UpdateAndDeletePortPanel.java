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

import com.pojo.PortClassifyInfo;
import com.pojo.PortInfo;
import com.pojo.Suppplier;
		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��28�� ����10:17:08
		 * TODO	�޸���ɾ��������
		 */
public class UpdateAndDeletePortPanel extends JPanel{
	private JLabel portId_Label = new JLabel("������");
	private JLabel portClassify_Label = new JLabel("������������");
	private JLabel supplierName_Label = new JLabel("������Ӧ��");
	private JLabel portName_Label = new JLabel("��������");
	private JLabel portPrice_Label = new JLabel("������");
	private JLabel portMaking_Label = new JLabel("����");
	private JLabel portNumber_Label = new JLabel("��������");
	
	private JComboBox<PortInfo> portId_Field = new JComboBox<PortInfo>();
	private JComboBox<PortClassifyInfo> portClassify_Field = new JComboBox<PortClassifyInfo>();
	private JComboBox<Suppplier> supplier = new JComboBox<Suppplier>();
	private JTextField portName_Field = new JTextField();
	private JTextField portPrice_Field = new JTextField();
	private JTextField portMaking_Field = new JTextField();
	private JTextField portNumber_Field = new JTextField();
	
	
	private JButton update = new JButton("�޸�");
	private JButton delete = new JButton("ɾ��");
	
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "yourpassword";
	public UpdateAndDeletePortPanel() {
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
		update.setBounds(120, 525, 80, 35);
		delete.setBounds(260, 525, 80, 35);
		portId_Field.setEditable(false);
		
		try{
			try(
					Connection conn = DriverManager.getConnection(url, user, password);
						){
					String sql = "select sId,sName from supplier";
					String sql2 = "select pcId, pcClassify from portclassify";
					String sql3 = "select piId from portinfo";
					PreparedStatement ps = conn.prepareStatement(sql);
					PreparedStatement ps2 = conn.prepareStatement(sql2);
					PreparedStatement ps3 = conn.prepareStatement(sql3);
					ResultSet rs = ps.executeQuery();
					ResultSet rs2 = ps2.executeQuery();
					ResultSet rs3 = ps3.executeQuery();
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
					while(rs3.next()){
						PortInfo port = new PortInfo();
						port.setPortId(rs3.getString(1));
						portId_Field.addItem(port);
					}
			}
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ�������ת");
			e1.printStackTrace();
		}
		
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = portName_Field.getText().trim();
				String making = portMaking_Field.getText().trim();
				String number2 = portNumber_Field.getText();
				String price = portPrice_Field.getText();
				if("".equals(number2)){
					JOptionPane.showMessageDialog(null, "�����Ҫ����Ʒ��Ϣ����ӵ���Ʒ����");
					return;
				}
				if("".equals(price)){
					JOptionPane.showMessageDialog(null, "�����۲�����Ϊ��");
					return;
				}
				
				int number3;
				double price1;
				try{
					number3 = Integer.parseInt(number2);
					price1 = Double.parseDouble(price);
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "����Ƿ�");
					return;
				}
				
				try{
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
								){
						String sql = "update portinfo set pcId = ?, sId = ?, piName = ?,piPrice = ?,piMaking = ?,piNumber = ?  where piId = ?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setObject(1, ((PortClassifyInfo)portClassify_Field.getSelectedItem()).getClassifyId());
						ps.setObject(2, ((Suppplier)supplier.getSelectedItem()).getSupplierId());
						ps.setObject(3, name);
						ps.setObject(4, price1);
						ps.setObject(5, making);
						ps.setObject(6, number3);
						ps.setObject(7, ((PortInfo)portId_Field.getSelectedItem()).getPortId());
						int n1 = ps.executeUpdate();
						if(n1 <= 0){
							JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ����Ժ�����");
							return;
						}else{
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
							portName_Field.setText("");
							portPrice_Field.setText("");
							portMaking_Field.setText("");
							portNumber_Field.setText("");
						}
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ�������ת");
					e1.printStackTrace();
				}
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(null, "��ע�⣺ɾ��������Ϣ֮�󣬹��ڸû���ļ�¼Ҳ����һ��ɾ��", "����", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if(n == 0){
					try{
						try(
							Connection conn = DriverManager.getConnection(url, user, password);
									){
							String sql = "update portinfo set piStatus = \"0\" where piId = ?";
							PreparedStatement ps = conn.prepareStatement(sql);
							ps.setObject(1, ((PortInfo)portId_Field.getSelectedItem()).getPortId());
							int n1 = ps.executeUpdate();
							if(n1 <= 0){
								JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ����Ժ�����");
								return;
							}else{
								JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
								
								try{
									portId_Field.removeItem(portId_Field.getSelectedItem());
								}catch(Exception e2){
									JOptionPane.showMessageDialog(null, "�������Ѿ�û�л�����Ϣ�ˣ��뼰ʱ���");
									return;
								}
							}
						}
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ�������ת");
						e1.printStackTrace();
					}
					
				}
				
				
			}
		});
		
		this.add(update);
		this.add(delete);
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
