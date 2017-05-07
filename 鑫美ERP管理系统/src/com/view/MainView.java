package com.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.first.AddCustomerPanel;
import com.first.AddCustomerZonePanel;
import com.first.AddEmployeePanel;
import com.first.AddPortClassifyPanel;
import com.first.AddPortPanel;
import com.first.SupplierManage;
import com.first.UpdateAndDeleteCustomerPanel;
import com.first.UpdateAndDeleteCustomerZone;
import com.first.UpdateAndDeleteEmployeePanel;
import com.first.UpdateAndDeletePortClassifyPanel;
import com.first.UpdateAndDeletePortPanel;
import com.first.UpdateAndDeleteSupplierPanel;
import com.fourth.QueryClassify;
import com.fourth.QueryCustomer;
import com.fourth.QueryCustomerZone;
import com.fourth.QueryDailyIncome;
import com.fourth.QueryEmpView;
import com.fourth.QueryInventory;
import com.fourth.QueryPortBelongs;
import com.fourth.QueryPortInfoView;
import com.fourth.QuerySupplier;
import com.listener.WindowClosingListener;
import com.main.UpdatePasswordView;
import com.pojo.EmpInfo;
import com.second.PurchaseManage;
import com.second.QueryPurchase;
import com.second.QueryVendition;
import com.third.AddDestroyPanel;
import com.third.QueryDestroy;

		/**
		 * 
		 * @author SiVan
		 * @time 2017��4��21�� ����2:34:56
		 * TODO	������			1920* 1080
		 */
public class MainView extends JFrame implements Runnable{
	
	private JPanel footer_Panel = new JPanel();					//�ײ����   ��ʾ��ǰ�û���ʱ��
	private JTabbedPane tabbedPane = new JTabbedPane();			//ѡ���壬λ�ڶ������������
	private JDesktopPane desktopPane = new JDesktopPane();		//�������ĵ�MDI
	
	//�ײ����
	private String time;
	private JLabel time_Label;
	
