package com.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

public class WindowClosingListener extends WindowAdapter{

	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		int i = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�˳�ô��", "�˳�", JOptionPane.YES_NO_OPTION);
		if(i == 0)
		{
			JOptionPane.showMessageDialog(null, "���ѳɹ��˳���ϵͳ");
			System.exit(0);
		}
			
	}
	
	
}
