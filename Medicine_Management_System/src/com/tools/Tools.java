package com.tools;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;

public class Tools {
	public static String getPassword(JPasswordField passwordfield) {
		String password = new String(passwordfield.getPassword());
		return password;
		}
	//���ô��ھ���
	public static void setWindowPosCenter(int WIDTH,int HEIGHT,JFrame frame) {
		Toolkit kit = Toolkit.getDefaultToolkit();//kit�ǻ�ȡ��Ļ��Ϣ����
		Dimension screenSize = kit.getScreenSize();//screenSize��ȡ��Ļ��С
		int width = screenSize.width;//width��ȡ��Ļ���
		int height = screenSize.height;//height��ȡ��Ļ�߶�
		int x = (width - WIDTH) / 2;
		int y = (height - HEIGHT) / 2;//ʹ�����ھ�����ʾ
		frame.setBounds(x,y,WIDTH,HEIGHT);
	}
	public static void messageWindows(String msg) {
		JOptionPane.showMessageDialog(null,  msg,"��Ϣ",JOptionPane.WARNING_MESSAGE);
	}
	public static  int addDataTable(ResultSet rs ,DefaultTableModel  model,int index) {
		int count=0;
		model.setNumRows(0);
		String  data[]=new String [index];
		try {
			while(rs.next()) {
				count++;
				for(int i=0;i<data.length;i++) {
					data[i]=rs.getString(i+1);
				}
				model.addRow(data);
			}
			rs.close();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return count;
		}
	}
}