package Gomoku.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class ButtonListener implements GUIConfig, ActionListener {
	public GUIJFrame gf;
	public JComboBox box;
	public LoginGUI logingui;
	
	public ButtonListener(LoginGUI logingui) {
		this.logingui = logingui;
	}
	public ButtonListener(GUIJFrame gf, JComboBox box) {
		this.gf = gf; //Get the left object 
		this.box = box; //Get the select box
	}
	@Override
	//当界面发生操作时进行处理
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Must use else if，因为如果没有else if你每次在右边的界面点击时它都会获取人人对战或者人机对战的信息，每次都会重置棋盘数组
		//Get the click contents，判断是不是"开始新游戏"这个按钮
		if(e.getActionCommand().equals("开始新游戏")) {
			// Redraw chessborad
			for(int i = 0; i < gf.chessPosition.length; i++) {
				for(int j = 0; j < gf.chessPosition[i].length; j++) {
					gf.chessPosition[i][j] = 0;
				}
				gf.repaint();
				//If is "play new game"，再为左半部分设置监听方法
				gf.turn = 1;
			}
		}
		//Judgment whether is the Regret button
		else if(e.getActionCommand().equals("悔棋")){
			if((gf.ChessPositionList.size() > 1) && (gf.turn != 0)) {
				//Set the corresponding position of the chess piece array to 0 把棋子数组相应的位置置为0
				ChessPosition l = new ChessPosition();
				//Get the last chess information
				l = gf.ChessPositionList.remove(gf.ChessPositionList.size() - 1);
				//Set the corresponding position array to 0
				gf.chessPosition[l.Listi][l.Listj] = 0;
				//Return the player turn
				if(gf.turn == 1) {
					gf.turn++;
				}else {
					gf.turn--;
				}
				//直接调用gf的重绘方法，重绘方法的画笔应该是在棋盘页面还没生成的时候就要获取
				// diaoyong repaint会自动调用paint方法，而且不用给参数
				gf.repaint();
			}else {
				gf.PopUp("Warning", "Can noy Regret chess");
			}
		}
		else if(e.getActionCommand().equals("认输")) {
			if(gf.turn == 1) {
				gf.PopUp("Game over", "White Win!!");
			}else {
				gf.PopUp("Game over", "Black Win!!");
			}
			gf.turn = 0;
		}
		else if(e.getActionCommand().equals("Play")) {
			GUIJFrame gf = new GUIJFrame();
			gf.initialGUI(); 
			//Close login pages
			logingui.dispose();
		}
		else if(box.getSelectedItem() == BATTLEBUTTON1) {
			gf.ChosseType = 0;
			gf.turn = 0;
		}
		else if(box.getSelectedItem() == BATTLEBUTTON2) {
			gf.ChosseType = 1;
			gf.turn = 0;
		}
	}
	
}
