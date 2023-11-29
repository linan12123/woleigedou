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
		frame.setTitle("ҩ�����ϵͳ");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//�Զ����ز��ͷŸô���
		frame.getContentPane().setLayout(null);//��getContentPane()�������JFrame���������
		//setLayout(null)��ʾ��Ϊ���Բ���
		//������θı䴰�嶼����ı��ѹ̶���������꼰��С

		JPanel panel = new JPanel();//��������
		panel.setBounds(10, 10, 416, 243);
		frame.getContentPane().add(panel); //��������ӽ�������
		panel.setLayout(null);

		JLabel label = new JLabel("ҩ�����ϵͳ");
		label.setBounds(100, 10, 342, 74);
		label.setFont(new Font("����",Font.PLAIN,35));
		label.setToolTipText("");//����Ϊ�����ͣʱ����ʾ
		panel.add(label);//��������ӽ�������

		JLabel label_1 = new JLabel("�˺�");
		label_1.setBounds(90, 94, 58, 18);
		panel.add(label_1);

		textfield = new JTextField();
		textfield.setBounds(157, 91, 113, 21);
		panel.add(textfield);
		textfield.setColumns(10);//�����˺��10λ

		JLabel label_2 = new JLabel("����");
		label_2.setBounds(90, 131, 58, 18);
		panel.add(label_2);

		passwordfield = new JPasswordField();
		passwordfield.setBounds(157, 128, 113, 21);
		panel.add(passwordfield);
		passwordfield.setColumns(16);//���������16λ


		JButton loginbutton = new JButton("��¼");
		loginbutton.addActionListener(new ActionListener() {
			//����¼�����
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
					//��¼�ɹ�
					ResultSet rs = Mysql.QueryData(sqlString, data);
					try {
						while(rs.next()) {
							if(rs.getString(3).equals("1")) {
								//��¼����Ա�û�
								Administrators window = new Administrators();
								window.frame.setVisible(true);
								frame.dispose();
							}else {
								//��¼��ͨ�û�
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
					Tools.messageWindows("�����������˺Ŵ���");
				}
			}
		});
		loginbutton.setBounds(157, 176, 97, 23);
		panel.add(loginbutton);
		JLabel zhucebutton = new JLabel("ע���˺�>");
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
		//��ͼƬ�Ž���
		ImageIcon img = new ImageIcon("src/img/loginwindows.jpg");//��ͼƬ��ȡ������img����
		JLabel bgimg = new JLabel(img);
		bgimg.setBounds( 0, 0, 410, 230);
		panel.add(bgimg);
	}
}