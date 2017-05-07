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

import com.google.common.base.Supplier;
import com.pojo.Suppplier;
/**
		 * 
		 * @author SiVan
		 * @time 2017��4��26�� ����10:11:41
		 * TODO	�޸ĺ�ɾ����Ӧ��
		 */
public class UpdateAndDeleteSupplierPanel extends JPanel{
	
	private JLabel supplierId = new JLabel("��Ӧ�̱��");
	private JLabel supplierName = new JLabel("����");
	private JLabel supplierLinkMan = new JLabel("��ϵ��");
	private JLabel supplierPhone = new JLabel("��ϵ��ʽ");
	private JLabel supplierBankAc = new JLabel("�����˺�");
	private JLabel supplierAddress = new JLabel("��Ӧ�̵�ַ");
	private JLabel supplierWeChat = new JLabel("��Ӧ��΢��");
	private JLabel supplier_Choose = new JLabel("��ѡ��Ӧ��");
	private JTextField id_Field = new JTextField();
	private JTextField linkman_Field = new JTextField();
	private JTextField phone_Field = new JTextField();
	private JTextField bankAc_Field = new JTextField();
	private JTextField address_Field = new JTextField();
	private JTextField name_Field = new JTextField();
	private JTextField wechat_Field = new JTextField();
	private JComboBox<Suppplier> Supplier = new JComboBox<Suppplier>();
	
	private JButton update = new JButton("�޸�");
	private JButton delete = new JButton("ɾ��");
	
	
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "yourpassword";
	public UpdateAndDeleteSupplierPanel() {
		// TODO Auto-generated constructor stub
		
		this.setLayout(null);
		supplierId.setBounds(30, 30, 80, 30);
		id_Field.setBounds(130, 30, 250, 30);
		supplierName.setBounds(30, 90, 80, 30);
		name_Field.setBounds(130, 90, 250, 30);
		supplierLinkMan.setBounds(30, 150, 80, 30);
		linkman_Field.setBounds(130, 150, 250, 30);
		supplierPhone.setBounds(30, 210, 80, 30);
		phone_Field.setBounds(130, 210, 250, 30);
		supplierBankAc.setBounds(30, 270, 80, 30);
		bankAc_Field.setBounds(130, 270, 250, 30);
		supplierAddress.setBounds(30, 330, 80, 30);
		address_Field.setBounds(130, 330, 250, 30);
		supplierWeChat.setBounds(30, 390, 80, 30);
		wechat_Field.setBounds(130, 390, 250, 30);
		supplier_Choose.setBounds(30, 450, 80, 30);
		Supplier.setBounds(130, 450, 200, 30);
		update.setBounds(130, 510, 80, 30);
		delete.setBounds(350, 510, 80, 30);
		
		
		
		
		try{
			try(
				Connection conn = DriverManager.getConnection(url, user, password);
					){
				String sql = "select sId,sName,sLinkman,sAddress,sPhone,sBankAc, sWeChat from supplier where sStatus = \"1\"";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					Suppplier supplier = new Suppplier();
					supplier.setSupplierAddress(rs.getString(4));
					supplier.setSupplierBankAc(rs.getString(6));
					supplier.setSupplierId(rs.getString(1));
					supplier.setSupplierLinkMan(rs.getString(3));
					supplier.setSupplierName(rs.getString(2));
					supplier.setSupplierPhone(rs.getString(5));
					supplier.setSupplierWeChat(rs.getString(7));
					Supplier.addItem(supplier);
				}
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
		
		Supplier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Suppplier su = (Suppplier) Supplier.getSelectedItem();
				id_Field.setText(su.getSupplierId());
				linkman_Field.setText(su.getSupplierLinkMan());
				phone_Field.setText(su.getSupplierPhone());
				bankAc_Field.setText(su.getSupplierBankAc());
				address_Field.setText(su.getSupplierAddress());
				name_Field.setText(su.getSupplierName());
				wechat_Field.setText(su.getSupplierWeChat());
			}
		});
		
		
		//���õ�һ��ʹ��ʱ���Ĭ��ֵ
		Suppplier su = (Suppplier) Supplier.getSelectedItem();
		id_Field.setText(su.getSupplierId());
		linkman_Field.setText(su.getSupplierLinkMan());
		phone_Field.setText(su.getSupplierPhone());
		bankAc_Field.setText(su.getSupplierBankAc());
		address_Field.setText(su.getSupplierAddress());
		name_Field.setText(su.getSupplierName());
		wechat_Field.setText(su.getSupplierWeChat());
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Suppplier su = (Suppplier) Supplier.getSelectedItem();
				String name = name_Field.getText();
				String sid = id_Field.getText();
				String linkman = linkman_Field.getText();
				String address = address_Field.getText();
				String bank = bankAc_Field.getText();
				String phone = phone_Field.getText();
				try{
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sql = "update supplier set sName = ?, sLinkman = ?,sAddress = ?, sPhone = ?,sBankAc = ?,sWeChat = ? where sId = ?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setObject(1, name);
						ps.setObject(2, linkman);
						ps.setObject(3, address);
						ps.setObject(4, phone);
						ps.setObject(5, bank);
						ps.setObject(6, wechat_Field.getText());
						ps.setObject(7, sid);
						System.out.println(sid);
						int n = ps.executeUpdate();
						System.out.println(n);
						if(n > 0){
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
							Supplier.removeItem(Supplier.getSelectedItem());
							String sql1 = "select sId,sName,sLinkman,sAddress,sPhone,sBankAc, sWeChat from supplier where sId = ?";
							PreparedStatement ps1 = conn.prepareStatement(sql1);
							ps1.setObject(1, su.getSupplierId());
							ResultSet rs = ps1.executeQuery();
							while(rs.next()){
								Suppplier su1 = new Suppplier();
								su1.setSupplierAddress(rs.getString(4));
								su1.setSupplierBankAc(rs.getString(6));
								su1.setSupplierId(rs.getString(1));
								su1.setSupplierLinkMan(rs.getString(3));
								su1.setSupplierName(rs.getString(2));
								su1.setSupplierPhone(rs.getString(5));
								su1.setSupplierWeChat(rs.getString(7));
								Supplier.addItem(su1);
							}
						}else{
							JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ���������");
							return;
						}
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Suppplier su = (Suppplier) Supplier.getSelectedItem();
				
				try{
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sql = "update supplier set sStatus = \"0\" where sName = ?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setObject(1, su.getSupplierName());
						int n = ps.executeUpdate();
						if(n > 0){
							JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
							Supplier.removeItem(Supplier.getSelectedItem());
						}else{
							JOptionPane.showMessageDialog(null, "�������ݿ�����Ƿ���������");
							return;
						}
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		this.add(supplierWeChat);
		this.add(wechat_Field);
		id_Field.setEditable(false);
		this.add(supplierId);
		this.add(id_Field);
		this.add(Supplier);
		this.add(supplierName);
		this.add(supplierLinkMan);
		this.add(linkman_Field);
		this.add(supplierPhone);
		this.add(phone_Field);
		this.add(name_Field);
		this.add(supplierBankAc);
		this.add(bankAc_Field);
		this.add(supplierAddress);
		this.add(address_Field);
		this.add(supplier_Choose);
		this.add(delete);
		this.add(update);
		this.setVisible(true);
	}
	
	
	
}
