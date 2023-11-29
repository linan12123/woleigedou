package com.windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.MouseInputListener;

import com.mysql.Mysql;
import com.other.EasyCode;
import com.tools.Tools;

public class Login {

	public JFrame frame;
	private JTextField textfield;
	private JPasswordField passwordfield;

	public Login() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("药店管理系统");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//自动隐藏并释放该窗体
		frame.getContentPane().setLayout(null);//用getContentPane()方法获得JFrame的内容面板
		//setLayout(null)表示设为绝对布局
		//不管如何改变窗体都不会改变已固定组件的坐标及大小

		JPanel panel = new JPanel();//设置盘子
		panel.setBounds(10, 10, 416, 243);
		frame.getContentPane().add(panel); //将盘子添加进窗体中
		panel.setLayout(null);

		JLabel label = new JLabel("药店管理系统");
		label.setBounds(100, 10, 342, 74);
		label.setFont(new Font("宋体",Font.PLAIN,35));
		label.setToolTipText("");//设置为鼠标悬停时不提示
		panel.add(label);//将标题添加进盘子中

		JLabel label_1 = new JLabel("账号");
		label_1.setBounds(90, 94, 58, 18);
		panel.add(label_1);

		textfield = new JTextField();
		textfield.setBounds(157, 91, 113, 21);
		panel.add(textfield);
		textfield.setColumns(10);//设置账号最长10位

		JLabel label_2 = new JLabel("密码");
		label_2.setBounds(90, 131, 58, 18);
		panel.add(label_2);

		passwordfield = new JPasswordField();
		passwordfield.setBounds(157, 128, 113, 21);
		panel.add(passwordfield);
		passwordfield.setColumns(16);//设置密码最长16位


		JButton loginbutton = new JButton("登录");
		loginbutton.addActionListener(new ActionListener() {
			//添加事件监听
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String data[] = {
						textfield.getText(),
						Tools.getPassword(passwordfield)
				};
				String sqlString = "select * from admin where account=? and password=?";
				int a = EasyCode.isLogin(sqlString, data);
				if(a == 1) {
					//登录成功
					ResultSet rs = Mysql.QueryData(sqlString, data);
					try {
						while(rs.next()) {
							if(rs.getString(3).equals("1")) {
								//登录管理员用户
								Administrators window = new Administrators();
								window.frame.setVisible(true);
								frame.dispose();
							}else {
								//登录普通用户
								Salesman window = new Salesman(textfield.getText());
								window.frame.setVisible(true);
								frame.dispose();
							}
						}
						rs.close();
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					Tools.messageWindows("密码错误或者账号错误");
				}
			}
		});
		loginbutton.setBounds(157, 176, 97, 23);
		panel.add(loginbutton);
		JLabel zhucebutton = new JLabel("注册账号>");
		zhucebutton.setBounds(328, 200, 80, 15);
		panel.add(zhucebutton);
		zhucebutton.addMouseListener(new MouseInputListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Reg window = new Reg();
				window.frame.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		//把图片放进来
		ImageIcon img = new ImageIcon("src/img/loginwindows.jpg");//将图片读取到变量img当中
		JLabel bgimg = new JLabel(img);
		bgimg.setBounds( 0, 0, 410, 230);
		panel.add(bgimg);
	}
}