package com.other;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.mysql.Mysql;
import com.tools.Tools;
public class EasyCode {
	public static int isLogin(String sqlstring,String data[]) {
		ResultSet rs = Mysql.QueryData(sqlstring, data);
		if(rs == null) {
			return 0;
		}else {
			try {
				if(rs.next()) {
					return 1;
				}else {
					return 0;
				}
			}catch(SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
	}
	public static void insertData(JTextField textField[],String sql,int j,String mes) {
		String data[]=new String[textField.length];
		for(int i=0;i<textField.length;i++) {
			data[i]=textField[i].getText();
		}
		if(textField[j-1].getText().equals("")) {
			Tools.messageWindows(mes);
		}else {
			int a=Mysql.upDate(sql, data);
			if(a==1) {
				Tools.messageWindows("添加成功");
			}else {
				Tools.messageWindows("添加失败");
			}
		}
	}
	public static void deleteDate(JTextField textField[],String sql,int j,String mes) {
		String data[]=new String[textField.length];
		for(int i=0;i<textField.length;i++) {
			data[i]=textField[i].getText();
		}
		if(textField[j-1].getText().equals("")) {
			Tools.messageWindows(mes);
		}else {
			int a=Mysql.upDate(sql, data);
			if(a==1) {
				Tools.messageWindows("删除成功");
			}else {
				Tools.messageWindows("删除失败");
			}
		}
	}
	public static void upData(JTextField textField[],String sql,int j,String mes) {
		String data[]=new String[textField.length];
		for(int i=0;i<textField.length;i++) {
			data[i]=textField[i].getText();
		}

		if(textField[j-1].getText().equals("")) {
			Tools.messageWindows(mes);
		}else {
			int a=Mysql.upDate(sql, data);
			if(a==1) {
				Tools.messageWindows("更改成功");
			}else {
				Tools.messageWindows("更改失败");
			}
		}
	}
	public static void showAllData(String sql,int rowacount,DefaultTableModel model) {
		ResultSet rs = Mysql.QueryData(sql, null);
		if(rs==null) {
			Tools.messageWindows("没有数据");
		}else {
			Tools.addDataTable(rs, model, rowacount);
		}
	}
	public static void showOneData(JTextField textField[],JTextField stextField[],String sql,int rowacount,DefaultTableModel model,int adt[]) {
		String data[]=new String[textField.length];
		for(int i=0;i<textField.length;i++) {
			data[i]=textField[i].getText();
		}
		//将数据读取到data[]
		ResultSet rs = Mysql.QueryData(sql, data);
		if(rs==null) {
			Tools.messageWindows("没有数据");
		}else {
			Tools.addDataTable(rs, model, rowacount);
			rs = Mysql.QueryData(sql, data);
			//再次执行
			try {
				if(rs.next()) {
					for(int i=0;i<stextField.length;i++) {
						stextField[i].setText(rs.getString(adt[i]));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}