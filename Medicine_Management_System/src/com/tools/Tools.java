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
	//设置窗口居中
	public static void setWindowPosCenter(int WIDTH,int HEIGHT,JFrame frame) {
		Toolkit kit = Toolkit.getDefaultToolkit();//kit是获取屏幕信息的类
		Dimension screenSize = kit.getScreenSize();//screenSize获取屏幕大小
		int width = screenSize.width;//width获取屏幕宽度
		int height = screenSize.height;//height获取屏幕高度
		int x = (width - WIDTH) / 2;
		int y = (height - HEIGHT) / 2;//使串窗口居中显示
		frame.setBounds(x,y,WIDTH,HEIGHT);
	}
	public static void messageWindows(String msg) {
		JOptionPane.showMessageDialog(null,  msg,"消息",JOptionPane.WARNING_MESSAGE);
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