package Gomoku.GUI;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Image;
public class MyJPnanel extends JPanel implements GUIConfig {
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(MESSAGEPICTURE, 0, 0,this.getWidth(), this.getHeight(), this);
	}
}
