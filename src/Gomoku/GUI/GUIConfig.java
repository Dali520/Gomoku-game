package Gomoku.GUI;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;

public interface GUIConfig {
	int X = 20;
	int Y = 20;
	int SIZE = 40;
	int ROW = 15;
	int COLLUMN = 15;
	int GUIWEIGHT = 1300;
	int GUIHEIGHT = 800;
	int MESSAGEWIDTH = 240;//�����ұ���Ϣ���Ŀ��
	Image BLACKCHESS= new ImageIcon("pic\\black.png").getImage();	//���ﲻ����ImageIcon
	Image WHITECHESS= new ImageIcon("pic\\white.png").getImage();	//���ﲻ����ImageIcon 
	Image CHESSBOARD= new ImageIcon("pic\\Chessboard2.jpg").getImage();	//���ﲻ����ImageIcon 
	Image MESSAGEPICTURE= new ImageIcon("pic\\MessagePictrue.jpg").getImage();	//���ﲻ����ImageIcon 
	Image LOGINPICTURE= new ImageIcon("pic\\LoginPicture.jpg").getImage();	//���ﲻ����ImageIcon 
	Image LOGINPICTURE2= new ImageIcon("pic\\LoginPicture2.jpg").getImage();	//���ﲻ����ImageIcon 
	//ImageIcon LOGINBUTTON = new ImageIcon("pic\\LoginButton.png"); 
	ImageIcon STARTBUTTON = new ImageIcon("pic\\StartButton.png"); 
	ImageIcon BACKBUTTON = new ImageIcon("pic\\BackButton.png"); 
	ImageIcon LOSEBUTTON = new ImageIcon("pic\\LoseButton.png");
	ImageIcon BATTLEBUTTON1 = new ImageIcon("pic\\Battle1.png");
	ImageIcon BATTLEBUTTON2 = new ImageIcon("pic\\Battle2.png");
	ImageIcon USERPICTURE = new ImageIcon("pic\\Userpic.jpg");
	//ImageIcon USERNAME = new ImageIcon("pic\\UserName.png");
	//ImageIcon USERSEX = new ImageIcon("pic\\UserSex.png");
	//ImageIcon USERLEVEL = new ImageIcon("pic\\UserLevel.png");
	Dimension dim1=new Dimension(MESSAGEWIDTH,0);//�����ұ���Ϣ���Ĵ�С
	Dimension dim2=new Dimension(145,40);//���õ�¼��ť����Ĵ�С
	Dimension dim3=new Dimension(120,120);//����ͷ������Ĵ�С
	Dimension dim4=new Dimension(120,40);//�����ұ߰�ť����Ĵ�С
}
