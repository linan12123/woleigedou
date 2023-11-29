package com.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.mysql.Mysql;
import com.tools.Tools;

public class Reg {

	public  JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JPasswordField passwordField;


	/**
	 * Create the application.
	 */
	public Reg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("ע�����");
		frame.setBounds(100, 100, 270, 232);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("�˺�");
		lblNewLabel.setBounds(69, 10, 58, 15);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(137, 7, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("����");
		lblNewLabel_1.setBounds(69, 41, 58, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("����");
		lblNewLabel_2.setBounds(69, 71, 58, 15);
		frame.getContentPane().add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(137, 69, 66, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("ע���˺�");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String data[]= {
						textField.getText(),
						new  String(passwordField.getPassword()),
						"2",
						textField_2.getText()

				};
				if(textField.getText().equals("")) {
					Tools.messageWindows("�˺Ų���Ϊ��");
				}else if(new  String(passwordField.getPassword()).equals("")){
					Tools.messageWindows("���벻��Ϊ��");
				}else if(textField_2.getText().equals("")){
					Tools.messageWindows("��������Ϊ��");
				}else {

					int a= Mysql.upDate("insert into admin VALUES (?,?,?,?)", data);
					if(a==1) {
						Tools.messageWindows("ע��ɹ�");
					}else {
						Tools.messageWindows("ע��ʧ��");
					}
				}

			}
		});
		btnNewButton.setBounds(96, 118, 97, 23);
		frame.getContentPane().add(btnNewButton);

		passwordField = new JPasswordField();
		passwordField.setBounds(137, 38, 68, 21);
		frame.getContentPane().add(passwordField);
	}
}
