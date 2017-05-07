package com.third;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.plugin.Chooser;
		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��19�� ����1:13:21
		 * TODO		�Ǽ�����ֻҪ�Ǽǿ�������ͻ��1������״̬����Ϊ�����������������״̬����Ϊ���޸�����������һ������ֻ���޸�״̬Ϊ����
		 */
public class AddDestroyPanel extends JPanel {
	
	private JLabel id_Label = new JLabel("������");
	private JLabel classify_Label = new JLabel("��������");
	private JLabel date_Label = new JLabel("������");
	private JLabel time_Label = new JLabel("��ʱ��");
	private JTextField id_Field = new JTextField();
	private JTextField date_Field = new JTextField();
	private JButton button = new JButton("���");
	private JComboBox<String> classify_Field = new JComboBox<String>();
	private JComboBox<String> time_Field = new JComboBox<String>();
	
	private String Url = "jdbc:mysql://localhost:3306/erp";
	private String User = "root";
	private String Password = "yourpassword";
	
	public AddDestroyPanel(){
		// TODO Auto-generated constructor stub
		this.setToolTipText("�����Ǽ��ڽ����Լ����������е�����");
		this.setLayout(null);
		
//		((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);		//����ѡ�����
		
		 try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} 
		
		id_Label.setBounds(40, 30, 80, 30);
		classify_Label.setBounds(40, 70, 80, 30);
		date_Label.setBounds(40, 110, 80, 30);
		time_Label.setBounds(40, 150, 80, 30);
		id_Field.setBounds(140, 30, 150, 30);
		classify_Field.setBounds(140, 70, 150, 30);
		date_Field.setBounds(140, 110, 150, 30);
		time_Field.setBounds(140, 150, 150, 30);
		button.setBounds(200, 190, 80, 30);
		
		Chooser chooser = Chooser.getInstance();
		chooser.register(date_Field);
		
		
		time_Field.removeAllItems();
		time_Field.addItem("����");
		time_Field.addItem("����");
		
		
		try{
			classify_Field.removeAllItems();
			Class.forName("com.mysql.jdbc.Driver");
			try(
				Connection conn2 = DriverManager.getConnection(Url, User, Password);
					){
				String sql2 = "select pcClassify from portClassify ";
				PreparedStatement ps2 = conn2.prepareStatement(sql2);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next())
				{
					classify_Field.addItem(rs2.getString(1));
				}
			}
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null, "�������ݿ��Ƿ������Լ����ݿ��Ƿ�����");
			e1.printStackTrace();
		}
		
		
		
		button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String id = id_Field.getText();
					String classify = (String) classify_Field.getSelectedItem();
					String time = (String) time_Field.getSelectedItem();
					String date =  date_Field.getText();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date1 = null;
					try {
						date1 = sdf.parse(date);
					} catch (ParseException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					
					//���ȼ���û����������Ƿ�Ϊ��
					if(id.equals("")){
						JOptionPane.showConfirmDialog(null, "�����Ų���Ϊ��", "����", 
								JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(date.equals("")){
						JOptionPane.showConfirmDialog(null, "�����ڲ���Ϊ��", "����", 
								JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(time.equals("")){
						JOptionPane.showConfirmDialog(null, "��ʱ�ڲ���Ϊ��", "����", 
								JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//ִ��JDBC
					try{
						Class.forName("com.mysql.jdbc.Driver");
						try(
							Connection conn = DriverManager.getConnection(Url, User, Password);
								){
							String sql = "Select pc.pcId,pi.piNumber from portinfo pi, portClassify pc where "
									+ "pc.pcId = pi.pcId and pi.piId = ? and pc.pcClassify = ? and pi.piStatus = \"1\"";
							PreparedStatement ps = conn.prepareStatement(sql);
							ps.setObject(1, id);
							ps.setObject(2, classify);
							ResultSet rs = ps.executeQuery();
							if(rs.next()){
								String pcid = rs.getString(1);
								String number = rs.getString(2);
								
								String sql2 = "SET FOREIGN_KEY_CHECKS = 0";
								String sql3 = "insert into destroy(piId, dWay, dTime, dStatus) values(?,?,?, 0)";
								String sql4 = "update portinfo set  piNumber = ? where piId = ?  and pcId = ? and piStatus = \"1\"";
								String sql5 = "SET FOREIGN_KEY_CHECKS = 0";
								PreparedStatement ps2 = conn.prepareStatement(sql2);
								PreparedStatement ps3 = conn.prepareStatement(sql3);
								PreparedStatement ps4 = conn.prepareStatement(sql4);
								PreparedStatement ps5 = conn.prepareStatement(sql5);
								ps3.setObject(1, id);
								int time1 = time.equals("����") ? 1 : 0; 
								ps3.setObject(2, time1);
								ps3.setObject(3, date1);
								ps4.setObject(1, (Integer.parseInt(number) - 1));
								ps4.setObject(2, id);
								ps4.setObject(3, pcid);
								if(Integer.parseInt(number) - 1 < 0){
									JOptionPane.showMessageDialog(null, "�Բ��𣬿��û���㹻��ʣ����������");
									return;
								}
								if(ps2.executeUpdate() >= 0 && ps3.executeUpdate() > 0 && ps4.executeUpdate() > 0 && ps5.executeUpdate() >= 0)
								{
									JOptionPane.showMessageDialog(null, "�Ǽǳɹ�");
									id_Field.setText("");
									date_Field.setText("");
								}else{
									JOptionPane.showConfirmDialog(null, "������д��Ϣ�Լ���������ͱ���Ƿ�", "����", 
											JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
								}
								
							}else{
								JOptionPane.showMessageDialog(null, "���û�ȷ�������Լ������Ƿ��Ӧ����ȷ");
							}
						}
					}catch(Exception e1){
						e1.printStackTrace();
						JOptionPane.showConfirmDialog(null, "������д��Ϣ�Լ���������ͱ���Ƿ�", "����", 
								JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE);
									}
					}
		});
		
		this.add(id_Label);
		this.add(classify_Label);
		this.add(date_Label);
		this.add(time_Label);
		this.add(id_Field);
		this.add(date_Field);
		this.add(classify_Field);
		this.add(time_Field);
		this.add(button);
		updateUI();
		
		this.setVisible(true);
	}
	
}