	/**
	 * 
	 * @param username		�û���
	 * @param urole			�û�Ȩ�ޣ�1Ϊ����Ա��0Ϊ��ͨ�û�
	 */
	public MainView(final String username, String urole) {
		// TODO Auto-generated constructor stub
		
		this.setTitle("����ERP����ϵͳ");
		this.setResizable(false);
		this.addWindowListener(new WindowClosingListener());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLayout(null);
		
		//��ȡ��ǰ��Ļ�ֱ���
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		double width = screen.getWidth();				
		double height = screen.getHeight();
		int width1 = (int)(0.9 * width);						//ERPϵͳ����Ļ���
		int height1 = (int)(0.9 * height);
		this.setSize(width1, height1);
		this.setLocationRelativeTo(null);
		
		
		
		//�������
		JPanel info_Panel = new JPanel();
		info_Panel.setBackground(new Color(215, 223, 194));
		info_Panel.setLayout(new BoxLayout(info_Panel, BoxLayout.X_AXIS));
		JPanel purchase_Panel = new JPanel();
		purchase_Panel.setBackground(new Color(215, 223, 194));
		purchase_Panel.setLayout(new BoxLayout(purchase_Panel, BoxLayout.X_AXIS));
		JPanel sale_Panel = new JPanel();
		sale_Panel.setBackground(new Color(215, 223, 194));
		sale_Panel.setLayout(new BoxLayout(sale_Panel, BoxLayout.X_AXIS));
		JPanel destroy_Panel = new JPanel();
		destroy_Panel.setBackground(new Color(215, 223, 194));
		destroy_Panel.setLayout(new BoxLayout(destroy_Panel, BoxLayout.X_AXIS));
		JPanel query_Panel = new JPanel();
		query_Panel.setBackground(new Color(215, 223, 194));
		query_Panel.setLayout(new BoxLayout(query_Panel, BoxLayout.X_AXIS));
		JPanel system_Panel = new JPanel();
		system_Panel.setBackground(new Color(215, 223, 194));
		system_Panel.setLayout(new BoxLayout(system_Panel, BoxLayout.X_AXIS));
		
			//info_Panel����ؼ� 
			JLabel employee_Label = new JLabel("Ա������", new ImageIcon("image\\employee.png"), SwingConstants.LEFT);
			employee_Label.setVerticalTextPosition(JLabel.BOTTOM);		//���ñ�ǩ�ı���ֱ����λ��ͼ��ײ�
			employee_Label.setHorizontalTextPosition(JLabel.CENTER);		//���ñ�ǩ�ı�����
			JLabel portClassify_Label = new JLabel("����������", new ImageIcon("image\\classify.png"), SwingConstants.LEFT);
			portClassify_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			portClassify_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel portInfoManag_Label = new JLabel("�������", new ImageIcon("image\\portinfo.png"), SwingConstants.LEFT);
			portInfoManag_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			portInfoManag_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel customerZone_Label = new JLabel("�ͻ��������", new ImageIcon("image\\zone.png"), SwingConstants.LEFT);
			customerZone_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			customerZone_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel customer_Label = new JLabel("�ͻ�����", new ImageIcon("image\\customer.png"), SwingConstants.LEFT);
			customer_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			customer_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel supplier_Manage = new JLabel("��Ӧ�̹���", new ImageIcon("image\\supplier1.png"), SwingConstants.LEFT);
			supplier_Manage.setVerticalTextPosition(JLabel.BOTTOM);		
			supplier_Manage.setHorizontalTextPosition(JLabel.CENTER);
			JLabel supplier_Label = new JLabel("��Ӧ����Ϣ", new ImageIcon("image\\supplier.png"), SwingConstants.LEFT);
			supplier_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			supplier_Label.setHorizontalTextPosition(JLabel.CENTER);
			//purchase_Panel����ؼ�
			JLabel purchase_Label = new JLabel("�ɹ��Ǽ�", new ImageIcon("image\\purchase_Label.png"), SwingConstants.LEFT);
			purchase_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			purchase_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel vendition_Label = new JLabel("���۵Ǽ�", new ImageIcon("image\\sale.png"), SwingConstants.LEFT);
			vendition_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			vendition_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel inventory_Label = new JLabel("������", new ImageIcon("image\\Inventory.png"), SwingConstants.LEFT);
			inventory_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			inventory_Label.setHorizontalTextPosition(JLabel.CENTER);
			//destroy_Panel����ؼ�
			JLabel destroySave_Label = new JLabel("����Ǽ�", new ImageIcon("image\\destroySave.png"), SwingConstants.LEFT);
			destroySave_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			destroySave_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel updateDestroy_Label = new JLabel("���»���", new ImageIcon("image\\update_Destroy.png"), SwingConstants.LEFT);
			updateDestroy_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			updateDestroy_Label.setHorizontalTextPosition(JLabel.CENTER);
			//query_Panel����ؼ�
			JLabel employeInfo_Label = new JLabel("Ա����Ϣ", new ImageIcon("image\\card.png"), SwingConstants.LEFT);
			employeInfo_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			employeInfo_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel zone_Label = new JLabel("�ͻ�����", new ImageIcon("image\\zone_Label.png"), SwingConstants.LEFT);
			zone_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			zone_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel customerInfo_Label = new JLabel("�ͻ���Ϣ", new ImageIcon("image\\customer_Info.png"), SwingConstants.LEFT);
			customerInfo_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			customerInfo_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel portClassify = new JLabel("��Ʒ����", new ImageIcon("image\\classify1.png"), SwingConstants.LEFT);
			portClassify.setVerticalTextPosition(JLabel.BOTTOM);		
			portClassify.setHorizontalTextPosition(JLabel.CENTER);
			JLabel portInfo_Label = new JLabel("��Ʒ��Ϣ", new ImageIcon("image\\portFlowerInfo.png"), SwingConstants.LEFT);
			portInfo_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			portInfo_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel inventoryInfo_Label = new JLabel("���ʣ��", new ImageIcon("image\\backup.png"), SwingConstants.LEFT);
			inventoryInfo_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			inventoryInfo_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel portBelong_Label = new JLabel("�������", new ImageIcon("image\\belong.png"), SwingConstants.LEFT);
			portBelong_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			portBelong_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel dailyIncome_Label = new JLabel("ÿ������", new ImageIcon("image\\dailyincome.png"), SwingConstants.LEFT);
			dailyIncome_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			dailyIncome_Label.setHorizontalTextPosition(JLabel.CENTER);
			//system_Panel����ؼ�
			JLabel update_Label = new JLabel("�޸�����", new ImageIcon("image\\update.png"), SwingConstants.LEFT);
			update_Label.setVerticalTextPosition(JLabel.BOTTOM);		//���ñ�ǩ�ı���ֱ����λ��ͼ��ײ�
			update_Label.setHorizontalTextPosition(JLabel.CENTER);		//���ñ�ǩ�ı�����
			JLabel cancel_Label = new JLabel("ע��", new ImageIcon("image\\cancel.png"), SwingConstants.LEFT);
			cancel_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			cancel_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel exit_Label = new JLabel("�˳�ϵͳ", new ImageIcon("image\\exit.png"), SwingConstants.LEFT);
			exit_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			exit_Label.setHorizontalTextPosition(JLabel.CENTER);
			JLabel help_Label = new JLabel("����", new ImageIcon("image\\help.png"), SwingConstants.LEFT);
			help_Label.setVerticalTextPosition(JLabel.BOTTOM);		
			help_Label.setHorizontalTextPosition(JLabel.CENTER);
		
		system_Panel.add(update_Label);
		system_Panel.add(Box.createHorizontalStrut(20));			//����һ�����Ϊ40�Ĳ��ɼ���������Ӽ��
		system_Panel.add(cancel_Label);
		system_Panel.add(Box.createHorizontalStrut(20));
//		system_Panel.add(help_Label);
//		system_Panel.add(Box.createHorizontalStrut(20));
		system_Panel.add(exit_Label);
		info_Panel.add(employee_Label);
		info_Panel.add(Box.createHorizontalStrut(20));
		info_Panel.add(portClassify_Label);
		info_Panel.add(Box.createHorizontalStrut(20));
		info_Panel.add(portInfoManag_Label);
		info_Panel.add(Box.createHorizontalStrut(20));
		info_Panel.add(customerZone_Label);
		info_Panel.add(Box.createHorizontalStrut(20));
		info_Panel.add(customer_Label);
		info_Panel.add(Box.createHorizontalStrut(20));
		info_Panel.add(supplier_Manage);
		purchase_Panel.add(purchase_Label);
		purchase_Panel.add(Box.createHorizontalStrut(20));
		purchase_Panel.add(vendition_Label);
		purchase_Panel.add(Box.createHorizontalStrut(20));
		purchase_Panel.add(inventory_Label);
		destroy_Panel.add(destroySave_Label);
		destroy_Panel.add(Box.createHorizontalStrut(20));
		destroy_Panel.add(updateDestroy_Label);
		query_Panel.add(employeInfo_Label);
		query_Panel.add(Box.createHorizontalStrut(20));
		query_Panel.add(zone_Label);
		query_Panel.add(Box.createHorizontalStrut(20));
		query_Panel.add(customerInfo_Label);
		query_Panel.add(Box.createHorizontalStrut(20));
		query_Panel.add(supplier_Label);
		query_Panel.add(Box.createHorizontalStrut(20));
		query_Panel.add(portClassify);
		query_Panel.add(Box.createHorizontalStrut(20));
		query_Panel.add(portInfo_Label);
		query_Panel.add(Box.createHorizontalStrut(20));
		query_Panel.add(inventoryInfo_Label);
		query_Panel.add(Box.createHorizontalStrut(20));
		query_Panel.add(portBelong_Label);
		query_Panel.add(Box.createHorizontalStrut(20));
		query_Panel.add(dailyIncome_Label);
		tabbedPane.setFocusable(false);
      	tabbedPane.setBackground(new Color(211, 230, 192));
      	tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		tabbedPane.setBounds(0, 0, width1, (int)(0.14 * height1));
		tabbedPane.addTab("������Ϣ����", new ImageIcon("image\\info.png"), info_Panel, "��Ա����������࣬������ϸ��Ϣ�Լ��ͻ����ͻ���������͹�Ӧ�����Ͻ��й���");
		tabbedPane.addTab("���������", new ImageIcon("image\\purchase.png"), purchase_Panel, "����ɹ������۵ǼǺͿ�����");
		tabbedPane.addTab("�������", new ImageIcon("image\\destroy.png"), destroy_Panel, "�Ի����𻵽��еǼǣ��Ӷ������û�ʹ�ÿ�����滻�𻵵Ļ���");
		tabbedPane.addTab("��ѯͳ��", new ImageIcon("image\\search.png"), query_Panel, "���û�δ֪�Ľ��в�ѯ���Ա��˽�");
		tabbedPane.addTab("ϵͳ����", new ImageIcon("image\\system.png"), system_Panel, "����������޸ģ�ע�����˳��Ͱ�������");
		
		//����DesktopPane �Ŀ�Ⱥ͸߶�
		final int width2 = width1;
		final int height2 = (int)(0.80 * height1);
		desktopPane.setBounds(0, (int)(0.14 * height1), width2, height2);
		//���Ĺ���
		
		//ҳ��ʵʱʱ��ˢ��
		Thread thread = new Thread(this);
		thread.start();
		
		//����ҳ�Ų���
		footer_Panel.setBounds(0, (int)(0.9 * height) - 60, (int)(0.9 * width), 35);
		footer_Panel.setBackground(new Color(215, 223, 194));
		footer_Panel.setLayout(new BoxLayout(footer_Panel, BoxLayout.X_AXIS));
		JLabel user_Label = new JLabel("��  ǰ  ��  ��  :  " + username, new ImageIcon("image\\footer_useer.png"), SwingConstants.LEFT);
		time_Label = new JLabel(new ImageIcon("image\\time.png"), SwingConstants.LEFT);
//		user_Label.setBounds(width1 * 1/ 5, 8, 150, 20);
//		time_Label.setBounds(width1 * 3/ 5, 8, 300, 20);
		footer_Panel.add(Box.createHorizontalStrut(width1 / 4));
		footer_Panel.add(user_Label);
		footer_Panel.add(Box.createHorizontalStrut(width1 / 4));
		footer_Panel.add(time_Label);
		//���������¼�
			//��Ϣ��������¼�
			//Ա������
			employee_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
//					JInternalFrame empManage = new EmployeeManageInternalFrame("Ա������", true, true, true, true);
//					empManage.setBounds(90, 90, width2 - 180, height2 - 180);
//					empManage.setVisible(true);
//					desktopPane.add(empManage);
					
					JDialog dialog = new JDialog();
					JTabbedPane tab = new JTabbedPane();
					final AddEmployeePanel addEmployeePanel = new AddEmployeePanel();
					final UpdateAndDeleteEmployeePanel updateAndDeleteEmployeePanel = new UpdateAndDeleteEmployeePanel();
					tab.addTab("���Ա��", addEmployeePanel);
					tab.addTab("�޸���ɾ��Ա����Ϣ", updateAndDeleteEmployeePanel);
					tab.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							// TODO Auto-generated method stub
							if(addEmployeePanel.i == 1){
								EmpInfo emp = new EmpInfo();
								emp.setName(addEmployeePanel.getNewEmp().getName());
			 					emp.setCard(addEmployeePanel.getNewEmp().getCard());
			 					emp.setPhone(addEmployeePanel.getNewEmp().getPhone());
			 					emp.setHiredate(addEmployeePanel.getNewEmp().getHiredate());
								updateAndDeleteEmployeePanel.eNameSelect.addItem(emp);
							}
						}
					});
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setBounds(600, 300, 450, 400);
					dialog.setVisible(true);
					dialog.setResizable(false);
					dialog.add(tab);
				}
			});
			//����������
			portClassify_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					JDialog dialog = new JDialog();
					JTabbedPane tab = new JTabbedPane();
					final AddPortClassifyPanel addPortClassifyPanel = new AddPortClassifyPanel();
					final UpdateAndDeletePortClassifyPanel updateAndDeletePortClassifyPanel = new UpdateAndDeletePortClassifyPanel();
					tab.addTab("��ӻ�������", addPortClassifyPanel);
					tab.addTab("�޸���ɾ����������", updateAndDeletePortClassifyPanel);
					tab.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					dialog.setBounds(600, 300, 450, 400);
					dialog.setVisible(true);
					dialog.setResizable(false);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.add(tab);
				}
			});
			//�������
			portInfoManag_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					JDialog dialog = new JDialog();
					JTabbedPane tab = new JTabbedPane();
					final AddPortPanel addPortPanel = new AddPortPanel();
					final UpdateAndDeletePortPanel updateAndDeletePortPanel = new UpdateAndDeletePortPanel();
					tab.addTab("��ӻ�����Ϣ", addPortPanel);
					tab.addTab("�޸���ɾ��������Ϣ", updateAndDeletePortPanel);
					tab.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					dialog.setBounds(600, 300, 450, 650);
					dialog.setVisible(true);
					dialog.setResizable(false);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.add(tab);
				}
				
			});
			//�ͻ��������
			customerZone_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					JDialog dialog = new JDialog();
					JTabbedPane tab = new JTabbedPane();
					final AddCustomerZonePanel addCustomerZonePanel = new AddCustomerZonePanel();
					final UpdateAndDeleteCustomerZone updateAndDeleteCustomerZone = new UpdateAndDeleteCustomerZone();
					tab.addTab("��ӿͻ�����", addCustomerZonePanel);
					tab.addTab("�޸���ɾ���ͻ�����", updateAndDeleteCustomerZone);
					tab.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					dialog.setBounds(600, 300, 450, 400);
					dialog.setVisible(true);
					dialog.setResizable(false);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.add(tab);
					
					
				}
				
			});
			
			//�ͻ�����
			customer_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
					JDialog dialog = new JDialog();
					JTabbedPane tab = new JTabbedPane();
					final AddCustomerPanel addCustomerPanel = new AddCustomerPanel();
					final UpdateAndDeleteCustomerPanel updateAndDeleteCustomerPanel = new UpdateAndDeleteCustomerPanel();
					tab.addTab("��ӿͻ���Ϣ", addCustomerPanel);
					tab.addTab("�޸���ɾ���ͻ���Ϣ", updateAndDeleteCustomerPanel);
					tab.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					dialog.setBounds(600, 300, 450, 620);
					dialog.setVisible(true);
					dialog.setResizable(false);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.add(tab);
				}
			});
			//��Ӧ�̹���
			supplier_Manage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
					JDialog dialog = new JDialog();
					JTabbedPane tab = new JTabbedPane();
					final SupplierManage addSupplierPanel = new SupplierManage();
					final UpdateAndDeleteSupplierPanel updateAndDeleteSupplierPanel = new UpdateAndDeleteSupplierPanel();
					tab.addTab("��ӹ�Ӧ����Ϣ", addSupplierPanel);
					tab.addTab("�޸���ɾ����Ӧ����Ϣ", updateAndDeleteSupplierPanel);
					tab.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							// TODO Auto-generated method stub
							
						}
					});
					dialog.setBounds(600, 300, 450, 620);
					dialog.setVisible(true);
					dialog.setResizable(false);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.add(tab);
				}
			});
			
			
			//
			//�������������¼�
			//�ɹ��Ǽ�
			purchase_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					JDialog dialog = new JDialog();
					JTabbedPane tab = new JTabbedPane();
					final PurchaseManage addPurchasePanel = new PurchaseManage();
					tab.addTab("�ɹ��Ǽ�", addPurchasePanel);
					dialog.setBounds(450, 200, 900, 750);
					dialog.setVisible(true);
					dialog.setResizable(false);
					dialog.add(tab);
					
					
				}
				
			});
			//���۵Ǽ�
			vendition_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					new QueryVendition();
				}
			});
			
			//������
			inventory_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					new com.second.QueryInventory();
					
				}
			});
			//�����������¼�
			destroySave_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					JDialog dialog = new JDialog();
					JTabbedPane tab = new JTabbedPane();
					final AddDestroyPanel addDestroyPanel = new AddDestroyPanel();
					tab.addTab("����Ǽ�", addDestroyPanel);
					dialog.setBounds(750, 400, 400, 350);
					dialog.setVisible(true);
					dialog.setResizable(false);
					dialog.add(tab);
				}
			});
			
			updateDestroy_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					new QueryDestroy();
				}
			});
			//��ѯͳ�Ƽ����¼�
				//Ա����Ϣ
				employeInfo_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					new QueryEmpView();
					}
				});
				
				customerInfo_Label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						new QueryCustomer();
					}
				});
				
				//�ͻ�����
				zone_Label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						new QueryCustomerZone();
					}
				});
				//�ͻ���Ϣ
				customerInfo_Label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						new QueryCustomer();
					}
				});
				//��Ӧ����Ϣ
				supplier_Label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
							new QuerySupplier();
					}
				});
				//��Ʒ����
				portClassify.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
							new QueryClassify();
					}
				});
				//��Ʒ��Ϣ
				portInfo_Label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
							new QueryPortInfoView();
					}
				});
				//���ʣ��
				inventoryInfo_Label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						new QueryInventory();
					}
				});
				//�������
				portBelong_Label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						new QueryPortBelongs();
					}
				});
				//ÿ������
				dailyIncome_Label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						new QueryDailyIncome();
					}
				});
				
			//ϵͳ�����еļ����¼�
			update_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					new UpdatePasswordView(username, MainView.this);
				}
			});
			
			
			cancel_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					MainView.this.dispose();
					new LogInView();
				}
			});
		
			exit_Label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					int n = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�˳�����ERP����ϵͳô��","�˳�",JOptionPane.YES_NO_OPTION);
					if(n == 0){
						JOptionPane.showMessageDialog(null, "���ѳɹ��˳�");
						System.exit(0);
					}else{
						JOptionPane.showMessageDialog(null, "�˳�ʧ��");
					}
					
				}
			});
			
			help_Label.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		
		
		
		this.add(tabbedPane);
		this.add(desktopPane);
		this.add(footer_Panel);
		this.setVisible(true);
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while(true){
			date = new Date();
			time = sdf.format(date);
			time_Label.setText("��  ǰ  ʱ  ��  ��" + time);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new MainView("s", "s");
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
