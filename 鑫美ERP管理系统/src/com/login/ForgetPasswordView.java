package com.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.resource.spi.work.HintsContext;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��12�� ����10:13:29
		 * TODO �������룺�û���Ҫ�����û������Լ������֤������м�飬ͨ��֮�������������ã�Ȼ���ȥERP֮���޸�
		 * 				��Ҫ�ж��û��Ƿ�ΪԱ���Լ��Ƿ���ְ,����������ǰ���֤����Ҫʹ��������ʽ����ƥ��
		 */
public class ForgetPasswordView extends JDialog{
	
	private JLabel username_Label = new JLabel("�û���");
	private JTextField username_Field = new JTextField();
	private JLabel idcard_Label = new JLabel("���֤����");
	private JTextField idcard_Field = new JTextField();
	private JButton reset_Button = new JButton("����");
	
	//���ݿ�
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "1018222wxw";
	

	public ForgetPasswordView() {
		// TODO Auto-generated constructor stub
		this.setTitle("��������");
		this.setSize(400, 240);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setResizable(false);
		
		username_Label.setBounds(50, 40, 100, 30);
		username_Field.setBounds(180, 40, 150, 30);
		idcard_Label.setBounds(50, 100, 100, 30);
		idcard_Field.setBounds(180, 100, 150, 30);
		reset_Button.setBounds(190, 160, 100, 30);
		
		reset_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = username_Field.getText();
				if("".equals(username)){
					JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
					return;
				}
				
				
				//���֤��������ƥ��
				final String id = idcard_Field.getText();
				String regex = "^\\d{17}[0-9xX]$";
				Pattern pattern = Pattern.compile(regex);
				Matcher match = pattern.matcher(id);
				if(!match.find()){
					JOptionPane.showMessageDialog(null, "�������֤�����ʽ�Ƿ���ȷ");
					return;
				}
				
				
				try{
					Class.forName("com.mysql.jdbc.Driver");
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sql = "select e.eId from employee e, userinfo u WHERE e.eStatus = '1' and e.eCard = ?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setObject(1, id);
						ResultSet rs = ps.executeQuery();
						if(rs.next())
						{
							String sql2 = "UPDATE  userinfo SET uPass = '123456' where uName = ?";
							ps = conn.prepareStatement(sql2);
							ps.setObject(1, username);
							int n = ps.executeUpdate();
							if(n <= 0){
								JOptionPane.showMessageDialog(null, "�����û����������");
								return;
							}
							JOptionPane.showMessageDialog(null, "���������ѱ�����Ϊ123456��Ϊ�˰�ȫ�������������ϵͳ������޸�");
							ForgetPasswordView.this.dispose();
						}
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "�������ݿ��Ƿ�������ת");
					e1.printStackTrace();
				}
			}
		});
		
		
		this.add(username_Label);
		this.add(username_Field);
		this.add(idcard_Label);
		this.add(idcard_Field);
		this.add(reset_Button);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ForgetPasswordView();
	}
}
