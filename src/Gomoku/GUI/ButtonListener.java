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
	//�����淢������ʱ���д���
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Must use else if����Ϊ���û��else if��ÿ�����ұߵĽ�����ʱ�������ȡ���˶�ս�����˻���ս����Ϣ��ÿ�ζ���������������
		//Get the click contents���ж��ǲ���"��ʼ����Ϸ"�����ť
		if(e.getActionCommand().equals("��ʼ����Ϸ")) {
			// Redraw chessborad
			for(int i = 0; i < gf.chessPosition.length; i++) {
				for(int j = 0; j < gf.chessPosition[i].length; j++) {
					gf.chessPosition[i][j] = 0;
				}
				gf.repaint();
				//If is "play new game"����Ϊ��벿�����ü�������
				gf.turn = 1;
			}
		}
		//Judgment whether is the Regret button
		else if(e.getActionCommand().equals("����")){
			if((gf.ChessPositionList.size() > 1) && (gf.turn != 0)) {
				//Set the corresponding position of the chess piece array to 0 ������������Ӧ��λ����Ϊ0
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
				//ֱ�ӵ���gf���ػ淽�����ػ淽���Ļ���Ӧ����������ҳ�滹û���ɵ�ʱ���Ҫ��ȡ
				// diaoyong repaint���Զ�����paint���������Ҳ��ø�����
				gf.repaint();
			}else {
				gf.PopUp("Warning", "Can noy Regret chess");
			}
		}
		else if(e.getActionCommand().equals("����")) {
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
