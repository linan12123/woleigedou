package com.windows;

import com.mysql.DBUtil;

public class IndexMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBUtil D=new DBUtil("root", "root", "medicinesystem");//���ݿ��˺ţ����ݿ����룬���ݿ�����
		Login window = new Login();
		window.frame.setVisible(true);
	}

}
