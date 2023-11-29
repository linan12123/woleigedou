package com.windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.Mysql;
import com.other.EasyCode;
import com.tools.Table;
import com.tools.Tools;

public class Salesman {

	public  JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	String account;
	private JTextField textField_8;

	public Salesman(String account) {

		this.account=account;
		initialize();

	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("药品管理界面");
		frame.setBounds(100, 100, 1016, 526);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 982, 479);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("药品编号");
		lblNewLabel.setBounds(10, 10, 58, 15);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(78, 7, 66, 21);
		panel.add(textField);
		textField.setColumns(10);

		//________________________________________________________

		Object columns[] ={"药品编号","药品名称","药品类型","药品销售人","药品余量"};//创建表格

		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension( 600,280));//设置整个滚动条窗口的大小
		JS.setBounds(10, 54, 950, 200);


		JButton btnNewButton = new JButton("查找药品");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(textField.getText().equals("")) {
					EasyCode.showAllData("select id,name,type,account,margin from medicine", 5, model);
				}else {
					String data[]= {
							textField.getText()
					};

					ResultSet rs = Mysql.QueryData("select id,name,type,account,margin from medicine where id=?",data);
					Tools.addDataTable(rs, model, 4);

				}

			}
		});
		btnNewButton.setBounds(147, 6, 97, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("添加药品");
		btnNewButton_1.addActionListener(new ActionListener() {
			int a;
			public void actionPerformed(ActionEvent e) {

				if(textField_1.getText().equals("")){
					Tools.messageWindows("请输入药品名称");
				}else if(textField_2.getText().equals("")){
					Tools.messageWindows("请输入药品类型");
				}else {
					String data[]= {
							textField_1.getText(),
							textField_2.getText(),
							"张三",
							"充足"
					};
					a = Mysql.upDate("insert into medicine(id,name,type,account,margin,intime) values ((select max(id)+1 from medicine med),?,?,?,?,now())", data);
				}
				if(a == 1) {
					Tools.messageWindows("添加药品成功");
				}

			}
		});
		btnNewButton_1.setBounds(515, 6, 97, 23);
		panel.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("药品名称");
		lblNewLabel_1.setBounds(255, 10, 81, 15);
		panel.add(lblNewLabel_1);
		textField_1 = new JTextField();
		textField_1.setBounds(315, 7, 66, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("药品类型");
		lblNewLabel_2.setBounds(385, 10, 81, 15);
		panel.add(lblNewLabel_2);
		textField_2 = new JTextField();
		textField_2.setBounds(445, 7, 66, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);

		panel.add(JS);

		JLabel lblNewLabel_4 = new JLabel("出货编码");
		lblNewLabel_4.setBounds(620, 10, 58, 15);
		panel.add(lblNewLabel_4);

		textField_8 = new JTextField();
		textField_8.setBounds(680, 8, 66, 21);
		panel.add(textField_8);
		textField_8.setColumns(10);
		JButton btnNewButton_2 = new JButton("出库");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String data[]= {
						account,
						textField_8.getText(),
						"充足"
				};
				//如果状态为租车中如果有则进行还车
				ResultSet rs = Mysql. QueryData("select * from medicine where account=(select name from admin where account=?) and id=? and margin=?", data);
				try {
					int a=0;
					while(rs.next()) {
						a++;
					}
					//有数据
					if(a!=0) {
						//可以进行出库
						String data1[]= {
								"售罄",
								account,
								textField_8.getText(),
						};


						a=Mysql.upDate("UPDATE medicine set margin=? where account=(select name from admin where account=?) and id=? ", data1);
						if(a==1) {
							Tools.messageWindows("出售成功");
						}else
							Tools.messageWindows("出售失败");

					}else {
						Tools.messageWindows("出售失败");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}
		});
		btnNewButton_2.setBounds(750, 6, 97, 23);
		panel.add(btnNewButton_2);



		//添加表格
		Object column[] ={"药品编号","药品名称","药品类型","售货人","入库时间","药品余量"};//创建表格

		Table t1Tabl=new Table(column);
		JTable tabl = t1Tabl.getTables();
		JScrollPane J = t1Tabl.getJScrollPane();
		DefaultTableModel mode = t1Tabl.getModel();
		//J.setPreferredSize(new Dimension( 600,280));//设置整个滚动条窗口的大小




		JButton btnNewButton_3 = new JButton("查看已添加药品");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data[]= {
						account
				};

				ResultSet rs = Mysql.QueryData("select id,name,type,account,intime,margin from medicine where account=(select name from admin where account=?)", data);
				Tools.addDataTable(rs, mode, 6);

			}
		});
		btnNewButton_3.setBounds(78, 279, 140, 23);
		panel.add(btnNewButton_3);


		J.setBounds(10, 320, 950, 149);
		panel.add(J);


	}
}
