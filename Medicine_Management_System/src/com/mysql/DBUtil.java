package com.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.tools.Tools;

public class DBUtil {

	public static Connection con = null;

	public DBUtil(String account,String password,String datbasName) {//���ݿ���˺ţ����ݿ������ �����ݿ������

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("���������ɹ�");


		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("��������ʧ��");

			e.printStackTrace();
		}

		String url="jdbc:mysql://localhost:3306/"+datbasName+"?useUnicode=true&characterEncoding=utf8&useSSL=true";

		//�������ݿ�
		try {
			con=DriverManager.getConnection(url,account, password);

			System.out.println("�������ݿ�ɹ�");

		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("�������ݿ�ʧ��");
			String temp=e.getMessage();
			System.out.println(temp);
			String[] arr1=temp.split(" ");
			if(arr1[0].equals("Unknown")) {
				System.out.println("�뽨������Ϊ��"+arr1[2]+"���ݿ�");
			}
			if(arr1[0].equals("Access")) {
				System.out.println("�������ݿ������Ƿ���ȷ�����ݿ��������");
			}
			if(temp.contains("the server was 0 milliseconds ago")){
				System.out.println("�밲װMysql���ݿ�");
			}

		}



	}

}
