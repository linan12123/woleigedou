package com.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysql {
	static Connection con = DBUtil.con;
	public static ResultSet QueryData(String sqlstring,String data[]) {
		ResultSet rs = null;//���������
		PreparedStatement persql;//Ԥ�������
		try {
			persql = con.prepareStatement(sqlstring);
			if(data != null) {
				for(int i=0;i<data.length;i++) {
					persql.setString(i+1, data[i]);//��i+1��������data[i]������
				}
			}//���������ݿ����

			System.out.println(persql);
			rs = persql.executeQuery();//executeQuery()ִ�в�ѯ
			//�����ݿ���Ӧ�Ĳ�ѯ��������rs��
			return rs;
		}catch(SQLException e) {
			e.printStackTrace();//��ʾ��������
			return rs;
		}
	}

	//---------------------------------------------------------------

	public static int  upDate(String sqlStr,String data[]) { //��������  ���������ݿ���� ��һ�� ����

		PreparedStatement preSql;
		int num;
		try {
			preSql=con.prepareStatement(sqlStr);
			if(data==null) {
				num=preSql.executeUpdate();//����һ��ִ�гɹ�������
			}else {


				for(int i=0;i<data.length;i++) {
					//���������data���� �������� ���ݿ����   
					preSql.setString(i+1,data[i]);
				}
				System.out.println(preSql);
				num=preSql.executeUpdate();//����һ��ִ�гɹ�������
			}
			return num;//������� ��>0����ִ�гɹ�   -1 ����ʧ��  
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
}
