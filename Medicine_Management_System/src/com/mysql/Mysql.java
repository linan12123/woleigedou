package com.mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysql {
	static Connection con = DBUtil.con;
	public static ResultSet QueryData(String sqlstring,String data[]) {
		ResultSet rs = null;//结果集对象
		PreparedStatement persql;//预处理语句
		try {
			persql = con.prepareStatement(sqlstring);
			if(data != null) {
				for(int i=0;i<data.length;i++) {
					persql.setString(i+1, data[i]);//第i+1个？填入data[i]的数据
				}
			}//补完整数据库语句

			System.out.println(persql);
			rs = persql.executeQuery();//executeQuery()执行查询
			//把数据库响应的查询结果存放在rs中
			return rs;
		}catch(SQLException e) {
			e.printStackTrace();//显示错误详情
			return rs;
		}
	}

	//---------------------------------------------------------------

	public static int  upDate(String sqlStr,String data[]) { //增加数据  传过来数据库语句 和一个 参数

		PreparedStatement preSql;
		int num;
		try {
			preSql=con.prepareStatement(sqlStr);
			if(data==null) {
				num=preSql.executeUpdate();//返回一个执行成功的数子
			}else {


				for(int i=0;i<data.length;i++) {
					//遍历传入的data数据 把他存入 数据库语句   
					preSql.setString(i+1,data[i]);
				}
				System.out.println(preSql);
				num=preSql.executeUpdate();//返回一个执行成功的数子
			}
			return num;//如果数字 就>0就是执行成功   -1 就是失败  
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
	}
}
