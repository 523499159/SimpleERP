package com.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.view.LogInView;
import com.view.MainView;
import com.view.MainView1;

		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��9�� ����7:02:40
		 * TODO    �޸�����
		 * 			1.�޸�֮ǰ�ж�������ǿ���Լ����볤��λ��
		 * 			2.�޸�ʱ�ж��¾������Ƿ��ظ����Լ���������ȷ�������Ƿ����
		 * 			3.�������⣺�޸ĳɹ�����ιرյ�ǰ�ģ���������
		 */
public class UpdatePasswordView extends JDialog{
	
	private JLabel oldpassword_Label = new JLabel("ԭʼ����");
	private JLabel newpassword_Label = new JLabel("������");
	private JLabel repeatpassword_Label = new JLabel("ȷ������");
	private JLabel newCheck_Label = new JLabel();
	private JLabel repeatCheck_Label = new JLabel();
	
	private JTextField oldpassword_Field = new JPasswordField();
	private JTextField newpassword_Field = new JPasswordField();
	private JTextField repeatpassword_Field = new JPasswordField();
	
	private JButton update_Button = new JButton("�޸�");

	//���ݿ�
	private String url = "jdbc:mysql://localhost:3306/erp";
	private String user = "root";
	private String password = "1018222wxw";
	public UpdatePasswordView(final String username,final MainView main) {
		// TODO Auto-generated constructor stub
		this.setTitle("�޸�����");
		this.setModal(true);
		this.setSize(400, 350);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		oldpassword_Label.setBounds(50, 50, 80, 35);
		oldpassword_Field.setBounds(150, 50, 180, 35);
		newpassword_Label.setBounds(50, 120, 80, 35);
		newpassword_Field.setBounds(150, 120, 180, 35);
		newCheck_Label.setBounds(360, 120, 30, 35);
		repeatpassword_Label.setBounds(50, 190, 80, 35);
		repeatpassword_Field.setBounds(150, 190, 180, 35);
		repeatCheck_Label.setBounds(360, 190, 30, 35);
		update_Button.setBounds(250, 260, 80, 35);
		
		this.add(oldpassword_Label);
		this.add(oldpassword_Field);
		this.add(newpassword_Label);
		this.add(newpassword_Field);
		this.add(newCheck_Label);
		this.add(repeatpassword_Label);
		this.add(repeatpassword_Field);
		this.add(repeatCheck_Label);
		this.add(update_Button);
		
		//��Ӽ��̼������¼�
		newpassword_Field.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				String newpassword = newpassword_Field.getText();
				
				//���볤��С��6λ
				if(newpassword.length() < 6){
					newCheck_Label.setText("��");
					return;
				}
				
				//������ȴ���6λ�����������ǿ�����ж�
				int num = 0;
				int character = 0;
				int other = 0;
				
				for (int i = 0; i < newpassword.length(); i++) {
					char ch = newpassword.charAt(i);
					if(ch >= '0' && ch <= '9')
						num = 1;
					else if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
						character = 1;
					else
						other = 1;
				}
				int result = other + num + character;
				if( result == 1 )
					newCheck_Label.setText("��");
				else if(result == 2)
					newCheck_Label.setText("��");
				else
					newCheck_Label.setText("ǿ");
				
			}
			
		});
		
		
		//���ȷ������ļ����¼�
		repeatpassword_Field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				String repeatpassword = repeatpassword_Field.getText();
				String newpassword = newpassword_Field.getText();
				if(repeatpassword.equals(newpassword))
					repeatCheck_Label.setText("��");
				else
					repeatCheck_Label.setText("��");
			}
		});
		
		//��Ӱ�ť�������¼�
		update_Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String oldpassword = oldpassword_Field.getText();
				String newpassword = newpassword_Field.getText();
				String repeatpassword = repeatpassword_Field.getText();
				
				//�����ж����볤���Ƿ�С�ڵ���6λ���֣�С�������������ֹ�������
				if(newpassword.length() < 6 )
				{
					newCheck_Label.setText("��");
					JOptionPane.showMessageDialog(null, "���볤�Ȳ���С��6λ");
					return;							//��Ϊ����С�ڵ���6λ��û��Ҫ�����ж���
				}
				
				
				if(!repeatpassword.equals(newpassword))
				{
					JOptionPane.showMessageDialog(null, "��������ȷ�����벻��ͬ�����Ժ����ԣ�");
					return;
				}
				
				if(oldpassword.equals(newpassword))
				{
					JOptionPane.showMessageDialog(null, "ԭʼ��������������ͬ���޸���Ч��");
					return;
				}
				try{
					Class.forName("com.mysql.jdbc.Driver"); 
					try(
						Connection conn = DriverManager.getConnection(url, user, password);
							){
						String sql = "update userinfo set uPass = ? where uPass = ? and uName = ?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setObject(1, newpassword);
						ps.setObject(2, oldpassword);
						ps.setObject(3, username);
						int n = ps.executeUpdate();
						if(n > 0)
						{
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
							UpdatePasswordView.this.dispose();
							main.dispose();
							new LogInView();
						}else{
							JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ����Ժ����ԣ�");
							return;
						}
					}
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "�������ݿ��Ƿ�������ת");
					e1.printStackTrace();
				}
			}
		});
		this.setVisible(true) ;
	}
}
