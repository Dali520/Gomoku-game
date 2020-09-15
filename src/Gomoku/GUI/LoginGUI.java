package Gomoku.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class LoginGUI extends JFrame implements GUIConfig {
	public List<User> userList = new ArrayList<User>();
	
	public void initialGUI() {
		this.setTitle("Gumoku login pages");
		this.setSize(850,635);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setLayout(null);//设置顶级容器JFrame为框架布局

		/*TextField Textname = new TextField();
		Textname.setBounds(550,700-dim2.height-80,dim2.width,dim2.height);
		this.add(Textname);
		
		TextField Textpwd = new TextField();
		Textpwd.setBounds(550,700-dim2.height*2-160,dim2.width,dim2.height);
		this.add(Textpwd);*/

		//ImageIcon LOGINBUTTON = new ImageIcon("pic\\LoginButton.png");
		JButton buttonLogin = new JButton("Play");
		buttonLogin.setBackground(Color.LIGHT_GRAY);
		buttonLogin.setForeground(Color.white);
		buttonLogin.setBounds(350,500,145,40);

		this.add(buttonLogin);
		
		ButtonListener bu = new ButtonListener(this);
		buttonLogin.addActionListener(bu);
		
		this.setVisible(true);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(LOGINPICTURE, 0, 0,this.getWidth(), this.getHeight(), this);
	}
	
	public static void main(String args[]) {
		LoginGUI ui=new LoginGUI();
		ui.initialGUI();
	
	}
}
