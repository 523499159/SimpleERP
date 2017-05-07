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
	/**
	 * 
	 * @author SiVan
	 * @time 2017��4��24�� ����10:23:32
	 * TODO	��������޸ĺ�ɾ��
	 */
public class UpdateAndDeletePortClassifyPanel extends JPanel{

	private JLabel classifyName = new JLabel("���������");
	private JLabel classifyId = new JLabel("�������");
	private JLabel classifyLabel = new JLabel("��ѡ�������");
	
	private JComboBox<PortClassifyInfo> classifyBox = new JComboBox<PortClassifyInfo>();
	
	private JTextField classifyName_Field = new JTextField();
	private JTextField classifyId_Field = new JTextField();
	
	private JButton update = new JButton("�޸�");
	private JButton delete = new JButton("ɾ��");
	
	
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "yourpassword";
	public UpdateAndDeletePortClassifyPanel() {
		// TODO Auto-generated constructor stub
		this.setToolTipText("���������ӣ��޸ĺ�ɾ����");
		this.setLayout(null);
		classifyName.setBounds(35, 105, 100, 35);
		classifyName_Field.setBounds(155, 105, 220, 35);
		classifyId.setBounds(35, 35, 220, 35);
		classifyId_Field.setBounds(155, 35, 220, 35);
		classifyLabel.setBounds(35, 175, 100, 35);
		classifyBox.setBounds(155, 175, 220, 35);
		update.setBounds(60, 245, 80, 35);
		delete.setBounds(310, 245, 80, 35);
		
		classifyId_Field.setEditable(false);
		try{
			try(
				Connection conn = DriverManager.getConnection(url, user, password);
					){
				String sql = "select pcId,pcClassify from portclassify where pcStatus = \"1\"";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					PortClassifyInfo portclassify = new PortClassifyInfo();
					portclassify.setClassifyId(rs.getString(1));
					portclassify.setClassifyName(rs.getString(2));
					classifyBox.addItem(portclassify);
				}
			}
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
		
		PortClassifyInfo portclassify = (PortClassifyInfo) classifyBox.getSelectedItem();
		classifyName_Field.setText(portclassify.getClassifyName());
		classifyId_Field.setText(portclassify.getClassifyId());
		
		
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				int n = JOptionPane.showConfirmDialog(null, "��ע�⣺ɾ����������֮�󣬶�Ӧ�Ļ���Ҳ����һ��ɾ��������", "����", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
				if(n == 0){
					try{
						try(
							Connection conn = DriverManager.getConnection(url, user, password);
								){
							String sql = "update portclassify set pcStatus = \"0\" where pcId = ?";
							PreparedStatement ps = conn.prepareStatement(sql);
							ps.setObject(1, ((PortClassifyInfo)classifyBox.getSelectedItem()).getClassifyId());
							int n1 = ps.executeUpdate();
							if(n1 > 0){
								JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
								try{
									classifyBox.removeItem(classifyBox.getSelectedItem());
								}catch(Exception e2){
									JOptionPane.showMessageDialog(null, "�������Ѿ�û�л������࣬�뼰ʱ���");
									return;
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
			}
		});
		
		
		classifyBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PortClassifyInfo portclassify = (PortClassifyInfo) classifyBox.getSelectedItem();
				classifyName_Field.setText(portclassify.getClassifyName());
				classifyId_Field.setText(portclassify.getClassifyId());
			}
		});
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = classifyName_Field.getText().trim();
				if("".equals(name)){
					JOptionPane.showMessageDialog(null, "�Բ��𣬻������Ʋ���Ϊ�գ�");
					return;
				}
				try{
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sql = "update portclassify set pcClassify = ? where pcId = ?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setObject(1, name);
						String id = ((PortClassifyInfo)classifyBox.getSelectedItem()).getClassifyId();
						ps.setObject(2, ((PortClassifyInfo)classifyBox.getSelectedItem()).getClassifyId());
						int n1 = ps.executeUpdate();
						if(n1 > 0){
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
							try{
								classifyBox.removeItem(classifyBox.getSelectedItem());
							}catch(Exception e2){
								JOptionPane.showMessageDialog(null, "�������Ѿ�û�л������࣬�뼰ʱ���");
								return;
							}
							PortClassifyInfo classify = new PortClassifyInfo();
							classify.setClassifyId(id);
							classify.setClassifyName(name);
							classifyBox.addItem(classify);
							PortClassifyInfo portclassify = (PortClassifyInfo) classifyBox.getSelectedItem();
							classifyName_Field.setText(portclassify.getClassifyName());
							classifyId_Field.setText(portclassify.getClassifyId());
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
		this.add(classifyName);
		this.add(classifyName_Field);
		this.add(classifyId);
		this.add(classifyId_Field);
		this.add(classifyLabel);
		this.add(classifyBox);
		this.add(update);
		this.add(delete);
		this.setVisible(true);
	}
}
