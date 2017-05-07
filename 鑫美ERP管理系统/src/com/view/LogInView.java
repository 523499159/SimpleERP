package com.view;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.security.auth.login.LoginContext;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.login.ForgetPasswordView;
import com.login.ValidCode;
import com.sun.awt.AWTUtilities;

public class LogInView  {
	private Point pressedPoint;
	private JFrame frame;
	private JLabel logo_Label = new JLabel("��������ERP����ϵͳ");
	private JLabel username_Label = new JLabel("�û���");
	private JLabel password_Label = new JLabel("����");
	
	private JTextField username_Field = new JTextField();
	private JTextField password_Field = new JPasswordField();
	private JLabel valid_Label = new JLabel("��֤��");
	private JTextField valid_Field = new JTextField();
	
	private JLabel forgetPassword_Label = new JLabel("��������?");
	
	private String Url = "jdbc:mysql://localhost:3306/erp";
	private String User = "root";
	private String Password = "yourpassword";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					LogInView window = new LogInView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogInView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(350, 344);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final ValidCode vcode = new ValidCode();
		vcode.setLocation(250, 180);
		
		logo_Label.setBounds(20, 0, 320, 30);
		logo_Label.setFont(new Font("���Ŀ���", Font.BOLD, 28));
		username_Label.setBounds(80, 80, 80, 35);
		username_Label.setFont(new Font("���Ŀ���", Font.BOLD, 15));
		password_Label.setBounds(80, 140, 80, 35);
		password_Label.setFont(new Font("���Ŀ���", Font.BOLD, 18));
		username_Field.setBounds(150, 80, 160, 35);
		password_Field.setBounds(150, 140, 160, 35);
		valid_Label.setBounds(80, 200, 80, 35);
		valid_Label.setFont(new Font("���Ŀ���", Font.BOLD, 18));
		valid_Field.setBounds(150, 200, 80, 35);
		vcode.setLocation(235, 200);
		forgetPassword_Label.setBounds(180, 255, 120, 30);
		forgetPassword_Label.setFont(new Font("����", Font.BOLD, 18));
		forgetPassword_Label.setForeground(Color.blue);
		
		frame.add(logo_Label);
		frame.add(username_Label);
		frame.add(password_Label);
		frame.add(username_Field);
		frame.add(password_Field);
		frame.add(valid_Label);
		frame.add(valid_Field);
		frame.add(forgetPassword_Label);
		frame.add(vcode);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 350, 344);
		lblNewLabel.setIcon(new ImageIcon("image\\loginbackground.png"));
		frame.getContentPane().add(lblNewLabel);
		
		final JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 284, 350, 60);
		ImageIcon icon = new ImageIcon("image\\login_button.png");
		icon.setImage(icon.getImage().getScaledInstance(350, 60,Image.SCALE_DEFAULT));
		lblNewLabel_1.setIcon(icon);
		frame.getContentPane().add(lblNewLabel_1);
		
		final JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(285, -15, 65, 78);
		lblNewLabel_2.setIcon(new ImageIcon("image\\login_close.png"));
		frame.getContentPane().add(lblNewLabel_2);
//		AWTUtilities.setWindowOpacity(this, value / 100f);// ʹ�û���ֵ�ı䴰��͸����
		AWTUtilities.setWindowOpaque(frame, false);  
		
		//���ô����ƶ�
		frame.addMouseMotionListener(new MouseMotionAdapter() {
	            @Override
	            public void mouseDragged(MouseEvent e) {
	            	Point point = e.getPoint();// ��ȡ��ǰ����		,�õ��������λ������ڴ��������
	                Point locationPoint = frame.getLocation();// ��ȡ��������
	                int x = locationPoint.x + point.x - pressedPoint.x;// �����ƶ����������
	                int y = locationPoint.y + point.y - pressedPoint.y;
	                frame.setLocation(x, y);// �ı䴰��λ��
	            }
	        });
	    frame.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	            	pressedPoint = e.getPoint();// ��¼�������
	            }
	        });
		
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			
			
			boolean pr = true;
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				ImageIcon icon = new ImageIcon("image\\login_button_pressed.png");
				icon.setImage(icon.getImage().getScaledInstance(350, 60,Image.SCALE_DEFAULT));
				lblNewLabel_1.setIcon(icon);
				
				String username = username_Field.getText();
				String password = password_Field.getText();
				String valid = valid_Field.getText();
				//���ȼ���û����������Ƿ�Ϊ��
				if(username.equals("")){
					JOptionPane.showConfirmDialog(null, "�û�������Ϊ��", "����", 
							JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(password.equals("")){
					JOptionPane.showConfirmDialog(null, "���벻��Ϊ��", "����", 
							JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(valid.equals("")){
					JOptionPane.showConfirmDialog(null, "��֤�벻��Ϊ��", "����", 
							JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//������֤�벻���ִ�Сд
				if(!valid.equalsIgnoreCase(vcode.getCode())){
					JOptionPane.showConfirmDialog(null, "��֤�����", "����", 
							JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//ִ��JDBC
				try{
					Class.forName("com.mysql.jdbc.Driver");
					try(
						Connection conn = DriverManager.getConnection(Url, User, Password);
							){
						//����Ա���Ƿ���ְ���Լ��û�������������ж�
						String sql = "SELECT u.uName, u.uPass, u.uRole from userinfo u, employee e where "
								+ "u.uName = ? and u.uPass = ? and e.eStatus = \"1\" and u.eId = e.eId";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setObject(1, username);
						ps.setObject(2, password);
						ResultSet rs = ps.executeQuery();
						if(!rs.next()){
							JOptionPane.showMessageDialog(null, "�û���������������Ժ�����");
							return;
						}else{
							JOptionPane.showMessageDialog(null, "��¼�ɹ�");
							frame.setVisible(true);
							frame.dispose();
							new MainView1(username,rs.getString(3));						//����ڶ���������Ȩ�ޣ�1�ǹ���Ա��0���û�
						}
					}
				}catch(Exception e1){
					e1.printStackTrace();
					JOptionPane.showConfirmDialog(null, "����MySql�����Ƿ����������߶˿ڳ�ͻ", "����", 
							JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			}
			
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(pr){
					ImageIcon icon = new ImageIcon("image\\login_button_rover.png");
					icon.setImage(icon.getImage().getScaledInstance(350, 60,Image.SCALE_DEFAULT));
					lblNewLabel_1.setIcon(icon);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				ImageIcon icon = new ImageIcon("image\\login_button.png");
				icon.setImage(icon.getImage().getScaledInstance(350, 60,Image.SCALE_DEFAULT));
				lblNewLabel_1.setIcon(icon);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("shubiao");
				ImageIcon icon = new ImageIcon("image\\login_button_rover.png");
				icon.setImage(icon.getImage().getScaledInstance(350, 60,Image.SCALE_DEFAULT));
				lblNewLabel_1.setIcon(icon);
			}
		});
		
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				ImageIcon icon = new ImageIcon("image\\login_close_rover.png");
				lblNewLabel_2.setIcon(icon);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				ImageIcon icon = new ImageIcon("image\\login_close.png");
				lblNewLabel_2.setIcon(icon);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				ImageIcon icon = new ImageIcon("image\\login_close_rover.png");
				lblNewLabel_2.setIcon(icon);
				frame.dispose();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				ImageIcon icon = new ImageIcon("image\\login_close_pressed.png");
				lblNewLabel_2.setIcon(icon);
			}
		});
		
		forgetPassword_Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				new ForgetPasswordView();
			}
		});
		
	}
}
